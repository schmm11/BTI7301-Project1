package ch.bfh.game_new.main;


/*
 * In this class are balance Related Values like health or velocity defined.
 * Because we want to easily balance the game, we want all the values in a central place
 */
public abstract class Config {

	//SinglePlayer Level 1 L_
	public final static int L_TIMER = 180;				// Timelimit erstes Level

	// Player P_
	public final static int P_STARTINGHEALTH = 70;		// Anfangsgesundheit
	public final static int P_MAXHEALTH = 100;			// Maximalgesundheit

	public final static double P_STARTINGENERGY = 100;	// Anfangsenergie
	public final static double P_MAXENERGY = 100;		// Maximal Energie
	public final static double P_ENERGYRESTORE = 0.1;	// Energy Aufladerate

	public final static double P_ACCELERATION = 0.5;	// Beschleunigung
	public final static double P_MAXSPEED = 3;			// Max Speed

	public final static int P_MISSILEAMMO = 12;			// Anfangsmunition Rakete
	public final static int P_MISSILEMAXAMMO = 30;		// Maximale Raketenmunition


	//Enemy Ship E_
	public final static int E_HEALTSTART = 1;			//Anfangsgesundheit
	public final static int E_HEALTMAX = 1;				//Maximale Gesundheit
	public final static double E_ENERGYACTUAL = 200;	//Anfangsenergie
	public final static double E_ENERGYMAX = 200;		//Maximale Energie
	public final static double E_ENERGYRECHARGE = 0.5;	//Enegie Aufladerate

	public final static double E_ACCELERATION = 0.5;	// Beschleunigung; 0.5
	public final static double E_MAXSPEED = 1.8;		// Max Speed 1.8

	//Enemy Turret T_
	public final static int T_RANGE = 1200;				// AggroRange 1200
	public final static int T_HEALTH = 50;				// Gesundheit
	public final static int T_ATTACKSPEED = 10;			// Attackspeed
	public final static double T_ATTACKDAMAGE = 15.0;	// Schaden der Turrets
	public final static double T_MISSILESPEED = 9.5;	// Geschwindigkeit der Turretwaffe


	// Modul BOOST B_
	public final static double B_MAXSPEED = 7;			// MAXSPEED bei Boost


	// Modul Phaser PH_
	public static final int PH_DELAY = 18;				// Attackspeed  18
	public static final double PH_PHASERSPEED = 6.0;	// Geschwindigkeit Phaser
	public static final double PH_PHASERDAMAGE = 5.0;	// Schaden Phaser 15

	//Modul PhaserBIG PB_
	public static final int PB_DELAY = 100;				// AttackSpeed
	public static final double PB_PHASERSPEED = 6.0;	// Geschwindigkeit Phaser
	public static final double PB_PHASERDAMAGE = 15.0;	// Schaden Phaser
	public static final int PB_ENERGYCOST = 20;			// Energie Kosten


	// Waffe Missile M_
	public static final int M_DELAY = 50;				// Attackspeed
	public final static double M_MISSILESPEED = 9.5;	// Geschwindigkeit der Rakete
	public final static double M_MISSILEDAMAGE = 15.0;	// Schaden Rakete

}
