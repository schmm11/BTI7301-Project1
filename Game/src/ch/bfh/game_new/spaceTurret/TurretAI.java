package ch.bfh.game_new.spaceTurret;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import ch.bfh.game_new.gameState.GameState;
import ch.bfh.game_new.spaceShip.Player;

public class TurretAI {

	// turret
	private EnemyTurret turret;
	private GameState state;
	
	// line of sight
	private boolean seeTarget;
	ArrayList<Rectangle> allRecs;
	private int tileSize;
	private int numberBlocked;
	
	// constructor
	public TurretAI(EnemyTurret turr, GameState state)
	{
		this.turret = turr;
		this.state = state;
		this.allRecs = new ArrayList<Rectangle>();
		
		this.tileSize = turr.getTileMap().getTileSize();
	}
	
	// getters and setters
	public ArrayList<Rectangle> getAllRecs(){return this.allRecs;}
	
	private void setAttack(boolean right, boolean left, boolean up, boolean down, Player player)
	{
		this.seeTarget = true;
		
		turret.setUp(false);
		turret.setDown(false);
		turret.setLeft(false);
		turret.setRight(false);
		
		this.allRecs.clear();
		this.numberBlocked = 0;
		
		turret.setRight(right);
		turret.setLeft(left);
		turret.setUp(up);
		turret.setDown(down);
		
		// creates line between Turret and Player
		this.turret.setLine(new Line2D.Double(turret.getx() + turret.getXmap(), turret.gety() + turret.getYmap(), player.getx() + player.getXmap(), player.gety() + player.getYmap()));
		
		// adds Rectangles for relevant Tiles
		
		// UPPER LEFT TILES
		for(int i = turret.getx(); i > player.getx(); i = i - tileSize)
		{
			for(int j = turret.gety(); j > player.gety(); j = j - tileSize)
			{
				allRecs.add(turret.getTileMap().getRectangle(i, j, turret.getXmap(), turret.getYmap()));
			}
		}
		
		// UPPER RIGHT TILES
		for(int i = turret.getx(); i < player.getx(); i = i + tileSize)
		{
			for(int j = turret.gety(); j > player.gety(); j = j - tileSize)
			{
				allRecs.add(turret.getTileMap().getRectangle(i, j, turret.getXmap(), turret.getYmap()));
			}
		}
		
		// LOWER RIGHT TILES
		for(int i = turret.getx(); i < player.getx(); i = i + tileSize)
		{
			for(int j = turret.gety(); j < player.gety(); j = j + tileSize)
			{
				allRecs.add(turret.getTileMap().getRectangle(i, j, turret.getXmap(), turret.getYmap()));
			}
		}
		
		// LOWER LEFT TILES
		for(int i = turret.getx(); i > player.getx(); i = i - tileSize)
		{
			for(int j = turret.gety(); j < player.gety(); j = j + tileSize)
			{
				allRecs.add(turret.getTileMap().getRectangle(i, j, turret.getXmap(), turret.getYmap()));
			}
		}
		
		// loop over Rectangles, check if type is BLOCKED (1) or NORMAL (0)
		for(int i = 0; i < allRecs.size(); i++)
		{
			int x = (allRecs.get(i).x - turret.getXmap()) / tileSize;
			int y = (allRecs.get(i).y - turret.getYmap()) / tileSize;
			
			int type = turret.getTileMap().getType(y, x);
			
			if(type == 1 && turret.getLine().intersects(allRecs.get(i)))
			{
				this.seeTarget = false;
			}
		}
		
		// fires Laser, if nothing blocks the line between Turret and Player
		if(this.seeTarget)
		{
			turret.activateLaser();	
		}
	}
	
	public void nextAction()
	{
		
		for(int i = 0; i < this.state.getListPlayer().size(); i++)
		{
			Player player = (Player) state.getListPlayer().get(i);
			
			if(turret.getUp().intersects(player.getRectangle()))
			{
				this.turret.setAngle(0);
				setAttack(false, false, true, false, player);
			}
			
			if(turret.getDown().intersects(player.getRectangle()))
			{
				this.turret.setAngle(180);
				setAttack(false, false, false, true, player);
			}
			
			if(turret.getLeft().intersects(player.getRectangle()))
			{
				this.turret.setAngle(270);
				setAttack(false, true, false, false, player);
			}
			
			if(turret.getRight().intersects(player.getRectangle()))
			{
				this.turret.setAngle(90);
				setAttack(true, false, false, false, player);
			}
			
			if(turret.getPolyDR().intersects(player.getRectangle()))
			{
				this.turret.setAngle(135);
				setAttack(true, false, false, true, player);
			}
			
			if(turret.getPolyDL().intersects(player.getRectangle()))
			{
				this.turret.setAngle(225);
				setAttack(false, true, false, true, player);
			}
			
			if(turret.getPolyUR().intersects(player.getRectangle()))
			{
				this.turret.setAngle(45);
				setAttack(true, false, true, false, player);
			}
			
			if(turret.getPolyUL().intersects(player.getRectangle()))
			{
				this.turret.setAngle(315);
				setAttack(false, true, true, false, player);
			}
		}
	}
}
