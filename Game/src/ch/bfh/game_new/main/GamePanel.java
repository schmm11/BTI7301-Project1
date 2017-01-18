package ch.bfh.game_new.main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import ch.bfh.game_new.gameState.GameStateManager;

public class GamePanel extends JPanel implements Runnable, KeyListener {

	// dimensions
	public static final int WIDTH = 1250;
	public static final int HEIGHT = 705;

	// game thread
	private Thread thread;
	private boolean running;
	private int FPS = 60;
	private long targetTime = 1000 / FPS;

	// graphics
	private BufferedImage image;
	private Graphics2D g;

	// GameStateManager
	private GameStateManager gsm;

	// constructor
	public GamePanel()
	{
		super();
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		requestFocus();
	}

	// GamePanel is done loading, thread can be started
	public void addNotify()
	{
		super.addNotify();
		if(thread == null)
		{
			thread = new Thread(this);
			addKeyListener(this);
			thread.start();
		}
	}

	// initialize GamePanel
	public void init()
	{
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		running = true;
		gsm = new GameStateManager();
	}

	// initialize and run game-loop
	public void run()
	{
		init();

		// timers for game-loop
		long start;
		long elapsed;
		long wait;

		// define game-loop
		while(running)
		{
			start = System.nanoTime();

			// update and draw game
			update();
			draw();
			drawToScreen();

			elapsed = System.nanoTime() - start;
			wait = targetTime - elapsed / 1000000;

			// try to wait, wait at least 1
			if(wait < 0){wait = 1;}

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

	// update method, calls update in GameStateManager
	private void update()
	{
		gsm.update();
	}

	// draw method, calls draw in GameStateManager
	private void draw()
	{
		gsm.draw(g);
	}

	// drawToScreen method, get Graphics, draw and dispose
	private void drawToScreen()
	{
		Graphics g2 = getGraphics();
		g2.drawImage(image, 0, 0, WIDTH, HEIGHT, null);
		g2.dispose();
	}

	// User-Input handling
	public void keyTyped(KeyEvent key)
	{
		// do nothing (yet)
	}

	public void keyPressed(KeyEvent key)
	{
		if(gsm != null)
		{
			gsm.keyPressed(key.getKeyCode());
		}
	}

	public void keyReleased(KeyEvent key)
	{
		if(gsm != null)
		{
			gsm.keyReleased(key.getKeyCode());
		}
	}
}
