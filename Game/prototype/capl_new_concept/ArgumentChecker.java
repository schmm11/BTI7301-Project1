package capl_new_concept;


import java.util.Objects;
import java.util.function.Predicate;


public final class ArgumentChecker
{
	private ArgumentChecker()
	{
	}

	public static <T> T require(final T xValue, final Predicate<T> xPredicate)
	{
		return require(xValue, xPredicate, "Predicate failed");
	}

	public static <T> T require(final T xValue, final Predicate<T> xPredicate, final String strErrorMessage)
	{
		Objects.requireNonNull(xValue);
		Objects.requireNonNull(xPredicate);
		Objects.requireNonNull(strErrorMessage);
		if(xPredicate.test(xValue))
		{
			return xValue;
		}
		else
		{
			throw new IllegalArgumentException(strErrorMessage);
		}
	}
}
