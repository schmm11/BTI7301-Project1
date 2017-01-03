package ch.bfh.game_new.pickups;

import ch.bfh.game_new.entity.Animation;
import ch.bfh.game_new.entity.ObjectType;
import ch.bfh.game_new.entity.SpaceObject;
import ch.bfh.game_new.entity.Team;
import ch.bfh.game_new.gameState.GameState;
import ch.bfh.game_new.spaceShip.Player;
import ch.bfh.game_new.tileMap.TileMap;

public class MissileUpgrade extends SpaceObject {

	// timer for animation
	private static final int ANIMDELAY = 50;
	
	// dimensions
	public static final int WIDTH = 40;
	public static final int HEIGHT = 40;
	
	// constructor
	public MissileUpgrade(TileMap tm, GameState state, int posX, int posY)
	{
		super(tm, state);
		this.posX = posX;
		this.posY = posY;
		this.type = ObjectType.UPGRADEMISSILE;
		this.team = Team.NEUTRAL;
		
		this.width = WIDTH;
		this.height = HEIGHT;
		this.cwidth = WIDTH;
		this.cheight = HEIGHT;
		
		// animation
		this.animation = new Animation();
		animation.setFrames(stateActual.getGSM().getPainter().getMissileUpgradeSprites());
		animation.setDelay(ANIMDELAY);
		
		// add object to map
		setPosition(posX, posY);
		
		// add object to ArrayList with all Missile-Upgrades in GameState
		stateActual.addMissileUpgrade(this);
	}
	
	/*
	 * (non-Javadoc)
	 * @see entity.SpaceObject#update()
	 */
	@Override
	public void update()
	{
		animation.update();
		
		for(int i = 0; i < stateActual.getListPlayer().size(); i++)
		{
			Player player = (Player) stateActual.getListPlayer().get(i);
			
			if(intersects(player))
			{
				if(player.getModMissile().getDoubleMissile() == false)
				{
					player.getModMissile().setDoubleMissile(true);
					stateActual.removeMissileUpgrade(this);
				}
			}
		}
	}
}
