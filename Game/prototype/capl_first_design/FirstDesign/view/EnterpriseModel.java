package capl_first_design.FirstDesign.view;


import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import capl_first_design.FirstDesign.modell.Ship;
import capl_first_design.FirstDesign.modell.SpaceObject;


public final class EnterpriseModel implements Model
{
	private static Image s_xImage;
	private static final int k_iWidth = 100;
	private static final int k_iHeight = 50;

	static
	{
		try
		{
			final BufferedImage xImage = ImageIO.read(EnterpriseModel.class.getResourceAsStream("/capl_first_design/FirstDesign/res/images/enterprise.png"));
			s_xImage = xImage.getScaledInstance(k_iWidth, k_iHeight, BufferedImage.SCALE_SMOOTH);
		}
		catch(final IOException xException)
		{
			xException.printStackTrace();
		}
	}


	@Override
	public void render(final SpaceObject xObject, final Graphics2D xGraphics)
	{
		xGraphics.rotate(((Ship) xObject).angle());
		xGraphics.drawImage(s_xImage, -k_iWidth / 2, -k_iHeight / 2, k_iWidth, k_iHeight, null);
	}
}
