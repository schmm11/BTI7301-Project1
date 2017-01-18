package ch.bfh.game_new.pickups;

import ch.bfh.game_new.entity.Animation;
import ch.bfh.game_new.entity.ObjectType;
import ch.bfh.game_new.entity.SpaceObject;
import ch.bfh.game_new.entity.Team;
import ch.bfh.game_new.gameState.GameState;
import ch.bfh.game_new.spaceShip.Player;
import ch.bfh.game_new.tileMap.TileMap;

public class MissilePickup extends SpaceObject{

	// timer
	private int timer;
	private static final int TIMERDELAY = 300;
	private static final int ANIMDELAY = 50;
	
	// status
	private boolean active;
	public static final int ACTIVE = 0;
	public static final int INACTIVE = 1;
	
	// dimensions
	public static final int WIDTH = 40;
	public static final int HEIGHT = 40;
	
	// amount of missiles restored
	public int amount;
	
	// constructor
	public MissilePickup(TileMap tm, GameState state, int posX, int posY, int amount)
	{
		super(tm, state);
		this.posX = posX;
		this.posY = posY;
		this.amount = amount;
		this.type = ObjectType.PICKUPMISSILE;
		this.team = Team.NEUTRAL;
		
		this.active = true;
		this.timer = 0;
		
		this.width = WIDTH;
		this.height = HEIGHT;
		this.cwidth = WIDTH;
		this.cheight = HEIGHT;
		
		// animation
		this.animation = new Animation();
		animation.setFrames(stateActual.getGSM().getPainter().getMissilePickupSprites().get(ACTIVE));
		animation.setDelay(ANIMDELAY);
		
		// add object to map
		setPosition(posX, posY);
		
		// add object to ArrayList with all MissilePickup-objects in GameState
		stateActual.addMissilePickup(this);
	}
	
	/*
	 * (non-Javadoc)
	 * @see entity.SpaceObject#update()
	 */
	@Override
	public void update()
	{
		if(this.active)
		{
			if(currentAction != ACTIVE)
			{
				currentAction = ACTIVE;
				animation.setFrames(stateActual.getGSM().getPainter().getMissilePickupSprites().get(ACTIVE));
				animation.setDelay(ANIMDELAY);
			}
			
			// check if pickup intersects another SpaceObject (Player)
			for(int i = 0; i < stateActual.getListPlayer().size(); i++)
			{
				Player player = (Player) stateActual.getListPlayer().get(i);
				
				if(intersects(player))
				{
					if(player.getMissileAmmo() < player.getMissileAmmoMax())
					{
						player.addMissileAmmo(amount);
						this.active = false;
						this.timer = TIMERDELAY;
					}
				}
			}
		}
		
		if(!this.active)
		{
			if(currentAction != INACTIVE)
			{
				currentAction = INACTIVE;
				animation.setFrames(stateActual.getGSM().getPainter().getMissilePickupSprites().get(INACTIVE));
				animation.setDelay(ANIMDELAY);
			}
			
			this.timer = this.timer - 1;
			if(this.timer == 0)
			{
				this.active = true;
			}
		}
		
		animation.update();
	}
}