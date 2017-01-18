package entrypoint;

import java.awt.BorderLayout;
import java.awt.DisplayMode;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.event.ListDataListener;

public abstract class Game
{
	public static void main(final String[] rgArgs)
	{
		final GraphicsEnvironment xEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
		final GraphicsDevice xDev = xEnv.getDefaultScreenDevice();
		final Toolkit xKit = Toolkit.getDefaultToolkit();
		final DisplayMode[] rgModes = xDev.getDisplayModes();
		final int iLength = rgModes.length;
		final String[] rgString = new String[iLength];
		for(int iIdx = 0; iIdx < iLength; ++iIdx)
		{
			final DisplayMode xMode = rgModes[iIdx];
			rgString[iIdx] = String.format("%dx%d [%dbit, %dHz]",
					xMode.getWidth(), xMode.getHeight(),
					xMode.getBitDepth(), xMode.getRefreshRate());
		}

		final JFrame xFrame = new JFrame();
			xFrame.setUndecorated(true);
			xFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			xFrame.setResizable(false);

			xFrame.getContentPane().setLayout(new GridLayout(10, 10));
			final JComboBox<String> xModes = new JComboBox<String>(rgString);
				xModes.setFont(new Font("sans-serif", Font.BOLD, 20));
				xModes.addActionListener((xAction) ->
				{
					xDev.setDisplayMode(rgModes[xModes.getSelectedIndex()]);
				});
			xFrame.getContentPane().add(xModes);

			xFrame.pack();

			xFrame.setVisible(true);

		xDev.setFullScreenWindow(xFrame);
	}
}
