package ch.bfh.game.gameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import ch.bfh.game.main.GamePanel;
import ch.bfh.game.renderer.GameRenderer;
import ch.bfh.game.tileMap.Background;

public class MenuState extends GameState {

	private Background bg;
	private BufferedImage option;
	private BufferedImage optionSelected;
	
	// Menu Choices
	private int currentChoice;
	private String[] options = {"1 Player", "2 Players", "Options", "Exit Game"};
	
	// Design
	private Color titleColor;
	private Font titleFont;
	private Font font;
	
	// Constructor
	public MenuState(GameStateManager gsm)
	{
		this.gsm = gsm;
		
		try
		{
			// load option panels
			option = ImageIO.read(getClass().getResourceAsStream("/02_Textures/04_Menu/Position_01.png"));
			optionSelected = ImageIO.read(getClass().getResourceAsStream("/02_Textures/04_Menu/Position_02.png"));
			
			bg = new Background("/02_Textures/02_BackGround/Background_02.jpg", 1);
			bg.setVector(0, 0);
			
			titleColor = new Color(0, 150, 150);
			titleFont = new Font("Century Gothic", Font.BOLD, 36);
			font = new Font("Arial", Font.BOLD, 16);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void init() 
	{
	
	}

	@Override
	public void update() 
	{
		bg.update();
	}

	@Override
	public void draw(Graphics2D g) 
	{
		// draw background
		GameRenderer.renderBackground(g);

		// draw title
		g.setColor(titleColor);
		g.setFont(titleFont);

		FontMetrics fm = g.getFontMetrics();
		
		String title = new String("SUPER STAR BATTLES");
		g.drawString(title, GamePanel.WIDTH / 2 - (fm.stringWidth(title) / 2), 260);
		
		// draw menu options
		g.setFont(font);
		FontMetrics fm2 = g.getFontMetrics();

		
		// loop through menu options
		for(int i = 0; i < options.length; i++)
		{
			if(i == currentChoice)
			{
				g.setColor(new Color(0, 250, 250));
				g.drawImage(optionSelected, GamePanel.WIDTH / 2 - (optionSelected.getWidth() / 2), 300 + i * 60, null);

			}
			else
			{
				g.setColor(new Color(0, 150, 150));
				g.drawImage(option, GamePanel.WIDTH / 2 - (option.getWidth() / 2), 300 + i * 60, null);
			}
			
			// 60 pixels between options
			g.drawString(options[i], GamePanel.WIDTH / 2 - (fm2.stringWidth(options[i]) / 2), 325 + i * 60);

		}
	}

	private void select()
	{
		if(currentChoice == 0)
		{
			// 1 Player
			
		}
		
		if(currentChoice == 1)
		{
			// 2 Players
			gsm.setState(GameStateManager.TWOPL1);
		}
		
		if(currentChoice == 2)
		{
			// Options
		}
		
		if(currentChoice == 3)
		{
			System.exit(0);
		}
	}
	
	@Override
	public void keyPressed(int k) 
	{
		if(k == KeyEvent.VK_ENTER)
		{
			select();
		}
		
		if(k == KeyEvent.VK_UP)
		{
			currentChoice--;
			if(currentChoice == -1)
			{
				currentChoice = options.length - 1;
			}
		}
		
		if(k == KeyEvent.VK_DOWN)
		{
			currentChoice++;
			if(currentChoice == options.length)
			{
				currentChoice = 0;
			}
		}
	}

	@Override
	public void keyReleased(int k) 
	{
		// no action required
	}

}
