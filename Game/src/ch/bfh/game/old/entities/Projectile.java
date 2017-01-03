package ch.bfh.game.entities;


import java.util.Optional;

import ch.bfh.game.main.ArgumentChecker;


public final class Projectile extends SpaceObject
{
	public enum Type
	{
		eMissile,
		eRound,
	};


	private final Optional<Team> k_xTeam;
	private final Type k_xType;


	public Projectile(final Team xTeam, final Type xType)
	{
		k_xTeam = Optional.ofNullable(xTeam);
		k_xType = ArgumentChecker.requireNonNull(xType, "Type musn't be null");
	}

	public Team getTeam()
	{
		return k_xTeam.get();
	}

	public boolean hasTeam()
	{
		return k_xTeam.isPresent();
	}

	public Type getType()
	{
		return k_xType;
	}

	@Override
	public void update()
	{
	}
}
