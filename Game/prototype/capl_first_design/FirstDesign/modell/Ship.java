package capl_first_design.FirstDesign.modell;


import java.awt.Graphics2D;

import capl_first_design.FirstDesign.controller.InputComponent;
import capl_first_design.FirstDesign.main.Galaxy;
import capl_first_design.FirstDesign.view.Model;


public final class Ship extends SpaceObject
{
	private final Model k_xModel;
	private final InputComponent k_xInput;

	private int m_iShield;
	private int m_iEnergy;


	public Ship(final double dPosX, final double dPosY, final int iHealth, final int iShield, final int iEnergy, final Model xModel, final InputComponent xInput)
	{
		super(dPosX, dPosY, iHealth);

		k_xModel = xModel;
		k_xInput = xInput;

		m_iShield = iShield;
		m_iEnergy = iEnergy;
	}

	public int shield()
	{
		return m_iShield;
	}

	public void shield(final int iShield)
	{
		m_iShield = iShield;
	}

	public int energy()
	{
		return m_iEnergy;
	}

	public void energy(final int iEnergy)
	{
		m_iEnergy = iEnergy;
	}

	@Override
	public void update(final Galaxy xGalaxy)
	{
		k_xInput.update(xGalaxy, this);
	}

	public void render(final Graphics2D xGraphics)
	{
		k_xModel.render(this, xGraphics);
	}
}
