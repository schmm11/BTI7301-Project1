package ch.bfh.game.entities;


import ch.bfh.game.math.Rectangle;
import ch.bfh.game.math.Vector;


public abstract class SpaceObject
{
	private Vector m_xPosition;
	private Vector m_xVelocity;
	private double m_dDirection;

	private Rectangle m_xBoundingBox;


	public final Vector getPosition()
	{
		return m_xPosition;
	}

	public final double getX()
	{
		return m_xPosition.getX();
	}

	public final double getY()
	{
		return m_xPosition.getY();
	}

	public final Vector getVelocity()
	{
		return m_xVelocity;
	}

	public final double getSpeed()
	{
		return m_xVelocity.getLength();
	}

	public final double getSpeedX()
	{
		return m_xVelocity.getX();
	}

	public final double getSpeedY()
	{
		return m_xVelocity.getY();
	}

	public final double getDirection()
	{
		return m_dDirection;
	}

	public Rectangle getBoundingBox()
	{
		return m_xBoundingBox;
	}

	public abstract void update();
}
