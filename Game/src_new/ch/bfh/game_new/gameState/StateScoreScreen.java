package ch.bfh.game_new.gameState;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import ch.bfh.game_new.entity.SpaceObject;
import ch.bfh.game_new.spaceShip.Player;
import ch.bfh.game_new.spaceShip.SpaceShip;
import ch.bfh.game_new.spaceShipModule.Projectile;
import ch.bfh.game_new.spaceTurret.SpaceTurret;

public class StateScoreScreen extends GameState {

	// GameState type
	private final static GameStateType gameStateType = GameStateType.SCORESCREEN;
	
	// menu options
	private int currentChoice;
	private String[] options = {"Main Menu", "Play Again", "Exit Game"};
	
	// Design stuff
	private Color titleColor;
	private Color colorDeath;
	private Color colorBronze;
	private Color colorSilver;
	private Color colorGold;
	private Font titleFont;
	private Font regularFont;
	
	// score info
	private boolean shipDestroyed;
	private int timeElapsed;
	private int timeMax;
	
	// constructor
	public StateScoreScreen(GameStateManager gsm)
	{
		this.gsm = gsm;
		
		// design
		this.titleColor = new Color(0, 150, 150);
		this.colorDeath = new Color(56, 56, 56);
		this.colorBronze = new Color(80, 50, 20);
		this.colorSilver = new Color(204, 204, 204);
		this.colorGold = new Color(255, 215, 0);
		
		this.titleFont = new Font("Century Gothic", Font.BOLD, 36);
		this.regularFont = new Font("Arial", Font.BOLD, 16);
	}
	
	/*
	 * (non-Javadoc)
	 * @see gameState.GameState#init()
	 */
	public void init()
	{
		StateSinglePlayer single = (StateSinglePlayer) this.gsm.getState(GameStateManager.SINGLEPLAYER_LV01);
		this.timeElapsed = single.getTimer();
		this.timeMax = single.getTimeMax();
		
		Player player = single.getPlayer();
		if(player.getDestroyed()){this.shipDestroyed = true;}
	}

	/*
	 * executes the current choice in the menu
	 */
	public void select()
	{
		if(currentChoice == 0)
		{
			// Main Menu
			gsm.setState(GameStateManager.MENU);
		}
		if(currentChoice == 1)
		{
			// Play Again
			gsm.setState(GameStateManager.SINGLEPLAYER_LV01);
		}
		
		if(currentChoice == 2)
		{
			// Exit Game
			System.exit(0);
		}
	}
	
	// getters and setters
	
	public Color getTitleColor(){return this.titleColor;}
	
	public Font getTitleFont(){return this.titleFont;}
	
	public Font getRegularFont(){return this.regularFont;}
	
	public Color getDeathColor(){return this.colorDeath;}
	
	public Color getBronzeColor(){return this.colorBronze;}
	
	public Color getSilverColor(){return this.colorSilver;}
	
	public Color getGoldColor(){return this.colorGold;}
	
	public void setCurrentChoice(int choice){this.currentChoice = choice;}
	
	public int getCurrentChoice(){return this.currentChoice;}
	
	public String[] getOptions(){return this.options;}
	
	public int getOptionsLength(){return options.length;}
	
	public int getTimeElapsed(){return this.timeElapsed;}
	
	public int getTimeMax(){return this.timeMax;}
	
	public boolean getShipDestroyed(){return this.shipDestroyed;}
	
	@Override
	public GameStateType getType(){return StateScoreScreen.gameStateType;}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(int k) 
	{
		gsm.getInputHandler().handlePressed(k, this);
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
	public ArrayList<SpaceTurret> getListTurret() {
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
	public ArrayList<Projectile> getListLaser() {
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
}
