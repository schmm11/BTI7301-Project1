package ch.bfh.game_new.spaceTurret;

import ch.bfh.game_new.spaceShipModule.Missile;
import ch.bfh.game_new.tileMap.TileMap;

public class TurretModLaser extends TurretModule {

	// cooldown-timer
	private int timer;
	private int timerMax;
	private boolean ready;
	
	// missile information
	boolean doubleLaser;
	
	private static final double LASERSPEED = 13.5;
	private static final double LASERDAMAGE = 7.5;
	
	// constructor
	public TurretModLaser(TileMap tm, SpaceTurret owner)
	{
		super(tm, owner);
		
		this.doubleLaser = true;
		this.ready = true;
		
		this.timer = 0;
		this.timerMax = 10;
	}
	
	/*
	 * activates the Module, creates a Missile (unlimited ammo)
	 */
	public void activateModule()
	{
		if(this.ready && !owner.explode)
		{
			if(doubleLaser)
			{
				if(owner.getAngle() == 0 || owner.getAngle() == 180)
				{
					new Laser(this.tileMap, this.state, this.owner, owner.getx() - 10, owner.gety(), LASERDAMAGE, LASERSPEED);
					new Laser(this.tileMap, this.state, this.owner, owner.getx() + 10, owner.gety(), LASERDAMAGE, LASERSPEED);
				}
				else if(owner.getAngle() == 90 || owner.getAngle() == 270)
				{
					new Laser(this.tileMap, this.state, this.owner, owner.getx(), owner.gety() + 10, LASERDAMAGE, LASERSPEED);
					new Laser(this.tileMap, this.state, this.owner, owner.getx(), owner.gety() - 10, LASERDAMAGE, LASERSPEED);
				}
				else if(owner.getAngle() == 45 || owner.getAngle() == 225)
				{
					new Laser(this.tileMap, this.state, this.owner, owner.getx() + 10, owner.gety() + 10, LASERDAMAGE, LASERSPEED);
					new Laser(this.tileMap, this.state, this.owner, owner.getx() - 10, owner.gety() - 10, LASERDAMAGE, LASERSPEED);
				}
				else
				{
					new Laser(this.tileMap, this.state, this.owner, owner.getx() - 10, owner.gety() + 10, LASERDAMAGE, LASERSPEED);
					new Laser(this.tileMap, this.state, this.owner, owner.getx() + 10, owner.gety() - 10, LASERDAMAGE, LASERSPEED);
				}
			}
			
			this.timer = this.timerMax;
			this.ready = false;
		}
	}
	
	/*
	 * updates the timer of the module.
	 * when the timer reaches 0, the module is ready to fire a missile
	 */
	public void updateTimer()
	{
		if(this.timer >= 0)
		{
			this.timer = this.timer - 1;
			if(this.timer < 0)
			{
				this.timer = 0;
				this.ready = true;
			}
		}
	}
	
	// getters and setters
	public void setTimerMax(int t){this.timerMax = t;}
	
	public boolean getReady(){return this.ready;}
	
	public boolean getDoubleMissile(){return this.doubleLaser;}
	
	public void setDoubleMissile(boolean b){this.doubleLaser= b;}
}
