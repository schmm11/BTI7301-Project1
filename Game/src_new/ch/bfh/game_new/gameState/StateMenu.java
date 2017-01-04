package ch.bfh.game_new.gameState;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import ch.bfh.game_new.entity.SpaceObject;
import ch.bfh.game_new.spaceShip.SpaceShip;
import ch.bfh.game_new.spaceShipModule.Projectile;
import ch.bfh.game_new.spaceTurret.SpaceTurret;

public class StateMenu extends GameState {

	// GameState type
	private final static GameStateType gameStateType = GameStateType.MENU;
	
	// menu options
	private int currentChoice;
	private String[] options = {"Single Player", "Multi Player", "Controls", "Exit Game"};
	
	// Design stuff
	private Color titleColor;
	private Font titleFont;
	private Font regularFont;
	
	// constructor
	public StateMenu(GameStateManager gsm)
	{
		this.gsm = gsm;
		
		// design
		this.titleColor = new Color(0, 150, 150);
		this.titleFont = new Font("Century Gothic", Font.BOLD, 36);
		this.regularFont = new Font("Arial", Font.BOLD, 16);
	}
	
	/*
	 * executes the current choice in the menu
	 */
	public void select()
	{
		if(currentChoice == 0)
		{
			// singleplayer
			gsm.setState(GameStateManager.SINGLEPLAYER_LV01);
		}
		if(currentChoice == 1)
		{
			// multiplayer
			gsm.setState(GameStateManager.MULTIPLAYER);
		}
		
		if(currentChoice == 2)
		{
			// TODO: Window with the Control Schema ?
			gsm.setState(GameStateManager.CONTROL);
		}
		
		if(currentChoice == 3)
		{
			// exit game
			System.exit(0);
		}
	}
	
	// getters and setters
	
	public Color getTitleColor(){return this.titleColor;}
	
	public Font getTitleFont(){return this.titleFont;}
	
	public Font getRegularFont(){return this.regularFont;}
	
	public void setCurrentChoice(int choice){this.currentChoice = choice;}
	
	public int getCurrentChoice(){return this.currentChoice;}
	
	public String[] getOptions(){return this.options;}
	
	public int getOptionsLength(){return options.length;}
	
	@Override
	public GameStateType getType(){return StateMenu.gameStateType;}
	
	@Override
	public void init(){} // no action required in StateMenu
	
	@Override
	public void update(){} // no action required in StateMenu
	
	// User-Input handling
	/*
	 * calls the method handlePressed from the InputHandler in the GameStateManager
	 */
	@Override
	public void keyPressed(int k)
	{
		gsm.getInputHandler().handlePressed(k, this);
	}
	
	/*
	 * does nothing
	 */
	@Override
	public void keyReleased(int k)
	{
		
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
	public ArrayList<SpaceObject> getListPhaserUpgrades() {
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
	public ArrayList<SpaceObject> getListMissileUpgrades() {
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
	public ArrayList<SpaceTurret> getListTurret() {
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
	public ArrayList<Projectile> getListLaser() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
