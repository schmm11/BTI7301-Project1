package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.Galaxy;
import modell.Ship;

	
public class UserInputComponent implements InputComponent, KeyListener{
	
	private boolean keyW = false;
	private boolean keyA = false;
	private boolean keyS = false;
	private boolean keyD = false;
	private boolean keySpace = false;
	
	@Override
	public void update(Galaxy world, Ship ship) {

		// Movement
		double x= 0;
		double y =0;
		if(keyW) y = y - 10;
		if(keyS) y = y + 10;
		if(keyA) x = x - 10;
		if(keyD) x = x + 10;
			
		ship.setX(x + ship.getPosX());
		ship.setY(y + ship.getPosY());
		
		// Use Item
		//if(keySpace) ship.useItem();
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_W) keyW = true;
		if (key == KeyEvent.VK_S) keyS = true;
		if (key == KeyEvent.VK_A) keyA = true;
		if (key == KeyEvent.VK_D) keyD = true;
		if (key == KeyEvent.VK_SPACE) keySpace = true;
		
			
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_W) keyW = false;
		if (key == KeyEvent.VK_S) keyS = false;
		if (key == KeyEvent.VK_A) keyA = false;
		if (key == KeyEvent.VK_D) keyD = false;
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
