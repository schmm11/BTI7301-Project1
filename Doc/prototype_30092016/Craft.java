import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Craft extends Sprite {


    private ArrayList<Missile> missiles;
    boolean bUp,bDown,bLeft,bRight;
    int ySpeed = 0;
    int xSpeed = 0;

    
    public Craft(int x, int y) {
        super(x, y);

        initCraft();
    }

    private void initCraft() {
        
        missiles = new ArrayList<>();
        loadImage("src/media/Enterprise_forward.png");
        getImageDimensions();
        
    }

    public void move() {
    	if(bRight){
    		//xSpeed = xSpeed +1;
    		//x = (x + (xSpeed));
    		++x;
    		loadImage("src/media/Enterprise_forward.png");
    	}	
        if (bLeft){
        	--x;
        	loadImage("src/media/Enterprise_back.png");
        } 
        if (bUp) {
        	--y;
        	loadImage("src/media/Enterprise_up.png");
        	
        }        	    
        if (bDown){
        	++y;
        	loadImage("src/media/Enterprise_down.png");
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
        }

        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
        	bRight = true;
        }

        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
        	bUp = true;
        }

        if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
        	bDown = true;
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
        missiles.add(new Missile(x + width, y + height / 2));
    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
        	bLeft = false;
        }

        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
        	bRight = false;      	
        }

        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
        	bUp = false;
        }

        if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
        	bDown = false;
        }
    }
}