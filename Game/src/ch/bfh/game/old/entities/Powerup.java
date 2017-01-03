package ch.bfh.game.entities;


import ch.bfh.game.main.ArgumentChecker;


public class Powerup extends SpaceObject
{
	public enum Type
	{
		eShieldPickup,
		eRepairPickup,
		eMovementBoost,
		eLaserMissile,
		eRocket;
	};


	private final Type k_xType;


	public Powerup(final Type xType)
	{
		k_xType = ArgumentChecker.requireNonNull(xType, "Type mustn't be null");
	}

	public Type getType()
	{
		return k_xType;
	}

	@Override
	public void update()
	{
	}

	public void interact(final Ship xShip)
	{
		//k_xType.interact(xShip);
	}
}
