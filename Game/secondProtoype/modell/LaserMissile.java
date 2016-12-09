package modell;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import main.Galaxy;

public class LaserMissile implements Missile{
	double posX;
	double posY;
	int dmg;
	boolean isAlive;
	
	public LaserMissile(double x, double y){
		this.posX = x;
		this.posY = y;
		this.dmg = 10;
		this.isAlive = true;
	}
	@Override
	public double getPosX() {
		return this.posX;
	}

	@Override
	public double getPosY() {
		return this.posY;
	}

	@Override
	public boolean isAlive() {
		return this.isAlive;
	}

	@Override
	public void update(Galaxy world) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Image render() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
