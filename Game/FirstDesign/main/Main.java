package main;
public class Main {

	private static Galaxy world;
	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		Galaxy world = new Galaxy();
		
		initGame();
	}
	private static void initGame() throws InterruptedException{
		
		// Init the World with its starting SpacObjects
		
		
		
		
		gameLoop();
		
	}

	private static void gameLoop() throws InterruptedException {
		while(true){
			//Game Loop Pattern
				
			double start = System.currentTimeMillis();
			world.processInput();
			world.update();
			world.render();

			Thread.sleep((long) (start + 16 - System.currentTimeMillis()));

		
	}
}}
