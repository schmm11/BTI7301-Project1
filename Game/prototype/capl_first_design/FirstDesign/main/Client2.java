package capl_first_design.FirstDesign.main;


import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.StandardProtocolFamily;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

import capl_first_design.FirstDesign.main.Settings.Command;


public final class Client2
{
	public static void main(final String[] rgArgs) throws Exception { new Client2(); }


	private final DatagramChannel k_xChannel;


	public Client2() throws Exception
	{
		k_xChannel = DatagramChannel.open(StandardProtocolFamily.INET);

		final ByteBuffer xBuffer = ByteBuffer.allocate(Settings.PACKAGE_SIZE);
		xBuffer.putInt(Command.Broadcast.ordinal());
		xBuffer.put("Hello".getBytes());
		xBuffer.flip();
		k_xChannel.send(xBuffer, new InetSocketAddress(InetAddress.getByName(Settings.MULTICAST_IP), Settings.MULTICAST_PORT));
		xBuffer.clear();
		final SocketAddress xServer = k_xChannel.receive(xBuffer);
		xBuffer.flip();
		final Command xCommand = Command.values()[xBuffer.getInt()];
		if(xCommand == Command.Confirm)
		{
			xBuffer.clear();
			xBuffer.putInt(Command.Register.ordinal());
			xBuffer.put("Hello, Server".getBytes());
			xBuffer.flip();
			k_xChannel.send(xBuffer, xServer);
		}
		else
		{
			System.err.println("Did not follow protocol");
		}
	}
}
