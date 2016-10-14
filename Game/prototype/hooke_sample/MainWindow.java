package hooke_sample;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;


public final class MainWindow extends JPanel implements Runnable
{
	private static final long serialVersionUID = 1L;

	private final int k_iWidth;
	private final int k_iHeight;

	private final Adapter k_xAdapter = new Adapter();

	private double dSpringConstant = 0.1;
	private double dResistanceConstant = 0.1;

	private final int iCSize = 10;
	private final int iSize = 20;

	private int iPosX = 0;
	private int iPosY = 0;

	private int iCPosX = 0;
	private int iCPosY = 0;

	private int iCVelX = 0;
	private int iCVelY = 0;


	private static final class Adapter extends KeyAdapter
	{
		private final boolean[] k_rgKeys = new boolean[256];

		@Override
		public void keyPressed(final KeyEvent xEvent)
		{
			final int iKeyCode = xEvent.getKeyCode();
			k_rgKeys[iKeyCode] = true;
		}

		@Override
		public void keyReleased(final KeyEvent xEvent)
		{
			final int iKeyCode = xEvent.getKeyCode();
			k_rgKeys[iKeyCode] = false;
		}

		public boolean pressed(final int iKeyCode)
		{
			return k_rgKeys[iKeyCode];
		}
	}

	public MainWindow(final int iWidth, final int iHeight)
	{
		k_iWidth = ArgumentChecker.check(iWidth, i -> i > 0, "Width must be positive");
		k_iHeight = ArgumentChecker.check(iHeight, i -> i > 0, "Height must be positive");

		setPreferredSize(new Dimension(k_iWidth, k_iHeight));

		final JFrame xFrame = new JFrame();
			xFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			xFrame.setContentPane(this);
			xFrame.setResizable(false);
			xFrame.addKeyListener(k_xAdapter);
			xFrame.pack();
			xFrame.setLocationRelativeTo(null);
			xFrame.setVisible(true);

		new Thread(this).start();
	}

	private void handleInput()
	{
		if(pressed(KeyEvent.VK_W, KeyEvent.VK_UP)) iPosY -= 10;
		if(pressed(KeyEvent.VK_S, KeyEvent.VK_DOWN)) iPosY += 10;
		if(pressed(KeyEvent.VK_A, KeyEvent.VK_LEFT)) iPosX -= 10;
		if(pressed(KeyEvent.VK_D, KeyEvent.VK_RIGHT)) iPosX += 10;
	}

	private boolean pressed(final int ...rgKeys)
	{
		boolean bPressed = false;
		for(final int iKey : rgKeys)
		{
			if(k_xAdapter.pressed(iKey))
			{
				bPressed = true;
				break;
			}
		}

		return bPressed;
	}

	private void update()
	{
		final double dSpringForceX = -dSpringConstant * (iCPosX - iPosX);
		final double dSpringForceY = -dSpringConstant * (iCPosY - iPosY);

		final double dResistanceForceX = -dResistanceConstant * iCVelX;
		final double dResistanceForceY = -dResistanceConstant * iCVelY;

		iCVelX += dSpringForceX + dResistanceForceX;
		iCVelY += dSpringForceY + dResistanceForceY;

		iCPosX += iCVelX;
		iCPosY += iCVelY;
	}

	@Override
	public void run()
	{
		while(true)
		{
			handleInput();
			update();
			repaint();

			try
			{
				Thread.sleep(16);
			}
			catch(final InterruptedException xException)
			{
				xException.printStackTrace();
			}
		}
	}

	@Override
	public void paintComponent(final Graphics xGraphics)
	{
		final Graphics2D xGraphics2D = (Graphics2D) xGraphics.create();

		xGraphics2D.clearRect(0, 0, getWidth(), getHeight());

		xGraphics2D.translate(getWidth() / 2, getHeight() / 2);

		xGraphics2D.setColor(Color.RED);
		xGraphics2D.fillOval(iPosX - iSize / 2, iPosY - iSize / 2, iSize, iSize);

		xGraphics2D.setColor(Color.BLACK);
		xGraphics2D.fillOval(iCPosX - iCSize / 2, iCPosY - iCSize / 2, iCSize, iCSize);

		xGraphics2D.dispose();
	}
}
