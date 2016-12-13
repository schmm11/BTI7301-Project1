package ch.bfh.game.modell;

import ch.bfh.game.tileMap.TileMap;

public class RocketItem implements Item{
	
	private int fireDelay;
	private int ammunition;
	
	
	@Override
	public Projectile spawnNewProjectile(TileMap tm, Player player) {
			System.out.print("Schuss");

		return null;
	}
	@Override
	public boolean checkFire() {
		if(this.ammunition != 0 && this.fireDelay == 0){
			this.fireDelay = 60/16; //Sets the Timer to 1 second (16milliseconds /60
			return true;
		}
		else
			return false;
	}
	
	//Updates the FireRateTimer
	@Override
	public void updateFireRateTimer() {
		this.fireDelay --;
	}
	
	
	
}
