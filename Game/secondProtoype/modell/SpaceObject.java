/**
 * 
 */
package modell;

import java.awt.Graphics;
import java.awt.Image;

import main.Galaxy;

/**
 * @author michu
 *
 */
public interface SpaceObject {

	public double getPosX();
	public double getPosY();
	public boolean isAlive();
	public void update(Galaxy world);
	public Image render();
	
}
