package ch.bfh.game_new.spaceShipModule;

import ch.bfh.game_new.entity.SpaceObject;
import ch.bfh.game_new.entity.SpaceObjectMoving;
import ch.bfh.game_new.entity.Team;
import ch.bfh.game_new.gameState.GameState;
import ch.bfh.game_new.spaceShip.EnemyShip;
import ch.bfh.game_new.spaceShip.Player;
import ch.bfh.game_new.spaceShip.SpaceShip;
import ch.bfh.game_new.tileMap.TileMap;

public abstract class  Projectile extends SpaceObjectMoving {

	// hit info
	protected boolean hit;
	protected boolean hitEnemy;
	protected boolean remove;
	
	// damage info
	protected double damage;
	
	// projectile status
	protected static final int MOVING = 0;
	protected static final int HIT = 1;
	
	// animation timer
	protected static final int DELAY = 50;
	
	// owner info
	protected SpaceObject owner;
	
	// constructor
	public Projectile(TileMap tm, GameState state, SpaceObject owner, double damage, double speed)
	{
		super(tm, state);
		this.owner = owner;
		this.damage = damage;
		this.moveSpeed = speed;
		this.maxSpeed = speed;
		
		this.team = owner.getTeam();
		
		this.hit = false;
		this.hitEnemy = false;
		this.angle = owner.getAngle();
		
		// based on angle, set direction for Projectile
		
		switch(angle)
		{
			case 0: up = true; break;
			case 45: up = true; right = true; break;
			case 90: right = true; break;
			case 135: down = true; right = true; break;
			case 180: down = true; break;
			case 225: down = true; left = true; break;
			case 270: left = true; break;
			case 315: up = true; left = true; break;
			default: up = true;
		}
		
		// based on direction, set vector for Projectile
		if(left){dx = - moveSpeed;}
		
		else if(right){dx = moveSpeed;}
		
		if(up){dy = - moveSpeed;}
		
		else if(down){dy = moveSpeed;}
	}
	
	/*
	 * sets hit to true and updates animation frames
	 */
	protected void setHit()
	{
		
	}
	
	/*
	 * sets hit to true, adds damage to object that got hit and updates animation frames
	 */
	protected void setHitShip(SpaceShip s)
	{
		
	}
	
	/*
	 * checks if object should be removed, removes it from ArrayList in GameState if true
	 */
	protected void checkAndRemove()
	{
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see entity.SpaceObject#update()
	 */
	public void update()
	{
		checkTileMapCollision();
		setPosition(xtemp, ytemp);
		
		System.out.println("left: " + this.left + "  right: " + this.right + "  up: " + this.up);
		
		// collision with map
		if(left || right)
		{
			if(dx == 0 && !hit)
			{
				setHit();
			}
		}
		
		if(up || down)
		{
			if(dy == 0 && !hit)
			{
				setHit();
			}
		}
		
		// collision with Enemy
		for(int i = 0; i < stateActual.getListEnemy().size(); i++)
		{
			EnemyShip enemy = (EnemyShip) stateActual.getListEnemy().get(i);
			
			if(this.team == Team.BLUE && this.intersects(enemy))
			{
				setHitShip(enemy);
			}
		}
		
		// collision with Player
		for(int i = 0; i < stateActual.getListPlayer().size(); i++)
		{
			Player player = (Player) stateActual.getListPlayer().get(i);
			
			if(this.team == Team.CPU && this.intersects(player))
			{
				setHitShip(player);
			}
		}

		
		// update animation and remove object if animation has played once 
		animation.update();
		
		checkAndRemove();
	}
}
