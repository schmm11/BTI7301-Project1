package ch.bfh.game_new.spaceTurret;

import ch.bfh.game_new.entity.Team;
import ch.bfh.game_new.gameState.GameState;
import ch.bfh.game_new.tileMap.TileMap;

public abstract class TurretModule {

	// TurretModule information
	protected SpaceTurret owner;
	protected TileMap tileMap;
	protected GameState state;
	protected Team team;
	
	// constructor
	public TurretModule(TileMap tm, SpaceTurret owner)
	{
		this.owner = owner;
		this.tileMap = tm;
		this.team = owner.getTeam();
		this.state = owner.getState();
	}
	
	// getters and setters
	public GameState getState(){return this.state;}
}
