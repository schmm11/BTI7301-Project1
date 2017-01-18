package ch.bfh.game_new.spaceTurret;

import javax.sound.sampled.Clip;

import ch.bfh.game_new.entity.ObjectType;
import ch.bfh.game_new.entity.SpaceObject;
import ch.bfh.game_new.entity.Team;
import ch.bfh.game_new.gameState.GameState;
import ch.bfh.game_new.tileMap.TileMap;

public abstract class SpaceTurret extends SpaceObject {

	// animation actions
	protected static final int IDLE = 0;
	protected static final int SHOOT = 1;
	protected static final int EXPLODING = 2;
	
	// modules
	TurretModMissile modMissile;
	TurretModLaser modLaser;
	
	// turret attributes
	protected double healthActual;
	protected double healthMax;
	protected boolean explode;
	protected boolean shoot;
	
	// recharge rate
	protected double rechargeRate;
	
	// constructor
	public SpaceTurret(TileMap tm, GameState state, Team team)
	{
		super(tm, state);
		this.team = team;
		
		this.type = ObjectType.SPACETURRET;
		this.modMissile = new TurretModMissile(tm, this);
		this.modLaser = new TurretModLaser(tm, this);
	}
	
	// getters and setters
	public double getHealthActual(){return this.healthActual;}
	
	public void setFiring(boolean b){this.shoot = b;}
	
	public void setAngle(int a){this.angle = a;}
	
	/*
	 * reduces the health of the ship by the given amount
	 */
	public void addDamage(double damage)
	{
		this.healthActual = this.healthActual - damage;
		if(this.healthActual <= 0)
		{
			this.healthActual = 0;
			this.setExplode();
		}
	}
	
	/*
	 * calls the activateModule function of the Laser TurretModule
	 */
	public void activateLaser()
	{
		if(this.modLaser.getReady())
		{
			this.modLaser.activateModule();
			setShoot();
		}
	}
	
	/*
	 * calls the activateModule function of the missile TurretModule
	 */
	public void activateMissile()
	{
		if(this.modMissile.getReady())
		{
			this.modMissile.activateModule();
			setShoot();
		}
	}
	
	/*
	 * sets explode to true and plays exploding animation
	 */
	protected void setExplode()
	{
		
	}
	
	/*
	 * sets shoot to true and plays shooting animation
	 */
	protected void setShoot()
	{
		
	}
}
