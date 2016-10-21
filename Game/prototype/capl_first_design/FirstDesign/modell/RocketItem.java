package capl_first_design.FirstDesign.modell;

import capl_first_design.FirstDesign.main.Galaxy;

public class RocketItem implements Item {
	private int ammo;
	private int dmg;
	
	public RocketItem(int ammo){
		this.ammo = ammo;
		this.dmg = 70;
	}

	@Override
	public void use(Galaxy world) {
		//Spawn a new RocketMissile in world.objects
	}

	@Override
	public void setAmmo(int ammo) {
		//Refill Ammo
		
	}
	
	
}
