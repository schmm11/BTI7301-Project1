package modell;

import main.Galaxy;

public interface Item {

	public void use(double x, double y, double angle, Galaxy world); //Use item, i.E. Spawn Missile, do a scan, do double dmg etc.
	public void setAmmo(int ammo);
}
