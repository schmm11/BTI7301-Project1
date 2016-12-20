package ch.bfh.game.main;

import java.util.Objects;
import java.util.function.Predicate;

public final class ArgumentChecker
{
	private ArgumentChecker()
	{
	}

	public static <T> T require(final T xT, final Predicate<T> xPredicate)
	{
		return require(xT, xPredicate, "");
	}

	public static <T> T require(final T xT, final Predicate<T> xPredicate, final String strErrorMessage)
	{
		Objects.requireNonNull(xT);
		Objects.requireNonNull(xPredicate);
		Objects.requireNonNull(strErrorMessage);
		if(xPredicate.test(xT))
		{
			return xT;
		}
		else
		{
			throw new IllegalArgumentException(strErrorMessage);
		}
	}

	public static <T> T requireNonNull(final T xObject, final String strErrorMessage)
	{
		return require(xObject, x -> x != null, strErrorMessage);
	}
}
