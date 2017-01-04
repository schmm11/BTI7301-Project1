package ch.bfh.game_new.spaceShip;

import java.awt.Polygon;
import java.awt.Rectangle;

import ch.bfh.game_new.entity.Animation;
import ch.bfh.game_new.entity.ObjectType;
import ch.bfh.game_new.entity.Team;
import ch.bfh.game_new.gameState.GameState;
import ch.bfh.game_new.main.Config;
import ch.bfh.game_new.tileMap.TileMap;

public class EnemyShip extends SpaceShip {

	// dimensions
	public static final int WIDTH = 60;
	public static final int HEIGHT = 80;
	
	// range
	public static final int RANGE = 300;
	public static final int BOOST = 200;
	
	// points for polygons
	private int xLeft;
	private int xRight;
	private int yTop;
	private int yBottom;
	
	// ship surroundings
	private Rectangle recUp;
	private Rectangle recDown;
	private Rectangle recLeft;
	private Rectangle recRight;
	
	private Polygon polyDR;
	private Polygon polyDL;
	private Polygon polyUR;
	private Polygon polyUL;
	
	// AI
	private EnemyAI ai;
	
	// constructor
	public EnemyShip(TileMap tm, GameState state)
	{
		super(tm, state);
		this.energyActual = Config.E_ENERGYACTUAL;
		this.energyMax = Config.E_ENERGYMAX;
		this.healthActual = Config.E_HEALTSTART;
		this.healthMax = Config.E_HEALTMAX;
		this.rechargeRate = Config.E_ENERGYRECHARGE;
		
		this.type = ObjectType.ENEMYSHIP;
		this.team = Team.CPU;
		
		this.angle = 0;
		
		this.width = WIDTH;
		this.height = HEIGHT;
		this.cwidth = WIDTH;
		this.cheight = HEIGHT;
		
		this.moveSpeed = Config.E_ACCELERATION;
		this.maxSpeed = Config.E_MAXSPEED;
		
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
		this.ai = new EnemyAI(this, this.stateActual);
		
		animation = new Animation();
		currentAction = IDLE;
		animation.setFrames(stateActual.getGSM().getPainter().getEnemySprites().get(IDLE));
		animation.setDelay(400);
		
		// add object to ArrayList with all objects in GameState
		stateActual.addEnemy(this);
	}
	
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
	 * @see spaceShip.SpaceShip#setExplode()
	 */
	@Override
	public void setExplode()
	{
		if(explode){return;}
		
		this.explode = true;
		animation.setFrames(stateActual.getGSM().getPainter().getEnemySprites().get(EXPLODE));
		animation.setDelay(60);
	}
	
	/*
	 * (non-Javadoc)
	 * @see entity.SpaceObject#update()
	 */
	public void update()
	{
		getNextPosition();
		checkTileMapCollision();
		setPosition(xtemp, ytemp);
		
		setSurroundings();
		ai.nextAction();
		
		// check boost
		if(this.boost)
		{
			if(currentAction != DASHING)
			{
				currentAction = DASHING;
				animation.setFrames(stateActual.getGSM().getPainter().getEnemySprites().get(DASHING));
				animation.setDelay(30);
			}
			
			this.maxSpeed = 7.0;
			this.energyActual = this.energyActual - 0.5;
			
			if(this.energyActual == 0)
			{
				this.boost = false;
				this.maxSpeed = 3.0;
				
				left = false;
				right = false;
				up = false;
				down = false;
			}
		}
		
		// is ship is not boosting, check if ship is moving
		else if(moving)
		{
			if(currentAction != MOVING)
			{
				currentAction = MOVING;
				animation.setFrames(stateActual.getGSM().getPainter().getEnemySprites().get(MOVING));
				animation.setDelay(30);
			}

		}
		
		// if ship is not boosting and not moving, ship is idle
		else
		{
			if(currentAction != IDLE)
			{
				currentAction = IDLE;
				animation.setFrames(stateActual.getGSM().getPainter().getEnemySprites().get(IDLE));
				animation.setDelay(400);
			}
		}
		
		// recharge energy
		if(this.energyActual < this.energyMax)
		{
			this.energyActual += this.rechargeRate;
			if(this.energyActual > this.energyMax)
			{
				this.energyActual = this.energyMax;
			}
		}
		
		// update Timers on modules
		this.modMissile.updateTimer();
		this.modPhaser.updateTimer();
		this.modPhaserBig.updateTimer();
		
		animation.update();
		
		// remove Ship if currentaction is explode and explode-animation has played once
		if(explode && animation.hasPlayedOnce())
		{
			stateActual.removeEnemy(this);
		}
	}
}
