package ch.bfh.game.modell;

import java.awt.image.BufferedImage;

import ch.bfh.game.tileMap.TileMap;

public class Projectile extends SpaceObject{
	
	private boolean hit;
	private boolean hitEnemy;
	private boolean remove;
	private int damage;
	protected boolean left;
	protected boolean right;
	protected boolean up;
	protected boolean down;
	protected int cwidth;
	protected int cheight;
	
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
		this.team = pl.getTeam();
	}
	
	@Override
	public void update(){
		
		checkTileMapCollision();
		animation.update();
		//if hit a wall (eg no more movement)
		if(((left || right) && dx == 0) && !hit){
			setHit();
			this.hit= true;
		}
		if((up || down) && dy == 0 && !hit){
			setHit();
			this.hit= true;
		}
		

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
	public boolean getHit(){
		return this.hit;
	}
	public int getCWidth(){
		return this.cwidth;
	}
	public int getCHeight(){
		return this.cheight;
	}
	public boolean getRemove(){
		return this.remove;
	}
	public int getTeam(){
		return this.team;
	}
	public int getDamage(){
		return this.damage;
	}


}
