package ch.bfh.game.renderer;


import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import javax.imageio.ImageIO;


public final class ImageCollection
{
	public static final ImageCollection IMAGES = createResourceCollection("images");

	private static ImageCollection createResourceCollection(final String strResourceDirectory)
	{
		try
		{
			final URI xURI = ClassLoader.getSystemResource(strResourceDirectory).toURI();
			final Path xPath = Paths.get(xURI);
			return new ImageCollection(Files.list(xPath));
		}
		catch(final URISyntaxException | IOException xException)
		{
			xException.printStackTrace();
			return null;
		}
	}


	private final Map<String, BufferedImage> k_mapImages = new HashMap<>();


	public ImageCollection(final Stream<Path> xFiles)
	{
		xFiles.forEach(xFile ->
		{
			try
			{
				final String strName = xFile.getFileName().toString();
				final BufferedImage xImage = ImageIO.read(xFile.toFile());
				k_mapImages.put(strName, xImage);
			}
			catch(final IOException xException)
			{
				//@TODO 
				//Throws errors....
				//xException.printStackTrace();
			}
		});
	}

	public Optional<BufferedImage> getImage(final String strIdentifier)
	{
		return Optional.ofNullable(k_mapImages.get(strIdentifier));
	}

	public boolean hasImage(final String strIdentifier)
	{
		return (strIdentifier != null && k_mapImages.containsKey(strIdentifier));
	}
}
