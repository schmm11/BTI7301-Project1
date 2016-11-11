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
	private final Map<SocketAddress, Ship> k_lstShips = new HashMap<>();
	private List<Shot> m_lstShots = new ArrayList<Shot>();

	private boolean m_bRunning = true;


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
			k_xChannel.send(xBuffer, xAddress);
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
				k_lstShips.values().forEach(Ship::update);
				xBuffer.putInt(k_lstShips.size());
				k_lstShips.values().forEach(xShip -> { xBuffer.putInt(xShip.x()); xBuffer.putInt(xShip.y()); });
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
						final ByteBuffer xTmp = ByteBuffer.allocate(Settings.PACKAGE_SIZE * 10);
						xTmp.clear();
						xTmp.put(xBuffer);
						xTmp.flip();
						k_xChannel.send(xTmp, xAddress);
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
					k_lstShips.put(xClient, new Ship());
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
					final Ship xShip = k_lstShips.get(xClient);
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
					m_lstShots.add(k_lstShips.get(xClient).shoot());
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
