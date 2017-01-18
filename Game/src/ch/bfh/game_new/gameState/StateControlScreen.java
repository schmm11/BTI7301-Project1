package ch.bfh.game_new.gameState;

import java.util.ArrayList;
import java.util.List;

import ch.bfh.game_new.entity.SpaceObject;
import ch.bfh.game_new.spaceShip.SpaceShip;
import ch.bfh.game_new.spaceShipModule.Projectile;
import ch.bfh.game_new.spaceTurret.SpaceTurret;

public class StateControlScreen extends GameState{
	
	//Constructor
	public StateControlScreen(GameStateManager gsm)
	{
		this.gsm = gsm;
		init();
	}
	
	
	/*
	 * This STATE doesn't need an INIT
	 * @see ch.bfh.game_new.gameState.GameState#init()
	 */
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}
	
	/*
	 * Needed for the ESC => Back to Menu
	 * @see ch.bfh.game_new.gameState.GameState#keyPressed(int)
	 */
	@Override
	public void keyPressed(int k) {
		gsm.getInputHandler().handlePressed(k, this);
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void keyReleased(int k) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addPlayer(SpaceShip s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removePlayer(SpaceShip s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<SpaceShip> getListPlayer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addEnemy(SpaceShip s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeEnemy(SpaceShip s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<SpaceShip> getListEnemy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addTurret(SpaceTurret t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeTurret(SpaceTurret t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<SpaceTurret> getListTurret() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addPhaser(Projectile p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removePhaser(Projectile p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Projectile> getListPhaser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addPhaserBig(Projectile p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removePhaserBig(Projectile p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Projectile> getListPhaserBig() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addMissile(Projectile p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeMissile(Projectile p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Projectile> getListMissile() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addLaser(Projectile p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeLaser(Projectile p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Projectile> getListLaser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addHealthPickup(SpaceObject o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeHealthPickup(SpaceObject o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<SpaceObject> getListHealthPickup() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addEnergyPickup(SpaceObject o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeEnergyPickup(SpaceObject o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<SpaceObject> getListEnergyPickup() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addMissilePickup(SpaceObject o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeMissilePickup(SpaceObject o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<SpaceObject> getListMissilePickup() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addPhaserUpgrade(SpaceObject o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removePhaserUpgrade(SpaceObject o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<SpaceObject> getListPhaserUpgrades() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addMissileUpgrade(SpaceObject o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeMissileUpgrade(SpaceObject o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<SpaceObject> getListMissileUpgrades() {
		// TODO Auto-generated method stub
		return null;
	}

}
