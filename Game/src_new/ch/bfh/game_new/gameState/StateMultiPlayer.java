package ch.bfh.game_new.gameState;

import java.util.ArrayList;

import ch.bfh.game_new.entity.SpaceObject;
import ch.bfh.game_new.main.GamePanel;
import ch.bfh.game_new.pickups.EnergyPickup;
import ch.bfh.game_new.pickups.HealthPickup;
import ch.bfh.game_new.pickups.MissilePickup;
import ch.bfh.game_new.spaceShip.EnemyShip;
import ch.bfh.game_new.spaceShip.Player;
import ch.bfh.game_new.spaceShip.SpaceShip;
import ch.bfh.game_new.spaceShipModule.Projectile;
import ch.bfh.game_new.tileMap.TileMap;

public class StateMultiPlayer extends GameState{
	
	// GameState type
	private final static GameStateType gameStateType = GameStateType.MULTIPLAYER;
	
	// map
	private TileMap tileMap;
	
	// SpaceObjects
	private Player player1;
	private Player player2;
	
	// constructor
	public StateMultiPlayer(GameStateManager gsm)
	{
		this.gsm = gsm;
		this.listPlayer = new ArrayList<SpaceShip>();
		this.listEnemy = new ArrayList<SpaceShip>();
		this.listPhaser = new ArrayList<Projectile>();
		this.listPhaserBig = new ArrayList<Projectile>();
		this.listMissile = new ArrayList<Projectile>();
		this.listHealthPickup = new ArrayList<SpaceObject>();
		this.listEnergyPickup = new ArrayList<SpaceObject>();
		this.listMissilePickup = new ArrayList<SpaceObject>();
	}
	
	/*
	 * Inits the State
	 * @see gameState.GameState#init()
	 */
	public void init()
	{
		// tileMap stuff
		this.tileMap = new TileMap(32);
		this.tileMap.loadTiles("/02_Textures/01_Map/Tileset_01.gif");
		this.tileMap.loadMap("/03_Data/01_Map/multiplayer_01.txt");
		
		// add Player
		player1 = new Player(tileMap, this);
		player1.setPosition(100, 200);
		
		player2 = new Player(tileMap, this);
		player2.setPosition(1000, 200);
		
		
		// add pickups
		HealthPickup pickupHP = new HealthPickup(this.tileMap, this, 600, 600, 25);
		EnergyPickup pickupEN = new EnergyPickup(this.tileMap, this, 300, 300, 40);
		MissilePickup pickupMI = new MissilePickup(this.tileMap, this, 1000, 500, 20);
	}
	
	// getters and setters
	public TileMap getTileMap(){return this.tileMap;}

	public GameStateManager getGSM(){return this.gsm;}
		
	public Player getPlayer1(){return this.player1;}
	public Player getPlayer2(){return this.player2;}
	
	@Override
	public GameStateType getType()
	{
		return StateMultiPlayer.gameStateType;
	}	
	
	/*
	 * @see gameState.GameState#update()
	 */
	@Override
	public void update() 
	{
		
		// update all ArrayLists holding SpaceObjects
		for(int i = 0; i < listPlayer.size(); i++){listPlayer.get(i).update();}
		for(int i = 0; i < listPhaser.size(); i++){listPhaser.get(i).update();}
		for(int i = 0; i < listPhaserBig.size(); i++){listPhaserBig.get(i).update();}
		for(int i = 0; i < listMissile.size(); i++){listMissile.get(i).update();}
		for(int i = 0; i < listHealthPickup.size(); i++){listHealthPickup.get(i).update();}
		for(int i = 0; i < listEnergyPickup.size(); i++){listEnergyPickup.get(i).update();}
		for(int i = 0; i < listMissilePickup.size(); i++){listMissilePickup.get(i).update();}
		
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
	}


	/*
	 * @see gameState.GameState#removeEnemy(spaceShip.SpaceShip)
	 */
	@Override
	public void removeEnemy(SpaceShip s) {
		this.listEnemy.remove(s);
	}

	/*
	 * @see gameState.GameState#getListEnemy()
	 */
	@Override
	public ArrayList<SpaceShip> getListEnemy() {
		return this.listEnemy;
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
}
