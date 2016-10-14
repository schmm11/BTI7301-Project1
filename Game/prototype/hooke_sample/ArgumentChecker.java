package hooke_sample;


import java.util.Objects;
import java.util.function.Predicate;


public final class ArgumentChecker
{
	public static <T> T check(final T xValue, final Predicate<T> xPredicate)
	{
		return check(xValue, xPredicate, "Predicate failed for value: " + xValue.toString());
	}

	public static <T> T check(final T xValue, final Predicate<T> xPredicate, final String strError)
	{
		Objects.requireNonNull(xValue);
		Objects.requireNonNull(xPredicate);
		Objects.requireNonNull(strError);
		if(xPredicate.test(xValue))
		{
			return xValue;
		}
		else
		{
			throw new IllegalArgumentException(strError);
		}
	}
}
