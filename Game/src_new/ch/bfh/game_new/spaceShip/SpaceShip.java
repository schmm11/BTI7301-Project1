package ch.bfh.game_new.spaceShip;

import ch.bfh.game_new.entity.SpaceObjectMoving;
import ch.bfh.game_new.gameState.GameState;
import ch.bfh.game_new.main.Config;
import ch.bfh.game_new.spaceShipModule.ModuleBoost;
import ch.bfh.game_new.spaceShipModule.ModuleMissile;
import ch.bfh.game_new.spaceShipModule.ModulePhaser;
import ch.bfh.game_new.spaceShipModule.ModulePhaserBig;
import ch.bfh.game_new.tileMap.TileMap;

public abstract class SpaceShip extends SpaceObjectMoving {
	
	// animation actions
	protected static final int IDLE = 0;
	protected static final int MOVING = 1;
	protected static final int DASHING = 2;
	protected static final int EXPLODE = 3;
	
	// modules
	protected ModulePhaser modPhaser;
	protected ModulePhaserBig modPhaserBig;
	protected ModuleMissile modMissile;
	protected ModuleBoost modBooster;
	
	// missile ammo
	protected int missileAmmo;
	protected int missileAmmoMax;
	
	// ship attributes
	protected double energyActual;
	protected double energyMax;
	protected double healthActual;
	protected double healthMax;
	protected boolean boost;
	protected boolean explode;
	
	// energy recharge
	protected double rechargeRate;
	
	// constructor
	public SpaceShip(TileMap tm, GameState state)
	{
		super(tm, state);
		modPhaser = new ModulePhaser(this.tileMap, this);
		modPhaserBig = new ModulePhaserBig(this.tileMap, this);
		modMissile = new ModuleMissile(this.tileMap, this);
		modBooster = new ModuleBoost(this.tileMap, this);
		
		this.missileAmmo = Config.P_MISSILEAMMO;
		this.missileAmmoMax = Config.P_MISSILEMAXAMMO;
	}
	
	// getters and setters
	public double getEnergyActual(){return this.energyActual;}
	
	public double getEnergyMax(){return this.energyMax;}
		
	public double getHealthActual(){return this.healthActual;}
	
	public double getHealthMax(){return this.healthMax;}
	
	public void setBoost(boolean b){this.boost = b;}
	
	public boolean getBoost(){return this.boost;}
	
	public double getRechargeRate(){return this.rechargeRate;}
	
	public void setRechargeRate(double r){this.rechargeRate = r;}
	
	public int getMissileAmmo(){return this.missileAmmo;}
	
	public int getMissileAmmoMax(){return this.missileAmmoMax;}
	
	/*
	 * calls the activateModule function of the phaser Module
	 */
	public void activatePhaser()
	{
		this.modPhaser.activateModule();
	}
	
	/*
	 * calls the activateModule function of the phaser BIG Module
	 */
	public void activatePhaserBig()
	{
		this.modPhaserBig.activateModule();
	}
	
	/*
	 * calls the activateModule function of the Missile Module
	 */
	public void activateMissile()
	{
		this.modMissile.activateModule();
	}
	
	/*
	 * calls the activateModule function of the boost module
	 */
	public void activateBoost()
	{
		this.modBooster.activateModule();
	}
	
	/*
	 * reduces the actual Energy by the given amount e
	 */
	public void consumeEnergy(int e)
	{
		this.energyActual = this.energyActual - e;
		if(this.energyActual < 0)
		{
			this.energyActual = 0;
		}
	}
	
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
	 * adds health to actual health
	 */
	public void addHealth(int h)
	{
		this.healthActual = this.healthActual + h;
		if(this.healthActual > this.healthMax)
		{
			this.healthActual = this.healthMax;
		}
	}
	
	/*
	 * adds energy to actual energy
	 */
	public void addEnergy(int e)
	{
		this.energyActual = this.energyActual + e;
		if(this.energyActual > this.energyMax)
		{
			this.energyActual = this.energyMax;
		}
	}
	
	/*
	 * adds missile ammunition to spaceShip
	 */
	public void addMissileAmmo(int n)
	{
		this.missileAmmo = this.missileAmmo + n;
		if(this.missileAmmo > this.missileAmmoMax)
		{
			this.missileAmmo = this.missileAmmoMax;
		}
	}
	
	/*
	 * removes a missile from missile ammo
	 */
	public void consumeMissile()
	{
		this.missileAmmo = this.missileAmmo - 1;
		if(this.missileAmmo < 0)
		{
			this.missileAmmo = 0;
		}
	}
	
	/*
	 * sets explode to true and plays exploding animation
	 */
	protected void setExplode()
	{
		
	}
}
