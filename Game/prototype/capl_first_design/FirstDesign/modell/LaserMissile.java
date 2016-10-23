package capl_first_design.FirstDesign.modell;


import java.awt.Graphics2D;

import capl_first_design.FirstDesign.main.Galaxy;


public final class LaserMissile implements Missile
{
	private double m_dPosX;
	private double m_dPosY;
	private int m_iDamage = 10;
	private boolean m_bAlive = true;

	public LaserMissile(final double dX, final double dY)
	{
		m_dPosX = dX;
		m_dPosY = dY;
	}

	@Override
	public double x()
	{
		return m_dPosX;
	}

	@Override
	public double y()
	{
		return m_dPosY;
	}

	@Override
	public boolean alive()
	{
		return m_bAlive;
	}

	@Override
	public void update(final Galaxy xGalaxy)
	{
	}

	@Override
	public void render(Galaxy xGalaxy, Graphics2D xGraphics)
	{
	}

}
