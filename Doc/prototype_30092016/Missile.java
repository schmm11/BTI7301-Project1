public class Missile extends Sprite {

    private final int BOARD_WIDTH = 800;
    private final int MISSILE_SPEED = 25;
    private int dir;
   

    public Missile(int x, int y, int dir) {
        super(x, y);
        this.dir = dir;
        
        initMissile();
    }
    
    private void initMissile() {
        
        loadImage("src/media/missile.png");
        getImageDimensions();        
    }

    public void move() {
        if(dir == 1) x += MISSILE_SPEED;
        if(dir == 2) y += MISSILE_SPEED;
        if(dir == 3) x -= MISSILE_SPEED;
        if(dir == 4) y -= MISSILE_SPEED;
        
        if (x > BOARD_WIDTH)
            vis = false;
    }
}