package ch.bfh.game.renderer;


import java.awt.Graphics2D;
import java.awt.TexturePaint;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Optional;

import ch.bfh.game.entities.SpaceObject;
import ch.bfh.game.main.ArgumentChecker;
import ch.bfh.game.main.Config;


public final class GameRenderer
{
	public static <T extends SpaceObject> void render(final Graphics2D xGraphics, final SpaceObject xObject)
	{
		ArgumentChecker.requireNonNull(xGraphics, "Graphics musn't be null");
		ArgumentChecker.requireNonNull(xObject, "SpaceObject musn't be null");
	}

	public static void renderBackground(final Graphics2D xGraphics)
	{
		final Optional<BufferedImage> xBackgroundImage = ImageCollection.IMAGES.getImage("background_menu.png");

		xBackgroundImage.ifPresent(xImage ->
		{
			xGraphics.setPaint(new TexturePaint(xImage, new Rectangle2D.Double(-Config.GAMEPANEL_WIDTH / 2, 0, Config.GAMEPANEL_WIDTH * 2, Config.GAMEPANEL_HEIGHT)));
			xGraphics.fillRect(0, 0, Config.GAMEPANEL_WIDTH, Config.GAMEPANEL_HEIGHT);
		});
	}
}
