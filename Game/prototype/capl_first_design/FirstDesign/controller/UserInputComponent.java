package capl_first_design.FirstDesign.controller;


import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import capl_first_design.FirstDesign.main.Galaxy;
import capl_first_design.FirstDesign.modell.Ship;


public final class UserInputComponent implements InputComponent
{
	public UserInputComponent()
	{
		k_xAdapter = new KeyAdapter()
		{
			@Override
			public void keyPressed(final KeyEvent xEvent)
			{
				UserInputComponent.this.keyPressed(xEvent.getKeyCode());
			}

			@Override
			public void keyReleased(final KeyEvent xEvent)
			{
				UserInputComponent.this.keyReleased(xEvent.getKeyCode());
			}
		};

		xFrame.addKeyListener(k_xAdapter);
		xFrame.requestFocus();
	}

	private final KeyAdapter k_xAdapter;
	private final boolean[] k_rgKeys = new boolean[256];

	@Override
	public void update(final Galaxy xGalaxy, final Ship xShip)
	{
		if(k_rgKeys[KeyEvent.VK_W]) xShip.move(0.1);
		if(k_rgKeys[KeyEvent.VK_S]) xShip.move(-0.1);
		if(k_rgKeys[KeyEvent.VK_A]) xShip.turn(-0.01);
		if(k_rgKeys[KeyEvent.VK_D]) xShip.turn(0.01);
	}

	private void keyPressed(final int iKeyCode)
	{
		k_rgKeys[iKeyCode] = true;
	}

	private void keyReleased(final int iKeyCode)
	{
		k_rgKeys[iKeyCode] = false;
	}
}
