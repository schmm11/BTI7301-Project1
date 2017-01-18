package capl_first_design.FirstDesign.main;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.TexturePaint;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import capl_first_design.FirstDesign.controller.UserInputComponent;
import capl_first_design.FirstDesign.modell.LaserItem;
import capl_first_design.FirstDesign.modell.Ship;
import capl_first_design.FirstDesign.modell.SpaceObject;
import capl_first_design.FirstDesign.view.EnterpriseModel;


public final class Galaxy
{
	private final static int k_iWidth = 500;
	private final static int k_iHeight = 500;

	private List<SpaceObject> m_lstObjects = new ArrayList<SpaceObject>();
	private final Ship k_xEnterprise;
	private JFrame k_xFrame;
	private BufferedImage m_xBackground = null;
	private double m_dCameraX = 0.0;
	private double m_dCameraY = 0.0;


	public Galaxy()
	{
		k_xFrame = new JFrame();
		k_xEnterprise = new Ship(0.0, 0.0, 100, 100, 100, new EnterpriseModel(), new UserInputComponent(k_xFrame), new LaserItem(100));
		m_lstObjects.add(k_xEnterprise);
		try
		{
			m_xBackground = ImageIO.read(getClass().getResourceAsStream("/capl_first_design/FirstDesign/res/images/background.jpg"));
		}
		catch(final IOException xException)
		{
			xException.printStackTrace();
		}

		k_xFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		k_xFrame.setContentPane(new JPanel()
		{
			private static final long serialVersionUID = -5914587930930459913L;


			@Override
			public Dimension getPreferredSize()
			{
				return new Dimension(k_iWidth, k_iHeight);
			}

			@Override
			public void paintComponent(final Graphics xGraphics)
			{
				final Graphics2D xGraphics2D = (Graphics2D) xGraphics.create();

				xGraphics2D.clearRect(0, 0, getWidth(), getHeight());

				render(xGraphics2D);
			}
		});
		k_xFrame.setResizable(false);
		k_xFrame.pack();
		k_xFrame.setLocationRelativeTo(null);
		k_xFrame.setVisible(true);

		new Thread(this::gameLoop).start();
	}

	public void gameLoop()
	{
		while(true)
		{
			final long lStart = System.currentTimeMillis();
			processInput();
			update();
			k_xFrame.repaint();

			try
			{
				Thread.sleep(lStart + 16l - System.currentTimeMillis());
			}
			catch(final InterruptedException xException)
			{
				xException.printStackTrace();
			}
		}
	}

	public void processInput()
	{
	}

	public void update()
	{
		m_lstObjects.forEach(xObject -> xObject.update(this));
		m_lstObjects = m_lstObjects.stream().filter(SpaceObject::alive).collect(Collectors.toList());
	}

	public void render(final Graphics2D xGraphics)
	{
		final TexturePaint xPaint = new TexturePaint(m_xBackground, new Rectangle2D.Double(-m_dCameraX, -m_dCameraY, k_iWidth, k_iHeight));
		xGraphics.setPaint(xPaint);
		xGraphics.fillRect(0, 0, k_iWidth, k_iHeight);

		final Graphics2D xGraphics2D = (Graphics2D) xGraphics.create();
		xGraphics2D.translate(k_iWidth / 2, k_iHeight / 2);

		final double dSpringForceX = 0.5 * (k_xEnterprise.x() - m_dCameraX);
		final double dSpringForceY = 0.5 * (k_xEnterprise.y() - m_dCameraY);

		m_dCameraX += dSpringForceX * 0.5;
		m_dCameraY += dSpringForceY * 0.5;

		xGraphics2D.translate((int) m_dCameraX, (int) m_dCameraY);

		m_lstObjects.forEach(xObject -> xObject.render(this, xGraphics2D));
	}
}
