package capl_first_design.FirstDesign.main;


import java.awt.Graphics2D;


public final class Main
{
	public static void main(final String[] rgArgs) throws Exception
	{
		final Window xWindow = new Window();
		final Game xGame = new SuperStarTrek();

		xGame.init();
		while(!xWindow.closeRequested())
		{
			final Graphics2D xGraphics = xWindow.graphics();

			xGame.input(xWindow.input());
			xGame.update();
			xGame.render(xGraphics, xWindow.bounds());

			xGraphics.dispose();
			xWindow.show();

			try
			{
				Thread.sleep(16);
			}
			catch(final InterruptedException xException)
			{
				xException.printStackTrace();
			}
		}

		xGame.cleanup();
		xWindow.close();
	}
}