package ch.bfh.game_new.main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Game {

	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(() ->
		{
			JFrame window = new JFrame("Super Star Trek");
			window.setContentPane(new GamePanel());
			window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			window.setResizable(false);
			window.pack();
			window.setVisible(true);
		});
	}
}
