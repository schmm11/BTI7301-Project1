package view;


import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class EnemyModell implements Modell{

	private static Image image;
	static
	{
		try
		{
			image = ImageIO.read(new File("src/res/enemy_red.png"));
		}
		catch(final IOException xException)
		{
			xException.printStackTrace();
		}
	}
	
	public EnemyModell()
	{
	}
	
	
	@Override
	public Image getImage() {
		return image;
	}

	
	

}
