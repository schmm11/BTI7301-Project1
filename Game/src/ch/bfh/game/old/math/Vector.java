package ch.bfh.game.math;


public final class Vector
{
	private final double k_dX;
	private final double k_dY;


	private Vector(final double dX, final double dY)
	{
		k_dX = dX;
		k_dY = dY;
	}

	public static Vector polar(final double dAngle, final double dMagnitude)
	{
		return new Vector(
			Math.cos(dAngle) * dMagnitude,
			Math.sin(dAngle) * dMagnitude
		);
	}

	public static Vector cartesian(final double dX, final double dY)
	{
		return new Vector(dX, dY);
	}

	public double getX()
	{
		return k_dX;
	}

	public double getY()
	{
		return k_dY;
	}

	public double getAngle()
	{
		return Math.atan2(k_dX, k_dY);
	}

	public double getLengthSquared()
	{
		return (k_dX * k_dX) + (k_dY * k_dY);
	}

	public double getLength()
	{
		return Math.sqrt(getLengthSquared());
	}

	@Override
	public boolean equals(final Object xOther)
	{
		if(xOther.getClass() == getClass())
		{
			final Vector xOtherVector = (Vector) xOther;
			return (xOtherVector.getX() == getX() && xOtherVector.getY() == getY());
		}
		else
		{
			return false;
		}
	}

	@Override
	public String toString()
	{
		return String.format("Vector2D(%g, %g", k_dX, k_dY);
	}
}
