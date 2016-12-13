package ch.bfh.game.modell;

import java.awt.image.BufferedImage;

import ch.bfh.game.tileMap.TileMap;

public class Projectile extends SpaceObject{
	
	private boolean hit;
	private boolean hitEnemy;
	private boolean remove;
	protected boolean left;
	protected boolean right;
	protected boolean up;
	protected boolean down;
	
	private int team;
	
	//animation
	private BufferedImage[] sprites;
	private BufferedImage[] hitSprites;
	

	public Projectile(Player pl, TileMap tm) {
		super(tm);
		this.left = pl.left;
		this.right = pl.right;
		this.up = pl.up;
		this.down = pl.down;
	}
	
	@Override
	public void update(){
		
		checkTileMapCollision();
		setPosition(xtemp, ytemp);
		

	}


}
