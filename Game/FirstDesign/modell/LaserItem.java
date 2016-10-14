package modell;

public class LaserItem implements Item {

	// Singleton? we only need one INstance of this?
	private int ammo;
	private int dmg;
	
	public LaserItem(int ammo){
		
		
		
	}
	public void setAmmo (int ammo){
		this.ammo = ammo;
		this.dmg = 100;
	}
	@Override
	public void use() {
		// Spawn a new Laser Missile
		
	}

}
