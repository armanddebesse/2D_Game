package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;
import main.GamePanel;
import main.UtilityTool;

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
		setup(0, "grass", false);
		setup(1, "tree", true);
		setup(2, "water", true);
		setup(3, "sand", false);
		setup(4, "earth", false);
		setup(5, "wall", true);
	}
	
	public void setup(int index, String imageName, boolean hasCollision) {
		UtilityTool uTool = new UtilityTool();
		
		try {
			tiles[index] = new Tile();
			tiles[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/"+imageName+".png"));
			tiles[index].image = uTool.scaleImage(tiles[index].image, gamePanel.tileSize, gamePanel.tileSize);
			tiles[index].collision = hasCollision;
			
		} catch (Exception e) {
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
			e.printStackTrace();
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
				
				graph2D.drawImage(tiles[tileNumber].image, screenX, screenY, null);
			}
			worldCol++;
			
			if(worldCol == gamePanel.maxWorldCol) {
				worldCol = 0;
				worldRow++;
			}
		}
	}
}
