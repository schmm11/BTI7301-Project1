package ch.bfh.game_new.gameState;

import java.util.ArrayList;

import ch.bfh.game_new.entity.SpaceObject;
import ch.bfh.game_new.main.GamePanel;
import ch.bfh.game_new.pickups.EnergyPickup;
import ch.bfh.game_new.pickups.HealthPickup;
import ch.bfh.game_new.pickups.MissilePickup;
import ch.bfh.game_new.pickups.MissileUpgrade;
import ch.bfh.game_new.pickups.PhaserUpgrade;
import ch.bfh.game_new.spaceShip.EnemyShip;
import ch.bfh.game_new.spaceShip.Player;
import ch.bfh.game_new.spaceShip.SpaceShip;
import ch.bfh.game_new.spaceShipModule.Projectile;
import ch.bfh.game_new.spaceTurret.EnemyTurret;
import ch.bfh.game_new.spaceTurret.SpaceTurret;
import ch.bfh.game_new.tileMap.TileMap;

public class StateSinglePlayer extends GameState {

	// GameState type
	private final static GameStateType gameStateType = GameStateType.SINGLEPLAYER;
	
	// map
	private TileMap tileMap;
	
	// SpaceObjects
	private Player player;

	// timer
	private long timeStart;
	private int timeElapsed;
	private final static int TIMEMAX = 120;
	
	// boolean to check, if all objects have been destroyed
	private boolean allShipsDestroyed;
	private boolean allTurretsDestroyed;
	
	// constructor
	public StateSinglePlayer(GameStateManager gsm)
	{
		this.gsm = gsm;
		this.listPlayer = new ArrayList<SpaceShip>();
		this.listEnemy = new ArrayList<SpaceShip>();
		this.listTurret = new ArrayList<SpaceTurret>();
		this.listPhaser = new ArrayList<Projectile>();
		this.listPhaserBig = new ArrayList<Projectile>();
		this.listMissile = new ArrayList<Projectile>();
		this.listLaser = new ArrayList<Projectile>();
		this.listHealthPickup = new ArrayList<SpaceObject>();
		this.listEnergyPickup = new ArrayList<SpaceObject>();
		this.listMissilePickup = new ArrayList<SpaceObject>();
		this.listPhaserUpgrade = new ArrayList<SpaceObject>();
		this.listMissileUpgrade = new ArrayList<SpaceObject>();
	}
	
	/*
	 * Inits the State
	 * @see gameState.GameState#init()
	 */
	public void init()
	{		
		// clear all lists
		this.listPlayer.clear();;
		this.listEnemy.clear();
		this.listTurret.clear(); 
		this.listPhaser.clear();
		this.listPhaserBig.clear();
		this.listMissile.clear();
		this.listLaser.clear();
		this.listHealthPickup.clear(); 
		this.listEnergyPickup.clear(); 
		this.listMissilePickup.clear(); 
		this.listPhaserUpgrade.clear(); 
		this.listMissileUpgrade.clear();
		
		// tileMap stuff
		this.tileMap = new TileMap(32);
		this.tileMap.loadTiles("/02_Textures/01_Map/Tileset_02.gif");
		this.tileMap.loadMap("/03_Data/01_Map/map4.txt");
		
		// add Player
		player = new Player(tileMap, this);
		player.setPosition(544, 416);
		System.out.println("player destroyed: " + player.getDestroyed());
		
		// add Health-pickups
		new HealthPickup(this.tileMap, this, 416, 352, 25);
		new HealthPickup(this.tileMap, this, 544, 352, 25);
		new HealthPickup(this.tileMap, this, 672, 352, 25);
		new HealthPickup(this.tileMap, this, 2400, 320, 25);
		new HealthPickup(this.tileMap, this, 2528, 2464, 25);
		
		// add Energy-pickups
		new EnergyPickup(this.tileMap, this, 416, 480, 40);
		new EnergyPickup(this.tileMap, this, 544, 480, 40);
		new EnergyPickup(this.tileMap, this, 2400, 448, 40);
		
		// add Missile-pickups
		new MissilePickup(this.tileMap, this, 672, 480, 20);
		new MissilePickup(this.tileMap, this, 2528, 2592, 20);
		
		// add upgrades
		new PhaserUpgrade(this.tileMap, this, 3360, 704);
		new MissileUpgrade(this.tileMap, this, 1920, 1600);
		
		// add enemy
		EnemyShip s1 = new EnemyShip(tileMap, this);
		s1.setPosition(2848, 1088);
		
		EnemyShip s2 = new EnemyShip(tileMap, this);
		s2.setPosition(1024, 1088);
		
		EnemyShip s3 = new EnemyShip(tileMap, this);
		s3.setPosition(1248, 2432);
		
		EnemyShip s4 = new EnemyShip(tileMap, this);
		s4.setPosition(2272, 2112);
		
		EnemyShip s5 = new EnemyShip(tileMap, this);
		s5.setPosition(3360, 2240);
		
		EnemyShip s6 = new EnemyShip(tileMap, this);
		s6.setPosition(3360, 960);
		
		// add turrets
		EnemyTurret t1 = new EnemyTurret(this.tileMap, this);
		t1.setPosition(1600, 480);
		
		EnemyTurret t2 = new EnemyTurret(this.tileMap, this);
		t2.setPosition(2016, 480);
		
		EnemyTurret t3 = new EnemyTurret(this.tileMap, this);
		t3.setPosition(1600, 2656);
		
		EnemyTurret t4 = new EnemyTurret(this.tileMap, this);
		t4.setPosition(2016, 2656);
		
		EnemyTurret t5 = new EnemyTurret(this.tileMap, this);
		t5.setPosition(1088, 1408);
		
		EnemyTurret t6 = new EnemyTurret(this.tileMap, this);
		t6.setPosition(1088, 1792);
		
		EnemyTurret t7 = new EnemyTurret(this.tileMap, this);
		t7.setPosition(2144, 1600);
		
		// timer
		this.timeStart = System.nanoTime();
	}
	
	// getters and setters
	public TileMap getTileMap(){return this.tileMap;}

	public GameStateManager getGSM(){return this.gsm;}
	
	public Player getPlayer(){return this.player;}

	public int getTimeMax(){return StateSinglePlayer.TIMEMAX;}
	
	/*
	 * returns true, if all objects (ships and turrets) have been destroyed
	 */
	public boolean allDestroyed()
	{
		return (allShipsDestroyed && allTurretsDestroyed);
	}
	
	/*
	 * @see gameState.GameState#getType()
	 */
	@Override
	public GameStateType getType()
	{
		return StateSinglePlayer.gameStateType;
	}
	
	/*
	 * @see gameState.GameState#update()
	 */
	@Override
	public void update() 
	{
		
		// update all ArrayLists holding SpaceObjects
		for(int i = 0; i < listPlayer.size(); i++){listPlayer.get(i).update();}
		for(int i = 0; i < listEnemy.size(); i++){listEnemy.get(i).update();}
		for(int i = 0; i < listTurret.size(); i++){listTurret.get(i).update();}
		for(int i = 0; i < listPhaser.size(); i++){listPhaser.get(i).update();}
		for(int i = 0; i < listPhaserBig.size(); i++){listPhaserBig.get(i).update();}
		for(int i = 0; i < listMissile.size(); i++){listMissile.get(i).update();}
		for(int i = 0; i < listLaser.size(); i++){listLaser.get(i).update();}
		for(int i = 0; i < listHealthPickup.size(); i++){listHealthPickup.get(i).update();}
		for(int i = 0; i < listEnergyPickup.size(); i++){listEnergyPickup.get(i).update();}
		for(int i = 0; i < listMissilePickup.size(); i++){listMissilePickup.get(i).update();}
		for(int i = 0; i < listPhaserUpgrade.size(); i++){listPhaserUpgrade.get(i).update();}
		for(int i = 0; i < listMissileUpgrade.size(); i++){listMissileUpgrade.get(i).update();}
		
		// set tileMap position
		if(this.player != null)
		{
			tileMap.setPosition(
				GamePanel.WIDTH / 2 - player.getx(), 
				GamePanel.HEIGHT / 2 - player.gety()
				);
		}
		
		// update in-game-timer
		this.timeElapsed = (int) ((System.nanoTime() - this.timeStart) / 1000000000);
		
		// check player got destroyed
		if(this.player != null)
		{
			if(this.player.getDestroyed())
			{
				this.gsm.setState(GameStateManager.SINGLEPLAYER_SCORE);
			}
			if(this.timeElapsed > this.TIMEMAX)
			{
				this.gsm.setState(GameStateManager.SINGLEPLAYER_SCORE);
			}
		}
		
		// check if all enemies have been destroyed
		if(allDestroyed())
		{
			this.gsm.setState(GameStateManager.SINGLEPLAYER_SCORE);
		}

	}
	
	/*
	 * returns an integer for the time elapsed
	 */
	public int getTimer()
	{
		return this.timeElapsed;
	}

	/*
	 * @see gameState.GameState#keyPressed(int)
	 */
	@Override
	public void keyPressed(int k) 
	{
		gsm.getInputHandler().handlePressed(k, this);
	}

	/*
	 * @see gameState.GameState#keyReleased(int)
	 */
	@Override
	public void keyReleased(int k) 
	{
		gsm.getInputHandler().handleReleased(k, this);
	}

	/*
	 * @see gameState.GameState#addPlayer(spaceShip.SpaceShip)
	 */
	@Override
	public void addPlayer(SpaceShip s) {
		this.listPlayer.add(s);
	}

	/*
	 * (non-Javadoc)
	 * @see gameState.GameState#removePlayer(spaceShip.SpaceShip)
	 */
	@Override
	public void removePlayer(SpaceShip s) {
		this.listPlayer.remove(s);
	}

	/*
	 * @see gameState.GameState#getListPlayer()
	 */
	@Override
	public ArrayList<SpaceShip> getListPlayer() {
		return this.listPlayer;
	}
	
	/*
	 * @see gameState.GameState#addEnemy(spaceShip.SpaceShip)
	 */
	@Override
	public void addEnemy(SpaceShip s) {
		this.listEnemy.add(s);
		this.allShipsDestroyed = false;
	}

	/*
	 * @see gameState.GameState#removeEnemy(spaceShip.SpaceShip)
	 */
	@Override
	public void removeEnemy(SpaceShip s) {
		this.listEnemy.remove(s);
		if(listEnemy.size() == 0)
		{
			this.allShipsDestroyed = true;
		}
		if(this.listTurret.size() == 0)
		{
			this.allTurretsDestroyed = true;
		}
	}

	/*
	 * @see gameState.GameState#getListEnemy()
	 */
	@Override
	public ArrayList<SpaceShip> getListEnemy() {
		return this.listEnemy;
	}

	/*
	 * @see gameState.GameState#addTurret(spaceTurret.SpaceTurret)
	 */
	@Override
	public void addTurret(SpaceTurret t) {
		this.listTurret.add(t);
		this.allTurretsDestroyed = false;
	}

	/*
	 * @see gameState.GameState#removeTurret(spaceTurret.SpaceTurret)
	 */
	@Override
	public void removeTurret(SpaceTurret t) {
		this.listTurret.remove(t);
		if(this.listTurret.size() == 0)
		{
			this.allTurretsDestroyed = true;
		}
		if(listEnemy.size() == 0)
		{
			this.allShipsDestroyed = true;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see gameState.GameState#getListTurret()
	 */
	@Override
	public ArrayList<SpaceTurret> getListTurret() {
		return this.listTurret;
	}
	
	/*
	 * @see gameState.GameState#addPhaser(spaceShipModule.Projectile)
	 */
	@Override
	public void addPhaser(Projectile p) {
		this.listPhaser.add(p);
	}

	/*
	 * @see gameState.GameState#removePhaser(spaceShipModule.Projectile)
	 */
	@Override
	public void removePhaser(Projectile p) {
		this.listPhaser.remove(p);
	}

	/*
	 * @see gameState.GameState#getListPhaser()
	 */
	@Override
	public ArrayList<Projectile> getListPhaser() {
		return this.listPhaser;
	}

	/*
	 * @see gameState.GameState#addPhaserBig(spaceShipModule.Projectile)
	 */
	@Override
	public void addPhaserBig(Projectile p) {
		this.listPhaserBig.add(p);
	}

	/*
	 * @see gameState.GameState#removePhaserBig(spaceShipModule.Projectile)
	 */
	@Override
	public void removePhaserBig(Projectile p) {
		this.listPhaserBig.remove(p);
	}

	/*
	 * @see gameState.GameState#getListPhaserBig()
	 */
	@Override
	public ArrayList<Projectile> getListPhaserBig() {
		return this.listPhaserBig;
	}

	/*
	 * @see gameState.GameState#addMissile(spaceShipModule.Projectile)
	 */
	@Override
	public void addMissile(Projectile p) {
		this.listMissile.add(p);
	}

	/*
	 * @see gameState.GameState#removeMissile(spaceShipModule.Projectile)
	 */
	@Override
	public void removeMissile(Projectile p) {
		this.listMissile.remove(p);
	}

	/*
	 * @see gameState.GameState#getListMissile()
	 */
	@Override
	public ArrayList<Projectile> getListMissile() {
		return this.listMissile;
	}
	
	/*
	 * (non-Javadoc)
	 * @see gameState.GameState#addLaser(spaceShipModule.Projectile)
	 */
	@Override
	public void addLaser(Projectile p) {
		this.listLaser.add(p);
	}

	/*
	 * @see gameState.GameState#removeLaser(spaceShipModule.Projectile)
	 */
	@Override
	public void removeLaser(Projectile p) {
		this.listLaser.remove(p);
	}

	/*
	 * @see gameState.GameState#getListLaser()
	 */
	@Override
	public ArrayList<Projectile> getListLaser() {
		return this.listLaser;
	}

	/*
	 * @see gameState.GameState#addHealthPickup(entity.SpaceObject)
	 */
	@Override
	public void addHealthPickup(SpaceObject o) {
		this.listHealthPickup.add(o);
	}

	/*
	 * @see gameState.GameState#removeHealthPickup(entity.SpaceObject)
	 */
	@Override
	public void removeHealthPickup(SpaceObject o) {
		this.listHealthPickup.remove(o);
	}

	/*
	 * @see gameState.GameState#getListHealthPickup()
	 */
	@Override
	public ArrayList<SpaceObject> getListHealthPickup() {
		return this.listHealthPickup;
	}

	/*
	 * @see gameState.GameState#addEnergyPickup(entity.SpaceObject)
	 */
	@Override
	public void addEnergyPickup(SpaceObject o) {
		this.listEnergyPickup.add(o);
	}

	/*
	 * @see gameState.GameState#removeEnergyPickup(entity.SpaceObject)
	 */
	@Override
	public void removeEnergyPickup(SpaceObject o) {
		this.listEnergyPickup.remove(o);
	}

	/*
	 * @see gameState.GameState#getListEnergyPickup()
	 */
	@Override
	public ArrayList<SpaceObject> getListEnergyPickup() {
		return this.listEnergyPickup;
	}

	/*
	 * @see gameState.GameState#addMissilePickup(entity.SpaceObject)
	 */
	@Override
	public void addMissilePickup(SpaceObject o) {
		this.listMissilePickup.add(o);
	}

	/*
	 * @see gameState.GameState#removeMissilePickup(entity.SpaceObject)
	 */
	@Override
	public void removeMissilePickup(SpaceObject o) {
		this.listMissilePickup.remove(o);
	}

	/*
	 * @see gameState.GameState#getListMissilePickup()
	 */
	@Override
	public ArrayList<SpaceObject> getListMissilePickup() {
		return this.listMissilePickup;
	}

	/*
	 * (non-Javadoc)
	 * @see gameState.GameState#addPhaserUpgrade(entity.SpaceObject)
	 */
	@Override
	public void addPhaserUpgrade(SpaceObject o) {
		this.listPhaserUpgrade.add(o);
	}

	/*
	 * (non-Javadoc)
	 * @see gameState.GameState#removePhaserUpgrade(entity.SpaceObject)
	 */
	@Override
	public void removePhaserUpgrade(SpaceObject o) {
		this.listPhaserUpgrade.remove(o);
	}

	/*
	 * (non-Javadoc)
	 * @see gameState.GameState#getListPhaserUpgrades()
	 */
	@Override
	public ArrayList<SpaceObject> getListPhaserUpgrades() {
		return this.listPhaserUpgrade;
	}

	/*
	 * (non-Javadoc)
	 * @see gameState.GameState#addMissileUpgrade(entity.SpaceObject)
	 */
	@Override
	public void addMissileUpgrade(SpaceObject o) {
		this.listMissileUpgrade.add(o);
	}

	/*
	 * (non-Javadoc)
	 * @see gameState.GameState#removeMissileUpgrade(entity.SpaceObject)
	 */
	@Override
	public void removeMissileUpgrade(SpaceObject o) {
		this.listMissileUpgrade.remove(o);
	}

	/*
	 * (non-Javadoc)
	 * @see gameState.GameState#getListMissileUpgrades()
	 */
	@Override
	public ArrayList<SpaceObject> getListMissileUpgrades() {
		return this.listMissileUpgrade;
	}





}
