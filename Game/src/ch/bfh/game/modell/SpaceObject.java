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
	
	public Rectangle getRectangle() {
		return new Rectangle(
				(int)posX - cwidth,
				(int)posY - cheight,
				cwidth,
				cheight
		);
	}
	
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
	
	public void setPosition(double x, double y) {
		this.posX = x;
		this.posY = y;
	}
	public void setVector(double dx, double dy) {
		this.dx = dx;
		this.dy = dy;
	}
	
	public void setMapPosition() {
		xmap = tileMap.getX();
		ymap = tileMap.getY();
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
}
