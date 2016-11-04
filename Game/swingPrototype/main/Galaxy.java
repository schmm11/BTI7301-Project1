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
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.JPanel;
import modell.*;
import controller.*;
import view.*;

public class Galaxy extends JPanel implements ActionListener{
	
	ArrayList<SpaceObject> objects = new ArrayList();
	ArrayList<LaserMissile> missiles = new ArrayList();
	ArrayList<SpaceObject> tempObjects = new ArrayList();
	
	private boolean ingame;
	
	int hitCounter1;
	int hitCounter2;
	
	UserInputComponent userInput = new UserInputComponent();
	UserInputComponent2 userInput2 = new UserInputComponent2();
	public Galaxy(){
		
		initGalaxy();
	}

	private void initGalaxy() {
		// Add all starting objects
		objects.add(new Ship(500, 200, 100, 100, 100, new EnterpriseModell(), userInput, new LaserItem(100, Color.RED))); // Enterprise
		objects.add(new Ship(100, 100, 100, 100, 100, new EnterpriseModell(), userInput2, new LaserItem(100, Color.BLUE))); // Enterprise
		
		for( int i = 0; i<=10; i++){		
			for (int j = 0; j <=10; j++){
				//objects.add(new Ship(i*40+100, j*40+100, 10,10,10, new EnemyModell(), new EnemyInputComponent(), new LaserItem(100)));
			}
		}
		this.addKeyListener(userInput);
		this.addKeyListener(userInput2);
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
			//System.out.println(sleeptime);
			if(sleeptime > 0) Thread.sleep(sleeptime);
}
	private void processInput() {
		
	}
	private void update(){
		synchronized(objects)
		{
			for(SpaceObject o : objects){
				o.update(this);
				
				// HIT Detection Test
				if (o instanceof LaserMissile) {
					if(o.getDimensions().intersects(objects.get(0).getDimensions())){
						hitCounter1 ++;
						((LaserMissile) o).setisAlive(false);
						
					}
					if(o.getDimensions().intersects(objects.get(1).getDimensions())){
						hitCounter2 ++;
						((LaserMissile) o).setisAlive(false);
						
					}
					System.out.println("Ship 1 Dmg: "+ hitCounter1 + "   Ship2 Dmg: "+ hitCounter2 );
				}
				
			}
				
				
			
			
			for(SpaceObject o : tempObjects){
				this.objects.add(o);
			}

			objects = (ArrayList<SpaceObject>) objects.stream().filter(SpaceObject::isAlive).collect(Collectors.toList());
		}
	}
	
	private void render(){	

		repaint();
	}

	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        //draw all objects
        synchronized(objects)
        {
	        for(SpaceObject o : objects){
	        	//o.render(g);
	        	if(o.getPosX() < 900 && o.getPosY() < 900)
	        	{
	        		g.drawImage(o.render(), (int)o.getPosX(), (int)o.getPosY(), this);	
	        	}
	        }
        }
        Toolkit.getDefaultToolkit().sync();
    }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// doesnt need any Action
	}
	public void setIngame(boolean ingame){
		this.ingame = ingame;
	}
	public void addSpaceObject(SpaceObject o){
		this.tempObjects.add(o);
	}
}
