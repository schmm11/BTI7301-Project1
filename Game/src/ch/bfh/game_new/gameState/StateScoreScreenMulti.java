package ch.bfh.game_new.gameState;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import ch.bfh.game_new.entity.SpaceObject;
import ch.bfh.game_new.spaceShip.Player;
import ch.bfh.game_new.spaceShip.SpaceShip;
import ch.bfh.game_new.spaceShipModule.Projectile;
import ch.bfh.game_new.spaceTurret.SpaceTurret;

public class StateScoreScreenMulti extends GameState {

	// GameStateType
	private final static GameStateType gameStateType = GameStateType.SCORESCREENMULTI;

	// menu options
	private int currentChoice;
	private String[] options = {"Main Menu", "Play Again", "Exit Game"};

	// Design stuff
	private Color titleColor;
	private Color winnerColor;
	private Font titleFont;
	private Font regularFont;

	// score info
	private boolean player1Winner;
	private boolean player2Winner;

	// constructor
	public StateScoreScreenMulti(GameStateManager gsm)
	{
		this.gsm = gsm;

		// design stuff
		this.titleColor = new Color(0, 150, 150);
		this.winnerColor = new Color(255, 215, 0);
		this.titleFont = new Font("Century Gothic", Font.BOLD, 36);
		this.regularFont = new Font("Arial", Font.BOLD, 16);
	}

	/*
	 * (non-Javadoc)
	 * @see ch.bfh.game_new.gameState.GameState#init()
	 */
	public void init()
	{
		this.player1Winner = false;
		this.player2Winner = false;
		StateMultiPlayer multi = (StateMultiPlayer) this.gsm.getState(GameStateManager.MULTIPLAYER);

		Player player1 = multi.getPlayer1();
		Player player2 = multi.getPlayer2();

		if(player1.getDestroyed())
		{
			this.player2Winner = true;
		}
		else if(player2.getDestroyed())
		{
			this.player1Winner = true;
		}
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
			gsm.setState(GameStateManager.MULTIPLAYER);
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

	public Color getWinnerColor(){return this.winnerColor;}

	public void setCurrentChoice(int choice){this.currentChoice = choice;}

	public int getCurrentChoice(){return this.currentChoice;}

	public String[] getOptions(){return this.options;}

	public int getOptionsLength(){return options.length;}

	public boolean getPlayer1Winner(){return this.player1Winner;}

	public boolean getPlayer2Winner(){return this.player2Winner;}

	@Override
	public GameStateType getType(){return StateScoreScreenMulti.gameStateType;}

	@Override
	public void update()
	{
		// nothing
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














