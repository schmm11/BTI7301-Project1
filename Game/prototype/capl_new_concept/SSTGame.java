package capl_new_concept;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;


public final class SSTGame extends BasicGame
{
	private final Ship k_xShip = new Ship(250, 250, Direction.North);
	private List<Shot> k_lstShots = new ArrayList<>();

	private Image m_xShipImage;
	private Image[] m_rgAcidFrames;
	private int m_iFrame = 0;
	private boolean m_bShooting = false;

	private boolean m_bMoving = false;
	private long m_lShootTime = 0;


	@Override
	public void init()
	{
		try
		{
			m_xShipImage = ImageIO.read(getClass().getResourceAsStream("/capl_new_concept/res/ship.png")).getScaledInstance(32, 32, BufferedImage.SCALE_SMOOTH);
			final BufferedImage xAcidImages = ImageIO.read(getClass().getResourceAsStream("/capl_new_concept/res/acid.png"));
			m_rgAcidFrames = new Image[]
			{
				xAcidImages.getSubimage(0, 0, 16, 16).getScaledInstance(8, 8, BufferedImage.SCALE_SMOOTH),
				xAcidImages.getSubimage(16, 0, 16, 16).getScaledInstance(8, 8, BufferedImage.SCALE_SMOOTH),
				xAcidImages.getSubimage(32, 0, 16, 16).getScaledInstance(8, 8, BufferedImage.SCALE_SMOOTH),
			};
		}
		catch(final IOException xException)
		{
			xException.printStackTrace();
		}
		System.out.println("SuperStarTrek initialized");
	}

	@Override
	public void input(final List<KeyAction> lstActions)
	{
		for(final KeyAction xAction : lstActions)
		{
			switch(xAction.keyCode())
			{
			case KeyEvent.VK_W:
				k_xShip.setDirection(Direction.North);
				m_bMoving = xAction.pressed();
				break;
			case KeyEvent.VK_S:
				k_xShip.setDirection(Direction.South);
				m_bMoving = xAction.pressed();
				break;
			case KeyEvent.VK_A:
				k_xShip.setDirection(Direction.East);
				m_bMoving = xAction.pressed();
				break;
			case KeyEvent.VK_D:
				k_xShip.setDirection(Direction.West);
				m_bMoving = xAction.pressed();
				break;
			case KeyEvent.VK_SPACE:
				switch(xAction.action())
				{
				case Pressed:
					m_bShooting = true;
					break;
				case Released:
					m_bShooting = false;
					break;
				}
				break;
			}
		}
	}

	public void update(final double dSeconds)
	{
		final int iFrame = (int) (dSeconds * 4);
		m_iFrame = (iFrame % m_rgAcidFrames.length);

		if(m_bMoving)
		{
			k_xShip.move(1);
		}

		k_lstShots = k_lstShots.stream().filter(Shot::active).collect(Collectors.toList());
		k_lstShots.forEach(Shot::update);

		if(m_bShooting && (System.nanoTime() - m_lShootTime) * 1e-9 > 0.25)
		{
			int iXOffset = 0;
			int iYOffset = 0;
			int iXWingOffset = 0;
			int iYWingOffset = 0;
			int iXCentralOffset = 0;
			int iYCentralOffset = 0;
			switch(k_xShip.direction())
			{
			case North:
				iYOffset -= 8;
				iXWingOffset = 16;
				iYCentralOffset -= 8;
				break;
			case South:
				iYOffset -= 8;
				iXWingOffset = 16;
				iYCentralOffset += 8;
				break;
			case West:
				iXOffset += 8;
				iYWingOffset = 16;
				iXCentralOffset += 8;
				break;
			case East:
				iXOffset -= 8;
				iYWingOffset = 16;
				iXCentralOffset -= 8;
				break;
			}
			k_lstShots.add(new Shot(
					k_xShip.x() - 4 + iXOffset + iXWingOffset,
					k_xShip.y() - 4 + iYOffset + iYWingOffset,
					k_xShip.direction()));
			k_lstShots.add(new Shot(
					k_xShip.x() - 4 + iXOffset - iXWingOffset,
					k_xShip.y() - 4 + iYOffset - iYWingOffset,
					k_xShip.direction()));
			k_lstShots.add(new Shot(
					k_xShip.x() - 4 + iXOffset + iXCentralOffset,
					k_xShip.y() - 4 + iYOffset + iYCentralOffset,
					k_xShip.direction()));
			m_lShootTime = System.nanoTime();
		}
	}

	@Override
	public void render(final Graphics2D xGraphics, final Rectangle2D xBounds)
	{
		xGraphics.setColor(Color.BLACK);
		xGraphics.fill(xBounds);

		final Graphics2D xShipGraphics = (Graphics2D) xGraphics.create();
		xShipGraphics.translate(k_xShip.x(), k_xShip.y());
		switch(k_xShip.direction())
		{
		case North:
			xShipGraphics.rotate(Math.toRadians(0));
			break;
		case South:
			xShipGraphics.rotate(Math.toRadians(180));
			break;
		case East:
			xShipGraphics.rotate(Math.toRadians(270));
			break;
		case West:
			xShipGraphics.rotate(Math.toRadians(90));
			break;
		}
		xShipGraphics.drawImage(m_xShipImage, -16, -16, null);
		k_lstShots.forEach(xShot -> xGraphics.drawImage(m_rgAcidFrames[m_iFrame], xShot.x(), xShot.y(), null));
	}
}
