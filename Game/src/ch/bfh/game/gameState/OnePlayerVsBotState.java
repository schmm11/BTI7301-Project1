package ch.bfh.game.gameState;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import ch.bfh.game.main.GamePanel;
import ch.bfh.game.modell.Player;
import ch.bfh.game.modell.Projectile;
import ch.bfh.game.modell.SpaceObject;
import ch.bfh.game.renderer.BotRenderer;
import ch.bfh.game.renderer.GameRenderer;
import ch.bfh.game.tileMap.Background;
import ch.bfh.game.tileMap.TileMap;

public class OnePlayerVsBotState extends GameState{

	private TileMap tileMap;
	private Background bg;
	
	private Player player1;
	private Player player2; //Bot == Player2
	
	private ArrayList<SpaceObject> spaceObjects;
	private ArrayList<Projectile> spaceProjectiles;
	private boolean player1Won;
	private boolean player2Won;
	
	// Constructor
	public OnePlayerVsBotState(GameStateManager gsm)
	{
		this.gsm = gsm;
		init();
		
		player1Won = false;
		player2Won = false;
		
		this.spaceObjects = new ArrayList<SpaceObject>();
		this.spaceProjectiles = new ArrayList<Projectile>();
	}
	
	public void init()
	{
		tileMap = new TileMap(32);	
		tileMap.loadTiles("/assets/images/02_Textures/01_Map/Tileset_1vsbot.gif");
		tileMap.loadMap("/assets/images/03_Data/01_Map/level_1vsbot"
				+ ".txt");
		tileMap.setPosition(0,  0);
		tileMap.setTween(1);
		
		bg = new Background("/assets/images/02_Textures/02_Background/Background_01.jpg", 0.1);
		
		player1 = new Player(tileMap);
		tileMap.getPlayerList().add(player1);
		player1.setPosition(100, 80);
		
		player2 = new Player(tileMap);
		tileMap.getPlayerList().add(player2);
		player2.setPosition(100, 640);
	}
	
	public void update()
	{
		if(player1Won || player2Won)
		{
			return;
		}
		
		// update player
		player1.update();
		
		/* Calculate the movement of the Bot and sets the "keys"
		 * 
		 */
		BotRenderer.calculateBotMovement(player2, player1);
		player2.update();
		
		//updates the Projectiles
		for(Projectile o : spaceProjectiles){
			if(o != null){
				o.update();
			}
		}
		
		
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
		
		// draw player with GameRenderer
		GameRenderer.render(g, player1);
		GameRenderer.render(g, player2);

		//draw Projectiles
		for(Projectile o : spaceProjectiles){
			if(o != null){
				GameRenderer.render(g, o);
			}
		}
		
		
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
			this.spaceProjectiles.add(player.spawnStandartItem());
		}	
	}
	
	private void secondaryFire(Player player){
		if(player.checkSecondaryFire()){
			this.spaceProjectiles.add(player.spawnSecondaryItem());
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
		if(k == KeyEvent.VK_ENTER) primaryFire(player1);
		if(k == KeyEvent.VK_O) secondaryFire(player1);
		
		// player 2
		// Bot doesnt need this controls
	}
	
	public void keyReleased(int k) {
		// player 1
		if(k == KeyEvent.VK_LEFT) player1.setLeft(false);
		if(k == KeyEvent.VK_RIGHT) player1.setRight(false);
		if(k == KeyEvent.VK_UP) player1.setUp(false);
		if(k == KeyEvent.VK_DOWN) player1.setDown(false);
		
		
		// player 2
		// Bot doesnt need this controls
	}
}
