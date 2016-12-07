package ch.bfh.game.gameState;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import ch.bfh.game.main.GamePanel;
import ch.bfh.game.modell.Player;
import ch.bfh.game.modell.SpaceObject;
import ch.bfh.game.tileMap.Background;
import ch.bfh.game.tileMap.TileMap;

public class TwoPlayerLevel1State extends GameState{

	private TileMap tileMap;
	private Background bg;
	
	private Player player1;
	private Player player2;
	
	private ArrayList<SpaceObject> spaceObjects;
	private boolean player1Won;
	private boolean player2Won;
	
	// Constructor
	public TwoPlayerLevel1State(GameStateManager gsm)
	{
		this.gsm = gsm;
		init();
		
		player1Won = false;
		player2Won = false;
		
		this.spaceObjects = new ArrayList<SpaceObject>();
	}
	
	public void init()
	{
		tileMap = new TileMap(32);	
		tileMap.loadTiles("/02_Textures/01_Map/Tileset_01.gif");
		tileMap.loadMap("/03_Data/01_Map/level_02"
				+ ".txt");
		tileMap.setPosition(0,  0);
		tileMap.setTween(1);
		
		bg = new Background("/02_Textures/02_Background/Background_01.jpg", 0.1);
		
		player1 = new Player(tileMap);
		tileMap.getPlayerList().add(player1);
		player1.setPosition(280, 280);
		
		player2 = new Player(tileMap);
		tileMap.getPlayerList().add(player2);
		player2.setPosition(400, 400);
	}
	
	public void update()
	{
		if(player1Won || player2Won)
		{
			return;
		}
		
		// update player
		player1.update();
		player2.update();
		tileMap.setPosition(
				GamePanel.WIDTH / 2 - player1.getx(),
				GamePanel.HEIGHT / 2 - player1.gety()
				);
		
		// check for winner
		if(player1.getHealth() == 0)
		{
			player2Won = true;
		}
		if(player2.getHealth() == 0)
		{
			player1Won = true;
		}
		
	}
	
	public void draw(Graphics2D g)
	{
		// draw bg
		bg.draw(g);
		
		// draw tilemap
		tileMap.draw(g);
		
		// draw playerNew
		player1.draw(g);
		player2.draw(g);
		
		// draw string winner
		if(player1Won)
		{
			g.drawString("PLAYER 1 IS THE WINNER", GamePanel.WIDTH / 2, GamePanel.HEIGHT / 2);
		}
		
		if(player2Won)
		{
			g.drawString("PLAYER 2 IS THE WINNER", GamePanel.WIDTH / 2, GamePanel.HEIGHT / 2);
		}
	}
	private void primaryFire(Player player){
		if(player.checkPrimaryFire()){
			this.spaceObjects.add(player.spawnStandartItem());
		}	
	}
	
	private void secondaryFire(Player player){
		if(player.checkSecondaryFire()){
			this.spaceObjects.add(player.spawnSecondaryItem());
		}	
	}
	
	public void keyPressed(int k) {
		// general
		if(k == KeyEvent.VK_ESCAPE) gsm.setState(0);
		
		// player 1
		if(k == KeyEvent.VK_LEFT) player1.setLeft(true);
		if(k == KeyEvent.VK_RIGHT) player1.setRight(true);
		if(k == KeyEvent.VK_UP) player1.setUp(true);
		if(k == KeyEvent.VK_DOWN) player1.setDown(true);
		if(k == KeyEvent.VK_P) primaryFire(player1);
		if(k == KeyEvent.VK_O) secondaryFire(player1);
		
		// player 2
		if(k == KeyEvent.VK_A) player2.setLeft(true);
		if(k == KeyEvent.VK_D) player2.setRight(true);
		if(k == KeyEvent.VK_W) player2.setUp(true);
		if(k == KeyEvent.VK_S) player2.setDown(true);
		if(k == KeyEvent.VK_Q) primaryFire(player2);
		if(k == KeyEvent.VK_E) secondaryFire(player2);
	}
	
	public void keyReleased(int k) {
		// player 1
		if(k == KeyEvent.VK_LEFT) player1.setLeft(false);
		if(k == KeyEvent.VK_RIGHT) player1.setRight(false);
		if(k == KeyEvent.VK_UP) player1.setUp(false);
		if(k == KeyEvent.VK_DOWN) player1.setDown(false);
		//if(k == KeyEvent.VK_P) player1.setPhaserFiring(false);
		
		// player 2
		if(k == KeyEvent.VK_A) player2.setLeft(false);
		if(k == KeyEvent.VK_D) player2.setRight(false);
		if(k == KeyEvent.VK_W) player2.setUp(false);
		if(k == KeyEvent.VK_S) player2.setDown(false);
		//if(k == KeyEvent.VK_Q) player2.setPhaserFiring(false);
	}
}
