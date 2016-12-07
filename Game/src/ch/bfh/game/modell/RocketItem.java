package ch.bfh.game.modell;

import ch.bfh.game.tileMap.TileMap;

public class RocketItem implements Item{


	@Override
	public Missile spawnNewMissile(TileMap tm, Player player) {
			System.out.print("Schuss");
		
//				missile = new RocketMissile(tm, player.getx(), player.gety())		
		return null;
	}
	
}
