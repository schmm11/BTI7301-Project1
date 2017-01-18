package capl_first_design.FirstDesign.view;


import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import capl_first_design.FirstDesign.modell.SpaceObject;


public final class EnterpriseModel implements Model
{
	private static Image s_xImage;
	private static AffineTransform s_xTransform;
	private static final int k_iWidth = 100;
	private static final int k_iHeight = 50;

	static
	{
		try
		{
			final BufferedImage s_xImage = ImageIO.read(EnterpriseModel.class.getResourceAsStream("/capl_first_design/FirstDesign/res/images/enterprise.png"));
			s_xTransform = new AffineTransform();
			s_xTransform.scale(1.0 / s_xImage.getWidth(), 1.0 / s_xImage.getHeight());
			s_xTransform.translate(-0.5, -0.5);
//			s_xTransform.translate(-dMinWidth / 2.0, -dMinWidth / 2.0);
		}
		catch(final IOException xException)
		{
			xException.printStackTrace();
		}
	}


	@Override
	public void render(final SpaceObject xObject, final Graphics2D xGraphics)
	{
		xGraphics.drawImage(s_xImage, s_xTransform, null);
	}
}
