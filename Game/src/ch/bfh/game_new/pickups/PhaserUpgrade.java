package ch.bfh.game_new.pickups;

import ch.bfh.game_new.entity.Animation;
import ch.bfh.game_new.entity.ObjectType;
import ch.bfh.game_new.entity.SpaceObject;
import ch.bfh.game_new.entity.Team;
import ch.bfh.game_new.gameState.GameState;
import ch.bfh.game_new.spaceShip.Player;
import ch.bfh.game_new.tileMap.TileMap;

public class PhaserUpgrade extends SpaceObject {

	// timer for animation
	private static final int ANIMDELAY = 50;
	
	// dimensions
	public static final int WIDTH = 40;
	public static final int HEIGHT = 40;
	
	// constructor
	public PhaserUpgrade(TileMap tm, GameState state, int posX, int posY)
	{
		super(tm, state);
		this.posX = posX;
		this.posY = posY;
		this.type = ObjectType.UPGRADEPHASER;
		this.team = Team.NEUTRAL;
		
		this.width = WIDTH;
		this.height = HEIGHT;
		this.cwidth = WIDTH;
		this.cheight = HEIGHT;
		
		// animation
		this.animation = new Animation();
		animation.setFrames(stateActual.getGSM().getPainter().getPhaserUpgradeSprites());
		animation.setDelay(ANIMDELAY);
		
		// add object to map
		setPosition(posX, posY);
		
		// add object to ArrayList with all Phaser-Upgrades in GameState
		stateActual.addPhaserUpgrade(this);
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
				if(player.getModPhaser().getDoublePhaser() == false)
				{
					player.getModPhaser().setDoublePhaser(true);
					stateActual.removePhaserUpgrade(this);
				}
			}
		}
	}
}
