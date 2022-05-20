package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
	GamePanel gamePanel;
	Tile[] tiles;
	int mapTileNumber[][];
	
	public TileManager(GamePanel _gamePanel) {
		this.gamePanel = _gamePanel;
		
		tiles = new Tile[3];
		mapTileNumber = new int[gamePanel.maxScreenCol][gamePanel.maxScreenRow];
		getTileImage();
		loadMap("/maps/map01.txt");
	}
	
	public void getTileImage () {
		try {
			tiles[0] = new Tile();
			tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
			
			tiles[1] = new Tile();
			tiles[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
			
			tiles[2] = new Tile();
			tiles[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadMap(String mapPath) {
		try {
			InputStream stream = getClass().getResourceAsStream(mapPath);
			BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
			
			int col = 0;
			int row = 0;
			
			while(col < gamePanel.maxScreenCol && row < gamePanel.maxScreenRow) {
				String line = reader.readLine();
				String numbers[] = line.split(" ");
				
				while (col < gamePanel.maxScreenCol) {
					
					int number = Integer.parseInt(numbers[col]);
					
					mapTileNumber[col][row] = number;
					col++;
				}
				
				if(col == gamePanel.maxScreenCol) {
					col = 0;
					row++;
				}
			}
			reader.close();
		}
		catch(Exception e) {
			
		}
	}
	
	public void draw(Graphics2D graph2D) {
		int col = 0;
		int row = 0;
		int x = 0;
		int y = 0;
		
		while(col < gamePanel.maxScreenCol && row < gamePanel.maxScreenRow) {
			
			int tileNumber = mapTileNumber[col][row];
			
			graph2D.drawImage(tiles[tileNumber].image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
			col++;
			x += gamePanel.tileSize;
			
			if(col == gamePanel.maxScreenCol) {
				col = 0;
				x = 0;
				row++;
				y += gamePanel.tileSize;
			}
		}
	}
}
