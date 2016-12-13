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
		
		//Movement Random
		double random = Math.random()*50;
		if(random < 0.5){
			botResetMovement(bot);
			bot.setLeft(true);
		}
		else if (random > 9.5 && random <10){
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
