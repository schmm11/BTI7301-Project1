package main;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class Main extends JFrame {
	private Galaxy galaxy;
	
	public Main(){
		initUI();
	}	
		
		public void initUI(){
			galaxy = new Galaxy();
			add(galaxy);
			
			setResizable(false);
			pack();
			
			setTitle("SST Prototype");
	        setLocationRelativeTo(null);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
	public static void main(String[] args) {
		Main ex = new Main();
		EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                ex.setVisible(true);
                
                System.out.println(Thread.currentThread().getName());
            }
		});
		while(true){
			
			try {
				ex.galaxy.gameLoop();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	}
	
	

}
