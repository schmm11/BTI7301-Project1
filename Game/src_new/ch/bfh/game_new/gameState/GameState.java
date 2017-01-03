package ch.bfh.game_new.gameState;

import java.util.ArrayList;
import java.util.List;

import ch.bfh.game_new.entity.SpaceObject;
import ch.bfh.game_new.spaceShip.SpaceShip;
import ch.bfh.game_new.spaceShipModule.Projectile;
import ch.bfh.game_new.spaceTurret.SpaceTurret;

public abstract class GameState {

	protected GameStateManager gsm;

	// ArrayLists for all Space-Objects
	protected ArrayList<SpaceShip> listPlayer;
	protected ArrayList<SpaceShip> listEnemy;
	protected ArrayList<SpaceTurret> listTurret;
	protected ArrayList<Projectile> listPhaser;
	protected ArrayList<Projectile> listPhaserBig;
	protected ArrayList<Projectile> listMissile;
	protected ArrayList<Projectile> listLaser;
	protected ArrayList<SpaceObject> listHealthPickup;
	protected ArrayList<SpaceObject> listEnergyPickup;
	protected ArrayList<SpaceObject> listMissilePickup;
	protected ArrayList<SpaceObject> listPhaserUpgrade;
	protected ArrayList<SpaceObject> listMissileUpgrade;

	/*
	 * sets up the GameState
	 */
	public abstract void init();

	/*
	 * updates the GameState
	 * Calls the Update Method of every SpaceObject in the State
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

	// *************** TURRETS ***************
	/*
	 * adds an object of type SpaceTurret to the ArrayList holding all Turrets for this GameState
	 */
	public abstract void addTurret(SpaceTurret t);

	/*
	 * removes an object of type SpaceTurret from the ArrayList holding all Turrets for this GameState
	 */
	public abstract void removeTurret(SpaceTurret t);

	/*
	 * returns an ArrayList holding all Turrets for this GameState
	 */
	public abstract List<SpaceTurret> getListTurret();

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

	// *************** LASER ***************
	/*
	 * adds an object of type Projectile to the ArrayList holding all Missile-objects for this GameState
	 */
	public abstract void addLaser(Projectile p);

	/*
	 * removes an object of type Projectile from the ArrayList holding all Missile-objects for this GameState
	 */
	public abstract void removeLaser(Projectile p);

	/*
	 * returns an ArrayList holding all Missile-objects for this GameState
	 */
	public abstract List<Projectile> getListLaser();

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

	// *************** PHASERUPGRADE ***************
	/*
	 * adds an object of type SpaceObject to the ArrayList holding all Phaser-Upgrades for this GameState
	 */
	public abstract void addPhaserUpgrade(SpaceObject o);

	/*
	 * removes an object of type SpaceObject from the ArrayList holding all Phaser-Upgrades for this GameState
	 */
	public abstract void removePhaserUpgrade(SpaceObject o);

	/*
	 * returns an ArrayList holding all Phaser-Upgrades for this GameState
	 */
	public abstract List<SpaceObject> getListPhaserUpgrades();

	// *************** MISSILEUPGRADE ***************
	/*
	 * adds an object of type SpaceObject to the ArrayList holding all Missile-Upgrades for this GameState
	 */
	public abstract void addMissileUpgrade(SpaceObject o);

	/*
	 * removes an object of type SpaceObject from the ArrayList holding all Missile-Upgrades for this GameState
	 */
	public abstract void removeMissileUpgrade(SpaceObject o);

	/*
	 * returns an ArrayList holding all Missile-Upgrades for this GameState
	 */
	public abstract List<SpaceObject> getListMissileUpgrades();



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
