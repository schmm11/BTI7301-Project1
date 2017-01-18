package modell;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
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
	public Ship(double posX, double posY, int health, int shield, int energy, Modell modell, InputComponent input, Item startItem)
	{
		this.health = health;
		this.energy = energy;
		this.shield = shield;
		this.posX = posX;
		this.posY = posY;
		this.isAlive = true;
		
		this.modell = modell;
		this.input = input;
		
		
		
	}
	
	public void setX(double x){
		this.posX = x;
	}
	public void setY(double y){
		this.posY = y;
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

	public Image render() {
		return this.modell.getImage();
	}
}
