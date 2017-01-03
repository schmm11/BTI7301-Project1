package ch.bfh.game_new.spaceTurret;

import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.Line2D;

import ch.bfh.game_new.entity.Animation;
import ch.bfh.game_new.entity.Team;
import ch.bfh.game_new.gameState.GameState;
import ch.bfh.game_new.spaceShip.EnemyShip;
import ch.bfh.game_new.spaceShip.Player;
import ch.bfh.game_new.tileMap.TileMap;

public class EnemyTurret extends SpaceTurret {

	// dimensions
	public static final int WIDTH = 84;
	public static final int HEIGHT = 64;
	
	// range
	public static final int RANGE = 1200;
	
	// points for polygons
	private int xLeft;
	private int xRight;
	private int yTop;
	private int yBottom;
	
	// turret surroundings
	private Rectangle recUp;
	private Rectangle recDown;
	private Rectangle recLeft;
	private Rectangle recRight;
	
	private Polygon polyDR;
	private Polygon polyDL;
	private Polygon polyUR;
	private Polygon polyUL;
	
	// AI
	private TurretAI ai;
	private Line2D.Double line;
	
	// constructor
	public EnemyTurret(TileMap tm, GameState state)
	{
		super(tm, state, Team.CPU);
		this.healthActual = 200;
		this.healthMax = 200;
		
		this.angle = 0;
		
		this.width = WIDTH;
		this.height = HEIGHT;
		this.cwidth = WIDTH;
		this.cheight = HEIGHT;
		
		// surroundings
		this.recUp = new Rectangle(0, 0, 0, 0);
		this.recDown = new Rectangle(0, 0, 0, 0);
		this.recLeft = new Rectangle(0, 0, 0, 0);
		this.recRight = new Rectangle(0, 0, 0, 0);
		
		this.polyDR = new Polygon();
		this.polyDL = new Polygon();
		this.polyUR = new Polygon();
		this.polyUL = new Polygon();
		
		// AI
		this.ai = new TurretAI(this, this.stateActual);
		this.line = new Line2D.Double();
		
		animation = new Animation();
		currentAction = IDLE;
		animation.setFrames(stateActual.getGSM().getPainter().getTurretSprites().get(IDLE));
		animation.setDelay(400);
		
		// add object to ArrayList with all objects in GameState
		stateActual.addTurret(this);
	}
	
	// getters and setters
	public void setLine(Line2D.Double line){this.line = line;}
	
	public Line2D.Double getLine(){return this.line;}
	
	public TileMap getTileMap(){return this.tileMap;}
	
	public TurretAI getAI(){return this.ai;}
	
	/*
	 * sets the rectangles to check surroundings
	 */
	public void setSurroundings()
	{
		this.recUp.setBounds(
				(int) (this.posX + this.xmap - this.cwidth / 2), 
				(int) (this.posY + this.ymap - this.cheight / 2) - RANGE, 
				Player.WIDTH, 
				RANGE);
		
		this.recDown.setBounds(
				(int) (this.posX + this.xmap - this.cwidth / 2), 
				(int) (this.posY + this.ymap - this.cheight / 2) + EnemyShip.HEIGHT, 
				Player.WIDTH, 
				RANGE
				);
		
		this.recRight.setBounds(
				(int) (this.posX + this.xmap - this.cwidth / 2) + EnemyShip.WIDTH, 
				(int) (this.posY + this.ymap - this.cheight / 2), 
				RANGE, 
				Player.HEIGHT
				);
		
		this.recLeft.setBounds(
				(int) (this.posX + this.xmap - this.cwidth / 2) - RANGE, 
				(int) (this.posY + this.ymap - this.cheight / 2), 
				RANGE, 
				Player.HEIGHT
				);
		
		
		this.xLeft = (int) (this.posX + this.xmap - this.cwidth / 2);
		this.xRight = (int) (this.posX + this.xmap + this.cwidth / 2);
		this.yTop = (int) (this.posY + this.ymap - this.cheight / 2);
		this.yBottom = (int) (this.posY + this.ymap + this.cheight / 2);
		
		
		// POLY DOWN RIGHT
		polyDR.reset();
		polyDR.addPoint(xRight, yTop);
		polyDR.addPoint(xLeft, yBottom);
		polyDR.addPoint(xLeft + RANGE, yBottom + RANGE);
		polyDR.addPoint(xRight + RANGE, yTop + RANGE);

		// POLY DOWN LEFT
		polyDL.reset();
		polyDL.addPoint(xLeft, yTop);
		polyDL.addPoint(xRight, yBottom);
		polyDL.addPoint(xRight - RANGE, yBottom + RANGE);
		polyDL.addPoint(xLeft - RANGE, yTop + RANGE);

		// POLY UP RIGHT
		polyUR.reset();
		polyUR.addPoint(xLeft, yTop);
		polyUR.addPoint(xRight, yBottom);
		polyUR.addPoint(xRight + RANGE, yBottom - RANGE);
		polyUR.addPoint(xLeft + RANGE, yTop - RANGE);
		
		// POLY UP LEFT
		polyUL.reset();
		polyUL.addPoint(xRight, yTop);
		polyUL.addPoint(xLeft, yBottom);
		polyUL.addPoint(xLeft - RANGE, yBottom - RANGE);
		polyUL.addPoint(xRight - RANGE, yTop - RANGE);

	}
	
	// get Polygons and Rectangles
	public Polygon getPolyDR(){return polyDR;}
	
	public Polygon getPolyDL(){return polyDL;}
	
	public Polygon getPolyUR(){return polyUR;}
	
	public Polygon getPolyUL(){return polyUL;}
	
	public Rectangle getUp(){return this.recUp;}
	
	public Rectangle getDown(){return this.recDown;}
	
	public Rectangle getLeft(){return this.recLeft;}
	
	public Rectangle getRight(){return this.recRight;}
	
	/*
	 * (non-Javadoc)
	 * @see spaceTurret.SpaceTurret#setExplode()
	 */
	@Override
	public void setExplode()
	{
		if(explode){return;}
		
		this.explode = true;
		animation.setFrames(stateActual.getGSM().getPainter().getTurretSprites().get(EXPLODING));
		animation.setDelay(60);
	}
	
	/*
	 * (non-Javadoc)
	 * @see spaceTurret.SpaceTurret#setShoot()
	 */
	@Override
	public void setShoot()
	{
		if(shoot || explode){return;}
		
		this.shoot = true;
		animation.setFrames(stateActual.getGSM().getPainter().getTurretSprites().get(SHOOT));
		animation.setDelay(40);
	}
	
	/*
	 * (non-Javadoc)
	 * @see entity.SpaceObject#update()
	 */
	public void update()
	{
		
		setSurroundings();
		ai.nextAction();
		
		if(this.shoot && animation.hasPlayedOnce())
		{
			currentAction = IDLE;
			this.shoot = false;
			animation.setFrames(stateActual.getGSM().getPainter().getTurretSprites().get(IDLE));
			animation.setDelay(400);
		}
		
		// update timers on turret-Modules
		this.modMissile.updateTimer();
		this.modLaser.updateTimer();
		
		
		animation.update();
		
		
		// remove turret if currentaction is explode and explode-animation has played once
		if(this.explode && animation.hasPlayedOnce())
		{
			stateActual.removeTurret(this);
		}
	}
}
