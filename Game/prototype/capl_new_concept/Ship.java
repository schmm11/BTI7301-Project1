package capl_new_concept;


public final class Ship
{
	private int m_iXPos;
	private int m_iYPos;
	private Direction m_xDirection;


	public Ship(final int iXPos, final int iYPos, final Direction xDirection)
	{
		m_iXPos = iXPos;
		m_iYPos = iYPos;
		m_xDirection = xDirection;
	}

	public void move(final int iDistance)
	{
		switch(m_xDirection)
		{
		case North:
			m_iYPos -= iDistance;
			break;
		case South:
			m_iYPos += iDistance;
			break;
		case East:
			m_iXPos -= iDistance;
			break;
		case West:
			m_iXPos += iDistance;
			break;
		}
	}

	public void setDirection(final Direction xDirection)
	{
		m_xDirection = xDirection;
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
