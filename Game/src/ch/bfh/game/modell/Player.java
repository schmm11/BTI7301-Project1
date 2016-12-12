package ch.bfh.game.modell;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import ch.bfh.game.tileMap.TileMap;

public class Player extends SpaceObject {

	private int health;
	private int maxHealth;
	private int shield;
	private int angle;
	private RocketItem standartWeapon;
	private Item secondaryWeapon;
	private int team;
	
	// animation
	private ArrayList<BufferedImage[]> sprites;
	private final int[] numFrames = 
		{
			1, 4, 4, 4
		};
	
	// animation actions
	private static final int IDLE = 0;
	private static final int MOVING = 1;
	private static final int MOVINGFIRING = 2;
	private static final int FIRING = 3;
	
	// movement
		protected boolean left;
		protected boolean right;
		protected boolean up;
		protected boolean down;
		protected boolean UsePrimary;
		protected boolean UseSecondary;
	
	
	
	public Player(TileMap tm) {
		super(tm);
		
		this.standartWeapon = new RocketItem();
		this.secondaryWeapon = new PhaserItem();
		this.team = 0;
		
		width = 60;
		height = 60;
		cwidth = 60;
		cheight = 60;
		
		maxHealth = 100;
		health = 100;
		
		shield = 0;
		
		moveSpeed = 0.5;
		maxSpeed = 1.6;
		angle = 90;

		try
		{
			BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/02_Textures/03_Player/Player_02.gif"));
			sprites = new ArrayList<BufferedImage[]>();
			
			for(int i = 0; i < 4; i++)
			{
				BufferedImage[] bi = new BufferedImage[numFrames[i]];
				for(int j = 0; j < numFrames[i]; j++)
				{
					bi[j] = spritesheet.getSubimage(j * width, i * height, width, height);
				}
				
				// add array of images to ArrayList holding all Arrays
				sprites.add(bi);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		animation = new Animation();
		currentAction = IDLE;
		animation.setFrames(sprites.get(IDLE));
		animation.setDelay(400);	
	
}

	
	public int getHealth(){
		return this.health;
	}
	
	public boolean checkPrimaryFire(){
		// Check Ammunition and TimeDelay
		return true;
	}
	public SpaceObject spawnStandartItem(){
//		return this.standartWeapon.spawnNewMissile(this);
		return null;
	}
	
	public boolean checkSecondaryFire(){
		// Check Ammunition and TimeDelay
		
		return true;
	}
	public SpaceObject spawnSecondaryItem(){
//		return this.secondaryWeapon.spawnNewMissile();
		return null;
	}
	
	
	@Override
	public void update()
	{
		// update position
		getNextPosition();
		checkTileMapCollision();
		setPosition(xtemp, ytemp);
		animation.update();
	}
	
	private void getNextPosition()
	{
		if(left)
		{
			dx -= moveSpeed;
			if(dx < - maxSpeed)
			{
				dx = -maxSpeed;
			}
			angle = 270;
		}
		
		else if(right)
		{
			dx += moveSpeed;
			if(dx > maxSpeed)
			{
				dx = maxSpeed;
			}
			angle = 90;
		}
		
		if(up)
		{
			dy -= moveSpeed;
			if(dy < -maxSpeed)
			{
				dy = -maxSpeed;
			}
			angle = 0;
		}
		else if(down)
		{
			dy += moveSpeed;
			if(dy > maxSpeed)
			{
				dy = maxSpeed;
			}
			angle = 180;
		}
	}
	
	public void draw(Graphics2D g)
	{
		//setMapPosition(); // TODO: für was zur Hölle ist die Funktion?
		
		// get Image
		BufferedImage img = animation.getImage();
		AffineTransform at = AffineTransform.getTranslateInstance(posX + xmap - width / 2 , posY + ymap - height / 2);
		at.rotate(Math.toRadians(angle), width / 2, height / 2);

		g.drawImage(img, at, null);
	}
	public Animation getAnimation(){ return animation;};
	public void setLeft(boolean b) { left = b; }
	public void setRight(boolean b) { right = b; }
	public void setUp(boolean b) { up = b; }
	public void setDown(boolean b) { down = b; }
	public void setUsePrimary(boolean b) { UsePrimary = b; }
	public int getAngle(){return this.angle;}
	
	
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
