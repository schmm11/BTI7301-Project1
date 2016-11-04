package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.Galaxy;
import modell.Ship;

	
public class UserInputComponent2 implements InputComponent, KeyListener{
	
	private boolean keyW = false;
	private boolean keyA = false;
	private boolean keyS = false;
	private boolean keyD = false;
	private boolean useItem1 = false;
	private boolean keyO = false;
	
	@Override
	public void update(Galaxy world, Ship ship) {

		// Movement
		double x= 0;
		double y =0;
		double angle =ship.getAngle();
		if(keyW && ship.getPosY() > 0.0) y = y - 5;
		if(keyS && ship.getPosY() < 870.00) y = y + 5;
		if(keyA && ship.getPosX() > 0.0) x = x - 5;
		if(keyD && ship.getPosX() < 870.00) x = x + 5;
		
		
		// Angle Adjustment
		if(keyW) angle = 0;
		if(keyD) angle = 90;
		if(keyS) angle = 180;
		if(keyA) angle = 270;
		if(keyW && keyD) angle = 45;
		if(keyD && keyS) angle = 135;
		if(keyS && keyA) angle = 225;
		if(keyA && keyW) angle = 315;
		
		if(angle > 0) ship.setAngle(angle);
		
		
		//world.setIngame(keyO);
			
		ship.setX(x + ship.getPosX());
		ship.setY(y + ship.getPosY());
		ship.setAngle(angle);
		
		if(useItem1){
			ship.useItem(0, world);
			useItem1 = false;
		}

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_UP) keyW = true;
		if (key == KeyEvent.VK_DOWN) keyS = true;
		if (key == KeyEvent.VK_LEFT) keyA = true;
		if (key == KeyEvent.VK_RIGHT) keyD = true;
		if (key == KeyEvent.VK_CONTROL) useItem1 = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_UP) keyW = false;
		if (key == KeyEvent.VK_DOWN) keyS = false;
		if (key == KeyEvent.VK_LEFT) keyA = false;
		if (key == KeyEvent.VK_RIGHT) keyD = false;
		if (key == KeyEvent.VK_CONTROL) useItem1 = false;
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
