package ch.bfh.game.renderer;


import java.awt.Graphics2D;
import java.awt.TexturePaint;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Optional;

import ch.bfh.game.modell.SpaceObject;

import ch.bfh.game.main.ArgumentChecker;
import ch.bfh.game.main.Config;
import ch.bfh.game.modell.Player;
import ch.bfh.game.modell.Projectile;


public final class GameRenderer
{
	
	/*
	 * Super function: renders SpaceObject
	 */
	public static <T extends SpaceObject> void render(final Graphics2D xGraphics, final SpaceObject xObject)
	{
		ArgumentChecker.requireNonNull(xGraphics, "Graphics musn't be null");
		ArgumentChecker.requireNonNull(xObject, "SpaceObject musn't be null");
		
}
	/*
	 * Render a Player 
	 * @param: Player: the Player which should be rendered
	 */
	public static <T extends SpaceObject> void render(final Graphics2D xGraphics, final Player xObject)
	{
		ArgumentChecker.requireNonNull(xGraphics, "Graphics musn't be null");
		ArgumentChecker.requireNonNull(xObject, "SpaceObject musn't be null");
		
		BufferedImage img = xObject.getAnimation().getImage();
		
		int posX = xObject.getx();
		int posY = xObject.gety();
		Double xmap = xObject.getXMap();
		Double ymap = xObject.getYMap();
		int width = xObject.getWidth();
		int height = xObject.getHeight();
		int angle = xObject.getAngle();
		
		AffineTransform at = AffineTransform.getTranslateInstance(posX + xmap - width / 2 , posY + ymap - height / 2);
		at.rotate(Math.toRadians(angle), width / 2, height / 2);
	
		xGraphics.drawImage(img, at, null);
		
		//Draw Player Status
		renderPlayerStatus(xGraphics, xObject);
	}
	
	/*
	 * Render a Projectile 
	 * @param: Projectile: the projectile which should be rendered
	 */
	public static <T extends SpaceObject> void render(final Graphics2D xGraphics, final Projectile xObject)
	{
		ArgumentChecker.requireNonNull(xGraphics, "Graphics musn't be null");
		ArgumentChecker.requireNonNull(xObject, "SpaceObject musn't be null");
		
		BufferedImage img = xObject.getAnimation().getImage();
		
		int posX = xObject.getx();
		int posY = xObject.gety();
		Double xmap = xObject.getXMap();
		Double ymap = xObject.getYMap();
		int width = xObject.getWidth();
		int height = xObject.getHeight();
		
		AffineTransform at = AffineTransform.getTranslateInstance(posX + xmap - width / 2 , posY + ymap - height / 2);
		
		xGraphics.drawImage(img, at, null);
	}
	/*
	 * @function Renders the Background in the menu
	 */
	public static void renderBackground(final Graphics2D xGraphics)
	{
		final Optional<BufferedImage> xBackgroundImage = ImageCollection.IMAGES.getImage("background_menu.jpg");

		xBackgroundImage.ifPresent(xImage ->
		{
			xGraphics.setPaint(new TexturePaint(xImage, new Rectangle2D.Double(-Config.GAMEPANEL_WIDTH / 2, 0, Config.GAMEPANEL_WIDTH * 2, Config.GAMEPANEL_HEIGHT)));
			xGraphics.fillRect(0, 0, Config.GAMEPANEL_WIDTH, Config.GAMEPANEL_HEIGHT);
		});
	}

	/*
	 * Renders the Health etc from the player
	 */
	private static void renderPlayerStatus(final Graphics2D xGraphics, final Player xObject){
		int xPos = 100;
		int yPos = 100;
		
		if(xObject.getTeam() == 0){
			xPos = 50;
			yPos = 50;
		}
		else if (xObject.getTeam() == 1){
			xPos = 1150;
			yPos = 50;
		}
		xGraphics.drawString( Integer.toString(xObject.getHealth()) , xPos, yPos);
	}

	
}
