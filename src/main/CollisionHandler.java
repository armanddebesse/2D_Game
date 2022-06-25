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
	
	public int checkEntity(Entity entity, Entity[] target) {
		int index = 999;
		
		for (int i = 0; i <  target.length; i++) {
			if (target[i] != null) {
				entity.solidArea.x += entity.worldX;
				entity.solidArea.y += entity.worldY;
				
				target[i].solidArea.x += target[i].worldX;
				target[i].solidArea.y += target[i].worldY;
				
				switch (entity.direction) {
				case UP:
					entity.solidArea.y -= entity.speed;
					if (entity.solidArea.intersects(target[i].solidArea)) {
						entity.collisionOn = true;
						index = i;
					}
					break;
				case DOWN:
					entity.solidArea.y += entity.speed;
					if (entity.solidArea.intersects(target[i].solidArea)) {
						entity.collisionOn = true;
						index = i;
					}
					break;
				case LEFT:
					entity.solidArea.x -= entity.speed;
					if (entity.solidArea.intersects(target[i].solidArea)) {
						entity.collisionOn = true;
						index = i;
					}
					break;
				case RIGHT:
					entity.solidArea.x += entity.speed;
					if (entity.solidArea.intersects(target[i].solidArea)) {
						entity.collisionOn = true;
						index = i;
					}
					break;

				}
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				
				target[i].solidArea.x = target[i].solidAreaDefaultX;
				target[i].solidArea.y = target[i].solidAreaDefaultY;
			}
		}
		
		return index;
	}
	
	public void checkPlayer(Entity entity) {
		entity.solidArea.x += entity.worldX;
		entity.solidArea.y += entity.worldY;
		
		gamePanel.player.solidArea.x += gamePanel.player.worldX;
		gamePanel.player.solidArea.y += gamePanel.player.worldY;
		
		switch (entity.direction) {
		case UP:
			entity.solidArea.y -= entity.speed;
			if (entity.solidArea.intersects(gamePanel.player.solidArea)) {
				entity.collisionOn = true;
			}
			break;
		case DOWN:
			entity.solidArea.y += entity.speed;
			if (entity.solidArea.intersects(gamePanel.player.solidArea)) {
				entity.collisionOn = true;
			}
			break;
		case LEFT:
			entity.solidArea.x -= entity.speed;
			if (entity.solidArea.intersects(gamePanel.player.solidArea)) {
				entity.collisionOn = true;
			}
			break;
		case RIGHT:
			entity.solidArea.x += entity.speed;
			if (entity.solidArea.intersects(gamePanel.player.solidArea)) {
				entity.collisionOn = true;
			}
			break;

		}
		entity.solidArea.x = entity.solidAreaDefaultX;
		entity.solidArea.y = entity.solidAreaDefaultY;
		
		gamePanel.player.solidArea.x = gamePanel.player.solidAreaDefaultX;
		gamePanel.player.solidArea.y = gamePanel.player.solidAreaDefaultY;
	}
}
