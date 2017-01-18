package capl_new_concept;


public final class Shot
{
	private final long k_lSpawnTime;
	private int m_iXPos;
	private int m_iYPos;
	private Direction m_xDirection;


	public Shot(final int iXPos, final int iYPos, final Direction xDirection)
	{
		m_iXPos = iXPos;
		m_iYPos = iYPos;
		m_xDirection = xDirection;
		k_lSpawnTime = System.nanoTime();
	}

	public void update()
	{
		switch(m_xDirection)
		{
		case North:
			m_iYPos -= 2;
			break;
		case South:
			m_iYPos += 2;
			break;
		case East:
			m_iXPos -= 2;
			break;
		case West:
			m_iXPos += 2;
			break;
		}
	}

	public boolean active()
	{
		return (System.nanoTime() - k_lSpawnTime) * 1e-9 < 1.0;
	}

	public int x()
	{
		return m_iXPos;
	}

	public int y()
	{
		return m_iYPos;
	}

	public Direction direction()
	{
		return m_xDirection;
	}
}
