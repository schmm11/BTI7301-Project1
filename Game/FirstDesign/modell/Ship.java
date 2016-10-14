package modell;

import java.util.ArrayList;

import controller.InputComponent;
import controller.UserInputComponent;
import main.Galaxy;
import view.EnterpriseModell;
import view.Modell;

public class Ship implements SpaceObject{
	private int health;
	private int energy;
	private int shield;
	private double posX;
	private double posY;
	private boolean isAlive;
	private final Modell modell;
	private final InputComponent input;
	private ArrayList<Item> items;
	public Ship(int health, int shield, int energy, Modell modell, InputComponent input, Item startItem)
	{
		this.health = health;
		this.energy = energy;
		this.shield = shield;
		this.posX = 50.50;
		this.posY = 50.50;
		this.isAlive = true;
		
		this.modell = modell;
		this.input = input;
		
		
		
	}

	@Override
	public double getPosX() {
		return this.posX;
	}

	@Override
	public double getPosY() {
		// TODO Auto-generated method stub
		return this.posY;
	}

	@Override
	public boolean isAlive() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void update(Galaxy world) {
		input.update(world, this);
	}

	@Override
	public void render(Galaxy world) {
		//modell.render(world);
	}
}
