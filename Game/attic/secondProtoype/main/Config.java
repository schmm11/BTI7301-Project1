package main;


public abstract class Config {
	public final static int BOARDSIZE_X = 900;
	public final static int BOARDSIZE_Y = 900;
	
	public final static int DELAY = 15;
	
	//Enterprise
	public final static int ICRAFT_WITDH = 40;
	public final static int ICRAFT_HEIGHT = 40;
	public final static int START_HEALTH = 200;
	public final static int START_MUNITION = 70;
	public final static int START_SHIELD = 100;
	public final static int SHIELD_USAGE_DASH = 5;
	
	//Enemy
	public final static int ENEMY_HEALTH = 50;
	public final static int ENEMY_SHIELD = 0;
	public final static int ENEMY_WIDTH = 40;
	public final static int ENEMY_HEIGTH = 40;
	public final static int DMG_ON_COLLISION = 1;
	
	//Missile
	public final static int MISSILE_SPEED = 3;
	public final static int MISSILE_DMG = 15;
	
	//Starbase
	public final static int SB_WIDTH = 50;
	public final static int SB_HEIGHT = 50;
	public final static int SB_HEALRATE = 5;
	public final static int SB_SHIELDRATE = 5;
	public final static int SB_MUNIRATE = 5;
	
	
}
