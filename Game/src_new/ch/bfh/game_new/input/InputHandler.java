package ch.bfh.game_new.input;

import java.awt.event.KeyEvent;

import ch.bfh.game_new.gameState.GameState;
import ch.bfh.game_new.gameState.GameStateType;
import ch.bfh.game_new.gameState.StateMenu;
import ch.bfh.game_new.gameState.StateScoreScreen;
import ch.bfh.game_new.gameState.StateSinglePlayer;
import ch.bfh.game_new.spaceShip.Player;

public class InputHandler {
	
	public InputHandler()
	{

	}
	
	/*
	 * handles user-input for pressed keys, depending on keycode and GameState
	 */
	public void handlePressed(int k, GameState state)
	{
		// stateActual "MENU"
		if(state.getType() == GameStateType.MENU)
		{
			StateMenu menu = (StateMenu) state;
			
			if(k == KeyEvent.VK_UP)
			{
				menu.setCurrentChoice(menu.getCurrentChoice() - 1);
				if(menu.getCurrentChoice() == -1)
				{
					menu.setCurrentChoice(menu.getOptionsLength() - 1);
				}
			}
			
			if(k == KeyEvent.VK_DOWN)
			{
				menu.setCurrentChoice(menu.getCurrentChoice() + 1);
				if(menu.getCurrentChoice() == menu.getOptionsLength())
				{
					menu.setCurrentChoice(0);
				}
			}
			
			if(k == KeyEvent.VK_ENTER)
			{
				menu.select();
			}
		}
		
		// stateActual "SCORE SCREEN"
		if(state.getType() == GameStateType.SCORESCREEN)
		{
			StateScoreScreen score = (StateScoreScreen) state;
			
			if(k == KeyEvent.VK_UP)
			{
				score.setCurrentChoice(score.getCurrentChoice() - 1);
				if(score.getCurrentChoice() == -1)
				{
					score.setCurrentChoice(score.getOptionsLength() - 1);
				}
			}
			
			if(k == KeyEvent.VK_DOWN)
			{
				score.setCurrentChoice(score.getCurrentChoice() + 1);
				if(score.getCurrentChoice() == score.getOptionsLength())
				{
					score.setCurrentChoice(0);
				}
			}
			
			if(k == KeyEvent.VK_ENTER)
			{
				score.select();
			}
		}
		
		// stateActual "SINGLEPLAYER"
		if(state.getType() == GameStateType.SINGLEPLAYER)
		{
			StateSinglePlayer single = (StateSinglePlayer) state;
			
			Player player = single.getPlayer();
			
			// movement
			if(k == KeyEvent.VK_LEFT){player.setLeft(true);}
			if(k == KeyEvent.VK_RIGHT){player.setRight(true);}
			if(k == KeyEvent.VK_UP){player.setUp(true);}
			if(k == KeyEvent.VK_DOWN){player.setDown(true);}
			
			// modules
			if(k == KeyEvent.VK_P){player.activatePhaser();}
			if(k == KeyEvent.VK_I){player.activatePhaserBig();}
			if(k == KeyEvent.VK_O){player.activateBoost();}
			if(k == KeyEvent.VK_L){player.activateMissile();}
		}
	}
	
	/*
	 * handles user-input for released keys, depending on keycode and GameState
	 */
	public void handleReleased(int k, GameState state)
	{
		if(state.getType() == GameStateType.SINGLEPLAYER)
		{
			StateSinglePlayer single = (StateSinglePlayer) state;
			
			Player player = single.getPlayer();
			
			if(k == KeyEvent.VK_LEFT){player.setLeft(false);}
			if(k == KeyEvent.VK_RIGHT){player.setRight(false);}
			if(k == KeyEvent.VK_UP){player.setUp(false);}
			if(k == KeyEvent.VK_DOWN){player.setDown(false);}
		}
	}
}
