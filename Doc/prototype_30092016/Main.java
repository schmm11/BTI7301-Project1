import java.awt.EventQueue;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;

public class Main extends JFrame {

	
	public Main(){
	initUI();
	}	
	
	public void initUI(){
		add(new Board());
		
		setResizable(false);
		pack();
		
		setTitle("SST Prototype");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		playMainSound("MainSound.wav");
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Main ex = new Main();
                ex.setVisible(true);
            }
		});
	}
	
	// Method to play sound in Loops
	public static synchronized void playMainSound(final String url) {
		  new Thread(new Runnable() {
		    public void run() {
		      try {
		        Clip clip = AudioSystem.getClip();
		        AudioInputStream inputStream = AudioSystem.getAudioInputStream(
		          Main.class.getResourceAsStream("media/"+url));
		        clip.open(inputStream);
		        
		        while(true){
		            clip.start();
		            clip.loop(clip.LOOP_CONTINUOUSLY);              
		        }  
		      } catch (Exception e) {
		        System.err.println(e.getMessage());
		      }
		    }
		  }).start();
		}

}