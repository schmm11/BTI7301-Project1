package ch.bfh.game_new.spaceTurret;

import ch.bfh.game_new.entity.Animation;
import ch.bfh.game_new.entity.ObjectType;
import ch.bfh.game_new.entity.SpaceObject;
import ch.bfh.game_new.gameState.GameState;
import ch.bfh.game_new.spaceShip.SpaceShip;
import ch.bfh.game_new.spaceShipModule.Projectile;
import ch.bfh.game_new.tileMap.TileMap;

public class Laser extends Projectile {

	// dimensions
	public static final int WIDTH = 7;
	public static final int HEIGHT = 32;
	
	// dimensions of hit-Area
	public static final int HITWIDTH = 30;
	public static final int HITHEIGHT = 30;
	
	// constructor
	public Laser(TileMap tm, GameState state, SpaceObject owner, int posX, int posY, double damage, double speed)
	{
		super(tm, state, owner, damage, speed);
		
		this.type = ObjectType.LASER;
		this.team = owner.getTeam();
		
		this.width = WIDTH;
		this.height = HEIGHT;
		this.cwidth = HITWIDTH;
		this.cheight = HITHEIGHT;
		
		// animation
		this.animation = new Animation();
		animation.setFrames(stateActual.getGSM().getPainter().getLaserSprites().get(MOVING));
		animation.setDelay(DELAY);
		
		// set Position of Laser
		setPosition(posX, posY);
		
		// add object to ArrayList holding all Laser-objects for this state
		stateActual.addLaser(this);
	}
	
	/*
	 * (non-Javadoc)
	 * @see spaceShipModule.Projectile#setHit()
	 */
	@Override
	public void setHit()
	{
		if(hit){return;}
		
		hit = true;
		animation.setFrames(stateActual.getGSM().getPainter().getLaserSprites().get(HIT));
		animation.setDelay(DELAY);
		dx = 0;
		dy = 0;
	}
	
	/*
	 * (non-Javadoc)
	 * @see spaceShipModule.Projectile#setHitShip(spaceShip.SpaceShip)
	 */
	@Override
	public void setHitShip(SpaceShip s)
	{
		if(hit){return;}
		
		s.addDamage(this.damage);
		
		hit = true;
		animation.setFrames(stateActual.getGSM().getPainter().getLaserSprites().get(HIT));
		animation.setDelay(DELAY);
		dx = 0;
		dy = 0;
	}
	
	/*
	 * (non-Javadoc)
	 * @see spaceShipModule.Projectile#checkAndRemove()
	 */
	@Override
	public void checkAndRemove()
	{
		if(hit && animation.hasPlayedOnce())
		{
			stateActual.removeLaser(this);
		}
	}
}
