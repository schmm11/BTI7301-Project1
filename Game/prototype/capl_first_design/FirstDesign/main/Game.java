package capl_first_design.FirstDesign.main;


import java.awt.Graphics2D;
import java.lang.Thread.State;


public class Game
{
	public static void main(final String[] rgArgs) throws Exception
	{
		final Game xGame = new Game();
		System.out.println("Before start\n");
		xGame.start();
		Thread.sleep(2500);
		xGame.stop();
		System.out.println("Interrupted\n");
		Thread.sleep(1500);
		System.out.println("Starting\n");
		xGame.start();
		Thread.sleep(1000);
		xGame.stop();
	}

	private final Thread k_xThread = new Thread(this::loop);
	private boolean m_bRunning;
	private boolean m_bStarted;


	public Game()
	{
	}

	public void start()
	{
		if(!(m_bRunning || m_bStarted))
		{
			m_bRunning = true;
			m_bStarted = true;
			init();
			k_xThread.start();
		}
	}

	public void stop()
	{
		if(m_bRunning)
		{
			m_bRunning = false;
			cleanup();
			try
			{
				k_xThread.join();
			}
			catch(final InterruptedException xException)
			{
			}
		}
	}

	public boolean running()
	{
		return m_bRunning;
	}

	private void loop()
	{
		while(m_bRunning)
		{
			try
			{
				input();
				update();
				render(null);

				Thread.sleep(1000);
			}
			catch(final InterruptedException xException)
			{
				xException.printStackTrace();
			}
		}
	}

	public void init()
	{
		System.out.println("init");
	}

	public void update()
	{
		System.out.println("update");
	}

	public void render(final Graphics2D xGraphics)
	{
		System.out.println("render");
	}

	public void input()
	{
		System.out.println("input");
	}

	public void cleanup()
	{
		System.out.println("cleanup");
	}
}

/*
public class Main {

	private static Galaxy world;
	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		Galaxy world = new Galaxy();
		
		initGame();
	}
	private static void initGame() throws InterruptedException{
		
		// Init the World with its starting SpacObjects
		
		
		
		
		world.gameLoop();
		
	}

}
*/
