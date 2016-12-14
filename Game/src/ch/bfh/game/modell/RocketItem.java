package ch.bfh.game.modell;

import ch.bfh.game.tileMap.TileMap;

public class RocketItem implements Item{
	
	private int standartFireDelay; // standart Fire Delay for this weapon
	private int fireDelay;
	private int ammunition;
	
	public RocketItem(){
		this.fireDelay = 100;
		this.standartFireDelay = 1; //Attack Speed = 1/1second
		this.ammunition = 50; //can Shoot immediately at the beginning of a game
	}
	
	
	@Override
	public Projectile spawnNewProjectile(TileMap tm, Player player) {
		System.out.println("Schuss");
		RocketProjectile projectile = new RocketProjectile(player, tm);	
		return projectile;
	}
	@Override
	public boolean checkFire() {
		if(this.ammunition > 0 && this.fireDelay == 0){
			this.fireDelay = standartFireDelay; //Sets the Timer to standart Delay (16milliseconds /60)
			this.ammunition--;
			return true;
		}
		else{
			System.out.println("Cant Shoot: Ammo= "+this.ammunition+"  fireDelay="+this.fireDelay);
		
			return false;
		}
	}
	
	//Updates the FireRateTimer
	@Override
	public void updateFireRateTimer() {
		if(this.fireDelay >0) this.fireDelay--;
	}
	
	
	
}
