import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Enemy implements ShipInterface{

	protected int x;
	protected int y;
	protected int health;
	protected int shield;
	protected int width = Config.ENEMY_WIDTH;
	protected int height = Config.ENEMY_HEIGTH;
	protected Image image;
	private ArrayList<Missile> missiles;
	private String missileDir = "E";
	private Boolean isAlive;
	private int timeCompensator;
	
	public Enemy (int x, int y){
		this.x = x;
		this.y = y;
		timeCompensator = 0;
		this.health = Config.ENEMY_HEALTH;
		this.shield = Config.ENEMY_SHIELD;
		this.isAlive = true;
		loadImage("src/media/Enemy.png");
		missiles = new ArrayList<>();	
	}
	
    protected void loadImage(String imageName) {
    	{
        ImageIcon ii = new ImageIcon(imageName);
        image = ii.getImage();
    	}
    }
    public ArrayList<Missile> getMissiles(){
    	return this.missiles;
    }
	
	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return this.x;
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return this.y;
	}

	@Override
	public void fire() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onHit(int dmg) {
		this.health = this.health - dmg;
		if(this.health < 1) this.destroy();
		
	}
	private void destroy(){
		this.x = -100;
		this.y = -100;
		this.isAlive = false;
	}
	public boolean isDestroyed(){
		return !isAlive;
	}

	@Override
	public void onCollision(int dmg) {
		this.health = this.health - dmg;
		if(this.health <1) this.destroy();	
	}



	@Override
	public Image getImage() {
		return this.image;
	}

	@Override
	public int getHealth() {
		return this.health;
	}

	@Override
	public Rectangle getBounds() {
		 return new Rectangle(x, y, width, height);	 
	}
	
	
	// enterpriseX,Y: postion of the enterprsie
	public void move(int enterpriseX, int enterpriseY) {
		int entX = enterpriseX;
		int entY = enterpriseY;
		
		int x = this.x;
		int y= this.y;
		
		//Distance in which the enemys going to shoot
		int dist = 300;
		//Distance in which enemys mooving
		int moveDist = 200;
		
	//first: Check if Enterprise is in a near quadrant 200px X 200px
		
		if((Math.abs(entX - x) < dist) && (Math.abs(entY - y) < dist)){ // then the Enterprise is near enough
			this.fire(entX, entY);
			//System.out.println("I want to fire cause Enterprise is nearer than "+dist+this );
		}
		if((Math.abs(entX - x) < moveDist) && (Math.abs(entY - y) < moveDist)){ // then the Enterprise is near enough
			this.go(entX, entY);
			System.out.println("I want to move cause Enterprise is nearer than "+moveDist+this);
		}
	}
	private void go(int enterpriseX, int enterpriseY) {
this.timeCompensator--;
		
		if(timeCompensator < 1){
			timeCompensator = 4;
			int entX = enterpriseX;
			int entY = enterpriseY;
			
			
			if(( x > entX) && (Math.abs((entX - this.x)) > Math.abs((entY -this.y))) ){
	    		++this.x;
	    		//loadImage("src/media/Enterprise_right_f.png");
	    	}	
	        if ((( y < entY) && (Math.abs((entX - this.x)) > Math.abs((entY -this.y))) )){
	        	--this.x;
	        	//loadImage("src/media/Enterprise_left_f.png");
	        } 
	        if (( x < entX) && (Math.abs((entX - this.x)) < Math.abs((entY - this.y))) ) {
	        	--this.y;
	        	//loadImage("src/media/Enterprise_up_f.png");
	        	
	        }        	    
	        if (( y > entY) && ( Math.abs((entX - this.x)) < Math.abs((entY -this.y))) ){
	        	++this.y;
	        	//loadImage("src/media/Enterprise_down_f.png");
	        } ;
	        
	        //Dont go of the map
	        if (x < 1) x = 1;
	        if (x > (Config.BOARDSIZE_X - Config.ICRAFT_HEIGHT)) x = Config.BOARDSIZE_X - Config.ICRAFT_HEIGHT;
	        if (y < 1) y = 1;
	        if( y > (Config.BOARDSIZE_Y - Config.ICRAFT_WITDH)) y= Config.BOARDSIZE_Y - Config.ICRAFT_WITDH;
	        	
			}
			
			
		
		
	}

	// enterpriseX,Y: postion of the enterprsie
	public void fire(int enterpriseX, int enterpriseY) {
		this.timeCompensator--;
		
		if(timeCompensator < 1){
			timeCompensator = 40;
			int entX = enterpriseX;
			int entY = enterpriseY;
			int x = this.x;
			int y= this.y;
			
			if(( x > entX) && (Math.abs((entX - x)) > Math.abs((entY -y))) ){
				this.missileDir = "W";
			}
			else if (( y > entY) && ( Math.abs((entX - x)) < Math.abs((entY -y))) ) {
				this.missileDir = "N";
			}
			else if (( x < entX) && (Math.abs((entX - x)) < Math.abs((entY -y))) ){
				this.missileDir = "S";
			}
			else if (( y < entY) && (Math.abs((entX - x)) > Math.abs((entY -y))) ){
				this.missileDir = "E";
			}
			missiles.add(new Missile(x + width, y + height / 2, missileDir));
	        Sound sound = new Sound("missile.wav");
	        sound.play();
			}
	}
	
	
	

	@Override
	public void move() {
		// Method with empty konstructor doesnt needed in interface?
		
	}

}
