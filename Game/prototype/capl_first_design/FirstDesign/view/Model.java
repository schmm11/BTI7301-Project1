package capl_first_design.FirstDesign.view;


import java.awt.Graphics2D;

import capl_first_design.FirstDesign.modell.SpaceObject;


public interface Model
{
	public void render(final SpaceObject xObject, final Graphics2D xGraphics);
}
