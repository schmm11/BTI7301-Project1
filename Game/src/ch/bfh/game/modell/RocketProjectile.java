package ch.bfh.game.modell;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import ch.bfh.game.tileMap.TileMap;
import ch.bfh.game.modell.Animation;


public class RocketProjectile extends Projectile{
	
	private boolean hit;
	private boolean hitEnemy;
	private boolean remove;
	private int phaserDamage;
	private double moveSpeed;

	
	//animation
	private BufferedImage[] sprites;
	private BufferedImage[] hitSprites;
	
	private int widthHit;
	private int heightHit;
	
	
	public RocketProjectile(Player player, TileMap tm) {
		super(player, tm);

		phaserDamage = 10;
		moveSpeed = 3.8;
		width = 16;
		height = 16;
		widthHit = 30;
		heightHit = 30;
		
		initDirection();
		
		this.posX = player.posX;
		this.posY = player.posY;
		this.xmap = player.xmap;
		this.ymap = player.ymap;
		
		
		// Load the Animation
				try
				{
					BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream(
							"/assets/images/02_Textures/05_Projectiles/Rocket_01.gif"));
	
					BufferedImage spriteHitsheet = ImageIO.read(getClass().getResourceAsStream(
							"/assets/images/02_Textures/05_Projectiles/Rocket_01_hit.gif"));

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
								i * widthHit, 0, widthHit, heightHit);
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
		// direction
				if(left)
				{
					this.dx = - moveSpeed;
				}
				
				else if(right)
				{
					this.dx = moveSpeed;
				}
				
				if(up)
				{
					this.dy = - moveSpeed;
				}
				
				else if(down)
				{
					this.dy = moveSpeed;
				}
	}
	

	
	/*
	 * What to do when the Rocket hits a ship or a wall?
	 * TODO
	 */
	public void setHit(){
		
	}
}
