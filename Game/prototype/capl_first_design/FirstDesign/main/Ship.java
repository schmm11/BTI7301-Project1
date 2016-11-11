package capl_first_design.FirstDesign.main;


public final class Ship
{
	private int m_iXPos = 0;
	private int m_iYPos = 0;
	private int m_iXSpeed = 0;
	private int m_iYSpeed = 0;

	public int x() { return m_iXPos; }
	public int y() { return m_iYPos; }
	public int speedX() { return m_iXSpeed; }
	public int speedY() { return m_iYSpeed; }
	public void speedX(final int iSpeedX) { m_iXSpeed += iSpeedX; }
	public void speedY(final int iSpeedY) { m_iYSpeed += iSpeedY; }

	public void update()
	{
		m_iXPos += m_iXSpeed * 5;
		m_iYPos += m_iYSpeed * 5;
	}
}
