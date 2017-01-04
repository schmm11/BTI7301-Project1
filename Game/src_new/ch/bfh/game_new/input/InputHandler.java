package ch.bfh.game_new.input;

import java.awt.event.KeyEvent;

import ch.bfh.game_new.gameState.GameState;
import ch.bfh.game_new.gameState.GameStateType;
import ch.bfh.game_new.gameState.StateMenu;
import ch.bfh.game_new.gameState.StateScoreScreen;
import ch.bfh.game_new.gameState.StateMultiPlayer;
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
		
		// Every State ESC Key
		if(k == KeyEvent.VK_ESCAPE){
			state.getGSM().goToMenu();
		}
		
		
		
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

		// stateActual "MultiPlayer"
				if(state.getType() == GameStateType.MULTIPLAYER)
				{
					StateMultiPlayer multi = (StateMultiPlayer) state;

					Player player1 = multi.getPlayer1();
					Player player2 = multi.getPlayer2();

					// movement
					if(k == KeyEvent.VK_LEFT){player1.setLeft(true);}
					if(k == KeyEvent.VK_RIGHT){player1.setRight(true);}
					if(k == KeyEvent.VK_UP){player1.setUp(true);}
					if(k == KeyEvent.VK_DOWN){player1.setDown(true);}

					if(k == KeyEvent.VK_A){player2.setLeft(true);}
					if(k == KeyEvent.VK_D){player2.setRight(true);}
					if(k == KeyEvent.VK_W){player2.setUp(true);}
					if(k == KeyEvent.VK_S){player2.setDown(true);}

					// modules
					if(k == KeyEvent.VK_P){player1.activatePhaser();}
					if(k == KeyEvent.VK_I){player1.activatePhaserBig();}
					if(k == KeyEvent.VK_O){player1.activateBoost();}
					if(k == KeyEvent.VK_L){player1.activateMissile();}

					if(k == KeyEvent.VK_1){player2.activatePhaser();}
					if(k == KeyEvent.VK_2){player2.activatePhaserBig();}
					if(k == KeyEvent.VK_3){player2.activateBoost();}
					if(k == KeyEvent.VK_4){player2.activateMissile();}
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

			//Release Keys
			if(k == KeyEvent.VK_LEFT){player.setLeft(false);}
			if(k == KeyEvent.VK_RIGHT){player.setRight(false);}
			if(k == KeyEvent.VK_UP){player.setUp(false);}
			if(k == KeyEvent.VK_DOWN){player.setDown(false);}
		}

		if(state.getType() == GameStateType.MULTIPLAYER)
		{
			StateMultiPlayer multi = (StateMultiPlayer) state;

			Player player1 = multi.getPlayer1();
			Player player2 = multi.getPlayer2();

			// Release Keys
			if(k == KeyEvent.VK_LEFT){player1.setLeft(false);}
			if(k == KeyEvent.VK_RIGHT){player1.setRight(false);}
			if(k == KeyEvent.VK_UP){player1.setUp(false);}
			if(k == KeyEvent.VK_DOWN){player1.setDown(false);}

			if(k == KeyEvent.VK_A){player2.setLeft(false);}
			if(k == KeyEvent.VK_D){player2.setRight(false);}
			if(k == KeyEvent.VK_W){player2.setUp(false);}
			if(k == KeyEvent.VK_S){player2.setDown(false);}
		}


	}
}
