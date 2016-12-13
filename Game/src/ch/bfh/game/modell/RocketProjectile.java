package ch.bfh.game.modell;

import java.awt.image.BufferedImage;

import ch.bfh.game.tileMap.TileMap;


public class RocketProjectile extends Projectile{
	
	private boolean hit;
	private boolean hitEnemy;
	private boolean remove;
	private int phaserDamage;
	
	private Player owner;
	private Player enemy;
	
	private BufferedImage[] sprites;
	private BufferedImage[] hitSprites;
	
	private int widthHit;
	private int heightHit;
	
	
	public RocketProjectile(TileMap tm) {
		super(tm);

	}
		
}
