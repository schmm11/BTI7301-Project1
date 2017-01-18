package view;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MissileModell implements Modell {
	
private static Image image;
	
	public MissileModell()
	{
		try {
		    image = ImageIO.read(new File("res/enemy_red.png"));		    
		} catch (IOException e) {
		}			
        
	}

	@Override
	public Image getImage(double angle) {
		// TODO Auto-generated method stub
		return ImageUtils.rotateImage(image, angle);
	}

}
