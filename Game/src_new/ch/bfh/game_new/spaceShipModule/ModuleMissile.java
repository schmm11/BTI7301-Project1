package ch.bfh.game_new.spaceShipModule;

import ch.bfh.game_new.spaceShip.SpaceShip;
import ch.bfh.game_new.tileMap.TileMap;

public class ModuleMissile extends Module {
	
	// cooldown-timer
	private int timer;
	private int timerMax;
	
	// constructor
	public ModuleMissile(TileMap tm, SpaceShip owner)
	{
		super(tm, owner);

		this.timer = 0;
		this.timerMax = 50;
	}
	
	/*
	 * activates the Module, creates a Missile, if ammo is > 0
	 */
	public void activateModule()
	{
		if(this.owner.getMissileAmmo() > 0 && this.timer == 0)
		{
			Missile m1 = new Missile(this.tileMap, this.state, this.owner, 15.0, 9.5);
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
	
}
