package capl;


import java.awt.Graphics2D;
import java.util.List;


public final class SSTMain
{
	public static void main(final String[] rgArgs)
	{
		final Display xDisplay = new BasicDisplay(500, 500);
		final Game xGame = new SSTGame();

		xDisplay.init();
		xGame.init();

		while(!xDisplay.closeRequested())
		{
			final List<KeyAction> lstActions = xDisplay.input();
			xGame.input(lstActions);

			xGame.update();

			final Graphics2D xGraphics = xDisplay.graphics();
			xGame.render(xGraphics);
			xGraphics.dispose();
			xDisplay.show();
		}

		xGame.cleanup();
		xDisplay.close();
	}
}
