package hooke_sample;


import java.util.Objects;


public enum Utils
{
	;

	public static int wrap(final double dValue, final double dMax)
	{
		ArgumentChecker.check(dMax, d -> d > 0, "dMax must be positive");
		return (int) (dValue - Math.floor(dValue / dMax) * dMax);
	}

	public static <T> T getWrapped(final T[] rgArgs, final int iIndex)
	{
		ArgumentChecker.check(Objects.requireNonNull(rgArgs), rg -> rg.length > 0, "Array cannot be empty");
		return rgArgs[wrap(iIndex, rgArgs.length)];
	}
}
