package spring_camera;


import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.TexturePaint;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;


public final class GridDraw extends JPanel implements Runnable
{
	private static final long serialVersionUID = 1L;


	private static final int k_iWidth = 1000;
	private static final int k_iHeight = 1000;

	private static final double k_dGridMinX = -2.5;
	private static final double k_dGridMaxX = 2.5;
	private static final double k_dGridMinY = -2.5;
	private static final double k_dGridMaxY = 2.5;

	private static final List<Character> k_rgXChars = IntStream.range('A', 'Z' + 1).boxed().map(i -> (char) i.intValue()).collect(Collectors.toList());
	private static final List<Character> k_rgYChars = IntStream.range('1', '9' + 1).boxed().map(i -> (char) i.intValue()).collect(Collectors.toList());

	private static final double k_dSpringConstant = 0.5;

	private final Listener k_xListener = new Listener();

	private final BufferedImage k_xImage;
	private final BufferedImage k_xEnterprise;

	private boolean m_bBG = true;

	// Camera
	private double m_dCX = 0.0;
	private double m_dCY = 0.0;

	// Ship
	private double m_dAngle = 0.0;
	private double m_dSpeed = 0.0;

	private double m_dX = 0.0;
	private double m_dY = 0.0;


	public static void main(final String[] rgArgs) throws Exception
	{
		new GridDraw();
	}

	public GridDraw() throws Exception
	{
		setPreferredSize(new Dimension(k_iWidth, k_iHeight));

		k_xImage = ImageIO.read(getClass().getResourceAsStream("/spring_camera/res/image.jpg"));
		k_xEnterprise = ImageIO.read(getClass().getResourceAsStream("/spring_camera/res/enterprise.png"));

		final JFrame xFrame = new JFrame();
			xFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			xFrame.setContentPane(this);
			xFrame.setResizable(false);
			xFrame.pack();
			xFrame.setLocationRelativeTo(null);
			xFrame.addKeyListener(k_xListener);
			xFrame.setVisible(true);

		new Thread(this).start();
	}

	@Override
	public void run()
	{
		while(true)
		{
			repaint();

//			if(k_xListener.pressed(KeyEvent.VK_W)) dDistance += 0.05;
//			if(k_xListener.pressed(KeyEvent.VK_S)) dDistance -= 0.05;
			if(k_xListener.pressed(KeyEvent.VK_W)) if(m_dSpeed == 0.0) m_dSpeed += 0.0001; else m_dSpeed *= 1.1;
			if(k_xListener.pressed(KeyEvent.VK_S)) m_dSpeed *= 0.9;
			m_dSpeed = Math.min(Math.max(m_dSpeed, 0.0), 0.1);
			if(k_xListener.pressed(KeyEvent.VK_A)) m_dAngle -= 0.05;
			if(k_xListener.pressed(KeyEvent.VK_D)) m_dAngle += 0.05;

			m_dX += (m_dSpeed * Math.cos(m_dAngle));
			m_dY += (m_dSpeed * Math.sin(m_dAngle));

			final double dForceX = -k_dSpringConstant * (m_dCX - m_dX);
			final double dForceY = -k_dSpringConstant * (m_dCY - m_dY);

			m_dCX += dForceX * 0.1;
			m_dCY += dForceY * 0.1;

			try
			{
				Thread.sleep(16);
			}
			catch(final InterruptedException xException)
			{
				xException.printStackTrace();
			}
		}
	}

	@Override
	protected final void paintComponent(final Graphics xGraphics)
	{
		super.paintComponent(xGraphics);
		final Graphics2D xGraphics2D = (Graphics2D) xGraphics.create();

		xGraphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		if(m_bBG)
		{
			TexturePaint xPaint = new TexturePaint(k_xImage, new Rectangle2D.Double(-m_dX * 10, -m_dY * 10, k_iWidth, k_iHeight));
			xGraphics2D.setPaint(xPaint);
		}
		else
		{
			xGraphics2D.setColor(Color.BLACK);
		}
		xGraphics2D.fillRect(0, 0, getWidth(), getHeight());

		if(!m_bBG)
		{
    		// horizontal
    		for(int iValue = (int) (m_dCY + k_dGridMinY); iValue <= (int) (m_dCY + k_dGridMaxY); ++iValue)
    		{
    			xGraphics2D.setColor(iValue == 0 || Math.floorMod(iValue, k_rgYChars.size()) == 0 ? Color.LIGHT_GRAY : Color.DARK_GRAY);
    			final int iY = convertY(iValue);
    			for(int i = (int) (m_dCX + k_dGridMinX) - 1; i <= (int) (m_dCX + k_dGridMaxX); ++i)
    			{
    				final int iMinX = convertX(i + 0.25);
    				final int iMaxX = convertX(i + 0.75);
    				xGraphics2D.drawLine(iMinX, iY, iMaxX, iY);
    			}
    		}

    		// vertical
    		for(int iValue = (int) (m_dCX + k_dGridMinX); iValue <= (int) (m_dCX + k_dGridMaxX); ++iValue)
    		{
    			xGraphics2D.setColor(iValue == 0 || Math.floorMod(iValue, k_rgXChars.size()) == 0 ? Color.LIGHT_GRAY : Color.DARK_GRAY);
    			final int iX = convertX(iValue);
    			for(int i = (int) (m_dCY + k_dGridMinY) - 1; i <= (int) (m_dCY + k_dGridMaxY); ++i)
    			{
    				final int iMinY = convertY(i + 0.25);
    				final int iMaxY = convertY(i + 0.75);
    				xGraphics2D.drawLine(iX, iMinY, iX, iMaxY);
    			}
    		}

    		xGraphics2D.setColor(Color.WHITE);
    		xGraphics2D.setFont(new Font("sans-serif", Font.PLAIN, 16));
    		// letters
    		for(int iY = (int) (m_dCY + k_dGridMinY); iY <= (int) (m_dCY + k_dGridMaxY); ++iY)
    		{
    			for(int iX = (int) (m_dCX + k_dGridMinX); iX <= (int) (m_dCX + k_dGridMaxX); ++iX)
    			{
    				final String strText = String.format("%c%c", k_rgXChars.get(Math.floorMod(iX, k_rgXChars.size())), k_rgYChars.get(Math.floorMod(iY, k_rgYChars.size())));
    				final FontMetrics xMetrics = xGraphics2D.getFontMetrics();
    				final int iConvertX = convertX(iX) - xMetrics.stringWidth(strText) / 2;
    				final int iConvertY = convertY(iY);
    				xGraphics2D.drawString(strText, iConvertX, iConvertY);
    			}
    		}
		}

		final double dX = (m_dCX - m_dX);
		final double dY = (m_dCY - m_dY);
		final Composite xTmp = xGraphics2D.getComposite();
		if(m_bBG)
		{
			if(dX > 0.75 && dY > 0.75)
			{
				final float fLength = (float) Math.sqrt(dX*dX + dY*dY) - 1.4f;
				xGraphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f - (fLength * 2.0f)));
			}
    		xGraphics2D.setColor(Color.WHITE);
    		xGraphics2D.fillOval(-1, -1, k_iWidth / 4 + 2, k_iHeight / 4 + 2);
    		xGraphics2D.clip(new Ellipse2D.Double(0, 0, k_iWidth / 4, k_iHeight / 4));
    		xGraphics2D.drawImage(drawOn(), 0, 0, k_iWidth / 4, k_iHeight / 4, null);
    		xGraphics2D.setClip(null);
		}
		xGraphics2D.setComposite(xTmp);

		if(m_bBG)
		{
			xGraphics2D.translate(convertX(m_dX), convertY(m_dY));
			xGraphics2D.rotate(m_dAngle);
			xGraphics2D.drawImage(k_xEnterprise, -50, -25, 100, 50, null);
		}
		else
		{
    		xGraphics2D.setColor(Color.RED);
    		final int[] rgXCoordinates = poly(m_dAngle, Math::cos, this::convertX, m_dX);
    		final int[] rgYCoordinates = poly(m_dAngle, Math::sin, this::convertY, m_dY);
    		final Polygon xPoly = new Polygon(rgXCoordinates, rgYCoordinates, 3);
    		xGraphics2D.fill(xPoly);
		}
	}

	private BufferedImage drawOn()
	{
		final BufferedImage xImage = new BufferedImage(k_iWidth, k_iHeight, BufferedImage.TYPE_INT_RGB);
		final Graphics2D xGraphics2D = xImage.createGraphics();

		xGraphics2D.setColor(Color.BLACK);
		xGraphics2D.fillRect(0, 0, getWidth(), getHeight());

		xGraphics2D.setStroke(new BasicStroke(4.0f));
		// horizontal
		for(int iValue = (int) (m_dCY + k_dGridMinY); iValue <= (int) (m_dCY + k_dGridMaxY); ++iValue)
		{
			xGraphics2D.setColor(iValue == 0 || Math.floorMod(iValue, k_rgYChars.size()) == 0 ? Color.LIGHT_GRAY : Color.DARK_GRAY);
			final int iY = convertY(iValue);
			for(int i = (int) (m_dCX + k_dGridMinX) - 1; i <= (int) (m_dCX + k_dGridMaxX); ++i)
			{
				final int iMinX = convertX(i + 0.25);
				final int iMaxX = convertX(i + 0.75);
				xGraphics2D.drawLine(iMinX, iY, iMaxX, iY);
			}
		}

		// vertical
		for(int iValue = (int) (m_dCX + k_dGridMinX); iValue <= (int) (m_dCX + k_dGridMaxX); ++iValue)
		{
			xGraphics2D.setColor(iValue == 0 || Math.floorMod(iValue, k_rgXChars.size()) == 0 ? Color.LIGHT_GRAY : Color.DARK_GRAY);
			final int iX = convertX(iValue);
			for(int i = (int) (m_dCY + k_dGridMinY) - 1; i <= (int) (m_dCY + k_dGridMaxY); ++i)
			{
				final int iMinY = convertY(i + 0.25);
				final int iMaxY = convertY(i + 0.75);
				xGraphics2D.drawLine(iX, iMinY, iX, iMaxY);
			}
		}

		xGraphics2D.setColor(Color.WHITE);
		xGraphics2D.setFont(new Font("sans-serif", Font.PLAIN, 16));
		// letters
		for(int iY = (int) (m_dCY + k_dGridMinY); iY <= (int) (m_dCY + k_dGridMaxY); ++iY)
		{
			for(int iX = (int) (m_dCX + k_dGridMinX); iX <= (int) (m_dCX + k_dGridMaxX); ++iX)
			{
				final String strText = String.format("%c%c", k_rgXChars.get(Math.floorMod(iX, k_rgXChars.size())), k_rgYChars.get(Math.floorMod(iY, k_rgYChars.size())));
				final FontMetrics xMetrics = xGraphics2D.getFontMetrics();
				final int iConvertX = convertX(iX) - xMetrics.stringWidth(strText) / 2;
				final int iConvertY = convertY(iY);
				xGraphics2D.drawString(strText, iConvertX, iConvertY);
			}
		}

		xGraphics2D.setColor(Color.RED);
		final int[] rgXCoordinates = poly(m_dAngle, Math::cos, this::convertX2, m_dX);
		final int[] rgYCoordinates = poly(m_dAngle, Math::sin, this::convertY2, m_dY);
		final Polygon xPoly = new Polygon(rgXCoordinates, rgYCoordinates, 3);
		xGraphics2D.fill(xPoly);

		return xImage;
	}

	private int[] poly(final double dAngle, final Function<Double, Double> xTrig, final Function<Double, Integer> xConvert, final double dOffset)
	{
		return new int[]
		{
			xConvert.apply(0.4 * xTrig.apply(Math.toRadians(0) + dAngle) + dOffset),
			xConvert.apply(0.2 * xTrig.apply(Math.toRadians(120) + dAngle) + dOffset),
			xConvert.apply(0.2 * xTrig.apply(Math.toRadians(240) + dAngle) + dOffset),
		};
	}

	private int convertX2(double dValue)
	{
		final double dGridX = (k_dGridMaxX - k_dGridMinX);
		return (int) ((dValue - k_dGridMinX - m_dX) * (getWidth() / dGridX));
	}

	private int convertY2(double dValue)
	{
		final double dGridY = (k_dGridMaxY - k_dGridMinY);
		return (int) ((dValue - k_dGridMinY - m_dY) * (getHeight() / dGridY));
	}

	private int convertX(double dValue)
	{
		final double dGridX = (k_dGridMaxX - k_dGridMinX);
		return (int) ((dValue - k_dGridMinX - m_dCX) * (getWidth() / dGridX));
	}

	private int convertY(double dValue)
	{
		final double dGridY = (k_dGridMaxY - k_dGridMinY);
		return (int) ((dValue - k_dGridMinY - m_dCY) * (getHeight() / dGridY));
	}

	private class Listener extends KeyAdapter
	{
		private final boolean[] k_rgKeys = new boolean[256];


		@Override
		public void keyPressed(final KeyEvent xEvent)
		{
			final int iKeyCode = xEvent.getKeyCode();
			k_rgKeys[iKeyCode] = true;
			if(iKeyCode == KeyEvent.VK_SPACE)
				m_bBG = !m_bBG;
		}

		@Override
		public void keyReleased(final KeyEvent xEvent)
		{
			final int iKeyCode = xEvent.getKeyCode();
			k_rgKeys[iKeyCode] = false;
		}

		public boolean pressed(final int iKeyCode)
		{
			return k_rgKeys[iKeyCode];
		}
	}
}
