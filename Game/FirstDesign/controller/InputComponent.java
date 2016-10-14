package controller;

import main.Galaxy;
import modell.Ship;

public interface InputComponent {
	public void update(Galaxy world, Ship ship);
}
