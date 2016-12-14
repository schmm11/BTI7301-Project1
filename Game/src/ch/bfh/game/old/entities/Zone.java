package ch.bfh.game.entities;


import ch.bfh.game.main.ArgumentChecker;
import ch.bfh.game.math.Rectangle;


public final class Zone extends SpaceObject
{
	private Rectangle k_xBounds;


	public Zone(final Rectangle xBounds)
	{
		k_xBounds = ArgumentChecker.requireNonNull(xBounds, "Bounds musn't be null");
	}

	public Rectangle getBounds()
	{
		return k_xBounds;
	}

	@Override
	public void update()
	{
	}
}
