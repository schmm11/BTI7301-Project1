package modell;

public class RocketItem implements Item {
	private int ammo;
	private int dmg;
	
	public RocketItem(int ammo){
		this.ammo = ammo;
		this.dmg = 70;
	}
	
	
	@Override
	public void use() {
		//Spawn a new RocketMissile
	}

}
