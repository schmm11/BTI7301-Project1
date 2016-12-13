package ch.bfh.game.renderer;


import javax.sound.sampled.*;

public class MainSound {
    private Clip clip;

	public MainSound (String fileName) {
		System.out.println("dfjkssdlf");
	        try {
	            AudioInputStream ais = AudioSystem.getAudioInputStream(MainSound.class.getResource("assets/sound"+fileName));
	            clip = AudioSystem.getClip();
	            clip.open(ais);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    public void play() {
	        try {
	            if (clip != null) {
	                new Thread() {
	                    public void run() {
	                        synchronized (clip) {
	                            clip.stop();
	                            clip.setFramePosition(0);
	                            clip.start();
	                        }
	                    }
	                }.start();
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    public void stop(){
	        if(clip == null) return;
	        clip.stop();
	    }
	    public void loop() {
	        try {
	            if (clip != null) {
	                new Thread() {
	                    public void run() {
	                        synchronized (clip) {
	                            clip.stop();
	                            clip.setFramePosition(0);
	                            clip.loop(Clip.LOOP_CONTINUOUSLY);
	                        }
	                    }
	                }.start();
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
     
	    public boolean isActive(){
	        return clip.isActive();
	     }
}