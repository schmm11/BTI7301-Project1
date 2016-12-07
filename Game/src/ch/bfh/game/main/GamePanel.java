package ch.bfh.game.main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import ch.bfh.game.gameState.GameStateManager;

public class GamePanel extends JPanel implements Runnable, KeyListener{

	// dimensions
	public static final int WIDTH = Config.GAMEPANEL_WIDTH;
	public static final int HEIGHT = Config.GAMEPANEL_HEIGHT;
	
	// game thread
	private Thread thread;
	private boolean running;
	private int FPS = 60;
	private long targetTime = 1000 / FPS;
	
	// image
	private BufferedImage image;
	private Graphics2D g;
	
	// GameStateManger
	private GameStateManager gsm;
	
	// Constructor
	public GamePanel()
	{
		super();
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		requestFocus();
	}
	
	public void addNotify()
	{
		// GamePanel is done loading, thread can be started
		super.addNotify();
		if(thread == null)
		{
			thread = new Thread(this);
			addKeyListener(this);
			thread.start();
		}
	}
	
	private void init()
	{
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g= (Graphics2D) image.getGraphics();
		running = true;
		
		gsm = new GameStateManager();
	}
	
	public void run()
	{
		init();
		long start;
		long elapsed;
		long wait;
		
		// Game-loop
		while(running)
		{
			start = System.nanoTime();
			
			update();
			draw();
			drawToScreen();
			
			elapsed = System.nanoTime() - start;
			
			wait = targetTime - elapsed / 1000000;
			
			// try to wait, wait at least 1
			if(wait < 0)
			{
				wait = 1;
			}
			
			try
			{
				Thread.sleep(wait);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	private void update()
	{
		gsm.update();
	}
	
	private void draw()
	{
		gsm.draw(g);
	}
	
	private void drawToScreen()
	{
		// get GamePanels Graphics
		Graphics g2 = getGraphics();
		g2.drawImage(image, 0, 0, WIDTH, HEIGHT,  null);
		g2.dispose();
	}
	
	
	// User Input handling
	public void keyTyped(KeyEvent key)
	{
		
	}
	
	public void keyPressed(KeyEvent key)
	{
		gsm.keyPressed(key.getKeyCode());
	}
	
	public void keyReleased(KeyEvent key)
	{
		gsm.keyReleased(key.getKeyCode());
	}
	
}
