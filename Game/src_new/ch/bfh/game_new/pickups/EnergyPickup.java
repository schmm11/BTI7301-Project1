package ch.bfh.game_new.pickups;

import ch.bfh.game_new.entity.Animation;
import ch.bfh.game_new.entity.ObjectType;
import ch.bfh.game_new.entity.SpaceObject;
import ch.bfh.game_new.entity.Team;
import ch.bfh.game_new.gameState.GameState;
import ch.bfh.game_new.spaceShip.Player;
import ch.bfh.game_new.tileMap.TileMap;

public class EnergyPickup extends SpaceObject {

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
	
	// amount of Energy restored
	public int amount;
	
	// constructor
	public EnergyPickup(TileMap tm, GameState state, int posX, int posY, int amount)
	{
		super(tm, state);
		this.posX = posX;
		this.posY = posY;
		this.amount = amount;
		this.type = ObjectType.PICKUPENERGY;
		this.team = Team.NEUTRAL;
		
		this.active = true;
		this.timer = 0;
		
		this.width = WIDTH;
		this.height = HEIGHT;
		this.cwidth = 30;
		this.cheight = 30;
		
		// animation
		this.animation = new Animation();
		animation.setFrames(stateActual.getGSM().getPainter().getEnergyPickupSprites().get(ACTIVE));
		animation.setDelay(ANIMDELAY);
		
		// add object to map
		setPosition(posX, posY);
		
		// add object to ArrayList with all EnergyPickup-objects in GameState
		stateActual.addEnergyPickup(this);
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
				animation.setFrames(stateActual.getGSM().getPainter().getEnergyPickupSprites().get(ACTIVE));
				animation.setDelay(ANIMDELAY);
			}
			
			// check if pickup intersects another SpaceObject (Player)
			for(int i = 0; i < stateActual.getListPlayer().size(); i++)
			{
				Player player = (Player) stateActual.getListPlayer().get(i);
				
				if(intersects(player))
				{
					if(player.getEnergyActual() < player.getEnergyMax())
					{
						player.addEnergy(amount);
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
				animation.setFrames(stateActual.getGSM().getPainter().getEnergyPickupSprites().get(INACTIVE));
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
