package capl_first_design.FirstDesign.modell;

import capl_first_design.FirstDesign.main.Galaxy;

public interface Item {

	public void use(Galaxy world); //Use item, i.E. Spawn Missile, do a scan, do double dmg etc.
	public void setAmmo(int ammo);
}
