package capl_first_design.FirstDesign.main;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;


public final class Main
{
	public static void main(final String[] rgArgs) throws Exception
	{
		final BufferedImage xImage = ImageIO.read(Main.class.getResourceAsStream("/capl_first_design/FirstDesign/res/images/enterprise.png"));
		final Window2 xWindow = new Window2();
			xWindow.k_xCanvas.addKeyListener(new KeyAdapter()
			{
				@Override
				public void keyPressed(final KeyEvent xEvent)
				{
					System.out.println("Key");
					switch(xEvent.getKeyCode())
					{
					case KeyEvent.VK_1:
						xWindow.setDimension(new Dimension(800, 600));
						break;
					case KeyEvent.VK_2:
						xWindow.setDimension(new Dimension(1366, 768));
						break;
					case KeyEvent.VK_3:
						xWindow.setFullscreen(true);
						break;
					case KeyEvent.VK_4:
						xWindow.setFullscreen(false);
						break;
					}
				}
			});
			xWindow.k_xCanvas.requestFocus();

		float f = 0.0f;
		while(!xWindow.closeRequested())
		{
			final Graphics2D g = xWindow.graphics();
			f = (f + 0.01f) % 1.0f;
			g.setColor(Color.getHSBColor(f, 1.0f, 1.0f));
			final BufferedImage xi = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
			final Graphics2D ig = xi.createGraphics();
			ig.setColor(Color.RED);
			ig.fillRect(0, 0, 100, 100);
			ig.setColor(Color.BLACK);
			ig.fillRect(5, 5, 90, 90);
			ig.dispose();
			g.drawImage(xi, 0, 0, xWindow.dimension().width, xWindow.dimension().height, null);
//			g.drawImage(xImage, 0, 0, xWindow.dimension().width, xWindow.dimension().height, null);
			xWindow.show();
			g.dispose();

			Thread.sleep(16);
		}

		xWindow.close();
	}
}