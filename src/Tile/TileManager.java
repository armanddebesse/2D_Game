package Tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class TileManager {
	GamePanel gamePanel;
	public Tile[] tile;
	public int mapTileNum[][];

	public TileManager(GamePanel gamePanel) {
		this.gamePanel= gamePanel;
		
		tile = new Tile[10];
		mapTileNum = new int[gamePanel.maxWorldCol][gamePanel.maxWorldRow];
		getTileImage();
		loadMap("/maps/world01.txt");
	}
	public void getTileImage() {
		setup(0, "grass", false);
		setup(1, "wall", true);
		setup(2, "water", true);
		setup(3, "earth", false);
		setup(4, "tree", true);
		setup(5, "sand", false);
	}
	
	public void setup(int index, String imagePath, boolean collision) {
		UtilityTool uTool = new UtilityTool();
		try {
			tile[index] = new Tile();
			tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/"+imagePath+".png"));
			tile[index].image = uTool.scaleImage(tile[index].image, gamePanel.tileSize, gamePanel.tileSize);
		    tile[index].collision = collision;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void loadMap(String filePath) {
		try {
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			while(col < gamePanel.maxWorldCol && row < gamePanel.maxWorldRow) {
				String line = br.readLine();
				while(col < gamePanel.maxWorldCol) {
					String numbers[] = line.split(" ");
					int num = Integer.parseInt(numbers[col]);
					mapTileNum[col][row] = num;
					col++;
				}
				if(col == gamePanel.maxWorldCol) {
					 col = 0;
					 row++;

				 }
			}
			br.close();
		}catch(Exception e){
			
		}
	}
	public void draw(Graphics2D g2) {
		
		int worldCol = 0;
		int worldRow = 0;
		
		while(worldCol < gamePanel.maxWorldCol && worldRow < gamePanel.maxWorldRow) {
			
			int tileNum = mapTileNum[worldCol][worldRow];
			
			int worldX = worldCol * gamePanel.tileSize;
			int worldY = worldRow * gamePanel.tileSize;
			double screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
			double screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;
			
			if (worldX + gamePanel.tileSize > gamePanel.player.worldX - gamePanel.player.screenX &&
				worldX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX &&
				worldY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY &&
				worldY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY) {
				
				g2.drawImage(tile[tileNum].image, (int)screenX, (int)screenY, null);
			}
			
			worldCol++;

			 if(worldCol == gamePanel.maxWorldCol) {
				 worldCol = 0;
				 worldRow++;
			 }
		}
	}
}
