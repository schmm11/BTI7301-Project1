package ch.bfh.game_new.spaceShipModule;

import ch.bfh.game_new.main.Config;
import ch.bfh.game_new.spaceShip.SpaceShip;
import ch.bfh.game_new.tileMap.TileMap;

public class ModuleMissile extends Module {
	
	// cooldown-timer
	private int timer;
	private int timerMax;
	
	// Missile information
	boolean doubleMissile;
	
	private static final double MISSILESPEED = Config.M_MISSILESPEED;
	private static final double MISSILEDAMAGE = Config.M_MISSILEDAMAGE;
	
	// constructor
	public ModuleMissile(TileMap tm, SpaceShip owner)
	{
		super(tm, owner);

		this.doubleMissile = false;
		
		this.timer = 0;
		this.timerMax = Config.M_DELAY;
	}
	
	/*
	 * activates the Module, creates a Missile, if ammo is > 0
	 */
	public void activateModule()
	{
		if(this.owner.getMissileAmmo() > 0 && this.timer == 0)
		{
			if(!doubleMissile)
			{
				new Missile(this.tileMap, this.state, this.owner, owner.getx(), owner.gety(), MISSILEDAMAGE, MISSILESPEED);
			}
			else
			{
				if(owner.getAngle() == 0 || owner.getAngle() == 180)
				{
					new Missile(this.tileMap, this.state, this.owner, owner.getx() - 10, owner.gety(), MISSILEDAMAGE, MISSILESPEED);
					new Missile(this.tileMap, this.state, this.owner, owner.getx() + 10, owner.gety(), MISSILEDAMAGE, MISSILESPEED);
				}
				else if(owner.getAngle() == 90 || owner.getAngle() == 270)
				{
					new Missile(this.tileMap, this.state, this.owner, owner.getx(), owner.gety() + 10, MISSILEDAMAGE, MISSILESPEED);
					new Missile(this.tileMap, this.state, this.owner, owner.getx(), owner.gety() - 10, MISSILEDAMAGE, MISSILESPEED);
				}
				else if(owner.getAngle() == 45 || owner.getAngle() == 225)
				{
					new Missile(this.tileMap, this.state, this.owner, owner.getx() + 10, owner.gety() + 10, MISSILEDAMAGE, MISSILESPEED);
					new Missile(this.tileMap, this.state, this.owner, owner.getx() - 10, owner.gety() - 10, MISSILEDAMAGE, MISSILESPEED);
				}
				else
				{
					new Missile(this.tileMap, this.state, this.owner, owner.getx() - 10, owner.gety() + 10, MISSILEDAMAGE, MISSILESPEED);
					new Missile(this.tileMap, this.state, this.owner, owner.getx() + 10, owner.gety() - 10, MISSILEDAMAGE, MISSILESPEED);
				}
			}
			this.owner.consumeMissile();
			this.timer = this.timerMax;
		}
	}
	
	/*
	 * updates the timer of the module.
	 * when the timer reaches 0, the module is ready to fire a missile
	 */
	public void updateTimer()
	{
		if(this.timer > 0)
		{
			this.timer = this.timer - 1;
			if(this.timer < 0)
			{
				this.timer = 0;
			}
		}
	}
	
	// getters and setters
	public void setTimerMax(int t){this.timerMax = t;}
	
	public boolean getDoubleMissile(){return this.doubleMissile;}
	
	public void setDoubleMissile(boolean b){this.doubleMissile = b;}
	
}
