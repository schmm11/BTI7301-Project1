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
		this.hit = false;
		this.left = pl.left;
		this.right = pl.right;
		this.up = pl.up;
		this.down = pl.down;
	}
	
	@Override
	public void update(){
		
		checkTileMapCollision();
		animation.update();
		//if hit a wall (eg no more movement)
		if((dy == 0 && dx == 0) && !hit){
			setHit();
			this.hit = true;
		}
		
		
		System.out.print("Hit: " + hit );
		System.out.print(" playedOnce: "+ animation.hasPlayedOnce() + " ");
		System.out.print("remove: "+remove );
		System.out.println("");
		if(hit && animation.hasPlayedOnce())
		{
			remove = true;
			
		}
		setPosition(xtemp, ytemp);
		

	}
	/*
	 * What to do when the Rocket hits a ship or a wall?
	 * TODO
	 */
	public void setHit(){
		
	}
	
	public boolean getRemove(){
		return this.remove;
	}


}
