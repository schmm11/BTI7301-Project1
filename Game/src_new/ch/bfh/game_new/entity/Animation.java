package ch.bfh.game_new.entity;

import java.awt.image.BufferedImage;
import java.util.spi.CurrencyNameProvider;

public class Animation {

	// images
	private BufferedImage[] frames;
	private int currentFrame;
	
	// timers
	private long startTime;
	private long delay;
	
	private boolean playedOnce;	// for Example: exploding animation only needs to play 1 time
	
	// constructor
	public Animation()
	{
		playedOnce = false;
	}
	
	/*
	 * sets the BufferedImage-array with frames for the animation
	 */
	public void setFrames(BufferedImage[] frames)
	{
		this.frames = frames;
		currentFrame = 0;
		startTime = System.nanoTime();
		playedOnce = false;
	}
	
	/*
	 * sets the delay between frames
	 */
	public void setDelay(long d)
	{
		delay = d;
	}
	
	/*
	 * sets the animation to a specific frame
	 */
	public void setFrame(int i)
	{
		currentFrame = i;
	}
	
	/*
	 * updates the frame, if delay-time has passed
	 */
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
	
	/*
	 * returns the number of the current frame of the animation
	 */
	public int getFrame()
	{
		return currentFrame;
	}
	
	/*
	 * returns the current image of the animation
	 */
	public BufferedImage getImage()
	{
		return frames[currentFrame];
	}
	
	/*
	 * returns true if the animation has played once (every frame has been used once)
	 */
	public boolean hasPlayedOnce()
	{
		return playedOnce;
	}
	
}
