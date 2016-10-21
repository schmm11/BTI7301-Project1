package capl_first_design.FirstDesign.modell;


import java.awt.Graphics2D;
import java.util.Arrays;
import java.util.List;

import capl_first_design.FirstDesign.controller.InputComponent;
import capl_first_design.FirstDesign.main.Galaxy;
import capl_first_design.FirstDesign.view.Model;


public final class Ship implements SpaceObject
{
	private double m_dX;
	private double m_dY;
	private double m_dAngle = 0.0;
	private int m_iHealth;
	private int m_iShield;
	private int m_iEnergy;
	private Model m_xModel;
	private InputComponent m_xInput;
	private List<Item> m_lstItems;


	public Ship(final double dX, final double dY, final int iHealth, final int iShield, final int iEnergy, final Model xModel, final InputComponent xInput, final Item xItem)
	{
		m_dX = dX;
		m_dY = dY;

		m_iHealth = iHealth;
		m_iShield = iShield;
		m_iEnergy = iEnergy;

		m_xModel = xModel;
		m_xInput = xInput;
		m_lstItems = Arrays.asList(xItem);
	}

	@Override
	public double x()
	{
		return m_dX;
	}

	@Override
	public double y()
	{
		return m_dY;
	}

	public double angle()
	{
		return m_dAngle;
	}

	public int health()
	{
		return m_iHealth;
	}

	public int shield()
	{
		return m_iShield;
	}

	public int energy()
	{
		return m_iEnergy;
	}

	public void move(final double dX, final double dY)
	{
		m_dX += dX;
		m_dY += dY;
	}

	public void move(final double dDistance)
	{
		m_dX += dDistance * Math.cos(m_dAngle);
		m_dY += dDistance * Math.sin(m_dAngle);
	}

	public void turn(final double dAngle)
	{
		m_dAngle += dAngle;
	}

	@Override
	public boolean alive()
	{
		return m_iHealth > 0;
	}

	@Override
	public void update(final Galaxy xGalaxy)
	{
		m_xInput.update(xGalaxy, this);
	}

	@Override
	public void render(final Galaxy xGalaxy, final Graphics2D xGraphics)
	{
		m_xModel.render(this, xGraphics);
	}
}
