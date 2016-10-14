/**
 * 
 */
package modell;

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
	public void render(Galaxy world);
	
}
