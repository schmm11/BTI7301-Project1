import java.awt.Image;
import java.awt.Rectangle;

public interface WeaponInterface {

	public int getX();
	public int getY();
	
	public void onHit(int dmg);
	public void onCollision(int dmg);
	
	public void move();
	public Rectangle getBounds();
	
	public Image getImage();
	
}
