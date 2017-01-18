package ch.bfh.game_new.spaceTurret;

import ch.bfh.game_new.main.Config;
import ch.bfh.game_new.spaceShipModule.Missile;
import ch.bfh.game_new.tileMap.TileMap;

public class TurretModMissile extends TurretModule {

	// cooldown-timer
	private int timer;
	private int timerMax;
	private boolean ready;
	
	// missile information
	boolean doubleMissile;
	
	private static final double MISSILESPEED = Config.T_MISSILESPEED;
	private static final double MISSILEDAMAGE = Config.T_ATTACKDAMAGE;
	
	// constructor
	public TurretModMissile(TileMap tm, SpaceTurret owner)
	{
		super(tm, owner);
		
		this.doubleMissile = false;
		this.ready = true;
		
		this.timer = 0;
		this.timerMax = 20;
	}
	
	/*
	 * activates the Module, creates a Missile (unlimited ammo)
	 */
	public void activateModule()
	{
		if(this.ready)
		{
			if(!doubleMissile)
			{
				new Missile(this.tileMap, this.state, this.owner, owner.getx(), owner.gety(), MISSILEDAMAGE, MISSILESPEED);
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
	
	public boolean getDoubleMissile(){return this.doubleMissile;}
	
	public void setDoubleMissile(boolean b){this.doubleMissile = b;}
}
