package capl_first_design.FirstDesign.main;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.StandardProtocolFamily;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;


public final class Client extends JPanel
{
	public static void main(final String[] rgArgs) { new Client(); }


	private static final long serialVersionUID = 2119474494068138935L;

	private final SocketAddress k_xServer = new InetSocketAddress(InetAddress.getLoopbackAddress(), Settings.PORT);
	private final List<Position> k_lstPositions = new ArrayList<Position>();
	private int m_iSent = 0;

	private class Position
	{
		private final int k_iX;
		private final int k_iY;

		public Position(final int iX, final int iY)
		{
			k_iX = iX;
			k_iY = iY;
		}

		public int x() { return k_iX; }
		public int y() { return k_iY; }
	}

	private DatagramChannel m_xChannel;
	private boolean m_bRunning = true;


	public Client()
	{
		try
		{
			m_xChannel = DatagramChannel.open(StandardProtocolFamily.INET);
			m_xChannel.configureBlocking(false);
		}
		catch(final IOException xException)
		{
			xException.printStackTrace();
		}

		final ByteBuffer xBuffer = ByteBuffer.allocate(1);
		xBuffer.put(Settings.JOIN);
		xBuffer.flip();

		try
		{
			m_iSent += m_xChannel.send(xBuffer, k_xServer);
		}
		catch(final IOException xException)
		{
			xException.printStackTrace();
		}

		final Thread xNetThread = new Thread(this::netLoop);
		xNetThread.start();
		final JFrame xFrame = new JFrame();
			xFrame.addWindowListener(new WindowAdapter()
			{
				@Override
				public void windowClosing(final WindowEvent xEvent)
				{
					try
					{
						final ByteBuffer xBuffer = ByteBuffer.allocate(1);
						xBuffer.put(Settings.DISCONNECT);
						xBuffer.flip();
						m_iSent += m_xChannel.send(xBuffer, k_xServer);
						m_bRunning = false;
						xNetThread.join();
						m_xChannel.disconnect();
						m_xChannel.close();
						xFrame.dispose();

						System.out.println("Bytes sent: " + m_iSent);
					}
					catch(final IOException xException)
					{
						xException.printStackTrace();
					}
					catch(final InterruptedException xException)
					{
						xException.printStackTrace();
					}
				}
			});
			xFrame.setContentPane(this);
			xFrame.setResizable(false);
			xFrame.pack();
			xFrame.setLocationRelativeTo(null);
			xFrame.addKeyListener(new Adapter());
			xFrame.requestFocus();
			xFrame.setVisible(true);
	}

	public void netLoop()
	{
		final ByteBuffer xBuffer = ByteBuffer.allocate(1000);
		SocketAddress xAddress = null;
		while(m_bRunning)
		{
			xBuffer.clear();
			try
			{
				xAddress = m_xChannel.receive(xBuffer);
			}
			catch(final IOException xException)
			{
				xException.printStackTrace();
			}

			if(xAddress != null && xAddress.equals(k_xServer))
			{
				xBuffer.flip();
				final byte bCount = xBuffer.get();
				k_lstPositions.clear();
				for(byte iIdx = 0; iIdx < bCount; ++iIdx)
				{
					k_lstPositions.add(new Position(xBuffer.getInt(), xBuffer.getInt()));
				}
				repaint();
			}

			Thread.yield();
		}
	}

	@Override
	public Dimension getPreferredSize()
	{
		return new Dimension(500, 500);
	}

	@Override
	public void paintComponent(final Graphics xGraphics)
	{
		xGraphics.setColor(Color.BLACK);
		xGraphics.fillRect(0, 0, getWidth(), getHeight());

		xGraphics.setColor(Color.WHITE);
		for(final Position xPosition : k_lstPositions)
		{
			final Graphics xTmp = xGraphics.create();
			xTmp.translate(Math.floorMod(xPosition.x(), getWidth()), Math.floorMod(xPosition.y(), getHeight()));
			xTmp.fillOval(-2, -2, 4, 4);
		}
	}

	private class Adapter extends KeyAdapter
	{
		private final boolean[] k_rgKeys = new boolean[256];

		private void sendDirection(final byte iCommand, final byte iDirection)
		{
			final ByteBuffer xBuffer = ByteBuffer.wrap(new byte[] { iCommand, iDirection });
			try
			{
				m_iSent += m_xChannel.send(xBuffer, k_xServer);
			}
			catch(final IOException xException)
			{
				xException.printStackTrace();
			}
		}

		@Override
		public void keyPressed(final KeyEvent xEvent)
		{
			if(k_rgKeys[xEvent.getKeyCode()])
			{
				return;
			}

			final byte iCommand = Settings.MOVE;
			switch(xEvent.getKeyCode())
			{
			case KeyEvent.VK_W:
				sendDirection(iCommand, Settings.NORTH);
				break;
			case KeyEvent.VK_S:
				sendDirection(iCommand, Settings.SOUTH);
				break;
			case KeyEvent.VK_A:
				sendDirection(iCommand, Settings.EAST);
				break;
			case KeyEvent.VK_D:
				sendDirection(iCommand, Settings.WEST);
				break;
			}
			k_rgKeys[xEvent.getKeyCode()] = true;
		}

		@Override
		public void keyReleased(final KeyEvent xEvent)
		{
			if(!k_rgKeys[xEvent.getKeyCode()])
			{
				return;
			}

			final byte iCommand = Settings.STOP;
			switch(xEvent.getKeyCode())
			{
			case KeyEvent.VK_W:
				sendDirection(iCommand, Settings.NORTH);
				break;
			case KeyEvent.VK_S:
				sendDirection(iCommand, Settings.SOUTH);
				break;
			case KeyEvent.VK_A:
				sendDirection(iCommand, Settings.EAST);
				break;
			case KeyEvent.VK_D:
				sendDirection(iCommand, Settings.WEST);
				break;
			}
			k_rgKeys[xEvent.getKeyCode()] = false;
		}
	}
}
