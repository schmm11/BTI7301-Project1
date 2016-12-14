package ch.bfh.game.modell;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import ch.bfh.game.tileMap.TileMap;
import ch.bfh.game.modell.Animation;


public class RocketProjectile extends Projectile{
	
	private boolean hit;
	private boolean hitEnemy;
	private boolean remove;
	private double moveSpeed;
	private int damage;

	
	//animation
	private BufferedImage[] sprites;
	private BufferedImage[] hitSprites;
	
	private int widthHit;
	private int heightHit;
	
	
	public RocketProjectile(Player player, TileMap tm) {
		super(player, tm);

		moveSpeed = 3.8;
		width = 16;
		height = 16;
		this.cwidth = 30; //collision Width
		this.cheight = 30; //Collision Height
		this.damage = 25;
		
		initDirection();
		
		this.posX = player.posX;
		this.posY = player.posY;
		this.xmap = player.xmap;
		this.ymap = player.ymap;
		
		
		// Load the Animation
				try
				{
					BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream(
							"/images/02_Textures/05_Projectiles/Rocket_01.gif"));
	
					BufferedImage spriteHitsheet = ImageIO.read(getClass().getResourceAsStream(
							"/images/02_Textures/05_Projectiles/Rocket_01_hit.gif"));

					// create 4 subimages for phaser
					sprites = new BufferedImage[4];
					for(int i = 0; i < sprites.length; i ++)
					{
						sprites[i] = spritesheet.getSubimage(
								i * width, 0, width, height);
					}
					// create 4 subimages for phaser hit 
					hitSprites = new BufferedImage[7];
					for(int i = 0; i < hitSprites.length; i++)
					{
						hitSprites[i] = spriteHitsheet.getSubimage(
								i * cwidth, 0, cwidth, cheight);
					}
					
					this.animation = new Animation();
					this.animation.setFrames(sprites);
					this.animation.setDelay(50);
				}
				
				catch(Exception e)
				{
					e.printStackTrace();
				}
	}
	
	/*
	 * sets the direction of the missile
	 */
	private void initDirection(){
				if(left)
				{
					this.dx = - moveSpeed;
				}
				if(right)
				{
					this.dx = moveSpeed;
				}
				if(up)
				{
					this.dy = - moveSpeed;
				}
				 if(down)
				{
					this.dy = moveSpeed;
				}
	}
	
	@Override
	public int getCWidth(){
		return this.cwidth;
	}
	@Override
	public int getCHeight(){
		return this.cheight;
	}
	
	@Override
	public void setHit() {
		this.hit = true;
		this.dy = 0;
		this.dx = 0;
		animation.setFrames(hitSprites);
		animation.setDelay(50);
	}
	@Override
	public int getDamage(){
		return this.damage;
	}
}
