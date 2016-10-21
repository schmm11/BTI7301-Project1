package capl_first_design.FirstDesign.main;


import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JFrame;


public final class Window
{
	private static final int k_iMinimumWidth = 500;
	private static final int k_iMinimumHeight = 500;

	private final List<DisplayMode> k_lstAvailableModes;
	private final DisplayMode k_xDefaultMode;
	private final GraphicsDevice k_xDevice;
	private final JFrame k_xFrame;

	private boolean m_bFullscreen;
	private Dimension m_xDimension;


	private Window(final Dimension xDimension, final String strTitle)
	{
		final GraphicsEnvironment xEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
		k_xDevice = xEnv.getDefaultScreenDevice();
		final Toolkit xToolkit = Toolkit.getDefaultToolkit();
		final Dimension xDefaultDimension = xToolkit.getScreenSize();
		k_lstAvailableModes = distinctDimensions(Arrays.asList(k_xDevice.getDisplayModes())).stream()
				.filter(xMode -> xMode.getWidth() >= k_iMinimumWidth && xMode.getHeight() >= k_iMinimumHeight)
				.collect(Collectors.toList());
		if(k_lstAvailableModes.size() <= 0)
		{
			throw new Error("No available screen resolutions!");
		}
		k_xDefaultMode = displayModeForDimension(xDefaultDimension)
				.orElseThrow(() -> new RuntimeException("No appropriate DisplayMode found"));

		if(xDimension == null) // fullscreen
		{
			m_xDimension = xDefaultDimension;
			m_bFullscreen = true;
		}
		else
		{
			m_xDimension = xDimension;
			m_bFullscreen = false;
		}

		k_xFrame = new JFrame(strTitle);
			k_xFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			k_xFrame.setResizable(false);
			k_xFrame.add(new JButton("Hello, World"));
		adjustScreen();
	}

	private List<DisplayMode> distinctDimensions(final List<DisplayMode> lstModes)
	{
		Objects.requireNonNull(lstModes);
		final Comparator<DisplayMode> xDisplayModeComparator = (xA, xB) ->
		{
			final int iDWidth = (int) (xA.getWidth() - xB.getWidth());
			return (iDWidth == 0) ? (int) (xA.getHeight() - xB.getHeight()) : iDWidth;
		};

		BufferStrategy x;
		final List<DisplayMode> lstDistinctModes = lstModes.stream()
			.collect(Collectors.groupingBy(xMode -> new Dimension(xMode.getWidth(), xMode.getHeight())))
			.values().parallelStream()
				.map(lst -> lst.stream()
					.sorted(xDisplayModeComparator)
					.findFirst())
				.filter(Optional::isPresent)
				.map(Optional::get)
				.collect(Collectors.toList());
		return lstDistinctModes;
	}

	private Optional<DisplayMode> displayModeForDimension(final Dimension xDimension)
	{
		Objects.requireNonNull(xDimension);
		return k_lstAvailableModes.parallelStream()
			.filter(xMode -> (xMode.getWidth() == xDimension.getWidth() && xMode.getHeight() == xDimension.getHeight()))
			.findFirst();
	}

	private void adjustScreen()
	{
		k_xFrame.setVisible(false);
		k_xFrame.dispose();
		k_xFrame.setUndecorated(m_bFullscreen);
		k_xFrame.setResizable(!m_bFullscreen);
		k_xFrame.setVisible(!m_bFullscreen);
		if(m_bFullscreen)
		{
			if(k_xDevice.isFullScreenSupported())
			{
				k_xDevice.setFullScreenWindow(k_xFrame);
				if(k_xDevice.isDisplayChangeSupported())
				{
					k_xDevice.setDisplayMode(displayModeForDimension(m_xDimension).orElse(k_xDefaultMode));
				}
			}
			else
			{
				k_xFrame.setSize(new Dimension(k_xDefaultMode.getWidth(), k_xDefaultMode.getHeight()));
				k_xFrame.setLocationRelativeTo(null);
			}
		}
		else
		{
			k_xFrame.setSize(m_xDimension);
			k_xFrame.setLocationRelativeTo(null);
		}
	}

	public Window(final String strTitle)
	{
		this(null, strTitle);
	}

	public Window()
	{
		this("");
	}

	public Window(final int iWidth, final int iHeight, final String strTitle)
	{
		this(new Dimension(iWidth, iHeight), strTitle);
	}

	public Window(final int iWidth, final int iHeight)
	{
		this(iWidth, iHeight, "");
	}

	public List<Dimension> dimension()
	{
		return k_lstAvailableModes.stream()
			.map(xMode -> new Dimension(xMode.getWidth(), xMode.getHeight()))
			.sorted((xA, xB) ->
			{
				final int iDWidth = (int) (xA.getWidth() - xB.getWidth());
				if(iDWidth == 0)
				{
					return (int) (xA.getHeight() - xB.getHeight());
				}
				else
				{
					return iDWidth;
				}
			})
			.collect(Collectors.toList());
	}

	public void setDimension(final int iWidth, final int iHeight)
	{
		setDimension(iWidth, iHeight);
	}

	public void setDimension(final Dimension xDimension)
	{
		m_xDimension = xDimension;
	}
}
