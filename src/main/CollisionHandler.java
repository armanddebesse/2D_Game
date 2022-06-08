package main;

import entity.Entity;
import object.SuperObject;

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
	public int checkObject(Entity entity, boolean isPlayer) {
		int index = 999;
		
		for (int i = 0; i <  gamePanel.obj.length; i++) {
			if (gamePanel.obj[i] != null) {
				entity.solidArea.x += entity.worldX;
				entity.solidArea.y += entity.worldY;
				
				gamePanel.obj[i].solidArea.x += gamePanel.obj[i].worldX;
				gamePanel.obj[i].solidArea.y += gamePanel.obj[i].worldY;
				
				switch (entity.direction) {
				case UP:
					entity.solidArea.y -= entity.speed;
					if (entity.solidArea.intersects(gamePanel.obj[i].solidArea)) {
						if (gamePanel.obj[i].collision) {
							entity.collisionOn = true;
						}
						if (isPlayer) {
							index = i;
						}
					}
					break;
				case DOWN:
					entity.solidArea.y += entity.speed;
					if (entity.solidArea.intersects(gamePanel.obj[i].solidArea)) {
						if (gamePanel.obj[i].collision) {
							entity.collisionOn = true;
						}
						if (isPlayer) {
							index = i;
						}
					}
					break;
				case LEFT:
					entity.solidArea.x -= entity.speed;
					if (entity.solidArea.intersects(gamePanel.obj[i].solidArea)) {
						if (gamePanel.obj[i].collision) {
							entity.collisionOn = true;
						}
						if (isPlayer) {
							index = i;
						}
					}
					break;
				case RIGHT:
					entity.solidArea.x += entity.speed;
					if (entity.solidArea.intersects(gamePanel.obj[i].solidArea)) {
						if (gamePanel.obj[i].collision) {
							entity.collisionOn = true;
						}
						if (isPlayer) {
							index = i;
						}
					}
					break;

				}
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				
				gamePanel.obj[i].solidArea.x = gamePanel.obj[i].solidAreaDefaultX;
				gamePanel.obj[i].solidArea.y = gamePanel.obj[i].solidAreaDefaultY;
			}
		}
		
		return index;
	}
}
