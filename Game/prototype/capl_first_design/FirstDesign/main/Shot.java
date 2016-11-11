package capl_first_design.FirstDesign.main;

public final class Shot
{
	private final int k_iSpeedX;
	private final int k_iSpeedY;
	private final long k_lStart;

	private int m_iX;
	private int m_iY;


	public Shot(final int iX, final int iY, final int iSpeedX, final int iSpeedY)
	{
		System.out.println("New shot: " + iX + " " + iY);
		m_iX = iX;
		m_iY = iY;
		if(iSpeedX == 0 && iSpeedY == 0)
		{
			k_iSpeedX = 1;
			k_iSpeedY = 0;
		}
		else
		{
			k_iSpeedX = iSpeedX;
			k_iSpeedY = iSpeedY;
		}
		k_lStart = System.nanoTime();
	}

	public int x() { return m_iX; }
	public int y() { return m_iY; }

	public void update()
	{
		m_iX += k_iSpeedX;
		m_iY += k_iSpeedY;
	}

	public boolean alive()
	{
		return (System.nanoTime() - k_lStart) * 1e-9 < 5;
	}
}
