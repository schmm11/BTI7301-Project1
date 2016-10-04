import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import java.util.ArrayList;

public class Enterprise implements ShipInterface {

	protected int x;
	protected int y;
	protected int munition;
	protected int health;
	protected int shield;
	boolean bUp,bDown,bLeft,bRight;
	protected int width = Config.ICRAFT_WITDH;
	protected int height = Config.ICRAFT_HEIGHT;
	protected Image image;
	private ArrayList<Missile> missiles;
	private String missileDir = "E"; // String to store the direction of the ship for the missiles SAS
	
	
	public Enterprise(int x, int y){
		this.x = x;
		this.y = y;
		this.munition = Config.START_MUNITION;
		this.health = Config.START_HEALTH;
		this.shield = Config.START_SHIELD;
		loadImage("src/media/Enterprise_right.png");
		missiles = new ArrayList<>();
	
	}
	
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
        if (key == KeyEvent.VK_SPACE) {
            fire();
            
            
        }
        if (key == KeyEvent.VK_Q || key == KeyEvent.VK_E) {
        	dash();  
        }

        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
        	bLeft = true; 	
        	missileDir = "W";
        }

        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
        	bRight = true;
        	missileDir = "E";
        }

        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
        	bUp = true;
        	missileDir = "N";
        }

        if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
        	bDown = true;
        	missileDir = "S";
        }
		
	}
	
    private void dash() {
    	if(bRight){
    		x = x+50;
    	}	
        if (bLeft){
        	x = x-50;
        } 
        if (bUp) {
        	y = y-50;   	
        }        	    
        if (bDown){
        	y = y +50;
        } ;
	}
	
	public void keyReleased(KeyEvent e) {
	        int key = e.getKeyCode();

	        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
	        	bLeft = false;
	        	loadImage("src/media/Enterprise_left.png");
	        }

	        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
	        	bRight = false; 
	        	loadImage("src/media/Enterprise_right.png");
	        }

	        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
	        	bUp = false;
	        	loadImage("src/media/Enterprise_up.png");
	        }

	        if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
	        	bDown = false;
	        	loadImage("src/media/Enterprise_down.png");
	        }
}
	

	@Override
	public void fire() {
		if(munition >= 1){		
		missiles.add(new Missile(x + width, y + height / 2, missileDir));
        munition--;
		}
		else {
			// Play "click Click" Sound
			
		}
	}

	@Override
	public void onHit(int dmg) {
		this.health = this.health - dmg;
		if(this.health <1) this.destroy();
		
	}

	private void destroy() {
		//Game Over
		
	}


	@Override
	public void onCollision(int dmg) {
		this.health = this.health - dmg;
		if(this.health <1) this.destroy();// TODO Auto-generated method stub
		
	}

	@Override
	public void move() {
		if(bRight){
    		++x;
    		loadImage("src/media/Enterprise_right_f.png");
    	}	
        if (bLeft){
        	--x;
        	loadImage("src/media/Enterprise_left_f.png");
        } 
        if (bUp) {
        	--y;
        	loadImage("src/media/Enterprise_up_f.png");
        	
        }        	    
        if (bDown){
        	++y;
        	loadImage("src/media/Enterprise_down_f.png");
        } ;
        
        //Dont go of the map
        if (x < 1) x = 1;
        if (x > (Config.BOARDSIZE_X - Config.ICRAFT_HEIGHT)) x = Config.BOARDSIZE_X - Config.ICRAFT_HEIGHT;
        if (y < 1) y = 1;
        if( y > (Config.BOARDSIZE_Y - Config.ICRAFT_WITDH)) y= Config.BOARDSIZE_Y - Config.ICRAFT_WITDH;
        	
	}

    protected void loadImage(String imageName) {

        ImageIcon ii = new ImageIcon(imageName);
        image = ii.getImage();
    }
	
    public ArrayList<Missile> getMissiles(){
    	return this.missiles;
    	
    }
    
	@Override
	public Image getImage() {	
		return image;
	}
	@Override
	public int getHealth() {
		return this.health;
	}
	public int getMunition() {
		return this.munition;
	}
	public int getShield() {
		return this.shield;
	}
	@Override
	public int getX() {
		return this.x;
	}
	@Override
	public int getY() {
		return this.y;
	}


	@Override
	public Rectangle getBounds() {
			return new Rectangle(x, y, width, height);
	}


	public void isOnStarbase(Starbase sb) {
		if(this.health <= Config.START_HEALTH) this.health = this.health + sb.getHealtRate();
		if(this.shield <= Config.START_SHIELD) this.shield = this.shield + sb.getShieldRate();
		if(this.munition <= Config.START_MUNITION)  this.munition = this.munition + sb.getMunitionRate();
		
	}
}
