package ch.bfh.game_new.main;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game {

	public static void main(String[] args)
	{
		JFrame window = new JFrame("Star Blaster");
		window.setContentPane(new GamePanel());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.pack();
		window.setVisible(true);
	}
}
