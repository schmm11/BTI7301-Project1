package ch.bfh.game_new.spaceShipModule;

import ch.bfh.game_new.spaceShip.SpaceShip;
import ch.bfh.game_new.tileMap.TileMap;

public class ModuleBoost extends Module {

	public ModuleBoost(TileMap tm, SpaceShip owner)
	{
		super(tm, owner);
	}
	
	/*
	 * activates the Module, sets boost in owner to true, updates energyActual
	 */
	public void activateModule()
	{
		if(owner.getBoost())
		{
			owner.setBoost(false);
			owner.setMaxSpeed(3.0);
			
			owner.setLeft(false);
			owner.setRight(false);
			owner.setUp(false);
			owner.setDown(false);
		}
		else
		{
			if(owner.getEnergyActual() > 0)
			{
				owner.setBoost(true);
				
				// force boost, depending on direction
				switch(owner.getAngle())
				{
				case 0: owner.setUp(true); break;
				case 45: owner.setUp(true); owner.setRight(true); break;
				case 90: owner.setRight(true); break;
				case 135: owner.setDown(true); owner.setRight(true); break;
				case 180: owner.setDown(true); break;
				case 225: owner.setDown(true); owner.setLeft(true); break;
				case 270: owner.setLeft(true); break;
				case 315: owner.setUp(true);owner.setLeft(true); break;
				}
			}
			else
			{
				owner.setBoost(false);
			}
		}
	}
}
