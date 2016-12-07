package ch.bfh.game.modell;

import java.awt.image.BufferedImage;
import java.util.spi.CurrencyNameProvider;

public class Animation {

	private BufferedImage[] frames;
	private int currentFrame;
	
	private long startTime;
	private long delay;	// time between frames
	
	private boolean playedOnce;	// for Example: Attacking animation only needs to play 1 time
	
	public Animation()
	{
		playedOnce = false;
	}
	
	public void setFrames(BufferedImage[] frames)
	{
		this.frames = frames;
		currentFrame = 0;
		startTime = System.nanoTime();
		playedOnce = false;
	}
	
	public void setDelay(long d)
	{
		delay = d;
	}
	
	public void setFrame(int i)
	{
		currentFrame = i;
	}
	
	public void update()
	{
		if(delay == - 1)
		{
			return;
		}
		
		long elapsed = (System.nanoTime() - startTime) / 1000000;
		if(elapsed > delay)
		{
			currentFrame++;
			startTime = System.nanoTime();
		}
		
		if(currentFrame == frames.length)
		{
			currentFrame = 0;
			playedOnce = true;
		}
	}
	
	public int getFrame()
	{
		return currentFrame;
	}
	
	public BufferedImage getImage()
	{
		return frames[currentFrame];
	}
	
	public boolean hasPlayedOnce()
	{
		return playedOnce;
	}
	
}
