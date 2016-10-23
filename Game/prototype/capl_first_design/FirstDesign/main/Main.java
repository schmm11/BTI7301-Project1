package capl_first_design.FirstDesign.main;


import java.awt.Graphics2D;


public final class Main
{
	public static void main(final String[] rgArgs) throws Exception
	{
		final Window2 xWindow = new Window2();

		final long lStart = System.nanoTime();
		boolean bChanged = false;
		while(!xWindow.closeRequested())
		{
			final long lTime = (System.nanoTime() - lStart);
			final Graphics2D g = xWindow.graphics();
			xWindow.show();
			g.dispose();
			Thread.yield();

			if(lTime * 1e-9 > 4 && !bChanged)
			{
				bChanged = true;
				xWindow.setFullscreen(true);
			}
		}

		xWindow.close();
	}
}
