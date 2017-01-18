package capl_first_design.FirstDesign.modell;


import capl_first_design.FirstDesign.main.Galaxy;


public final class LaserItem implements Item
{
	private int m_iAmmo;
	private int m_iDamage = 40;

	public LaserItem(final int iAmmo)
	{
		m_iAmmo = iAmmo;
	}

	public void setAmmo(final int iAmmo)
	{
		m_iAmmo = iAmmo;
	}

	@Override
	public void use(final Galaxy xGalaxy)
	{
	}
}
