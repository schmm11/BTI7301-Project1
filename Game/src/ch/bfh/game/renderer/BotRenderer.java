package ch.bfh.game.renderer;

import java.awt.event.KeyEvent;

import ch.bfh.game.modell.Player;

public final class BotRenderer {
	
	/*
	 * Calculate the Bots movement 
	 * @param:	Player bot: the Bot which should move
	 * @param:	Player enemy: The enemy (e.g. the real) player. Needed for AI
	 */
	public static void calculateBotMovement(Player bot, Player enemy){
		
		
		double random = Math.random()*10;
		if(random < 0.5){
			botResetMovement(bot);
			bot.setLeft(true);
		}
		else if (random > 9.5){
			botResetMovement(bot);
			bot.setRight(true);
		}
		else if (random > 7.5 && random < 8){
			botResetMovement(bot);
			bot.setUp(true);
		}
		else if (random > 5.5 && random < 6){
			botResetMovement(bot);
			bot.setDown(true);
		}
		
		
		/*
		if(k == KeyEvent.VK_LEFT) player1.setLeft(false);
		if(k == KeyEvent.VK_RIGHT) player1.setRight(false);
		if(k == KeyEvent.VK_UP) player1.setUp(false);
		if(k == KeyEvent.VK_DOWN) player1.setDown(false);
		*/
		
	}
	
	/* Set all the "set Direction" Booleans of a Player to false
	 * 
	 */
	private static void botResetMovement(Player bot){
		bot.setDown(false);
		bot.setLeft(false);
		bot.setRight(false);
		bot.setUp(false);
	}
}
