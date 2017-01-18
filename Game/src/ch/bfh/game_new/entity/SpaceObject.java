package ch.bfh.game_new.entity;

import java.awt.Rectangle;

import ch.bfh.game_new.gameState.GameState;
import ch.bfh.game_new.main.GamePanel;
import ch.bfh.game_new.tileMap.TileMap;

public class SpaceObject {

	// type
	protected ObjectType type;
	protected Team team;
	
	// GameState info
	protected GameState stateActual;
	
	// animation
	protected Animation animation;
	protected int currentAction;
	protected int previousAction;
	protected int angle;
	
	// tile stuff
	protected TileMap tileMap;
	protected int tileSize;
	protected double xmap;
	protected double ymap;
	
	// position
	protected double posX;
	protected double posY;
	
	// boolean values for direction
	protected boolean left;
	protected boolean right;
	protected boolean up;
	protected boolean down;
	
	// dimension
	protected int width;
	protected int height;
	
	// collision box
	protected int cwidth;
	protected int cheight;
	
	// constructor
	public SpaceObject(TileMap tm, GameState state)
	{
		this.tileMap = tm;
		this.tileSize = tm.getTileSize();
		this.stateActual = state;
	}
	
	/*
	 * returns true, if the collision box of this SpaceObjects
	 * intersects with the collision box of another SpaceObject
	 */
	public boolean intersects(SpaceObject o)
	{
		Rectangle r1 = getRectangle();
		Rectangle r2 = o.getRectangle();
		return r1.intersects(r2);
	}
	
	/*
	 * returns a Rectangle with the dimensions from collision box of the SpaceObject
	 */
	public Rectangle getRectangle()
	{
		return new Rectangle(
				(int) (posX + xmap - cwidth / 2), 
				(int) (posY + ymap - cheight / 2) , 
				cwidth, 
				cheight
				);
	}
	
	// getters and setters
	public int getx(){return (int) posX;}
	
	public int gety(){return (int) posY;}
	
	public int getXmap(){return (int) xmap;}
	
	public int getYmap(){return (int) ymap;}
	
	public int getWidth(){return width;}
	
	public int getHeight(){return height;}
	
	public int getAngle(){return angle;}
	
	public ObjectType getType(){return type;}
	
	public GameState getState(){return this.stateActual;}
	
	public Animation getAnimation(){return this.animation;}
	
	public Team getTeam(){return this.team;}
	
	// directions of SpaceObject
	public void setLeft(boolean b){left = b;}
	
	public void setRight(boolean b){right = b;}
	
	public void setUp(boolean b){up = b;}
	
	public void setDown(boolean b){down = b;}
	
	/*
	 * set position of the SpaceObject
	 */
	public void setPosition(double x, double y) 
	{
		this.posX = x;
		this.posY = y;
	}
	
	/*
	 * set map-position of the SpaceObject
	 */
	public void setMapPosition() 
	{
		xmap = tileMap.getX();
		ymap = tileMap.getY();
	}
	
	/*
	 * returns true if SpaceObject is not on screen
	 */
	public boolean notOnScreen() {
		return posX + xmap + width < 0 ||
			posX + xmap - width > GamePanel.WIDTH ||
			posY + ymap + height < 0 ||
			posY + ymap - height > GamePanel.HEIGHT;
	}
	
	/*
	 * updates the SpaceObject
	 */
	public void update(){}
}
