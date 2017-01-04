package ch.bfh.game_new.spaceShipModule;

import ch.bfh.game_new.main.Config;
import ch.bfh.game_new.spaceShip.SpaceShip;
import ch.bfh.game_new.tileMap.TileMap;

public class ModulePhaserBig extends Module {

	// Phaser BIG information
	private int energyCost;
	
	private double dmg;
	private double speed;
	
	// cooldown-timer
	private int timer;
	private int timerMax;
	
	// constructor
	public ModulePhaserBig(TileMap tm, SpaceShip owner)
	{
		super(tm, owner);
		
		this.energyCost = Config.PB_ENERGYCOST;
		this.dmg = Config.PB_PHASERDAMAGE;
		this.speed = Config.PB_PHASERSPEED;
		
		this.timer = 0;
		this.timerMax = Config.PB_DELAY;
	}
	
	/*
	 * activates the Module, creates a Big Phaser, if Energy is sufficient
	 */
	public void activateModule()
	{
		if(this.energyCost <= owner.getEnergyActual() && this.timer == 0)
		{
			new PhaserBig(this.tileMap, this.state, this.owner, dmg, speed);
			owner.consumeEnergy(energyCost);
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
