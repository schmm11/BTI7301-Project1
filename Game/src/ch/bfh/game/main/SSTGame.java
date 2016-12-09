package ch.bfh.game.main;


import java.awt.Graphics2D;
import java.util.List;


public class SSTGame implements Game
{
	@Override
	public void init()
	{
		System.out.println("Game started");
	}

	@Override
	public void input(final List<KeyAction> lstActions)
	{
	}

	@Override
	public void update()
	{
	}

	@Override
	public void render(final Graphics2D xGraphics)
	{
		xGraphics.fillRect(0, 0, 100, 100);
	}

	@Override
	public void cleanup()
	{
	}
}
