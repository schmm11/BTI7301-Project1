package ch.bfh.game_new.gameState;

import java.awt.Graphics2D;
import java.util.ArrayList;

import ch.bfh.game_new.input.InputHandler;
import ch.bfh.game_new.painter.PaintComponent;

public class GameStateManager {

	// all states in ArrayList and currentState
	private ArrayList<GameState> gameStates;
	private int currentState;
	
	// Actual GameStates
	private StateMenu stateMenu;
	private StateSinglePlayer singlePlayer;
	private StateMultiPlayer multiPlayer;
	
	// user-Input
	private InputHandler handler;
	
	// drawing
	private PaintComponent painter;
	
	// different States
	public static final int MENU = 0;
	public static final int SINGLEPLAYER_LV01 = 1;
	public static final int MULTIPLAYER = 2;
	
	// constructor
	public GameStateManager()
	{
		gameStates = new ArrayList<GameState>();
		
		//create all the States
		this.stateMenu = new StateMenu(this);
		this.singlePlayer = new StateSinglePlayer(this);
		this.multiPlayer = new StateMultiPlayer(this);
		
		this.gameStates.add(stateMenu);
		this.gameStates.add(singlePlayer);
		this.gameStates.add(multiPlayer);
	
		this.currentState = MENU;
		this.handler = new InputHandler();
		
		this.painter = new PaintComponent();

	}
	
	// getters and setters
	/*
	 * changes the current GameState of the GameStateManager
	 */
	public void setState(int state)
	{
		this.currentState = state;
		gameStates.get(currentState).init();
	}
	
	/*
	 * return the current GameState
	 */
	public GameState getCurrentState()
	{
		return this.gameStates.get(currentState);
	}
	
	/*
	 * returns the InputHandler from the GameStateManager
	 */
	public InputHandler getInputHandler(){return this.handler;}
	
	/*
	 * returns the PaintComponent from the GameStateManger
	 */
	public PaintComponent getPainter(){return this.painter;}
	
	/*
	 * calls update-method of the current GameState
	 */
	public void update()
	{
		gameStates.get(currentState).update();
	}
	
	/*
	 * calls draw-method for the current GameState
	 */
	public void draw(Graphics2D g)
	{
		if(currentState == MENU)
		{
			painter.drawMenu(g, this.stateMenu);
		}
		else if(currentState == SINGLEPLAYER_LV01)
		{
			painter.drawSinglePlayer(g, this.singlePlayer);
		}
		else if(currentState == MULTIPLAYER)
		{
			painter.drawMultiPlayer(g, this.multiPlayer);
		}
	}
	
	// User-Input handling
	/*
	 * calls keyPressed-method of the current GameState
	 */
	public void keyPressed(int k)
	{
		gameStates.get(currentState).keyPressed(k);
	}
	
	/*
	 * calls keyReleased-method of the current GameState
	 */
	public void keyReleased(int k)
	{
		gameStates.get(currentState).keyReleased(k);
	}
}
