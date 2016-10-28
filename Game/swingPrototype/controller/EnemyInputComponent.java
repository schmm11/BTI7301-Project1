package controller;

import main.Galaxy;
import modell.Ship;

public class EnemyInputComponent implements InputComponent{
	

	@Override
	public void update(Galaxy world, Ship ship)
	{
		ship.setX(800 * Math.random());
		ship.setY(800 * Math.random());
		
	}
}
