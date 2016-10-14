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
		
		objects.add(new Ship(0, 0, 0, new EnterpriseModell(), new UserInputComponent(), new LaserItem(100))); // Enterprise
		
		objects.add(new Ship(100,100,100, new EnemyModell(), new EnemyInputComponent(), new LaserItem(500)));
		
		
		
		
		
	}
	
	
	public void processInput(){
		// Main mehtod to process User Inputs
		
		
	}
	public void update(){
		//Updates Every Space Object in World
		for(SpaceObject o:objects){
			o.update(this);
		}
		
		// Destroy dead items // See Update Pattern
		for(SpaceObject o:objects){
			if(!(o.isAlive())){
				objects.remove(o);
			}
		}
		
	}
	public void render(){
		// Renders every Space object
		for(SpaceObject o:objects){
			o.render(this);
		}
	}
	
}
