package modell;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
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
	private long weaponLag;
	private boolean isAlive;
	private final Modell modell;
	private final InputComponent input;
	private double angle;
	private ArrayList<Item> items;
	public Ship(double posX, double posY, int health, int shield, int energy, Modell modell, InputComponent input, Item startItem)
	{
		this.weaponLag = 0;
		this.health = health;
		this.energy = energy;
		this.shield = shield;
		this.posX = posX;
		this.posY = posY;
		this.isAlive = true;
		this.angle = 225;
		this.modell = modell;
		this.input = input;
		this.items = new ArrayList<Item>();
		items.add(startItem);
		
		
	}
	
	public void setX(double x){
		this.posX = x;
	}
	public void setY(double y){
		this.posY = y;
	}
	public void setAngle(double angle){
		this.angle = angle;
	}
	public double getAngle(){
		return this.angle;
	}	
	public void useItem(int itemIndex, Galaxy world){
		if(this.weaponLag == 0){
			
		this.weaponLag = 20;
		this.items.get(itemIndex).use(this.posX, this.posY, this.angle, world);
		}
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
		return true;
	}

	@Override
	public void update(Galaxy world) {
		input.update(world, this);
		if(this.weaponLag >0)	this.weaponLag = this.weaponLag-1;
	}

	public Image render() {
		return this.modell.getImage(this.angle);	
	}
	
	public Rectangle getDimensions(){
		return new Rectangle ( (int)this.posX, (int)this.posY, 45, 45);
	}
	
	
}
