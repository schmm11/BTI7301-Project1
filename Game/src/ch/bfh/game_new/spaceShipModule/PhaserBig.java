package ch.bfh.game_new.spaceShipModule;

import ch.bfh.game_new.entity.Animation;
import ch.bfh.game_new.entity.ObjectType;
import ch.bfh.game_new.entity.SpaceObject;
import ch.bfh.game_new.gameState.GameState;
import ch.bfh.game_new.spaceShip.SpaceShip;
import ch.bfh.game_new.spaceTurret.SpaceTurret;
import ch.bfh.game_new.tileMap.TileMap;

public class PhaserBig extends Projectile {

	// dimensions
	public static final int WIDTH = 20;
	public static final int HEIGHT = 20;
	
	// dimensions of hit-Area
	public static final int HITWIDTH = 30;
	public static final int HITHEIGHT = 30;
	
	// constructor
	public PhaserBig(TileMap tm, GameState state, SpaceObject owner, double damage, double speed)
	{
		super(tm, state, owner, damage, speed);
		
		this.type = ObjectType.PHASERBIG;
		
		cwidth = HITWIDTH;
		cheight = HITHEIGHT;
		width = WIDTH;
		height = HEIGHT;
		
		// animation
		animation = new Animation();
		animation.setFrames(stateActual.getGSM().getPainter().getPhaserBigSprites().get(MOVING));
		animation.setDelay(50);
		
		// set position of Phaser BIG
		setPosition(owner.getx(), owner.gety());
		
		// add object to ArrayList holding all PhaserBig-objects for this state
		stateActual.addPhaserBig(this);
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
		animation.setFrames(stateActual.getGSM().getPainter().getPhaserBigSprites().get(HIT));
		animation.setDelay(50);
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
		animation.setFrames(stateActual.getGSM().getPainter().getPhaserBigSprites().get(HIT));
		animation.setDelay(DELAY);
		dx = 0;
		dy = 0;
	}
	
	/*
	 * (non-Javadoc)
	 * @see spaceShipModule.Projectile#setHitTurret(spaceTurret.SpaceTurret)
	 */
	@Override
	public void setHitTurret(SpaceTurret t)
	{
		if(hit){return;}
		
		t.addDamage(this.damage);
		
		hit = true;
		animation.setFrames(stateActual.getGSM().getPainter().getPhaserSprites().get(HIT));
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
			stateActual.removePhaserBig(this);
		}
	}
}
