package view;

import java.awt.Graphics2D;

import modell.SpaceObject;

public interface Modell {
	public Graphics2D render(SpaceObject o);
}
