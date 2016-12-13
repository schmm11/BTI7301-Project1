package ch.bfh.game.modell;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import ch.bfh.game.main.GamePanel;
import ch.bfh.game.tileMap.Tile;
import ch.bfh.game.tileMap.TileMap;

public class SpaceObject {
	// tile stuff
	protected TileMap tileMap;
	protected int tileSize;
	protected double xmap;	// Width of the Map??
	protected double ymap;
	

	
	// position and vector
	protected double posX;
	protected double posY;
	protected double dx;
	protected double dy;
	
	// dimensions
	protected int width;
	protected int height;
	
	// collision box
	protected int cwidth;
	protected int cheight;
	
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
	
	// animation
	protected Animation animation;
	protected int currentAction;
	protected int previousAction;
	
	
	
	// movement attributes
	protected double moveSpeed;
	protected double maxSpeed;
	protected double stopSpeed;
	protected double fallSpeed;
	protected double maxFallSpeed;
	protected double jumpStart;
	protected double stopJumpSpeed;
	
	
	// constructor
	public SpaceObject(TileMap tm) {
		tileMap = tm;
		tileSize = tm.getTileSize(); 
	}
	
	public boolean intersects(SpaceObject o) {
		Rectangle r1 = getRectangle();
		Rectangle r2 = o.getRectangle();
		return r1.intersects(r2);
	}
	
	/*
	 * @return a rectangle with the size of the SpaceObject
	 */
	public Rectangle getRectangle() {
		return new Rectangle(
				(int)posX - this.getCWidth(),
				(int)posY - this.getCHeight(),
				this.getCWidth(),
				this.getCHeight()
		);
	}
	
	/*
	 * Calculates the "corners" of the object. Used for CheckCollision 
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
	

	
	public int getx() { return (int)posX; }
	public int gety() { return (int)posY; }
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	public int getCWidth() { return cwidth; }
	public int getCHeight() { return cheight; }
	public double getXMap(){return xmap;}
	public double getYMap(){return ymap;}
	
	public void setPosition(double x, double y) {
		this.posX = x;
		this.posY = y;
	}
	public void setVector(double dx, double dy) {
		this.dx = dx;
		this.dy = dy;
	}
	
	public void setMapPosition() { // TODO: Was genau macht diese Funktion?
		xmap = tileMap.getX();
		ymap = tileMap.getY();
	}
    /*
    * Animation getter
    * @return Animation
    */
	public Animation getAnimation()
	{ 
		return animation;
	}
	

	
	public boolean notOnScreen() {
		return posX + xmap + width < 0 ||
			posX + xmap - width > GamePanel.WIDTH ||
			posY + ymap + height < 0 ||
			posY + ymap - height > GamePanel.HEIGHT;
	}
	
	public void update(){
		
	}
	public void draw(Graphics2D g){
		
	}
	
	/*
	 * Checks if theres a collision with the (tile)map
	 */
public void checkTileMapCollision() {
		
		currCol = (int)posX / tileSize;
		currRow = (int)posY / tileSize;
		
		xdest = posX + dx;
		ydest = posY + dy;
		
		xtemp = posX;
		ytemp = posY;
		
		// up and down
		calculateCorners(posX, ydest);
		
		if(dy < 0) {
			if(topLeft || topRight) {
				dy = 0;
//				ytemp = currRow * tileSize + cheight / 2;
			}
			else {
				ytemp += dy;
			}
		}
		if(dy > 0) {
			if(bottomLeft || bottomRight) {
				dy = 0;
				// falling = false;  //TODO: Why do we need the falling?
			}
			else {
				ytemp += dy;
			}
		}
		
		// left and right
		calculateCorners(xdest, posY);
		
		if(dx < 0) {
			if(topLeft || bottomLeft) {
				dx = 0;
//				xtemp = currCol * tileSize + cwidth / 2;
			}
			else {
				xtemp += dx;
			}
		}
		if(dx > 0) {
			if(topRight || bottomRight) {
				dx = 0;
//				xtemp = (currCol + 1) * tileSize - cwidth / 2;
			}
			else {
				xtemp += dx;
			}
		}
	}
	
}
