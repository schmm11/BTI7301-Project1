package ch.bfh.game_new.entity;

import ch.bfh.game_new.gameState.GameState;
import ch.bfh.game_new.tileMap.Tile;
import ch.bfh.game_new.tileMap.TileMap;

public class SpaceObjectMoving extends SpaceObject {

	// vector info
	protected double dx;
	protected double dy;
	

	
	// boolean value for movement
	protected boolean moving;
	
	// movement attributes
	protected double moveSpeed;
	protected double maxSpeed;
	
	// collision
	protected int currRow;
	protected int currCol;
	protected double xdest;
	protected double ydest;
	protected double xtemp;
	protected double ytemp;
	protected boolean topLeft;
	protected boolean topRight;
	protected boolean bottomLeft;
	protected boolean bottomRight;
	
	// constructor
	public SpaceObjectMoving(TileMap tm, GameState state)
	{
		super(tm, state);
	}
	
	/*
	 * calculates corners, checks if Tiles in corners are BLOCKED or NORMAL
	 */
	public void calculateCorners(double x, double y) {
		
		int leftTile = (int)(x - cwidth / 2) / tileSize;
		int rightTile = (int)(x + cwidth / 2 - 1) / tileSize;
		int topTile = (int)(y - cheight / 2) / tileSize;
		int bottomTile = (int)(y + cheight / 2 - 1) / tileSize;
				
		int tl = tileMap.getType(topTile, leftTile);
		int tr = tileMap.getType(topTile, rightTile);
		int bl = tileMap.getType(bottomTile, leftTile);
		int br = tileMap.getType(bottomTile, rightTile);
		
		topLeft = tl == Tile.BLOCKED;
		topRight = tr == Tile.BLOCKED;
		bottomLeft = bl == Tile.BLOCKED;
		bottomRight = br == Tile.BLOCKED;
	}
	
	/*
	 * checks if SpaceObject is colliding with BLOCKED Tile
	 */
	public void checkTileMapCollision()
	{
		currCol = (int) posX / tileSize;
		currRow = (int) posY / tileSize;
		
		xdest = posX + dx;
		ydest = posY + dy;
		
		xtemp = posX;
		ytemp = posY;
		
		// check vertical
		calculateCorners(posX, ydest);
		
		if(dy < 0)
		{
			if(topLeft || topRight)
			{
				dy = 0;
			}
			else
			{
				ytemp += dy;
			}
		}
		
		if(dy > 0)
		{
			if(bottomLeft || bottomRight)
			{
				dy = 0;
			}
			else
			{
				ytemp += dy;
			}
		}
		
		// check horizontal
		calculateCorners(xdest, posY);
		
		if(dx < 0)
		{
			if(topLeft || bottomLeft)
			{
				dx = 0;
			}
			else
			{
				xtemp += dx;
			}
		}
		
		if(dx > 0)
		{
			if(topRight || bottomRight)
			{
				dx = 0;
			}
			else
			{
				xtemp += dx;
			}
		}
	}
	
	// getters and setters
	/*
	 * set vector of the SpaceObject
	 */
	public void setVector(double dx, double dy) 
	{
		this.dx = dx;
		this.dy = dy;
	}
	
	/*
	 * sets the next Position for the SpaceObject
	 */
	public void getNextPosition()
	{

		this.moving = true;
		
		if(left)
		{
			angle = 270;
			dx -= moveSpeed;
			if(dx < - maxSpeed)
			{
				dx = -maxSpeed;
			}
		}
		
		else if(right)
		{
			angle = 90;
			dx += moveSpeed;
			if(dx > maxSpeed)
			{
				dx = maxSpeed;
			}
		}
		
		if(up)
		{
			angle = 0;
			dy -= moveSpeed;
			if(dy < -maxSpeed)
			{
				dy = -maxSpeed;
			}
		}
		else if(down)
		{
			angle = 180;
			dy += moveSpeed;
			if(dy > maxSpeed)
			{
				dy = maxSpeed;
			}
		}
		
		if(up && right){angle = 45;}
		
		if(down && right){angle = 135;}
		
		if(down && left){angle = 225;}
		
		if(up && left){angle = 315;}
		
		if(!up && !down && !left && !right)
		{
			this.moving = false;
			if(dx < -maxSpeed){dx = -maxSpeed;}
			if(dx > maxSpeed){dx = maxSpeed;}
			if(dy < - maxSpeed){dy = -maxSpeed;}
			if(dy > maxSpeed){dy = maxSpeed;}
		}
	}
	
	// speed attribues
	public void setMaxSpeed(double s){maxSpeed = s;}
	
	public void setMoveSpeed(double s){moveSpeed = s;}
	
	// get angle
	public int getAngle(){return this.angle;}
}
