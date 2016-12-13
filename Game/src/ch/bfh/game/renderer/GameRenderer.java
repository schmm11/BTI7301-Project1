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
		System.out.print(posX );
		System.out.print(posY );
		System.out.print(xmap );
		System.out.println(ymap );
		
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
	
	

	
}
