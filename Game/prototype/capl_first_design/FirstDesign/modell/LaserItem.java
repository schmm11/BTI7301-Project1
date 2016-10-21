package capl_first_design.FirstDesign.modell;

import capl_first_design.FirstDesign.main.Galaxy;

public class LaserItem implements Item {

	// Singleton? we only need one INstance of this?
	private int ammo;
	private int dmg;
	
	public LaserItem(int ammo){
		this.ammo = ammo;
		this.dmg = 40; //Config File for Laser dmg?
		
		
	}
	public void setAmmo (int ammo){
		// Refill Ammo on Starbase
		this.ammo = ammo;
		this.dmg = 100;
	}
	@Override
	public void use(Galaxy world) {
		// Spawn a new Laser Missile in world.objects
		
	}
}
