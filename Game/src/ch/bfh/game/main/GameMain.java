package ch.bfh.game.main;


import java.awt.EventQueue;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;



public final class GameMain
{
	public static void main(final String[] rgArgs)
	{
		final JFrame xFrame = new JFrame("Super Star Battles to the MAX");
			
			xFrame.setContentPane(new GamePanel());
			xFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			xFrame.setResizable(false);
			xFrame.pack();
			xFrame.setVisible(true);

	}
	
}
