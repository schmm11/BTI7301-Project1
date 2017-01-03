package first;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;
public class Board extends JPanel implements ActionListener{

	private Timer timer;
	private Enterprise enterprise;
	private ArrayList<Enemy> enemys;
	private boolean ingame;
	private ArrayList<Missile> missiles;
	private ArrayList<Starbase> starbases;
	
	public Board(){
		initBoard();
	}

	private void initBoard(){
		addKeyListener(new TAdapter());
		setFocusable(true);
		setBackground(Color.BLACK);
		ingame=true;
		setPreferredSize(new Dimension(Config.BOARDSIZE_X, Config.BOARDSIZE_Y));
		//Inint ArrayLists
		enemys = new ArrayList<>();
		starbases = new ArrayList<>();
		//Init Own Ship
		enterprise = new Enterprise(Config.ICRAFT_WITDH, Config.ICRAFT_HEIGHT);
	
		//Init StarBase
		starbases.add(new Starbase(300,300));
		
		//Init Enemys
		enemys.add(new Enemy(600,600));
		enemys.add(new Enemy(700,600));
		//enemys.add(new Enemy(500,600));
		enemys.add(new Enemy(300,600));
		enemys.add(new Enemy(200,600));
		
		//Init Timer
		timer = new Timer(Config.DELAY, this);
		timer.start();
	}

	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if(checkVictory()){
        	drawGameWon(g);
        }
        
        else if (ingame && enterprise.getHealth() > 1) {
            drawObjects(g);
        }
        
        else {
            drawGameOver(g);
        }

        Toolkit.getDefaultToolkit().sync();
    }
	private boolean checkVictory(){
		boolean victory = true;
        for(Enemy e: enemys){
        	if(!e.isDestroyed()) victory = false;
        }
        return victory;
		
	}
	private void drawObjects(Graphics g){
		//Draw Starbase
		for(Starbase sb : starbases){
			g.drawImage(sb.getImage(), sb.getX(), sb.getY(), this);
		}
		
		//Draw Enterprise
		g.drawImage(enterprise.getImage(),  enterprise.getX(),  enterprise.getY(), this);	
		
		//Draw Missiles from the enterprise
		ArrayList<Missile> ms = enterprise.getMissiles();		
		for (Missile m : ms) {
            if (m.isAlive()) {
                g.drawImage(m.getImage(), m.getX(), m.getY(), this);
                m.move();
            }
		}
        
		
		//draw Enemys
		for (Enemy enemy:enemys){
			g.drawImage(enemy.getImage(), enemy.getX(), enemy.getY(), this);		
			// Draw enemy rockets
			for (Missile m : enemy.getMissiles()){
				g.drawImage(m.getImage(), m.getX(), m.getY(), this);
                m.move();
			}
		}
		
		
		//Draw Status
		 g.setColor(Color.WHITE);
		 g.drawString("Health: "+ enterprise.getHealth(), 5, 15);
		 g.drawString("Shield: "+ enterprise.getShield(), 5, 25);
		 g.drawString("Munition: "+ enterprise.getMunition(), 5, 35);
		 g.drawString("Position: x="+ enterprise.getX()+ "  y="+ enterprise.getY(), 5, 45);
	}
	
	
	private void drawGameOver(Graphics g) {

        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 30);
        FontMetrics fm = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, Config.BOARDSIZE_X/2,Config.BOARDSIZE_Y/2);
    }
	private void drawGameWon(Graphics g) {

        String msg = "SIEG HEIL!";
        Font small = new Font("Helvetica", Font.BOLD, 30);
        FontMetrics fm = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, Config.BOARDSIZE_X/2,Config.BOARDSIZE_Y/2);
    }
	
	
	
	private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            enterprise.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            enterprise.keyPressed(e);
        }
    }





	@Override
	public void actionPerformed(ActionEvent arg0) {
		updateCraft();
		for(Enemy enemy : enemys){
			updateEnemy(enemy);
		}
		
       // updateMissiles();

        checkCollisions();

        repaint();
		
	}
	public void checkCollisions(){
		Rectangle rEnterprise = enterprise.getBounds();
		
		// Enterprise hit Ship
		for(Enemy enemy : enemys){
			Rectangle rEnemy = enemy.getBounds();
			if (rEnterprise.intersects(rEnemy)){
				enterprise.onCollision(Config.DMG_ON_COLLISION);
				enemy.onCollision(Config.DMG_ON_COLLISION);
			}
			
			
		}
		
		//Enterprise hits a StarBase	
		
		for(Starbase sb : starbases){
			Rectangle rSb = sb.getBounds();
			if(rEnterprise.intersects(rSb)){
				enterprise.isOnStarbase(sb);
			}
		}
		
		// Enemy get Hit by rocket 
		missiles = enterprise.getMissiles();
		for(Missile m : missiles){
			if (m.isAlive()){
				Rectangle rMissile = m.getBounds();
				for(Enemy enemy : enemys){
					Rectangle rEnemy = enemy.getBounds();
					if(rMissile.intersects(rEnemy)){
						m.destroy();
						enemy.onHit(m.getDMG());
						//if(enemy.isDestroyed()) enemys.remove(enemy);
					}
					/* Enterprise hits itself, doesn't work
					if(rMissile.intersects(rEnterprise)){
						enterprise.onHit(m.getDMG());
					}
					*/
				}
			}
		}
		
		// Enterprise gets hits by a rocket
		for(Enemy enemy : enemys){
			for(Missile m : enemy.getMissiles()){
				if(m.isAlive()){
					Rectangle rMissile = m.getBounds();
					if(rMissile.intersects(rEnterprise)){
						m.destroy();
						enterprise.onHit(m.getDMG());
					}
				}
			}
		}
	}
	
	
    private void updateCraft() {
    	enterprise.move();    
    }
    private void updateEnemy(Enemy enemy){
    	enemy.move(enterprise.getX(), enterprise.getY());	
    }
	
}
