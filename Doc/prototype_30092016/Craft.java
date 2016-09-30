import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Craft extends Sprite {

	public int health = 100;
	public int shield = 100;;
	public int munition  =200;;
    private ArrayList<Missile> missiles;
    boolean bUp,bDown,bLeft,bRight;
    public int direction = 1; // 1 = right, 2 = down, 3 = left, 4 = right
    int ySpeed = 0;
    int xSpeed = 0;
    
    
    public Craft(int x, int y) {
        super(x, y);

        initCraft();
    }

    private void initCraft() {
        
        missiles = new ArrayList<>();
        loadImage("src/media/Enterprise_right.png");
        getImageDimensions();
        
    }

    public void move() {
    	if(bRight){
    		//xSpeed = xSpeed +1;
    		//x = (x + (xSpeed));
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
        
        
        if (x < 1) {
            x = 1;
        }

        if (y < 1) {
            y = 1;
        }
        
    }

    private void rotateLeft() {
		// TODO Auto-generated method stub
		
	}

	public ArrayList getMissiles() {
        return missiles;
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();
        if (key == KeyEvent.VK_SPACE) {
            fire();
            
            
        }
        if (key == KeyEvent.VK_Q || key == KeyEvent.VK_E) {
        	dash();  
        }

        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
        	bLeft = true;
        	direction = 3;
        	
        }

        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
        	bRight = true;
        	direction = 1;

        }

        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
        	bUp = true;
        	direction = 4;

        }

        if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
        	bDown = true;
        	direction = 2;

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

	public void fire() {
        
		if(munition >= 1){
		missiles.add(new Missile(x + width, y + height / 2, direction));
        munition--;
		}
		else {
			// Play "click Click" Sound
			
		}
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
}