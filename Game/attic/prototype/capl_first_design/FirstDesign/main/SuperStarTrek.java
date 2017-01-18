package capl_first_design.FirstDesign.main;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import capl_first_design.FirstDesign.controller.InputComponent;
import capl_first_design.FirstDesign.modell.Ship;
import capl_first_design.FirstDesign.view.EnterpriseModel;

 
public final class SuperStarTrek extends BasicGame
{
	private Ship m_xEnterprise;
	private List<Ship> m_lstShips = new ArrayList<Ship>();


	@Override
	public void init()
	{
		m_xEnterprise = new Ship(0.0, 0.0, 100, 0, 0, new EnterpriseModel(), new InputComponent() {
			@Override
			public void update(Galaxy xGalaxy, Ship xShip)
			{
			}
		});
		m_lstShips.add(m_xEnterprise);
	}

	@Override
	public void input(final List<KeyAction> lstKeyActions)
	{
	}

	@Override
	public void update()
	{
	}

	@Override
	public void render(final Graphics2D xGraphics, final Rectangle2D xBounds)
	{
		xGraphics.setColor(Color.WHITE);
		xGraphics.fill(xBounds);

		xGraphics.setColor(Color.BLACK);
		for(final Ship xShip : m_lstShips)
		{
			xShip.render(xGraphics);
		}
	}

	@Override
	public void cleanup()
	{
	}
}
