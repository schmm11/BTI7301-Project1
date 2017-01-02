package ch.bfh.game_new.spaceShip;

import java.awt.Rectangle;

import ch.bfh.game_new.entity.ObjectType;
import ch.bfh.game_new.entity.SpaceObject;
import ch.bfh.game_new.gameState.GameState;

public class EnemyAI {

	// ship & GameState
	private EnemyShip enemy;
	private GameState state;
	
	// ship surroundings
	private Rectangle recFront;
	
	// constructor
	public EnemyAI(EnemyShip enemy, GameState state)
	{
		this.enemy = enemy;
		this.state = state;
	}
	
	private void setAttack(boolean right, boolean left, boolean up, boolean down)
	{
		enemy.setUp(false);
		enemy.setDown(false);
		enemy.setLeft(false);
		enemy.setRight(false);
		
		enemy.setRight(right);
		enemy.setLeft(left);
		enemy.setUp(up);
		enemy.setDown(down);
		
		enemy.activateMissile();
		enemy.activatePhaser();
	}
	
	private void setBoost(boolean right, boolean left, boolean up, boolean down)
	{
		enemy.setUp(false);
		enemy.setDown(false);
		enemy.setLeft(false);
		enemy.setRight(false);
		
		enemy.setRight(right);
		enemy.setLeft(left);
		enemy.setUp(up);
		enemy.setDown(down);
		
		enemy.activateBoost();
	}
	
	public void nextAction()
	{
		for(int i = 0; i < this.state.getListPlayer().size(); i++)
		{
			Player player = (Player) state.getListPlayer().get(i);
				
			if(enemy.getUp().intersects(player.getRectangle()))
			{
				setAttack(false, false, true, false);
			}
			
			if(enemy.getDown().intersects(player.getRectangle()))
			{
				setAttack(false, false, false, true);
			}
			
			if(enemy.getLeft().intersects(player.getRectangle()))
			{
				setAttack(false, true, false, false);
			}
			
			if(enemy.getRight().intersects(player.getRectangle()))
			{
				setAttack(true, false, false, false);
			}
			
			if(enemy.getPolyDR().intersects(player.getRectangle()))
			{
				setAttack(true, false, false, true);
			}
			
			if(enemy.getPolyDL().intersects(player.getRectangle()))
			{
				setAttack(false, true, false, true);
			}
			
			if(enemy.getPolyUR().intersects(player.getRectangle()))
			{
				setAttack(true, false, true, false);
			}
			
			if(enemy.getPolyUL().intersects(player.getRectangle()))
			{
				setAttack(false, true, true, false);
			}
		}
	}
}
