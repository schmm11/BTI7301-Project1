package capl_first_design.FirstDesign.main;


import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public final class Server3
{
	public static void main(final String[] rgArgs)
	{
		new Server3();
	}

	public Server3()
	{
		ExecutorService x = Executors.newSingleThreadExecutor();
		final Future<Integer> future = x.submit(new Callable<Integer>()
		{
			@Override
			public Integer call()
			{
				try
				{
					Thread.sleep(3000);
					return 2;
				}
				catch(final InterruptedException x)
				{
					return -1;
				}
			}
		});
		try {
			int i = future.get(4, TimeUnit.SECONDS);
			System.out.println(i);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.exit(0);
		final List<NetworkInterface> lstInterfaces = getInterfaces();
		lstInterfaces.stream()
			.filter(xInterface ->
			{
				try
				{
					return !xInterface.isLoopback() && !xInterface.isVirtual() && InetAddress.getLocalHost().isReachable(xInterface, 0, 50);
//					return !xInterface.isLoopback() && !xInterface.isVirtual() && xInterface.isUp();
				}
				catch(final SocketException xException)
				{
					xException.printStackTrace();
					return false;
				}
				catch(final IOException xException)
				{
					xException.printStackTrace();
					return false;
				}
			}).forEach(System.out::println);
	}

	private List<NetworkInterface> getInterfaces()
	{
		final List<NetworkInterface> lstInterfaces = new ArrayList<>();
		try
		{
			final Enumeration<NetworkInterface> xInterfaces = NetworkInterface.getNetworkInterfaces();
			while(xInterfaces.hasMoreElements())
			{
				lstInterfaces.add(xInterfaces.nextElement());
			}
		}
		catch(final SocketException xException)
		{
			xException.printStackTrace();
		}

		return lstInterfaces;
	}
}
