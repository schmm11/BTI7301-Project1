package ch.bfh.game_new.spaceShip;

import ch.bfh.game_new.entity.Animation;
import ch.bfh.game_new.entity.ObjectType;
import ch.bfh.game_new.entity.Team;
import ch.bfh.game_new.gameState.GameState;
import ch.bfh.game_new.main.Config;
import ch.bfh.game_new.spaceShipModule.ModuleMissile;
import ch.bfh.game_new.spaceShipModule.ModulePhaser;
import ch.bfh.game_new.tileMap.TileMap;

public class Player extends SpaceShip {

	// dimensions
	public static final int WIDTH = 60;
	public static final int HEIGHT = 60;

	// constructor
	public Player(TileMap tm, GameState state)
	{
		super(tm, state);
		this.energyActual = Config.P_STARTINGENERGY;
		this.energyMax = Config.P_MAXENERGY;
		this.healthActual = Config.P_STARTINGHEALTH;
		this.healthMax = Config.P_MAXHEALTH;
		this.rechargeRate = Config.P_ENERGYRESTORE;

		this.type = ObjectType.PLAYERSHIP;
		this.team = Team.BLUE;

		this.width = WIDTH;
		this.height = HEIGHT;
		this.cwidth = WIDTH;
		this.cheight = HEIGHT;

		this.moveSpeed = Config.P_ACCELERATION;
		this.maxSpeed = Config.P_MAXSPEED;

		animation = new Animation();
		currentAction = IDLE;
		animation.setFrames(stateActual.getGSM().getPainter().getPlayerSprites().get(IDLE));
		animation.setDelay(400);

		// add object to ArrayList with all objects in GameState
		stateActual.addPlayer(this);
	}

	// setter and getter
	public ModulePhaser getModPhaser(){return this.modPhaser;}

	public ModuleMissile getModMissile(){return this.modMissile;}

	public void setTeam(Team team){this.team = team;}

	public boolean getDestroyed()
	{
		if(this.explode && animation.hasPlayedOnce())
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see spaceShip.SpaceShip#setExplode()
	 */
	@Override
	public void setExplode()
	{
		if(explode){return;}

		this.explode = true;
		animation.setFrames(stateActual.getGSM().getPainter().getPlayerSprites().get(EXPLODE));
		animation.setDelay(60);
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
		if(this.boost && !explode)
		{
			if(currentAction != DASHING)
			{
				currentAction = DASHING;
				animation.setFrames(stateActual.getGSM().getPainter().getPlayerSprites().get(DASHING));
				animation.setDelay(30);
			}

			this.maxSpeed = Config.B_MAXSPEED;
			this.energyActual = this.energyActual - 0.5;

			if(this.energyActual <= 0)
			{
				this.boost = false;
				this.maxSpeed = Config.P_MAXSPEED;

				left = false;
				right = false;
				up = false;
				down = false;
			}
		}

		// is ship is not boosting, check if ship is moving
		else if(moving && !explode)
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
			if(currentAction != IDLE && !explode)
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
