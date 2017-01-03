package capl_new_concept;


import java.awt.Graphics2D;
import java.util.List;


public final class SSTMain
{
	public static void main(final String[] rgArgs)
	{
		final SSTGame xGame = new SSTGame();
		final GameWindow xWindow = new GameWindow(500, 500);
		xWindow.init();

		xGame.init();
		final long lStart = System.nanoTime();
		while(!xWindow.closeRequested())
		{
			final List<KeyAction> lstActions = xWindow.input();
			xGame.input(lstActions);

			final Graphics2D xGraphics = xWindow.xGraphics();
			xGame.render(xGraphics, xWindow.bounds());
			xWindow.show();

			xGame.update((System.nanoTime() - lStart) * 1e-9);

			try
			{
				Thread.sleep(5);
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
