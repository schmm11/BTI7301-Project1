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

import capl_first_design.FirstDesign.main.Settings.Command;

public final class Server2
{
	public static void main(final String[] rgArgs) throws Exception { new Server2(); }


	private final DatagramChannel k_xChannel;
	private final InetAddress k_xGroup;

	private boolean m_bRunning = true;


	public Server2() throws Exception
	{
		k_xChannel = DatagramChannel.open(StandardProtocolFamily.INET);
		k_xGroup = InetAddress.getByName(Settings.MULTICAST_IP);

		k_xChannel.join(k_xGroup, NetworkInterface.getByInetAddress(InetAddress.getLocalHost()));
		k_xChannel.bind(new InetSocketAddress(Settings.MULTICAST_PORT));
		k_xChannel.configureBlocking(false);

		final Thread xNetThread = new Thread(this::netLoop);

		xNetThread.start();

		try(final BufferedReader xReader = new BufferedReader(new InputStreamReader(System.in)))
		{
			xReader.lines().filter(strLine -> strLine.equalsIgnoreCase("quit")).findFirst();
		}

		m_bRunning = false;
		xNetThread.join();

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

			if(!xBuffer.hasRemaining() && xBuffer.remaining() < Integer.SIZE)
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
				System.out.print("Message: '");
				while(xBuffer.hasRemaining())
				{
					System.out.print((char) xBuffer.get());
				}
				System.out.println("' from: " + xClient);
				break;
			default:
				System.err.println("Uknown command from: " + xClient);
				break;
			}

			Thread.yield();
		}
	}
}
