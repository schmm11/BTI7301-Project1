import java.awt.Image;
import java.awt.Rectangle;

public interface ShipInterface {
	
	public int getX();
	public int getY();
	
	public void fire();
	public void onHit(int dmg);
	public void onCollision(int dmg);
	
	public void move();
	public Rectangle getBounds();
	
	public Image getImage();
	int getHealth();
	
}
