package capl_first_design.FirstDesign.main;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.SocketAddress;
import java.net.StandardProtocolFamily;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import capl_first_design.FirstDesign.main.Settings.Command;
import capl_first_design.FirstDesign.main.Settings.Direction;


public final class Server2
{
	public static void main(final String[] rgArgs) throws Exception { new Server2(); }


	private final DatagramChannel k_xChannel;
	private final InetAddress k_xGroup;
	private final Map<SocketAddress, Pair<Ship, Integer>> k_lstShips = new HashMap<>();
	private List<Shot> m_lstShots = new ArrayList<Shot>();

	private static class Pair<K, V>
	{
		private final K k_xK;
		private final V k_xV;


		public Pair(final K xK, final V xV)
		{
			k_xK = xK;
			k_xV = xV;
		}

		public K k() { return k_xK; }
		public V v() { return k_xV; }
	}

	private boolean m_bRunning = true;
	private int m_iSent = 0;


	public Server2() throws Exception
	{
		k_xChannel = DatagramChannel.open(StandardProtocolFamily.INET);
		k_xGroup = InetAddress.getByName(Settings.MULTICAST_IP);

		k_xChannel.join(k_xGroup, NetworkInterface.getByName("wlan0"));
		k_xChannel.bind(new InetSocketAddress(Settings.MULTICAST_PORT));
		k_xChannel.configureBlocking(false);

		final Thread xNetThread = new Thread(this::netLoop);
		final Thread xUpdateThread = new Thread(this::update);

		xNetThread.start();
		xUpdateThread.start();

		try(final BufferedReader xReader = new BufferedReader(new InputStreamReader(System.in)))
		{
			xReader.lines().filter(strLine -> strLine.equalsIgnoreCase("quit")).findFirst();
		}

		m_bRunning = false;
		xNetThread.join();
		xUpdateThread.join();

		k_xChannel.close();

		if(m_iSent > 1024 * 1024)
		{
			System.out.println("Megabytes sent: " + (m_iSent) / (1024 * 1024));
		}
		else if(m_iSent > 1024)
		{
			System.out.println("Kilobytes sent: " + (m_iSent) / 1024);
		}
		else
		{
			System.out.println("Bytes sent: " + m_iSent);
		}
	}

	public SocketAddress rx(final ByteBuffer xBuffer)
	{
		try
		{
			final SocketAddress xAddress = k_xChannel.receive(xBuffer);
			xBuffer.flip();
			return xAddress;
		}
		catch(final IOException xException)
		{
			xException.printStackTrace();
		}
		return null;
	}

	public void tx(final ByteBuffer xBuffer, final SocketAddress xAddress)
	{
		try
		{
			m_iSent += k_xChannel.send(xBuffer, xAddress);
		}
		catch(final IOException xException)
		{
			xException.printStackTrace();
		}
	}

	public void update()
	{
		while(m_bRunning)
		{
			final ByteBuffer xBuffer = ByteBuffer.allocate(Settings.PACKAGE_SIZE * 10);
			xBuffer.putInt(Command.Update.ordinal());
			synchronized(k_lstShips)
			{
				k_lstShips.values().stream().map(Pair::k).forEach(Ship::update);
				xBuffer.putInt(k_lstShips.size());
				k_lstShips.values().forEach(xPair -> { xBuffer.putInt(xPair.k().x()); xBuffer.putInt(xPair.k().y()); xBuffer.putInt(xPair.v()); });
			}
			synchronized(m_lstShots)
			{
				m_lstShots = m_lstShots.stream().filter(Shot::alive).collect(Collectors.toList());
				m_lstShots.forEach(Shot::update);
				xBuffer.putInt(m_lstShots.size());
				m_lstShots.forEach(xShot -> { xBuffer.putInt(xShot.x()); xBuffer.putInt(xShot.y()); });
			}
			try
			{
				xBuffer.flip();
				synchronized(k_lstShips)
				{
					for(final SocketAddress xAddress : k_lstShips.keySet())
					{
						m_iSent += k_xChannel.send(ByteBuffer.wrap(xBuffer.array()), xAddress);
					}
				}
				Thread.sleep(32);
			}
			catch(final InterruptedException xException)
			{
				xException.printStackTrace();
			}
			catch(final IOException xException)
			{
				xException.printStackTrace();
			}
		}
	}

	public void netLoop()
	{
		final ByteBuffer xBuffer = ByteBuffer.allocate(Settings.PACKAGE_SIZE);
		while(m_bRunning)
		{
			xBuffer.clear();
			final SocketAddress xClient = rx(xBuffer);
			if(xClient == null)
			{
				continue;
			}

			if(!xBuffer.hasRemaining() && xBuffer.remaining() < Integer.SIZE / 8)
			{
				System.err.println("Error, invalid buffer contents");
				continue;
			}

			final Command xCommand = Command.values()[xBuffer.getInt()];
			switch(xCommand)
			{
			case Broadcast:
				xBuffer.clear();
				xBuffer.putInt(Command.Confirm.ordinal());
				xBuffer.flip();
				tx(xBuffer, xClient);
				break;
			case Register:
				synchronized(k_lstShips)
				{
					k_lstShips.put(xClient, new Pair<Ship, Integer>(new Ship(), (int) (Math.random() * Integer.MAX_VALUE)));
				}
				break;
			case Unregister:
				synchronized(k_lstShips)
				{
					k_lstShips.remove(xClient);
				}
				break;
			case Move:
				synchronized(k_lstShips)
				{
					final Direction xDirection = Direction.values()[xBuffer.getInt()];
					final Ship xShip = k_lstShips.get(xClient).k();
					switch(xDirection)
					{
					case North: xShip.speedY(-1); break;
					case South: xShip.speedY(1); break;
					case East: xShip.speedX(1); break;
					case West: xShip.speedX(-1); break;
					}
				}
				break;
			case Stop:
				break;
			case Shoot:
				synchronized(k_lstShips)
				{
					m_lstShots.add(k_lstShips.get(xClient).k().shoot());
				}
				break;
			default:
				System.err.println("Uknown command from: " + xClient);
				break;
			}

			Thread.yield();
		}
	}
}
