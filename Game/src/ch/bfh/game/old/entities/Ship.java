package ch.bfh.game.entities;


import ch.bfh.game.main.ArgumentChecker;


public final class Ship extends SpaceObject
{
	enum Type
	{
		Frigate,
		Corvette,
		Battlecruiser,
		StarDestroyer,
		Fly,
	}


	private final Team k_xTeam;


	public Ship(final Team xTeam)
	{
		k_xTeam = ArgumentChecker.requireNonNull(xTeam, "Team musn't be null");
	}

	public Team team()
	{
		return k_xTeam;
	}

	@Override
	public void update()
	{
	}
}
