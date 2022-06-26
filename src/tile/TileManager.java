package tile;

import java.awt.Graphics2D;
import main.GamePanel;

public class TileManager {
	GamePanel gamePanel;
	public Tile[] tiles;
	public int mapTileNumber[][];
	public Map map;
	
	public TileManager(GamePanel _gamePanel) {
		this.gamePanel = _gamePanel;
		map = new Map("res/maps/map.json", gamePanel);
		tiles = map.tiles;
		mapTileNumber = map.data;
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
			
			// Stop moving camera at the edge of map
			if (gamePanel.player.screenX > gamePanel.player.worldX) {
				screenX = worldX;
			}
			if (gamePanel.player.screenY > gamePanel.player.worldY) {
				screenY = worldY;
			}
			int rightOffset = gamePanel.screenWidth - gamePanel.player.screenX;
			if (rightOffset > gamePanel.worldWidth - gamePanel.player.worldX) {
				screenX = gamePanel.screenWidth - (gamePanel.worldWidth - worldX);
			}
			int bottomOffset = gamePanel.screenHeight - gamePanel.player.screenY;
			if (bottomOffset > gamePanel.worldHeight - gamePanel.player.worldY) {
				screenY = gamePanel.screenHeight - (gamePanel.worldWidth - worldY);
			}
			
			
			if ((worldX + gamePanel.tileSize> gamePanel.player.worldX - gamePanel.player.screenX &&
				 worldX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX &&
				 worldY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY &&
				 worldY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY) ||
				 gamePanel.player.screenX > gamePanel.player.worldX ||
				 gamePanel.player.screenY > gamePanel.player.worldY ||
				 rightOffset > gamePanel.worldWidth - gamePanel.player.worldX ||
				 bottomOffset > gamePanel.worldHeight - gamePanel.player.worldY) {
				
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
