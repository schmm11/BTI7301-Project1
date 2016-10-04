import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Enemy implements ShipInterface{

	protected int x;
	protected int y;
	protected int health;
	protected int shield;
	protected int width = Config.ENEMY_WIDTH;
	protected int height = Config.ENEMY_HEIGTH;
	protected Image image;
	private ArrayList<Missile> missiles;
	private String missileDir = "E";
	private Boolean isAlive;
	
	public Enemy (int x, int y){
		this.x = x;
		this.y = y;
		this.health = Config.ENEMY_HEALTH;
		this.shield = Config.ENEMY_SHIELD;
		this.isAlive = true;
		loadImage("src/media/Enemy.png");
		missiles = new ArrayList<>();	
	}
	
	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}
	
    protected void loadImage(String imageName) {
    	{
        ImageIcon ii = new ImageIcon(imageName);
        image = ii.getImage();
    	}
    }
    public ArrayList<Missile> getMissiles(){
    	return this.missiles;
    }
	
	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return this.x;
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return this.y;
	}

	@Override
	public void fire() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onHit(int dmg) {
		this.health = this.health - dmg;
		if(this.health < 1) this.destroy();
		
	}
	private void destroy(){
		this.x = -100;
		this.y = -100;
		this.isAlive = false;
	}
	public boolean isDestroyed(){
		return !isAlive;
	}

	@Override
	public void onCollision(int dmg) {
		this.health = this.health - dmg;
		if(this.health <1) this.destroy();	
	}



	@Override
	public Image getImage() {
		return this.image;
	}

	@Override
	public int getHealth() {
		return this.health;
	}

	@Override
	public Rectangle getBounds() {
		 return new Rectangle(x, y, width, height);
		 
		 
	}

}
