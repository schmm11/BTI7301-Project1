package capl_first_design.FirstDesign.modell;


import java.awt.Graphics2D;

import capl_first_design.FirstDesign.main.Galaxy;


public interface SpaceObject
{
	public double x();
	public double y();
	public boolean alive();
	public void update(final Galaxy xGalaxy);
	public void render(final Galaxy xGalaxy, final Graphics2D xGraphics);
}
