package capl_first_design.FirstDesign.modell;


import capl_first_design.FirstDesign.main.Galaxy;


public final class RocketItem implements Item
{
	private int m_iAmmo;
	private int m_iDamage = 70;


	public RocketItem(final int iAmmo)
	{
		m_iAmmo = iAmmo;
	}

	@Override
	public void use(final Galaxy xGalaxy)
	{
	}

	@Override
	public void setAmmo(final int iAmmo)
	{
	}
	
	
}
