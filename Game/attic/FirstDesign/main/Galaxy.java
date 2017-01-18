package main;

import java.util.ArrayList;

import controller.EnemyInputComponent;
import controller.UserInputComponent;
import modell.*;
import view.EnemyModell;
import view.EnterpriseModell;

public class Galaxy {

	ArrayList<SpaceObject> objects;
	public Galaxy(){
		
		objects.add(new Ship(10.01, 10.10, 100, 100, 100, new EnterpriseModell(), new UserInputComponent(), new LaserItem(100))); // Enterprise
		
		
		
		
		
		
	}
	
	public void gameLoop() throws InterruptedException
	{
		while(true){
			//Game Loop Pattern	with static 60fps
			double start = System.currentTimeMillis();
			this.processInput();
			this.update();
			this.render();

			Thread.sleep((long) (start + 16 - System.currentTimeMillis()));	
	}
}
	
	
	public void processInput()
	{
		// Main mehtod to process User Inputs
		
		
	}
	public void update()
	{
		//Updates Every Space Object in World
		for(SpaceObject o:objects)
		{
			o.update(this);
		}
		
		// Destroy dead items // See Update Pattern
		for(SpaceObject o:objects)
		{
			if(!(o.isAlive())){
				objects.remove(o);
			}
		}
		
	}
	public void render()
	{
		// Renders every Space object
		for(SpaceObject o:objects){
			o.render(this);
		}
	}	
}
