package ch.bfh.game.modell;

import ch.bfh.game.tileMap.TileMap;

public interface Item {
	public Projectile spawnNewProjectile(TileMap tm, Player player);
	
	/*
	 * Checks if its possible to Shoot
	 * @return TRUE if its possible, false if not
	 */
	public boolean checkFire();
	
	/*
	 * Updates the FireRateTimer
	 */
	public void updateFireRateTimer();
	
	
}
