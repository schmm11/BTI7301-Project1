package capl_first_design.FirstDesign.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

public final class Foobar
{
	public static void main(final String[] rgArgs) throws Exception
	{
		final Socket xSocket = new Socket(InetAddress.getByName("towel.blinkenlights.nl"), 23);
		final BufferedReader xReader = new BufferedReader(new InputStreamReader(xSocket.getInputStream()));
//		xReader.lines().forEach(System.out::println);
	}
}
