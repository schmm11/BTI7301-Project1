package ch.bfh.game_new.gameState;

import java.util.ArrayList;

import ch.bfh.game_new.entity.SpaceObject;
import ch.bfh.game_new.spaceShip.SpaceShip;
import ch.bfh.game_new.spaceShipModule.Phaser;
import ch.bfh.game_new.spaceShipModule.PhaserBig;
import ch.bfh.game_new.spaceShipModule.Projectile;

public abstract class GameState {
	
	protected GameStateManager gsm;
	
	// ArrayLists for all Space-Objects
	protected ArrayList<SpaceShip> listPlayer;
	protected ArrayList<SpaceShip> listEnemy;
	protected ArrayList<Projectile> listPhaser;
	protected ArrayList<Projectile> listPhaserBig;
	protected ArrayList<Projectile> listMissile;
	protected ArrayList<SpaceObject> listHealthPickup;
	protected ArrayList<SpaceObject> listEnergyPickup;
	protected ArrayList<SpaceObject> listMissilePickup;
	
	/*
	 * sets up the GameState
	 */
	public abstract void init();
	
	/*
	 * updates the GameState
	 */
	public abstract void update();
	
	// User-Input handling
	public abstract void keyPressed(int k);
	public abstract void keyReleased(int k);
	
	
	// manage ArrayLists holding all SpaceObjects
	// *************** PLAYER ***************
	/*
	 * adds an object of type SpaceShip to the ArrayList holding all Players for this GameState
	 */
	public abstract void addPlayer(SpaceShip s);
	
	/*
	 * removes an object of type SpaceShip from the ArrayList holding all Players for this GameState
	 */
	public abstract void removePlayer(SpaceShip s);
	
	/*
	 * returns an ArrayList holding all Players for this GameState
	 */
	public abstract ArrayList<SpaceShip> getListPlayer();
	
	// *************** ENEMY ***************
	/*
	 * adds an object of type SpaceShip to the ArrayList holding all Enemies for this GameState
	 */
	public abstract void addEnemy(SpaceShip s);
	
	/*
	 * removes an object of type SpaceShip from the ArrayList holding all Enemies for this GameState
	 */
	public abstract void removeEnemy(SpaceShip s);
	
	/*
	 * returns an ArrayList holding all Enemies for this GameState
	 */
	public abstract ArrayList<SpaceShip> getListEnemy();
	
	// *************** PHASER *************** 
	/*
	 * adds an object of type Projectile to the ArrayList holding all Phaser-objects for this GameState
	 */
	public abstract void addPhaser(Projectile p);
	
	/*
	 * removes an object of type Projectile from the ArrayList holding all Phaser-objects for this GameState
	 */
	public abstract void removePhaser(Projectile p);
	
	/*
	 * returns an ArrayList holding all Phaser-objects for this GameState
	 */
	public abstract ArrayList<Projectile> getListPhaser();
	
	// *************** PHASER BIG *************** 
	/*
	 * adds an object of type Projectile to the ArrayList holding all PhaserBig-objects for this GameState
	 */
	public abstract void addPhaserBig(Projectile p);
	
	/*
	 * removes an object of type Projectile from the ArrayList holding all PhaserBig-objects for this GameState
	 */
	public abstract void removePhaserBig(Projectile p);
	
	/*
	 * returns an ArrayList holding all PhaserBig-objects for this GameState
	 */
	public abstract ArrayList<Projectile> getListPhaserBig();
	
	// *************** MISSILE *************** 
	/*
	 * adds an object of type Projectile to the ArrayList holding all Missile-objects for this GameState
	 */
	public abstract void addMissile(Projectile p);
	
	/*
	 * removes an object of type Projectile from the ArrayList holding all Missile-objects for this GameState
	 */
	public abstract void removeMissile(Projectile p);
	
	/*
	 * returns an ArrayList holding all Missile-objects for this GameState
	 */
	public abstract ArrayList<Projectile> getListMissile();
	
	// *************** HEALTHPICKUP *************** 
	/*
	 * adds an object of type SpaceObject to the ArrayList holding all HealthPickup-objects for this GameState
	 */
	public abstract void addHealthPickup(SpaceObject o);
	
	/*
	 * removes an object of type SpaceObject from the ArrayList holding all HealthPickup-objects for this GameState
	 */
	public abstract void removeHealthPickup(SpaceObject o);
	
	/*
	 * returns an ArrayList holding all HealthPickup-objects for this GameState
	 */
	public abstract ArrayList<SpaceObject> getListHealthPickup();
	
	// *************** ENERGYPICKUP *************** 
	/*
	 * adds an object of type SpaceObject to the ArrayList holding all EnergyPickup-objects for this GameState
	 */
	public abstract void addEnergyPickup(SpaceObject o);
	
	/*
	 * removes an object of type SpaceObject from the ArrayList holding all EnergyPickup-objects for this GameState
	 */
	public abstract void removeEnergyPickup(SpaceObject o);
	
	/*
	 * returns an ArrayList holding all EnergyPickup-objects for this GameState
	 */
	public abstract ArrayList<SpaceObject> getListEnergyPickup();
	
	// *************** MISSILEPICKUP *************** 
	/*
	 * adds an object of type SpaceObject to the ArrayList holding all MissilePickup-objects for this GameState
	 */
	public abstract void addMissilePickup(SpaceObject o);
	
	/*
	 * removes an object of type SpaceObject from the ArrayList holding all MissilePickup-objects for this GameState
	 */
	public abstract void removeMissilePickup(SpaceObject o);
	
	/*
	 * returns an ArrayList holding all MissilePickup-objects for this GameState
	 */
	public abstract ArrayList<SpaceObject> getListMissilePickup();
	
	
	// GameState type
	protected GameStateType type;
	
	/*
	 * returns the GameStateType of the GameState
	 */
	public GameStateType getType()
	{
		return this.type;
	}
	
	/*
	 * returns the GameStateManager of the GameState
	 */
	public GameStateManager getGSM()
	{
		return this.gsm;
	}

}
