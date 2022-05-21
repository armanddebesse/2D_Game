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
	public Tile[] tiles;
	public int mapTileNumber[][];
	
	public TileManager(GamePanel _gamePanel) {
		this.gamePanel = _gamePanel;
		
		tiles = new Tile[6];
		mapTileNumber = new int[gamePanel.maxWorldCol][gamePanel.maxWorldRow];
		getTileImage();
		loadMap("/maps/map02.txt");
	}
	
	public void getTileImage () {
		try {
			tiles[0] = new Tile();
			tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
			
			tiles[1] = new Tile();
			tiles[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
			tiles[1].collision = true;
			
			tiles[2] = new Tile();
			tiles[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
			tiles[2].collision = true;
			
			tiles[3] = new Tile();
			tiles[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png"));
			
			tiles[4] = new Tile();
			tiles[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));
			
			tiles[5] = new Tile();
			tiles[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
			tiles[5].collision = true;
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
			
			while(col < gamePanel.maxWorldCol && row < gamePanel.maxWorldRow) {
				String line = reader.readLine();
				String numbers[] = line.split(" ");
				
				while (col < gamePanel.maxWorldCol) {
					
					int number = Integer.parseInt(numbers[col]);
					
					mapTileNumber[col][row] = number;
					col++;
				}
				
				if(col == gamePanel.maxWorldCol) {
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
		int worldCol = 0;
		int worldRow = 0;
		
		while(worldCol < gamePanel.maxWorldCol && worldRow < gamePanel.maxWorldRow) {
			
			int tileNumber = mapTileNumber[worldCol][worldRow];
			
			int worldX = worldCol * gamePanel.tileSize;
			int worldY = worldRow * gamePanel.tileSize;
			int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
			int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;
			
			if (worldX + gamePanel.tileSize> gamePanel.player.worldX - gamePanel.player.screenX &&
				worldX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX &&
				worldY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY &&
				worldY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY) {
				
				graph2D.drawImage(tiles[tileNumber].image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
			}
			worldCol++;
			
			if(worldCol == gamePanel.maxWorldCol) {
				worldCol = 0;
				worldRow++;
			}
		}
	}
}
