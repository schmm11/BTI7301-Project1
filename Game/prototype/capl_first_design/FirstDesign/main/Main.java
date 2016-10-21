package capl_first_design.FirstDesign.main;


import java.awt.Color;
import java.awt.Graphics2D;


public final class Main
{
	public static void main(final String[] rgArgs)
	{
		final Window2 xWindow = new Window2();

		boolean bCheck = false;
		final long lStart = System.nanoTime();
		while(!xWindow.closeRequested())
		{
			final Graphics2D g = xWindow.graphics();
			final long lTime = (System.nanoTime() - lStart);
			g.setColor(Color.getHSBColor(lTime * 1e-9f, 1.0f, 1.0f));
			g.fillRect(0, 0, xWindow.dimension().width, xWindow.dimension().height);
			g.dispose();
			xWindow.show();
		}

		xWindow.close();
	}
}
