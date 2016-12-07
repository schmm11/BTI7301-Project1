package ch.bfh.game.modell;

import ch.bfh.game.tileMap.TileMap;

public interface Item {
	public Missile spawnNewMissile(TileMap tm, Player player);
}
