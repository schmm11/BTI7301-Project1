package ch.bfh.game.gameState;

import java.util.ArrayList;

public class GameStateManager {

	private ArrayList<GameState> gameStates;
	private int currentState;
	
	public static final int MENUSTATE = 0;
	public static final int TWOPL1 = 1; //Two PLayer level 1
	public static final int TWOPL2 = 2; //1 vs Bot Mulitplayer
	
	// Constructor
	public GameStateManager()
	{
		gameStates = new ArrayList<GameState>();
		
		currentState = MENUSTATE;
		gameStates.add(new MenuState(this));
		gameStates.add(new TwoPlayerLevel1State(this));
		gameStates.add(new OnePlayerVsBotState(this));
	}
	
	public void setState(int state)
	{
		currentState = state;
		gameStates.get(currentState).init();
	}
	
	
	public void update()
	{
		gameStates.get(currentState).update();
	}
	
	public void draw(java.awt.Graphics2D g)
	{
		gameStates.get(currentState).draw(g);
	}
	
	
	// User Input handling
	public void keyPressed(int k)
	{
		gameStates.get(currentState).keyPressed(k);
	}
	
	public void keyReleased(int k)
	{
		gameStates.get(currentState).keyReleased(k);
	}
}
