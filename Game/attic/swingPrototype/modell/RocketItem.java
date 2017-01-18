package modell;

import main.Galaxy;

public class RocketItem implements Item {
	private int ammo;
	private int dmg;
	
	public RocketItem(int ammo){
		this.ammo = ammo;
		this.dmg = 70;
	}


	@Override
	public void setAmmo(int ammo) {
		//Refill Ammo
		
	}


	@Override
	public void use(double x, double y, double angle, Galaxy world) {
		// TODO Auto-generated method stub
		
	}
	
	
}
