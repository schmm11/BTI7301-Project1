package capl_first_design.FirstDesign.main;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.StandardProtocolFamily;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.HashMap;
import java.util.Map;


public final class Server
{
	public static void main(final String[] rgArgs) { new Server(); }

	private final Map<SocketAddress, Ship> k_mapShips = new HashMap<>();
	private DatagramChannel m_xChannel;
	private boolean m_bRunning = true;
	private int m_iSent = 0;

	public Server()
	{
		try
		{
			m_xChannel = DatagramChannel.open(StandardProtocolFamily.INET);
			m_xChannel.bind(new InetSocketAddress(Settings.PORT));
			m_xChannel.configureBlocking(false);

			final BufferedReader xReader = new BufferedReader(new InputStreamReader(System.in));
			final Thread xGameThread = new Thread(this::gameLoop);
			final Thread xNetThread = new Thread(this::netLoop);

			xGameThread.start();
			xNetThread.start();

			xReader.lines().filter(strLine -> strLine.equals("quit")).findFirst();

			m_bRunning = false;
			xGameThread.join();
			xNetThread.join();

			m_xChannel.disconnect();
			m_xChannel.close();

			System.out.println("Bytes sent: " + m_iSent);
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

	private void gameLoop()
	{
		final ByteBuffer xBuffer = ByteBuffer.allocate(1000);
		while(m_bRunning)
		{
			synchronized(k_mapShips)
			{
				k_mapShips.values().stream().forEach(Ship::update);
			}

			xBuffer.clear();
			xBuffer.put((byte) k_mapShips.size());
			k_mapShips.values().stream().forEach(xShip -> { xBuffer.putInt(xShip.x()); xBuffer.putInt(xShip.y()); });
			xBuffer.flip();
			try
			{
				for(final SocketAddress xAddress : k_mapShips.keySet())
				{
					m_iSent += m_xChannel.send(ByteBuffer.wrap(xBuffer.array()), xAddress);
				}
			}
			catch(final IOException xException)
			{
				xException.printStackTrace();
			}

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

	private void netLoop()
	{
		final ByteBuffer xBuffer = ByteBuffer.allocate(100);

		while(m_bRunning)
		{
			try
			{
				xBuffer.clear();
				final SocketAddress xAddress = m_xChannel.receive(xBuffer);
				if(xAddress != null)
				{
					xBuffer.flip();
					synchronized(k_mapShips)
					{
						if(k_mapShips.containsKey(xAddress))
						{
							final byte iCommand = xBuffer.get();
							switch(iCommand)
							{
							case Settings.STOP:
							{
								final Ship xShip = k_mapShips.get(xAddress);
								final byte bDirection = xBuffer.get();
								switch(bDirection)
								{
								case Settings.NORTH:
									xShip.speedX();
									xShip.speedY(1);
									break;
								case Settings.SOUTH:
									xShip.speedX(0);
									xShip.speedY(-1);
									break;
								case Settings.EAST:
									xShip.speedX(1);
									xShip.speedY(0);
									break;
								case Settings.WEST:
									xShip.speedX(-1);
									xShip.speedY(0);
									break;
								}
							}
								break;
							case Settings.MOVE:
							{
								final Ship xShip = k_mapShips.get(xAddress);
								final byte bDirection = xBuffer.get();
								switch(bDirection)
								{
								case Settings.NORTH:
									xShip.speedX();
									xShip.speedY(-1);
									break;
								case Settings.SOUTH:
									xShip.speedX(0);
									xShip.speedY(1);
									break;
								case Settings.EAST:
									xShip.speedX(-1);
									xShip.speedY(0);
									break;
								case Settings.WEST:
									xShip.speedX(1);
									xShip.speedY(0);
									break;
								}
							}
								break;
							case Settings.DISCONNECT:
								k_mapShips.remove(xAddress);
								break;
							default:
								System.err.printf("Received unknown command (%d) for registered player\n", iCommand);
								break;
							}
						}
						else
						{
							final byte iCommand = xBuffer.get();
							switch(iCommand)
							{
							case Settings.JOIN:
								k_mapShips.put(xAddress, new Ship());
								break;
							default:
								System.err.printf("Received invalid command (%d) from unregistered player\n", iCommand);
								break;
							}
						}
					}
				}
			}
			catch(final IOException xException)
			{
				xException.printStackTrace();
			}

			Thread.yield();
		}
	}
}
