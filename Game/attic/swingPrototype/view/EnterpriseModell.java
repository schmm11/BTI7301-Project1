package view;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


public class EnterpriseModell implements Modell{

	private static Image image;
	
	public EnterpriseModell()
	{
		try {
		    image = ImageIO.read(new File("res/enterprise.png"));		    
		} catch (IOException e) {
		}			
        
	}
	
	
	@Override
	public Image getImage(double angle) {
		
		//return image;
		return ImageUtils.rotateImage(image, angle);
	}

}
