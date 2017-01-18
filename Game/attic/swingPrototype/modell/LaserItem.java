package modell;

import java.awt.Color;
import java.util.Objects;

import main.Galaxy;

public class LaserItem implements Item {

	// Singleton? we only need one INstance of this?
	private int ammo;
	private int dmg;
	private final Color k_xColor;
	
	public LaserItem(int ammo, final Color xColor){
		this.ammo = ammo;
		this.dmg = 40; //Config File for Laser dmg?
		k_xColor = Objects.requireNonNull(xColor);
		
		
	}
	public void setAmmo (int ammo){
		// Refill Ammo on Starbase
		this.ammo = ammo;
		this.dmg = 100;
	}
	@Override
	public void use(double x, double y, double angle, Galaxy world) {
		System.out.println("dshfhslkf");
		LaserMissile missile = new LaserMissile(x, y, angle, k_xColor);	
		world.addSpaceObject(missile);
	}
	
}
