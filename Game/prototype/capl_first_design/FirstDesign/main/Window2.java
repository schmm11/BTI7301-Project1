package capl_first_design.FirstDesign.main;


import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


public final class Window2
{
	private static final Dimension k_xMinimumSize = new Dimension(500, 500);
	private static final int k_iNumBuffers = 2;

	private static final String k_strDefaultTitle = "Window";
	private static final Dimension k_xDefaultSize = new Dimension(500, 500);

	private final GraphicsDevice k_xDevice;
	private final List<DisplayMode> k_lstAvailableDisplayModes;
	private final DisplayMode k_xFullscreenMode;
	private final Frame k_xFrame;
	private final Canvas k_xCanvas;
	private final BufferStrategy k_xStrategy;

	private Dimension m_xDimension;
	private boolean m_bFullscreen;
	private boolean m_bCloseRequested = false;


	public Window2()
	{
		this(k_xDefaultSize, false, k_strDefaultTitle);
	}


	private Window2(final Dimension xPreferred, final boolean bFullscreen, final String strTitle)
	{
		final GraphicsEnvironment xEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
		final Toolkit xToolkit = Toolkit.getDefaultToolkit();
		final Dimension xDefaultDimension = xToolkit.getScreenSize();

		k_xDevice = xEnv.getDefaultScreenDevice();

		// get 'best' displaymode for distinct screen resolutions
		k_lstAvailableDisplayModes = distinctDimensions(Arrays.asList(k_xDevice.getDisplayModes())).stream()
				// filter low screen resolutions out
				.filter(xMode -> xMode.getWidth() >= k_xMinimumSize.width)
				.filter(xMode -> xMode.getHeight() >= k_xMinimumSize.height)
				.collect(Collectors.toList());
		if(k_lstAvailableDisplayModes.isEmpty())
		{
			throw new Error("No suitable DisplayModes found");
		}

		k_xFullscreenMode = displayModeForDimension(xDefaultDimension)
			.orElseThrow(() -> new Error("No fullscreen DisplayMode found"));

		m_xDimension = xPreferred;
		m_bFullscreen = bFullscreen;

		k_xFrame = new Frame(k_xDevice.getDefaultConfiguration());
			k_xCanvas = new Canvas();
			k_xFrame.add(k_xCanvas);
			k_xFrame.setResizable(false);
			k_xFrame.addWindowListener(new WindowAdapter()
			{
				@Override
				public void windowClosing(final WindowEvent xEvent)
				{
					m_bCloseRequested = true;
				}
			});
			adjustScreen();
			k_xCanvas.createBufferStrategy(k_iNumBuffers);
		k_xStrategy = k_xCanvas.getBufferStrategy();
	}


	private void adjustScreen()
	{
		if(m_bFullscreen)
		{
		}
		else
		{
			k_xDevice.setFullScreenWindow(null);

			k_xFrame.dispose();
			k_xFrame.setUndecorated(false);
			k_xCanvas.setSize(m_xDimension);
			k_xFrame.pack();
			k_xFrame.setVisible(true);
		}
	}


	public void close()
	{
		k_xStrategy.dispose();
		k_xFrame.dispose();
	}


	public Graphics2D graphics()
	{
		return (Graphics2D) k_xStrategy.getDrawGraphics();
	}

	public void show()
	{
		if(!k_xStrategy.contentsLost())
		{
			k_xStrategy.show();
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

	public void setDimension(final Dimension xDimension)
	{
		m_xDimension = new Dimension(xDimension.width, xDimension.height);
		adjustScreen();
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
}
