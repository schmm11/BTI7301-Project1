package first;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Missile implements WeaponInterface {
	private int x;
	private int y;
	private boolean isAlive;
	private int dmg;
	private Image image;
	private String direction;
	
	public Missile(int x, int y, String direction){
		this.direction = direction;
		
		// Placint the rockets at the right Spawn
		if(direction.equals("E")){
			this.x= x-9;
			this.y= y-10;
		}
		else if(direction.equals("S")){
			this.x= x-32;
			this.y= y+11;
		}
		else if(direction.equals("N")){
			this.x= x-32;
			this.y= y-32;
		}
		else if(direction.equals("W")){
			this.x= x-58;
			this.y= y-10;
			
		}


		this.dmg = Config.MISSILE_DMG;
		this.isAlive = true;
	
		loadImage("media/missile.png");
	}
	
	public void destroy()	{
		this.isAlive = false;
		this.x = -1000;
		this.y = -110;
	}
	
	public boolean isAlive(){
		return this.isAlive;
	}
	
	@Override
	public int getX() {
		return this.x;
	}

	@Override
	public int getY() {
		return this.y;
	}

	@Override
	public void onHit(int dmg) {
		//doesnt needed?
		
	}

	@Override
	public void onCollision(int dmg) {
		//doesnt needed?
		
	}

	@Override
	public void move() {
        if(direction.equals("N")) y -= Config.MISSILE_SPEED;
        if(direction.equals("E")) x += Config.MISSILE_SPEED;
        if(direction.equals("S")) y += Config.MISSILE_SPEED;
        if(direction.equals("W")) x -= Config.MISSILE_SPEED;
        
        if (x > Config.BOARDSIZE_X || x < 0 || y> Config.BOARDSIZE_Y || y<0)
        	this.destroy();
		
	}
	
	public int getDMG(){
		return this.dmg;
	}
	@Override
	public Image getImage() {
		return this.image;
	}
    protected void loadImage(String imageName) {

        ImageIcon ii = new ImageIcon(getClass().getResource(imageName));
        image = ii.getImage();
    }

	@Override
	public Rectangle getBounds() {
		// TODO: Set widht and height of missile
		return new Rectangle(x, y, 4, 4);
	}

}
