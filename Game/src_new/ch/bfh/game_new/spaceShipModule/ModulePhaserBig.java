package ch.bfh.game_new.spaceShipModule;

import ch.bfh.game_new.spaceShip.SpaceShip;
import ch.bfh.game_new.tileMap.TileMap;

public class ModulePhaserBig extends Module {

	// Phaser BIG information
	private int energyCost;
	
	// cooldown-timer
	private int timer;
	private int timerMax;
	
	// constructor
	public ModulePhaserBig(TileMap tm, SpaceShip owner)
	{
		super(tm, owner);
		
		this.energyCost = 20;
		
		this.timer = 0;
		this.timerMax = 100;
	}
	
	/*
	 * activates the Module, creates a Big Phaser, if Energy is sufficient
	 */
	public void activateModule()
	{
		if(this.energyCost <= owner.getEnergyActual() && this.timer == 0)
		{
			PhaserBig big = new PhaserBig(this.tileMap, this.state, this.owner, 50, 5.0);
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
