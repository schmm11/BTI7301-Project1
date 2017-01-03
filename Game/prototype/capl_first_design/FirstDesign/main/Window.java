package capl_first_design.FirstDesign.main;


import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import capl_first_design.FirstDesign.main.KeyAction.Action;


public final class Window
{
	private static final Dimension k_xMinimumSize = new Dimension(800, 600);
	private static final int k_iNumBuffers = 2;

	private static final String k_strDefaultTitle = "Window";
	private static final Dimension k_xDefaultSize = new Dimension(800, 600);

	private final GraphicsDevice k_xDevice;
	private final List<DisplayMode> k_lstAvailableDisplayModes;
	private final DisplayMode k_xFullscreenMode;
	public final Frame k_xFrame;
	public final Canvas k_xCanvas;
	private final List<KeyAction> k_lstKeyActions = new ArrayList<>();

	private BufferStrategy m_xStrategy;
	private Dimension m_xDimension;
	private boolean m_bFullscreen;
	private boolean m_bCloseRequested = false;
	private AffineTransform m_xTransform;
	private Rectangle2D m_xBounds;


	private Window(final Dimension xPreferred, final boolean bFullscreen, final String strTitle)
	{
		final GraphicsEnvironment xEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
		final Toolkit xToolkit = Toolkit.getDefaultToolkit();
		final Dimension xDefaultDimension = xToolkit.getScreenSize();

		k_xDevice = xEnv.getDefaultScreenDevice();

		// get 'best' DisplayMode for distinct screen resolutions
		k_lstAvailableDisplayModes = distinctDimensions(Arrays.asList(k_xDevice.getDisplayModes())).stream()
				// filter low screen resolutions out
				.filter(xMode -> xMode.getWidth() >= k_xMinimumSize.width)
				.filter(xMode -> xMode.getHeight() >= k_xMinimumSize.height)
				.collect(Collectors.toList());
		if(k_lstAvailableDisplayModes.isEmpty())
		{
			// At least the fullscreen mode should work
			throw new Error("No suitable DisplayModes found");
		}

		k_xFullscreenMode = displayModeForDimension(xDefaultDimension)
				.orElse(new DisplayMode(500, 500, 32, DisplayMode.REFRESH_RATE_UNKNOWN));
//				.orElseThrow(() -> new Error("No fullscreen DisplayMode found"));
		
		m_xDimension = xPreferred;
		m_bFullscreen = bFullscreen;

		// initialize the screen
		k_xFrame = new Frame(k_xDevice.getDefaultConfiguration());
			k_xCanvas = new Canvas();
				k_xCanvas.setIgnoreRepaint(true);
			k_xFrame.addKeyListener(new WindowKeyAdapter());
			k_xFrame.setIgnoreRepaint(true);
			k_xFrame.add(k_xCanvas);
			k_xFrame.setResizable(false);
			k_xFrame.setAlwaysOnTop(true);
			k_xFrame.addWindowListener(new WindowAdapter()
			{
				@Override
				public void windowClosing(final WindowEvent xEvent)
				{
					m_bCloseRequested = true;
				}
			});

		adjustFullscreen();
		adjustDimension();

		k_xCanvas.createBufferStrategy(k_iNumBuffers);
		m_xStrategy = k_xCanvas.getBufferStrategy();
		final double dWidth = m_xDimension.getWidth();
		final double dHeight = m_xDimension.getHeight();
		final double dMin = Math.min(dWidth, dHeight);
		final double dHorizontal = dWidth / dMin;
		final double dVertical = dHeight / dMin;
		m_xBounds = new Rectangle2D.Double(-dHorizontal, -dVertical, 2.0 * dHorizontal, 2.0 * dVertical);
		m_xTransform = new AffineTransform();
		m_xTransform.scale(1.0 / m_xBounds.getWidth(), 1.0 / m_xBounds.getHeight());
		m_xTransform.scale(dWidth, dHeight);
		m_xTransform.translate(-m_xBounds.getX(), -m_xBounds.getY());
	}

	public Window()
	{
		this(k_xDefaultSize, false, k_strDefaultTitle);
	}

	private void adjustFullscreen()
	{
		k_xFrame.dispose();
		if(m_bFullscreen)
		{
			if(k_xDevice.isFullScreenSupported())
			{
				k_xFrame.setUndecorated(true);
				k_xDevice.setFullScreenWindow(k_xFrame);
				adjustDimension();
			}
		}
		else
		{
			k_xDevice.setFullScreenWindow(null);
			k_xFrame.setUndecorated(false);
			k_xFrame.setVisible(true);
			adjustDimension();
		}
	}

	private void adjustDimension()
	{
		if(m_bFullscreen && k_xDevice.isDisplayChangeSupported())
		{
			// set resolution to preferred size or use fall-back resolution if not available
			final DisplayMode xMode = displayModeForDimension(m_xDimension).orElse(k_xFullscreenMode);
			System.out.println(xMode.getWidth() + " " + xMode.getHeight());
			k_xDevice.setDisplayMode(xMode);
		}
		else
		{
			k_xCanvas.setSize(m_xDimension);
			k_xFrame.pack();
		}
	}


	public void close()
	{
		m_xStrategy.dispose();
		k_xFrame.dispose();
	}


	public synchronized Graphics2D graphics()
	{
		final Graphics2D xGraphics = (Graphics2D) m_xStrategy.getDrawGraphics();
		xGraphics.setTransform(m_xTransform);
		return xGraphics;
	}

	public synchronized Rectangle2D bounds()
	{
		return m_xBounds;
	}

	public synchronized void show()
	{
		if(!m_xStrategy.contentsLost())
		{
			m_xStrategy.show();
		}
	}

	public List<KeyAction> input()
	{
		try
		{
			return new ArrayList<KeyAction>(k_lstKeyActions);
		}
		finally
		{
			k_lstKeyActions.clear();
		}
	}

	public boolean closeRequested()
	{
		return m_bCloseRequested;
	}

	public Dimension dimension()
	{
		return m_xDimension;
	}

	public int width()
	{
		return dimension().width;
	}

	public int height()
	{
		return dimension().height;
	}

	public synchronized void setDimension(final Dimension xDimension)
	{
		if(!m_xDimension.equals(xDimension))
		{
			m_xDimension = new Dimension(xDimension);
			adjustDimension();

			k_xCanvas.createBufferStrategy(k_iNumBuffers);
			m_xStrategy = k_xCanvas.getBufferStrategy();
			final double dWidth = m_xDimension.getWidth();
			final double dHeight = m_xDimension.getHeight();
			final double dMin = Math.min(dWidth, dHeight);
			final double dHorizontal = dWidth / dMin;
			final double dVertical = dHeight / dMin;
			m_xBounds = new Rectangle2D.Double(-dHorizontal, -dVertical, 2.0 * dHorizontal, 2.0 * dVertical);
			m_xTransform = new AffineTransform();
			m_xTransform.scale(1.0 / m_xBounds.getWidth(), 1.0 / m_xBounds.getHeight());
			m_xTransform.scale(dWidth, dHeight);
			m_xTransform.translate(-m_xBounds.getX(), -m_xBounds.getY());
		}
	}

	public synchronized void setFullscreen(final boolean bFullscreen)
	{
		if(bFullscreen != m_bFullscreen)
		{
			m_bFullscreen = bFullscreen;
			adjustFullscreen();

			k_xCanvas.createBufferStrategy(k_iNumBuffers);
			m_xStrategy = k_xCanvas.getBufferStrategy();
			final double dWidth = m_xDimension.getWidth();
			final double dHeight = m_xDimension.getHeight();
			final double dMin = Math.min(dWidth, dHeight);
			final double dHorizontal = dWidth / dMin;
			final double dVertical = dHeight / dMin;
			m_xBounds = new Rectangle2D.Double(-dHorizontal, -dVertical, 2.0 * dHorizontal, 2.0 * dVertical);
			m_xTransform = new AffineTransform();
			m_xTransform.scale(1.0 / m_xBounds.getWidth(), 1.0 / m_xBounds.getHeight());
			m_xTransform.scale(dWidth, dHeight);
			m_xTransform.translate(-m_xBounds.getX(), -m_xBounds.getY());
		}
	}


	private List<DisplayMode> distinctDimensions(final List<DisplayMode> lstDisplayModes)
	{
		Objects.requireNonNull(lstDisplayModes);
		// sort by bit depth, then by refresh rate
		final Comparator<DisplayMode> xDisplayModeComparator = (xA, xB) ->
		{
			final int iBitDepthCompare = (xA.getBitDepth() - xB.getBitDepth());
			return (iBitDepthCompare == 0) ? (xA.getRefreshRate() - xB.getRefreshRate()) : iBitDepthCompare;
		};

		final List<DisplayMode> lstDistinctModes = lstDisplayModes.stream()
			// group the display modes by resolution
			.collect(Collectors.groupingBy(xMode -> new Dimension(xMode.getWidth(), xMode.getHeight()))).values().stream()
				.map(lstModes -> lstModes.stream()
					// get the 'best' resolution, highest bit depth and then highest refresh rate
					.sorted(xDisplayModeComparator)
					.findFirst())
				// filter empty lists (should not be any because of groupBy)
				.filter(Optional::isPresent)
				.map(Optional::get)
				.collect(Collectors.toList());
		return lstDistinctModes;
	}


	private Optional<DisplayMode> displayModeForDimension(final Dimension xDimension)
	{
		Objects.requireNonNull(xDimension);
		// find resolution with same width and height
		return k_lstAvailableDisplayModes.stream()
			.filter(xMode -> xMode.getWidth() == xDimension.getWidth())
			.filter(xMode -> xMode.getHeight() == xDimension.getHeight())
			.findFirst();
	}


	public List<Dimension> availableDimensions()
	{
		// sort by width, then by height
		final Comparator<Dimension> xComparator = (xA, xB) ->
		{
			final int iWidthCompare = (int) (xA.getWidth() - xB.getWidth());
			return (iWidthCompare == 0) ? (int) (xA.getHeight() - xB.getHeight()) : iWidthCompare;
		};

		return k_lstAvailableDisplayModes.stream()
			.map(xMode -> new Dimension(xMode.getWidth(), xMode.getHeight()))
			.sorted(xComparator)
			.collect(Collectors.toList());
	}

	private final class WindowKeyAdapter extends KeyAdapter
	{
		@Override
		public void keyPressed(final KeyEvent xEvent)
		{
			k_lstKeyActions.add(new KeyAction(xEvent.getKeyCode(), Action.Pressed));
		}

		@Override
		public void keyReleased(final KeyEvent xEvent)
		{
			k_lstKeyActions.add(new KeyAction(xEvent.getKeyCode(), Action.Released));
		}
	}
}