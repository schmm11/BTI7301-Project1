package capl;


import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JFrame;

import capl.KeyAction.Action;


public final class BasicDisplay implements Display
{
	private static final int k_iNumBuffers = 3;

	private final int k_iWidth;
	private final int k_iHeight;
	private final JFrame k_xFrame;
	private final Canvas k_xCanvas;
	private final List<KeyAction> k_lstActions;

	private BufferStrategy m_xStrategy;
	private boolean m_bCloseRequested = false;


	public BasicDisplay(final int iWidth, final int iHeight)
	{
		k_iWidth = iWidth;
		k_iHeight = iHeight;
		k_xFrame = new JFrame();
		k_xCanvas = new Canvas();
		k_lstActions = new ArrayList<>();
	}

	@Override
	public void init()
	{
		k_xFrame.getContentPane().add(k_xCanvas);
		k_xCanvas.setPreferredSize(new Dimension(k_iWidth, k_iHeight));
		k_xFrame.setResizable(false);
		k_xFrame.pack();
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
			@Override
			public void keyPressed(final KeyEvent xEvent)
			{
				k_lstActions.add(new KeyAction(xEvent.getKeyCode(), Action.Pressed));
			}

			@Override
			public void keyReleased(final KeyEvent xEvent)
			{
				k_lstActions.add(new KeyAction(xEvent.getKeyCode(), Action.Released));
			}
		});
		k_xFrame.setVisible(true);

		k_xCanvas.createBufferStrategy(k_iNumBuffers);
		m_xStrategy = k_xCanvas.getBufferStrategy();
	}

	@Override
	public boolean closeRequested()
	{
		return m_bCloseRequested;
	}

	@Override
	public List<KeyAction> input()
	{
		final List<KeyAction> lstRes = new ArrayList<>(k_lstActions);
		k_lstActions.clear();
		return Collections.unmodifiableList(lstRes);
	}

	@Override
	public Graphics2D graphics()
	{
		return (Graphics2D) m_xStrategy.getDrawGraphics();
	}

	@Override
	public void show()
	{
		m_xStrategy.show();
	}

	@Override
	public void close()
	{
		m_xStrategy.dispose();
		k_xFrame.dispose();
	}
}
