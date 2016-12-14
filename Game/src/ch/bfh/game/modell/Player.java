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
		//this.secondaryWeapon = new PhaserItem();
		
		width = 60;
		height = 60;
		cwidth = 60;
		cheight = 60;
		
		maxHealth = 100;
		health = 100;
		
		shield = 0;
		
		moveSpeed = 10;
		maxSpeed = 10;
		angle = 90;

		try
		{
			BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/images/02_Textures/03_Player/Player_02.gif"));
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
		
		this.animation = new Animation();
		currentAction = IDLE;
		this.animation.setFrames(sprites.get(IDLE));
		this.animation.setDelay(400);	
	
}

	/*
	 * Getter And Setter Functions Here
	 */
	
	public void setLeft(boolean b) 
	{ 
		left = b; }
	public void setRight(boolean b) 
	{ 
		right = b; 
	}
	public void setUp(boolean b) 
	{ 
		up = b; 
	}
	public void setDown(boolean b) 
	{ 
		down = b;
		}
	public void setUsePrimary(boolean b) 
	{ 
		UsePrimary = b;
	}
	public int getAngle()
	{
		return this.angle;
	}
	public int getHealth()
	{
		return this.health;
	}
	public int getTeam()
	{
		return this.team;
	}
	public void setTeam(int team)
	{
		this.team = team;
	}
	
    /*
    * Check if the Primary Weapon of the player is allowed to fire
    * @return True if allowed, false if not
    */
	public boolean checkPrimaryFire(){
		if(this.left || this.right || this.up || this.down){ // Dont shoot we no Key is pressed
			return this.standartWeapon.checkFire();
		}
		return false;
	}
    /*
    * Spawns a new Missile from player's primary slot
    * @return New SpaceObject (Missile)
    */
	public Projectile spawnStandartItem(){
		return this.standartWeapon.spawnNewProjectile(this.tileMap, this);
	}
	
    /*
    * Check if the Secondary Weapon of the player is allowed to fire
    * @return True if allowed, false if not
    *
    */
	public boolean checkSecondaryFire(){
		if(this.left || this.right || this.up || this.down){ // Dont shoot we no Key is pressed
			return this.secondaryWeapon.checkFire();
		}
		return false;
	}
    /*
    * Spawns a new Missile from player's secondary slot
    * @return New SpaceObject (Missile)
    * TODO
    */
	public Projectile spawnSecondaryItem(){
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

		// Count the Attack Speed Timer of the items down.
		this.standartWeapon.updateFireRateTimer();
		//this.secondaryWeapon.updateFireRateTimer();
		
		
	}
	


	/*
	 * Updates the players x and y Positions
	 */
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

	public void hitByProjectile(Projectile p) {
			this.health = this.health - 10;
			System.out.println("I GOT HIT and now have a health of: "+ this.health);
	}
	
	/* @deprecated
	 * @see ch.bfh.game.renderer.GameRenderer
	
	public void draw(Graphics2D g)
	{
		//setMapPosition();
		
		// get Image
		BufferedImage img = animation.getImage();
		AffineTransform at = AffineTransform.getTranslateInstance(posX + xmap - width / 2 , posY + ymap - height / 2);
		at.rotate(Math.toRadians(angle), width / 2, height / 2);

		g.drawImage(img, at, null);
	}
	 */
	

	
	
}
