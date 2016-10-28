package controller;

import main.Galaxy;
import modell.Ship;

public class EnemyInputComponent implements InputComponent{
	

	@Override
	public void update(Galaxy world, Ship ship)
	{
		ship.setX(ship.getPosX() +1);
		ship.setY(ship.getPosY() +1);
		
	}
}
