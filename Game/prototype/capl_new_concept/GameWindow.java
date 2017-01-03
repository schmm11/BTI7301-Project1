package capl_new_concept;


import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JFrame;

import capl_new_concept.KeyAction.Action;


public final class GameWindow
{
	private final JFrame k_xFrame;
	private final GameCanvas k_xCanvas;
	private final List<KeyAction> k_lstActions;
	private final int k_iWidth;
	private final int k_iHeight;

	private BufferStrategy m_xStrategy;
	private boolean m_bCloseRequested;


	public GameWindow(final int iWidth, final int iHeight)
	{
		k_iWidth = ArgumentChecker.require(iWidth, i -> i > 0, "Width must be greater than zero");
		k_iHeight = ArgumentChecker.require(iHeight, i -> i > 0, "Height must be greater than zero");

		k_xFrame = new JFrame();
		k_xCanvas = new GameCanvas(k_iWidth, k_iHeight);
		k_lstActions = new ArrayList<>();

		m_bCloseRequested = false;
	}

	public void init()
	{
		k_xFrame.getContentPane().add(k_xCanvas);
		k_xFrame.setResizable(false);
		k_xFrame.pack();
		k_xFrame.setAlwaysOnTop(true);
		k_xFrame.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(final WindowEvent xEvent)
			{
				m_bCloseRequested = true;
			}
		});
		k_xFrame.addKeyListener(new KeyAdapter()
		{
			private boolean[] k_rgKeys = new boolean[256];


			@Override
			public void keyPressed(final KeyEvent xEvent)
			{
				final int iKeyCode = xEvent.getKeyCode();
				if(iKeyCode > 0 && iKeyCode < 256 && !k_rgKeys[iKeyCode])
				{
					k_lstActions.add(new KeyAction(xEvent.getKeyCode(), Action.Pressed));
					k_rgKeys[iKeyCode] = true;
				}
			}

			@Override
			public void keyReleased(final KeyEvent xEvent)
			{
				final int iKeyCode = xEvent.getKeyCode();
				if(iKeyCode > 0 && iKeyCode < 256 && k_rgKeys[iKeyCode])
				{
					k_lstActions.add(new KeyAction(xEvent.getKeyCode(), Action.Released));
					k_rgKeys[iKeyCode] = false;
				}
			}
		});
		k_xFrame.requestFocus();
		k_xFrame.setVisible(true);
		k_xCanvas.createBufferStrategy(2);
		m_xStrategy = k_xCanvas.getBufferStrategy();
	}

	public Graphics2D xGraphics()
	{
		return (Graphics2D) m_xStrategy.getDrawGraphics();
	}

	public void show()
	{
		m_xStrategy.show();
	}

	public int width()
	{
		return k_iWidth;
	}

	public int height()
	{
		return k_iHeight;
	}

	public void close()
	{
		m_xStrategy.dispose();
		k_xFrame.dispose();
	}

	public boolean closeRequested()
	{
		return m_bCloseRequested;
	}

	public List<KeyAction> input()
	{
		final List<KeyAction> lstRes = new ArrayList<>();
		lstRes.addAll(k_lstActions);
		k_lstActions.clear();
		return Collections.unmodifiableList(lstRes);
	}

	public Rectangle2D bounds()
	{
		return new Rectangle2D.Float(0, 0, k_iWidth, k_iHeight);
	}

	private static class GameCanvas extends Canvas
	{
		private static final long serialVersionUID = 7792052253924873410L;

		private final int k_iWidth;
		private final int k_iHeight;


		public GameCanvas(final int iWidth, final int iHeight)
		{
			k_iWidth = ArgumentChecker.require(iWidth, i -> i > 0, "Width must be greater than zero");
			k_iHeight = ArgumentChecker.require(iHeight, i -> i > 0, "Height must be greater than zero");
		}

		@Override
		public Dimension getPreferredSize()
		{
			return new Dimension(k_iWidth, k_iHeight);
		}
	}
}
