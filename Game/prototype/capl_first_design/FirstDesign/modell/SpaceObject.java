package capl_first_design.FirstDesign.modell;


import capl_first_design.FirstDesign.main.Galaxy;


public abstract class SpaceObject
{
	public double m_dPosX;
	public double m_dPosY;
	public int m_iHealth;


	public SpaceObject(final double dX, final double dY, final int iHealth)
	{
		m_dPosX = dX;
		m_dPosY = dY;
		m_iHealth = iHealth;
	}

	public double x()
	{
		return m_dPosX;
	}

	public void x(final double dX)
	{
		m_dPosX = dX;
	}

	public double y()
	{
		return m_dPosY;
	}

	public void y(final double dY)
	{
		m_dPosY = dY;
	}

	public int health()
	{
		return m_iHealth;
	}

	public void health(final int iHealth)
	{
		m_iHealth = iHealth;
	}

	public boolean alive()
	{
		return m_iHealth > 0;
	}

	public abstract void update(final Galaxy xGalaxy);
}
