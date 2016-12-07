package ch.bfh.game.tileMap;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import ch.bfh.game.main.GamePanel;
import ch.bfh.game.modell.Player;

public class TileMap {

	// position
	private double x;
	private double y;
	
	// bounds
	private int xmin;
	private int xmax;
	private int ymin;
	private int ymax;
	
	private double tween;
	
	// map
	private int[][] map;
	private int tileSize;
	private int numRows;
	private int numCols;
	private int width;
	private int height;
	
	// tileset
	private BufferedImage tileset;
	private int numTilesAcross;
	private Tile[][] tiles;
	
	// drawing (only draw visible stuff)
	private int rowOffset;
	private int colOffset;
	private int numRowsToDraw;
	private int numColsToDraw;
	
	// players
	private ArrayList<Player> players;
	
	// Constructor
	public TileMap(int tileSize)
	{
		this.tileSize = tileSize;
		this.players = new ArrayList<Player>();
		numRowsToDraw = GamePanel.HEIGHT / tileSize + 2;
		numColsToDraw = GamePanel.WIDTH / tileSize + 2;
		tween = 0.07;
		
	}
	
	// loading tiles in memory
	public void loadTiles(String s)
	{
		try
		{
			tileset = ImageIO.read(getClass().getResourceAsStream(s));
			
			numTilesAcross = tileset.getWidth() / tileSize;
			tiles = new Tile[3][numTilesAcross];
			
			BufferedImage subimage;
			
			for(int col = 0; col < numTilesAcross; col++)
			{
				subimage = tileset.getSubimage(col * tileSize, 0, tileSize, tileSize);
				tiles[0][col] = new Tile(subimage, Tile.NORMAL);
				
				subimage = tileset.getSubimage(col * tileSize, tileSize, tileSize, tileSize);
				tiles[1][col] = new Tile(subimage, Tile.BLOCKED);
				
				subimage = tileset.getSubimage(col * tileSize, 2 * tileSize, tileSize, tileSize);
				tiles[2][col] = new Tile(subimage, Tile.BLOCKED);
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	// loads map-file in memory
	public void loadMap(String s)
	{
		try
		{
			// load map-file
			InputStream in = getClass().getResourceAsStream(s);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			
			// read map-file
			// map info
			numCols = Integer.parseInt(br.readLine());
			numRows = Integer.parseInt(br.readLine());
			
			map = new int[numRows][numCols];

			width = numCols * tileSize;
			height = numRows * tileSize;
			
			xmin = GamePanel.WIDTH - width;
			xmax = 0;
			ymin = GamePanel.HEIGHT - height;
			ymax = 0;
			
			// start reading map
			String delims = "\\s+";
			for(int row = 0; row < numRows; row++)
			{
				String line = br.readLine();
				String[] tokens = line.split(delims);
				
				for(int col = 0; col < numCols; col++)
				{
					map[row][col] = Integer.parseInt(tokens[col]);
				}
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public int getTileSize()
	{
		return tileSize;
	}
	
	public ArrayList<Player> getPlayerList()
	{
		return this.players;
	}
	
	public int getX()
	{
		return (int) x;
	}
	
	public int getY()
	{
		return (int) y;
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	public void setTween(double t)
	{
		this.tween = t;
	}
	
	public int getType(int row, int col)
	{
		int rc = map[row][col];
		int r = rc / numTilesAcross;
		int c = rc % numTilesAcross;
		return tiles[r][c].getType();
	}
	
	public void setPosition(double x, double y)
	{
		this.x += (x - this.x) * tween;
		this.y += (y - this.y) * tween;
		
		fixBounds();
		
		// find out, where to start drawing
		colOffset = (int) - this.x / tileSize;
		rowOffset = (int) - this.y / tileSize;
	}
	
	private void fixBounds()
	{
		if(x < xmin)
		{
			x = xmin;
		}
		
		if(x > xmax)
		{
			x = xmax;
		}
		
		if(y < ymin)
		{
			y = ymin;
		}
		
		if(y > ymax)
		{
			y = ymax;
		}
	}
	
	public void draw(Graphics2D g)
	{
		for(int row = rowOffset; row < rowOffset + numRowsToDraw; row++)
		{
			
			if(row >= numRows)
			{
				break;
			}
			
			for(int col = colOffset; col < colOffset + numColsToDraw; col++)
			{
				if(col >= numCols)
				{
					break;
				}
				
				int rc = map[row][col];
				int r = rc / numTilesAcross;
				int c = rc % numTilesAcross;
				
				g.drawImage(tiles[r][c].getImage(), (int) x + col * tileSize, (int) y + row * tileSize, null);
			}
		}
	}
}