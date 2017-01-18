package modell;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Objects;

import main.Galaxy;
import view.MissileModell;
import view.Modell;

public class LaserMissile implements Missile{
	double posX;
	double posY;
	double angle;
	int dmg;
	boolean isAlive;
	private final Modell modell;
	private final long k_lStart;
	private final Color k_xColor;
	
	public LaserMissile(double x, double y, double angle, final Color xColor){
		this.angle = angle;
		this.posX = x + this.getStartingX();
		this.posY = y +this.getStartingY();
		this.dmg = 10;
		this.isAlive = true;
		modell = new MissileModell();
		k_lStart = System.nanoTime();
		k_xColor = Objects.requireNonNull(xColor);
	}
	
	public double getStartingX(){
		if(this.angle == 45  || this.angle == 90 || this.angle == 135) return 75;	
		if(this.angle == 225 || this.angle == 270 || this.angle == 315) return -75;	
		else return 0;
	}
	
	public double getStartingY(){
		if(this.angle == 315  || this.angle == 0 || this.angle == 45) return -75;	
		if(this.angle == 135 || this.angle == 180 || this.angle == 225) return 75;	
		else return 0;
	}
	
	
	@Override
	public double getPosX() {
		return this.posX;
	}

	@Override
	public double getPosY() {
		return this.posY;
	}
	public void setisAlive(boolean a){
		this.isAlive = a;
	}

	@Override
	public boolean isAlive() {
		
		return this.isAlive && (posX >= 0 && posX < 900) && (posY >= 0 && posY < 900);
	}

	@Override
	public void update(Galaxy world) {
		switch ( (int) angle ) {
         case 0:  	this.posX = this.posX + 0.0;
         			this.posY = this.posY - 1.1;
         			break;
         
         case 45:  	this.posX = this.posX + 1.1;
					this.posY = this.posY - 1.1;
					break;
					
         case 90:  	this.posX = this.posX + 1.1;
					this.posY = this.posY + 0.0;
					break;
					
         case 135:  this.posX = this.posX + 1.1;
					this.posY = this.posY + 1.1;
					break;
		
         case 180:  	this.posX = this.posX + 0.0;
						this.posY = this.posY + 1.1;
						break;
			
         case 225:  	this.posX = this.posX - 1.1;
						this.posY = this.posY + 1.1;
						break;
					
         case 270:  	this.posX = this.posX - 1.1;
						this.posY = this.posY + 0.0;
						break;
						
         case 315:  	this.posX = this.posX - 1.1;
						this.posY = this.posY - 1.1;
						break;
		 }		
		
	}
	@Override
	public Image render() {
		final Image xImage = modell.getImage(angle);
		final BufferedImage xResImage = new BufferedImage(xImage.getWidth(null), xImage.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		final Graphics2D xGraphics = (Graphics2D) xResImage.getGraphics();
		xGraphics.setColor(k_xColor);
		xGraphics.fillRect(0, 0, xResImage.getWidth(), xResImage.getHeight());
		xGraphics.dispose();
		return xResImage;
	}
	public Rectangle getDimensions(){
		int x = this.modell.getImage(angle).getWidth(null);
		int y = this.modell.getImage(angle).getHeight(null);
		//Rectangle r = new Rectangle()
		return new Rectangle((int)this.posX, (int)this.posY, x,y);
	}
	

}
