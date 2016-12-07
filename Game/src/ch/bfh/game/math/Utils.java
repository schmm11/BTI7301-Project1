package ch.bfh.game.math;

public class Utils
{
	private Utils()
	{
	}

	public static void main(final String[] rgArgs)
	{
		System.out.println(inrange(2.0, 2.5, 3.0));
	}

	public static <T extends Comparable<T>> boolean inrange(final T needle, final T left, final T right)
	{
		return needle.compareTo(left) >= 0 && needle.compareTo(right) <= 0;
	}

	public static boolean inrange(final int iFirst, final int iSecond, final int iRange)
	{
		return Math.abs(iFirst - iSecond) < iRange;
	}

	public static boolean inrange(final short iFirst, final short iSecond, final short iRange)
	{
		return Math.abs(iFirst - iSecond) < iRange;
	}
}
