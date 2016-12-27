package ch.bfh.game_new.painter;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import ch.bfh.game_new.entity.ObjectType;
import ch.bfh.game_new.entity.SpaceObject;
import ch.bfh.game_new.gameState.StateMenu;
import ch.bfh.game_new.gameState.StateSinglePlayer;
import ch.bfh.game_new.main.GamePanel;
import ch.bfh.game_new.pickups.EnergyPickup;
import ch.bfh.game_new.pickups.HealthPickup;
import ch.bfh.game_new.pickups.MissilePickup;
import ch.bfh.game_new.spaceShip.EnemyShip;
import ch.bfh.game_new.spaceShip.Player;
import ch.bfh.game_new.spaceShipModule.Missile;
import ch.bfh.game_new.spaceShipModule.Phaser;
import ch.bfh.game_new.spaceShipModule.PhaserBig;

public class PaintComponent {
	
	// GameState StateMenu
	private StateMenu stateMenu;
	private BufferedImage menuBackground;
	private BufferedImage menuOption;
	private BufferedImage menuOptionSelected;
	
	// GameState StateSinglePlayer
	private StateSinglePlayer singlePlayer;
	private BufferedImage singlePlayerBackground;
	
	// Pickup Health
	private ArrayList<BufferedImage[]> pickupHealthSprites;
	private BufferedImage[] pickupHealthActive;
	private BufferedImage[] pickupHealthInactive;
	
	// Pickup Energy
	private ArrayList<BufferedImage[]> pickupEnergySprites;
	private BufferedImage[] pickupEnergyActive;
	private BufferedImage[] pickupEnergyInactive;
	
	// Pickup Missile
	private ArrayList<BufferedImage[]> pickupMissileSprites;
	private BufferedImage[] pickupMissileActive;
	private BufferedImage[] pickupMissileInactive;
	
	// Phaser
	private ArrayList<BufferedImage[]> phaserSprites;
	private BufferedImage[] phaserMoving;
	private BufferedImage[] phaserHit;
	
	// PhaserBig
	private ArrayList<BufferedImage[]> phaserBigSprites;
	private BufferedImage[] phaserBigMoving;
	private BufferedImage[] phaserBigHit;
	
	// Missile
	private ArrayList<BufferedImage[]> missileSprites;
	private BufferedImage[] missileMoving;
	private BufferedImage[] missileHit;
	
	// SpaceShip Player
	private ArrayList<BufferedImage[]> playerSprites;
	private int[] playerSpritesFrames = {1, 4, 4, 4}; 
	
	// SpaceShip Enemy
	private ArrayList<BufferedImage[]> enemySprites;
	private int[] enemySpritesFrames = {1, 4, 4, 13};
	
	// HUD
	private BufferedImage hudEnergyEmpty;
	private BufferedImage hudEnergyFull;
	private BufferedImage hudAmmo;
	
	
	// constructor
	public PaintComponent()
	{
		// load images
		try
		{
			// GAMESTATE MENU
			menuBackground = ImageIO.read(getClass().getResourceAsStream(
					"/02_Textures/02_Background/Background_02.jpg"));
			
			menuOption = ImageIO.read(getClass().getResourceAsStream(
					"/02_Textures/04_Menu/Position_01.png"));
			
			menuOptionSelected = ImageIO.read(getClass().getResourceAsStream(
					"/02_Textures/04_Menu/Position_02.png"));
			
			// GAMESTATE SINGLEPLAYER
			singlePlayerBackground = ImageIO.read(getClass().getResourceAsStream(
					"/02_Textures/02_Background/Background_01.jpg"));
			
			// PICKUP HEALTH
			BufferedImage healthSheet = ImageIO.read(getClass().getResourceAsStream(
					"/02_Textures/06_Icons/pickup_health.gif"));
			
			BufferedImage healthSheetInactive = ImageIO.read(getClass().getResourceAsStream(
					"/02_Textures/06_Icons/pickup_health_inactive.gif"));
			
			// create subimages
			pickupHealthSprites = new ArrayList<BufferedImage[]>();
			pickupHealthActive = new BufferedImage[6];
			pickupHealthInactive = new BufferedImage[6];
			
			for(int i = 0; i < pickupHealthActive.length; i++)
			{
				pickupHealthActive[i] = healthSheet.getSubimage(i * HealthPickup.WIDTH, 0, HealthPickup.WIDTH, HealthPickup.HEIGHT);
			}
			
			for(int i = 0; i < pickupHealthInactive.length; i++)
			{
				pickupHealthInactive[i] = healthSheetInactive.getSubimage(i * HealthPickup.WIDTH, 0, HealthPickup.WIDTH, HealthPickup.HEIGHT);
			}
			
			pickupHealthSprites.add(pickupHealthActive);
			pickupHealthSprites.add(pickupHealthInactive);
			
			// PICKUP ENERGY
			BufferedImage energySheet = ImageIO.read(getClass().getResourceAsStream(
					"/02_Textures/06_Icons/pickup_energy.gif"));
			
			BufferedImage energySheetInactive = ImageIO.read(getClass().getResourceAsStream(
					"/02_Textures/06_Icons/pickup_energy_inactive.gif"));
			
			// create subimages
			pickupEnergySprites = new ArrayList<BufferedImage[]>();
			pickupEnergyActive = new BufferedImage[6];
			pickupEnergyInactive = new BufferedImage[6];
			
			for(int i = 0; i < pickupEnergyActive.length; i++)
			{
				pickupEnergyActive[i] = energySheet.getSubimage(i * EnergyPickup.WIDTH, 0, EnergyPickup.WIDTH, EnergyPickup.HEIGHT);
			}
			
			for(int i = 0; i < pickupEnergyInactive.length; i++)
			{
				pickupEnergyInactive[i] = energySheetInactive.getSubimage(i * EnergyPickup.WIDTH, 0, EnergyPickup.WIDTH, EnergyPickup.HEIGHT);
			}
			
			pickupEnergySprites.add(pickupEnergyActive);
			pickupEnergySprites.add(pickupEnergyInactive);
			
			// PICKUP MISSILE
			BufferedImage missileSheet = ImageIO.read(getClass().getResourceAsStream(
					"/02_Textures/06_Icons/pickup_missile.gif"));
			
			BufferedImage missileSheetInactive = ImageIO.read(getClass().getResourceAsStream(
					"/02_Textures/06_Icons/pickup_missile_inactive.gif"));
			
			// create subimages
			pickupMissileSprites = new ArrayList<BufferedImage[]>();
			pickupMissileActive = new BufferedImage[6];
			pickupMissileInactive = new BufferedImage[6];
			
			for(int i = 0; i < pickupMissileActive.length; i++)
			{
				pickupMissileActive[i] = missileSheet.getSubimage(i * MissilePickup.WIDTH, 0, MissilePickup.WIDTH, MissilePickup.HEIGHT);
			}
			
			for(int i = 0; i < pickupMissileInactive.length; i++)
			{
				pickupMissileInactive[i] = missileSheetInactive.getSubimage(i * MissilePickup.WIDTH, 0, MissilePickup.WIDTH, MissilePickup.HEIGHT);
			}
			
			pickupMissileSprites.add(pickupMissileActive);
			pickupMissileSprites.add(pickupMissileInactive);
			
			// PHASER
			BufferedImage phaserMovingSheet = ImageIO.read(getClass().getResourceAsStream(
					"/02_Textures/03_Player/Phaser_01.gif"));
			
			BufferedImage phaserHitSheet = ImageIO.read(getClass().getResourceAsStream(
					"/02_Textures/03_Player/Phaser_01_hit.gif"));
			
			// create Subimages
			phaserSprites = new ArrayList<BufferedImage[]>();
			phaserMoving = new BufferedImage[4];
			phaserHit = new BufferedImage[7];
			
			for(int i = 0; i < phaserMoving.length; i++)
			{
				phaserMoving[i] = phaserMovingSheet.getSubimage(i * Phaser.WIDTH, 0, Phaser.WIDTH, Phaser.HEIGHT);
			}
			
			for(int i = 0; i < phaserHit.length; i++)
			{
				phaserHit[i] = phaserHitSheet.getSubimage(i * Phaser.HITWIDTH, 0, Phaser.HITWIDTH, Phaser.HITHEIGHT);
			}
			
			phaserSprites.add(phaserMoving);
			phaserSprites.add(phaserHit);
			
			// PHASER BIG
			BufferedImage phaserBigMovingSheet = ImageIO.read(getClass().getResourceAsStream(
					"/02_Textures/03_Player/Phaser_02.gif"));
			
			BufferedImage phaserBigHitSheet = ImageIO.read(getClass().getResourceAsStream(
					"/02_Textures/03_Player/Phaser_02_hit.gif"));
			
			// create subimages
			phaserBigSprites = new ArrayList<BufferedImage[]>();
			phaserBigMoving = new BufferedImage[6];
			phaserBigHit = new BufferedImage[7];
			
			for(int i = 0; i < phaserBigMoving.length; i++)
			{
				phaserBigMoving[i] = phaserBigMovingSheet.getSubimage(i * PhaserBig.WIDTH, 0, PhaserBig.WIDTH, PhaserBig.HEIGHT);
			}
			
			for(int i = 0; i < phaserBigHit.length; i++)
			{
				phaserBigHit[i] = phaserBigHitSheet.getSubimage(i * PhaserBig.HITWIDTH, 0, PhaserBig.HITWIDTH, PhaserBig.HITHEIGHT);
			}
			
			phaserBigSprites.add(phaserBigMoving);
			phaserBigSprites.add(phaserBigHit);
			
			// MISSILE
			BufferedImage missileMovingSheet = ImageIO.read(getClass().getResourceAsStream(
					"/02_Textures/03_Player/Missile_01.gif"));
			
			// create subimages
			missileSprites = new ArrayList<BufferedImage[]>();
			missileMoving = new BufferedImage[4];
			missileHit = phaserHit;
			
			for(int i = 0; i < missileMoving.length; i++)
			{
				missileMoving[i] = missileMovingSheet.getSubimage(i * Missile.WIDTH, 0, Missile.WIDTH, Missile.HEIGHT);
			}
			
			missileSprites.add(missileMoving);
			missileSprites.add(missileHit);
			
			// SPACESHIP PLAYER
			BufferedImage playerSheet = ImageIO.read(getClass().getResourceAsStream(
					"/02_Textures/03_Player/Player_03.gif"));
			
			// create subimages
			playerSprites = new ArrayList<BufferedImage[]>();
			
			for(int i = 0; i < playerSpritesFrames.length; i++)
			{
				BufferedImage[] bi = new BufferedImage[playerSpritesFrames[i]];
				
				for(int j = 0; j < playerSpritesFrames[i]; j++)
				{
					bi[j] = playerSheet.getSubimage(j * Player.WIDTH, i * Player.HEIGHT, Player.WIDTH, Player.HEIGHT);
				}
				
				// add array to ArrayList holding all Player sprites
				this.playerSprites.add(bi);
			}
			
			// SPACESHIP ENEMY
			BufferedImage enemySheet = ImageIO.read(getClass().getResourceAsStream(
					"/02_Textures/03_Player/Enemy_01.gif"));
			
			// create subimages
			enemySprites = new ArrayList<BufferedImage[]>();
			
			for(int i = 0; i < enemySpritesFrames.length; i++)
			{
				BufferedImage[] bi  = new BufferedImage[enemySpritesFrames[i]];
				
				for(int j = 0; j < enemySpritesFrames[i]; j++)
				{
					bi[j] = enemySheet.getSubimage(j * EnemyShip.WIDTH, i * EnemyShip.HEIGHT, EnemyShip.WIDTH, EnemyShip.HEIGHT);
				}
				
				// add array to ArrayList holding all EnemyShip sprites
				this.enemySprites.add(bi);
			}
			
			// HUD 
			hudEnergyEmpty = ImageIO.read(getClass().getResourceAsStream(
					"/02_Textures/05_HUD/hud_empty.gif"));
			
			hudEnergyFull = ImageIO.read(getClass().getResourceAsStream(
					"/02_Textures/05_HUD/hud_energy.gif"));
			
			hudAmmo = ImageIO.read(getClass().getResourceAsStream(
					"/02_Textures/05_HUD/hud_ammo_01.gif"));
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/*
	 * draws everything in the GameState StateMenu
	 */
	public void drawMenu(Graphics2D g, StateMenu state)
	{
		this.stateMenu = state;
		
		// draw Background
		g.drawImage(menuBackground, 0, 0, null);
		
		// draw title
		g.setColor(stateMenu.getTitleColor());
		g.setFont(stateMenu.getTitleFont());
		
		FontMetrics fm = g.getFontMetrics();
		
		String title = new String("Star Blaster");
		g.drawString(title, GamePanel.WIDTH / 2 - (fm.stringWidth(title) / 2), 260);
		
		// draw menu options
		g.setFont(stateMenu.getRegularFont());
		
		// loop through menu options
		FontMetrics fm2 = g.getFontMetrics();
		for(int i = 0; i < stateMenu.getOptionsLength(); i++)
		{
			if(i == stateMenu.getCurrentChoice())
			{
				g.setColor(new Color(0, 250, 250));
				g.drawImage(menuOptionSelected, GamePanel.WIDTH / 2 - (menuOptionSelected.getWidth() / 2), 300 + i * 60, null);
			}
			else
			{
				g.setColor(new Color(0, 150, 150));
				g.drawImage(menuOption, GamePanel.WIDTH / 2 - (menuOption.getWidth() / 2), 300 + i * 60, null);
			}
			
			String[] options = stateMenu.getOptions();
			g.drawString(options[i], GamePanel.WIDTH / 2 - (fm2.stringWidth(options[i]) / 2), 325 + i * 60);
		}
	}
	
	/*
	 * draws all objects in GameState StateSinglePlayer 
	 */
	public void drawSinglePlayer(Graphics2D g, StateSinglePlayer singlePlayer)
	{
		this.singlePlayer = singlePlayer;
		
		// draw background
		g.drawImage(singlePlayerBackground,  this.singlePlayer.getTileMap().getX() / 4,  this.singlePlayer.getTileMap().getY() / 4, null);
		
		// draw TileMap
		singlePlayer.getTileMap().draw(g);
		
		// draw SpaceObjects
		
		// HEALTH PICKUPS
		for(int i = 0; i < singlePlayer.getListHealthPickup().size(); i++)
		{
			HealthPickup obj = (HealthPickup) singlePlayer.getListHealthPickup().get(i);
			drawObject(g, obj, obj.WIDTH, obj.HEIGHT);
		}
		
		// ENERGY PICKUPS
		for(int i = 0; i < singlePlayer.getListEnergyPickup().size(); i++)
		{
			EnergyPickup obj = (EnergyPickup) singlePlayer.getListEnergyPickup().get(i);
			drawObject(g, obj, obj.WIDTH, obj.HEIGHT);
		}
		
		// MISSILE PICKUPS
		for(int i = 0; i < singlePlayer.getListMissilePickup().size(); i++)
		{
			MissilePickup obj = (MissilePickup) singlePlayer.getListMissilePickup().get(i);
			drawObject(g, obj, obj.WIDTH, obj.HEIGHT);
		}
		
		// PHASER PROJECTILES
		for(int i = 0; i < singlePlayer.getListPhaser().size(); i++)
		{
			Phaser obj = (Phaser) singlePlayer.getListPhaser().get(i);
			drawObject(g, obj, obj.WIDTH, obj.HEIGHT);
		}
		
		// PHASERBIG PROJECTILES
		for(int i = 0; i < singlePlayer.getListPhaserBig().size(); i++)
		{
			PhaserBig obj = (PhaserBig) singlePlayer.getListPhaserBig().get(i);
			drawObject(g, obj, obj.WIDTH, obj.HEIGHT);
		}
		
		// MISSILE PROJECTILES
		for(int i = 0; i < singlePlayer.getListMissile().size(); i++)
		{
			Missile obj = (Missile) singlePlayer.getListMissile().get(i);
			drawObject(g, obj, obj.WIDTH, obj.HEIGHT);
		}
		
		// ENEMY
		for(int i = 0; i < singlePlayer.getListEnemy().size(); i++)
		{
			EnemyShip obj = (EnemyShip) singlePlayer.getListEnemy().get(i);
			drawObject(g, obj, obj.WIDTH, obj.HEIGHT);
			
			// *** DEBUG ***
			g.draw(obj.getUp());
			g.draw(obj.getDown());
			g.draw(obj.getLeft());
			g.draw(obj.getRight());
			
			g.setColor(Color.YELLOW);
			g.draw(obj.getPolyDR());
			g.draw(obj.getPolyDL());
			g.draw(obj.getPolyUR());
			g.draw(obj.getPolyUL());

			g.setColor(Color.BLUE);
		}
		
		// PLAYER
		for(int i = 0; i < singlePlayer.getListPlayer().size(); i++)
		{
			Player obj = (Player) singlePlayer.getListPlayer().get(i);
			drawObject(g, obj, obj.WIDTH, obj.HEIGHT);
		}

		
		// draw HUD
		AffineTransform atHUD = AffineTransform.getTranslateInstance(40, 40);
		atHUD.scale(0.75, 0.75);
		g.drawImage(hudEnergyEmpty, atHUD, null);
		
		AffineTransform atAm = AffineTransform.getTranslateInstance(40, 520);
		g.drawImage(hudAmmo, atAm, null);
		
		// draw energybars
		Player player = this.singlePlayer.getPlayer();
		if(player != null)
		{
			// energy
			double energyPercentage = (player.getEnergyMax() - player.getEnergyActual()) / 100;
			AffineTransform atEF = AffineTransform.getTranslateInstance(77, 82);
			atEF.scale(1 - (0.75 * (energyPercentage)) - 0.25, 0.75);
			g.drawImage(hudEnergyFull, atEF, null);
			
			// health
			double healthPercentage = (player.getHealthMax() - player.getHealthActual()) / 100;
			AffineTransform atEH = AffineTransform.getTranslateInstance(77, 61);
			atEH.scale(1 - (0.75 * (healthPercentage)) - 0.25, 0.75);
			g.drawImage(hudEnergyFull, atEH, null);
			
			// ammo
			g.setColor(new Color(0, 150, 150));
			g.drawString("Missile Ammo  " + player.getMissileAmmo() + " / " + player.getMissileAmmoMax(), 45, 605);
		}
		
	}
	
	/*
	 * draws an object to the map
	 */
	private void drawObject(Graphics2D g, SpaceObject o, int width, int height)
	{
		o.setMapPosition();
		BufferedImage img = o.getAnimation().getImage();
		AffineTransform at = AffineTransform.getTranslateInstance(
				o.getx() + o.getXmap() - width / 2, 
				o.gety() + o.getYmap() - height / 2
				);
		at.rotate(Math.toRadians(o.getAngle()), width / 2, height / 2);
		g.drawImage(img, at, null);
	}
	
	/*
	 * returns an ArrayList holding 2 BufferedImage-arrays
	 * for health-Pickups, active = 0 and inactive = 1
	 */
	public ArrayList<BufferedImage[]> getHealthPickupSprites()
	{
		return this.pickupHealthSprites;
	}
	
	/*
	 * returns an ArrayList holding 2 BufferedImage-arrays
	 * for energy-Pickups, active = 0, inactive = 1
	 */
	public ArrayList<BufferedImage[]> getEnergyPickupSprites()
	{
		return this.pickupEnergySprites;
	}
	
	/*
	 * returns an ArrayList holding 2 BufferedImage-arrays
	 * for missile-Pickups, active = 0, inactive = 1
	 */
	public ArrayList<BufferedImage[]> getMissilePickupSprites()
	{
		return this.pickupMissileSprites;
	}
	
	/*
	 * returns an ArrayList holding 2 BufferedImage-arrays
	 * for Phasers, moving = 0, hit = 1
	 */
	public ArrayList<BufferedImage[]> getPhaserSprites()
	{
		return this.phaserSprites;
	}
	
	/*
	 * returns an ArrayList holding 2 BufferedImage-arrays
	 * for PhaserBig, moving = 0, hit = 1
	 */
	public ArrayList<BufferedImage[]> getPhaserBigSprites()
	{
		return this.phaserBigSprites;
	}
	
	/*
	 * returns an ArrayList holding 2 BufferedImage-arrays
	 * for Missiles, moving = 0, hit = 1
	 */
	public ArrayList<BufferedImage[]> getMissileSprites()
	{
		return this.missileSprites;
	}
	
	/*
	 * returns an ArrayList holding 4 different BufferedImage-arrays
	 * for Player, idle = 0, moving = 1, dash = 2
	 */
	public ArrayList<BufferedImage[]> getPlayerSprites()
	{
		return this.playerSprites;
	}
	
	/*
	 * returns an ArrayList holding 4 different BufferedImage-arrays
	 * for EnemyShip, idle = 0, moving = 1, dash = 2, explode = 3
	 */
	public ArrayList<BufferedImage[]> getEnemySprites()
	{
		return this.enemySprites;
	}
}
