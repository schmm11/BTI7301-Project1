package ch.bfh.game_new.spaceShip;

import ch.bfh.game_new.entity.Animation;
import ch.bfh.game_new.entity.ObjectType;
import ch.bfh.game_new.entity.Team;
import ch.bfh.game_new.gameState.GameState;
import ch.bfh.game_new.tileMap.TileMap;

public class Player extends SpaceShip {

	// dimensions
	public static final int WIDTH = 60;
	public static final int HEIGHT = 60;
	
	// constructor
	public Player(TileMap tm, GameState state)
	{
		super(tm, state);
		this.energyActual = 100;
		this.energyMax = 100;
		this.healthActual = 30;
		this.healthMax = 100;
		this.rechargeRate = 0.1;
		
		this.type = ObjectType.PLAYERSHIP;
		this.team = Team.BLUE;
		
		this.width = WIDTH;
		this.height = HEIGHT;
		this.cwidth = WIDTH;
		this.cheight = HEIGHT;
		
		this.moveSpeed = 0.5;
		this.maxSpeed = 3.0;
		
		animation = new Animation();
		currentAction = IDLE;
		animation.setFrames(stateActual.getGSM().getPainter().getPlayerSprites().get(IDLE));
		animation.setDelay(400);
		
		// add object to ArrayList with all objects in GameState
		stateActual.addPlayer(this);
	}
	
	/*
	 * (non-Javadoc)
	 * @see entity.SpaceObject#update()
	 */
	public void update()
	{
		getNextPosition();
		checkTileMapCollision();
		setPosition(xtemp, ytemp);
		
		// check boost
		if(this.boost)
		{
			if(currentAction != DASHING)
			{
				currentAction = DASHING;
				animation.setFrames(stateActual.getGSM().getPainter().getPlayerSprites().get(DASHING));
				animation.setDelay(30);
			}
			
			this.maxSpeed = 7.0;
			this.energyActual = this.energyActual - 0.5;
			
			if(this.energyActual <= 0)
			{
				this.boost = false;
				this.maxSpeed = 3.0;
				
				left = false;
				right = false;
				up = false;
				down = false;
			}
		}
		
		// is ship is not boosting, check if ship is moving
		else if(moving)
		{
			if(currentAction != MOVING)
			{
				currentAction = MOVING;
				animation.setFrames(stateActual.getGSM().getPainter().getPlayerSprites().get(MOVING));
				animation.setDelay(30);
			}

		}
		
		// if ship is not boosting and not moving, ship is idle
		else
		{
			if(currentAction != IDLE)
			{
				currentAction = IDLE;
				animation.setFrames(stateActual.getGSM().getPainter().getPlayerSprites().get(IDLE));
				animation.setDelay(400);
			}
		}
		
		// recharge energy
		if(this.energyActual < this.energyMax)
		{
			this.energyActual += this.rechargeRate;
			if(this.energyActual > this.energyMax)
			{
				this.energyActual = this.energyMax;
			}
		}
		
		// update Timers on modules
		this.modMissile.updateTimer();
		this.modPhaser.updateTimer();
		this.modPhaserBig.updateTimer();
		
		animation.update();
	}
}
