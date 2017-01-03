package ch.bfh.game.main;


import java.awt.Graphics2D;
import java.util.List;


public interface Display
{
	public void init();
	
	public boolean closeRequested();

	public List<KeyAction> input();
	
	public Graphics2D graphics();

	public void show();

	public void close();
}
