package first;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Starbase {
	
	private int x;
	private int y;
	private int width;
	private int heigth;
	private int healRate;
	private int shieldRate;
	private int muniRate;
	private Image image;
	
	public Starbase(int x, int y){
		this.x = x;
		this.y = y;
		this.width = Config.SB_WIDTH;
		this.heigth = Config.SB_HEIGHT;
		this.healRate = Config.SB_HEALRATE;
		this.shieldRate = Config.SB_SHIELDRATE;
		this.muniRate = Config.SB_MUNIRATE;
		
		loadImage("media/Starbase.png");
		
	}
	
    protected void loadImage(String imageName) {

        ImageIcon ii = new ImageIcon(getClass().getResource(imageName));
        image = ii.getImage();
    }

    public void onCollission(){
    	//needed?
    }
    
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, heigth);
	}
    
    public Image getImage(){
    	return this.image;
    }
    public int getX(){
    	return this.x;
    }
    public int getY(){
    	return this.y;
    }
    public int getHealtRate(){
    	return this.healRate;
    }
    public int getShieldRate(){
    	return this.shieldRate;
    }
    public int getMunitionRate(){
    	return this.muniRate;
    }


}
