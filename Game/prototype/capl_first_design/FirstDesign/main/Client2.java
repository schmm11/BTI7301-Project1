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

import capl_first_design.FirstDesign.main.Settings.Command;
import capl_first_design.FirstDesign.main.Settings.Direction;


public final class Client2 extends JPanel
{
	public static void main(final String[] rgArgs) throws Exception { new Client2(); }


	private static final long serialVersionUID = 188229424787044910L;

	private final SocketAddress k_xGroup = new InetSocketAddress(InetAddress.getByName(Settings.MULTICAST_IP), Settings.MULTICAST_PORT);
	private final DatagramChannel k_xChannel;
	private final SocketAddress k_xServer;
	private final List<Ship> k_lstShips = new ArrayList<>();
	private final List<Position> k_lstShots = new ArrayList<>();

	private static class Ship
	{
		private final Position k_iPosition;
		private final int k_iColor;


		public Ship(final int iX, final int iY, final int iColor)
		{
			k_iPosition = new Position(iX, iY);
			k_iColor = iColor;
		}

		public int x() { return k_iPosition.x(); }
		public int y() { return k_iPosition.y(); }
		public int color() { return k_iColor; }
	}

	private static class Position
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

	private boolean m_bRunning = true;


	public Client2() throws Exception
	{
		k_xChannel = DatagramChannel.open(StandardProtocolFamily.INET);

		final ByteBuffer xBuffer = ByteBuffer.allocate(Settings.PACKAGE_SIZE);

		boolean bRegistered = false;
		SocketAddress xServer = null;
		while(!bRegistered)
		{
			xBuffer.clear();
			xBuffer.putInt(Command.Broadcast.ordinal());
			xBuffer.flip();
			k_xChannel.send(xBuffer, k_xGroup);
			xBuffer.clear();
			xServer = k_xChannel.receive(xBuffer);
			xBuffer.flip();
			if(xBuffer.hasRemaining() && xBuffer.remaining() >= Integer.SIZE / 8)
			{
				bRegistered = (Command.values()[xBuffer.getInt()] == Command.Confirm);
			}
			Thread.yield();
		}

		k_xServer = xServer;

		xBuffer.clear();
		xBuffer.putInt(Command.Register.ordinal());
		xBuffer.flip();
		k_xChannel.send(xBuffer, k_xServer);

		System.out.println("We are registered to " + xServer);

		final Thread xNetThread = new Thread(this::netLoop);

		final JFrame xFrame = new JFrame();
			xFrame.addWindowListener(new WindowAdapter()
			{
				@Override
				public void windowClosing(final WindowEvent xEvent)
				{
					try
					{
						m_bRunning = false;
						xNetThread.join();
						xBuffer.clear();
						xBuffer.putInt(Command.Unregister.ordinal());
						xBuffer.flip();
						k_xChannel.send(xBuffer, k_xServer);
						k_xChannel.close();
						xFrame.dispose();
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

		xNetThread.start();
	}

	public void netLoop()
	{
		final ByteBuffer xBuffer = ByteBuffer.allocate(Settings.PACKAGE_SIZE * 10);

		while(m_bRunning)
		{
			try
			{
				xBuffer.clear();
				k_xChannel.receive(xBuffer);
				xBuffer.flip();
			}
			catch(final IOException xException)
			{
				xException.printStackTrace();
			}

			if(!xBuffer.hasRemaining())
			{
				continue;
			}
			final Command xCommand = Command.values()[xBuffer.getInt()];
			if(xCommand == Command.Update)
			{
				synchronized(k_lstShips)
				{
					k_lstShips.clear();
					final int iNumShips = xBuffer.getInt();
					for(int i = 0; i < iNumShips; ++i)
					{
						k_lstShips.add(new Ship(xBuffer.getInt(), xBuffer.getInt(), xBuffer.getInt()));
					}
				}
				synchronized(k_lstShots)
				{
					k_lstShots.clear();
					final int iNumShots = xBuffer.getInt();
					for(int i = 0; i < iNumShots; ++i)
					{
						k_lstShots.add(new Position(xBuffer.getInt(), xBuffer.getInt()));
					}
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
		synchronized(k_lstShips)
		{
			for(final Ship xShip : k_lstShips)
			{
				xGraphics.setColor(new Color(xShip.color()));
				final int iX = Math.floorMod(xShip.x() - 2 + 250, getWidth());
				final int iY = Math.floorMod(xShip.y() - 2 + 250, getHeight());
				xGraphics.fillRect(iX, iY, 4, 4);
			}
		}

		xGraphics.setColor(Color.YELLOW);
		synchronized(k_lstShots)
		{
			for(final Position xPosition : k_lstShots)
			{
				final int iX = Math.floorMod(xPosition.x() - 1 + 250, getWidth());
				final int iY = Math.floorMod(xPosition.y() - 1 + 250, getHeight());
				xGraphics.fillRect(iX, iY, 2, 2);
			}
		}
	}

	private class Adapter extends KeyAdapter
	{
		private boolean[] k_rgKeys = new boolean[256];

		public void sendDirection(final Command xCommand, final Direction xDirection)
		{
			final ByteBuffer xBuffer = ByteBuffer.allocate(Settings.PACKAGE_SIZE);
			try
			{
				xBuffer.clear();
				xBuffer.putInt(xCommand.ordinal());
				xBuffer.putInt(xDirection.ordinal());
				xBuffer.flip();
				k_xChannel.send(xBuffer, k_xServer);
			}
			catch(final IOException xException)
			{
				xException.printStackTrace();
			}
		}

		@Override
		public void keyPressed(final KeyEvent xEvent)
		{
			final int iKeyCode = xEvent.getKeyCode();
			if(!k_rgKeys[iKeyCode])
			{
				final ByteBuffer xBuffer = ByteBuffer.allocate(Settings.PACKAGE_SIZE);
				k_rgKeys[iKeyCode] = true;
				switch(iKeyCode)
				{
				case KeyEvent.VK_SPACE:
					xBuffer.clear();
					xBuffer.putInt(Command.Shoot.ordinal());
					xBuffer.flip();
					try
					{
						k_xChannel.send(xBuffer, k_xServer);
					}
					catch(final IOException xException)
					{
						xException.printStackTrace();
					}
					break;
				case KeyEvent.VK_W:
					sendDirection(Command.Move, Direction.North);
					break;
				case KeyEvent.VK_S:
					sendDirection(Command.Move, Direction.South);
					break;
				case KeyEvent.VK_A:
					sendDirection(Command.Move, Direction.West);
					break;
				case KeyEvent.VK_D:
					sendDirection(Command.Move, Direction.East);
					break;
				}
			}
		}

		@Override
		public void keyReleased(final KeyEvent xEvent)
		{
			final int iKeyCode = xEvent.getKeyCode();
			if(k_rgKeys[iKeyCode])
			{
				k_rgKeys[iKeyCode] = false;
				switch(iKeyCode)
				{
				case KeyEvent.VK_W:
					sendDirection(Command.Stop, Direction.North);
					break;
				case KeyEvent.VK_S:
					sendDirection(Command.Stop, Direction.South);
					break;
				case KeyEvent.VK_A:
					sendDirection(Command.Stop, Direction.West);
					break;
				case KeyEvent.VK_D:
					sendDirection(Command.Stop, Direction.East);
					break;
				}
			}
		}
	}
}
