package capl_new_concept;


import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.List;


public interface Game
{
	public abstract void init();
	public abstract void input(final List<KeyAction> lstKeyActions);
	public abstract void render(final Graphics2D xGraphics, final Rectangle2D xBounds);
	public abstract void update();
	public abstract void cleanup();
}
