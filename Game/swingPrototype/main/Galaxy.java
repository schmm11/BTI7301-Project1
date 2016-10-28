package main;


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
import javax.swing.JPanel;
import modell.*;
import controller.*;
import view.*;

public class Galaxy extends JPanel implements ActionListener{
	
	ArrayList<SpaceObject> objects = new ArrayList();
	private boolean ingame;
	UserInputComponent userInput = new UserInputComponent();
	public Galaxy(){
		
		initGalaxy();
	}

	private void initGalaxy() {
		// Add all starting objects
		objects.add(new Ship(10.01, 10.10, 100, 100, 100, new EnterpriseModell(), userInput, new LaserItem(100))); // Enterprise
		for( int i = 0; i<=10; i++){		
			for (int j = 0; j <=10; j++){
				objects.add(new Ship(i*40+100, j*40+100, 10,10,10, new EnemyModell(), new EnemyInputComponent(), new LaserItem(100)));
			}
		}
		this.addKeyListener(userInput);
		setFocusable(true);
		setVisible(true);
		setBackground(Color.BLACK);
		this.ingame = true;
		setPreferredSize(new Dimension(Config.BOARDSIZE_X, Config.BOARDSIZE_Y));	
	}
	
	public void gameLoop() throws InterruptedException
	{
			//Game Loop Pattern	with static 60fps
			long start = System.currentTimeMillis();
			this.processInput();
			this.update();
			this.render();
			long sleeptime = start + 16 -System.currentTimeMillis();
			System.out.println(sleeptime);
			if(sleeptime > 0) Thread.sleep(sleeptime);
		
}
	private void processInput() {
		
	}
	private void update(){
		for(SpaceObject o : objects){
			o.update(this);
		}
	}
	
	private void render(){	

		repaint();
	}

	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        //draw all objects
        for(SpaceObject o : objects){
        	//o.render(g);
        	if(o.getPosX() < 900 && o.getPosY() < 900){
        	g.drawImage(o.render(), (int)o.getPosX(), (int)o.getPosY(), this);	
        }     
        }
        Toolkit.getDefaultToolkit().sync();
    }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// doesnt need any Action
	}
}
