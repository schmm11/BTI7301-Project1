package ch.bfh.game_new.spaceShipModule;

import ch.bfh.game_new.entity.Team;
import ch.bfh.game_new.gameState.GameState;
import ch.bfh.game_new.spaceShip.SpaceShip;
import ch.bfh.game_new.tileMap.TileMap;

public abstract class Module {

	// Module Information
	protected SpaceShip owner;
	protected TileMap tileMap;
	protected GameState state;
	protected Team team;
	
	// status
	protected boolean usable;
	
	// constructor
	public Module(TileMap tm, SpaceShip owner)
	{
		this.owner = owner;
		this.tileMap = tm;
		this.team = owner.getTeam();
		this.state = owner.getState();
	}
	
	// getters and setters
	public boolean getUsable(){return this.usable;}
	
	public void setUsable(boolean b){this.usable = b;}
	
	public GameState getState(){return this.state;}
	
}
