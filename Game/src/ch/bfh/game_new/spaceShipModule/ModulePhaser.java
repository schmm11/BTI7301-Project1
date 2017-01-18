package ch.bfh.game_new.spaceShipModule;

import ch.bfh.game_new.main.Config;
import ch.bfh.game_new.spaceShip.SpaceShip;
import ch.bfh.game_new.tileMap.TileMap;

public class ModulePhaser extends Module {

	// Phaser Information
	private int energyCost;
	boolean doublePhaser;
	
	private static final double PHASERSPEED = Config.PH_PHASERSPEED;
	private static final double PHASERDAMAGE = Config.PH_PHASERDAMAGE;
	
	// cooldown-timer
	private int timer;
	private int timerMax;
	
	// constructor
	public ModulePhaser(TileMap tm, SpaceShip owner)
	{
		super(tm, owner);
		
		this.energyCost = 5;
		this.doublePhaser = false;
		
		this.timer = 0;
		this.timerMax = Config.PH_DELAY;
	}
	
	/*
	 * activates the Module, creates a Phaser, if Energy is sufficient
	 */
	public void activateModule()
	{
		if(this.energyCost <= owner.getEnergyActual() && this.timer == 0)
		{
			if(!doublePhaser)
			{
				new Phaser(this.tileMap, this.state, this.owner, owner.getx(), owner.gety(), PHASERDAMAGE, PHASERSPEED);
			}
			else
			{
				if(owner.getAngle() == 0 || owner.getAngle() == 180)
				{
					new Phaser(this.tileMap, this.state, this.owner, owner.getx() - 10, owner.gety(), PHASERDAMAGE, PHASERSPEED);
					new Phaser(this.tileMap, this.state, this.owner, owner.getx() + 10, owner.gety(), PHASERDAMAGE, PHASERSPEED);
				}
				else if(owner.getAngle() == 90 || owner.getAngle() == 270)
				{
					new Phaser(this.tileMap, this.state, this.owner, owner.getx(), owner.gety() + 10, PHASERDAMAGE, PHASERSPEED);
					new Phaser(this.tileMap, this.state, this.owner, owner.getx(), owner.gety() - 10, PHASERDAMAGE, PHASERSPEED);
				}
				else if(owner.getAngle() == 45 || owner.getAngle() == 225)
				{
					new Phaser(this.tileMap, this.state, this.owner, owner.getx() + 10, owner.gety() + 10, PHASERDAMAGE, PHASERSPEED);
					new Phaser(this.tileMap, this.state, this.owner, owner.getx() - 10, owner.gety() - 10, PHASERDAMAGE, PHASERSPEED);
				}
				else
				{
					new Phaser(this.tileMap, this.state, this.owner, owner.getx() - 10, owner.gety() + 10, PHASERDAMAGE, PHASERSPEED);
					new Phaser(this.tileMap, this.state, this.owner, owner.getx() + 10, owner.gety() - 10, PHASERDAMAGE, PHASERSPEED);
				}
			}
			this.owner.consumeEnergy(energyCost);
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
	
	public boolean getDoublePhaser(){return this.doublePhaser;}
	
	public void setDoublePhaser(boolean b){this.doublePhaser = b;}
}
