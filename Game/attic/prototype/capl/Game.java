package capl;


import java.awt.Graphics2D;
import java.util.List;


public interface Game
{
	public void init();

	public void input(final List<KeyAction> lstActions);

	public void update();

	public void render(final Graphics2D xGraphics);

	public void cleanup();
}
