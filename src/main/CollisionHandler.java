package main;

import entity.Entity;

public class CollisionHandler {
	GamePanel gamePanel;
	
	public CollisionHandler(GamePanel _gamePanel){
		gamePanel = _gamePanel;
	}
	
	public void checkTile(Entity entity) {
		int entityLeftWorldX = entity.worldX + entity.solidArea.x;
		int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
		int entityTopWorldY = entity.worldY + entity.solidArea.y;
		int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
		
		int entityLeftCol = entityLeftWorldX / gamePanel.tileSize;
		int entityRightCol = entityRightWorldX / gamePanel.tileSize;
		int entityTopRow = entityTopWorldY / gamePanel.tileSize;
		int entityBottomRow = entityBottomWorldY / gamePanel.tileSize;
		
		int tileNum1, tileNum2;
		
		switch (entity.direction) {
			case UP:
				entityTopRow = (entityTopWorldY - entity.speed) / gamePanel.tileSize;
				tileNum1 = gamePanel.tileManager.mapTileNumber[entityLeftCol][entityTopRow];
				tileNum2 = gamePanel.tileManager.mapTileNumber[entityRightCol][entityTopRow];
				if (gamePanel.tileManager.tiles[tileNum1].collision || gamePanel.tileManager.tiles[tileNum2].collision) {
					entity.collisionOn = true;
				}
				break;
			case DOWN:
				entityBottomRow = (entityBottomWorldY + entity.speed) / gamePanel.tileSize;
				tileNum1 = gamePanel.tileManager.mapTileNumber[entityLeftCol][entityBottomRow];
				tileNum2 = gamePanel.tileManager.mapTileNumber[entityRightCol][entityBottomRow];
				if (gamePanel.tileManager.tiles[tileNum1].collision || gamePanel.tileManager.tiles[tileNum2].collision) {
					entity.collisionOn = true;
				}
				break;
			case LEFT:
				entityLeftCol = (entityLeftWorldX - entity.speed) / gamePanel.tileSize;
				tileNum1 = gamePanel.tileManager.mapTileNumber[entityLeftCol][entityTopRow];
				tileNum2 = gamePanel.tileManager.mapTileNumber[entityLeftCol][entityBottomRow];
				if (gamePanel.tileManager.tiles[tileNum1].collision || gamePanel.tileManager.tiles[tileNum2].collision) {
					entity.collisionOn = true;
				}
				break;
			case RIGHT:
				entityRightCol = (entityRightWorldX + entity.speed) / gamePanel.tileSize;
				tileNum1 = gamePanel.tileManager.mapTileNumber[entityRightCol][entityTopRow];
				tileNum2 = gamePanel.tileManager.mapTileNumber[entityRightCol][entityBottomRow];
				if (gamePanel.tileManager.tiles[tileNum1].collision || gamePanel.tileManager.tiles[tileNum2].collision) {
					entity.collisionOn = true;
				}
				break;
		}
	}
}
