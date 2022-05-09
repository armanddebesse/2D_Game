package Tile;

import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
	GamePanel gamePanel;
	Tile[] tile;

	public TileManager(GamePanel gamePanel) {
		this.gamePanel= gamePanel;
		
		tile = new Tile[10];
		
		getTileImage();
	}
	public void getTileImage() {
		try {
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
			
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
			
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
		
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void draw(Graphics2D g2) {
		
		int col = 0;
		int row = 0;
		int x = 0;
		int y = 0;
		
		while(col < gamePanel.maxScreenCol && row < gamePanel.maxScreenRow) {
			g2.drawImage(tile[0].image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
			
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
