package main;

import entity.Entity;

public class CollisionChecker {
	
	GamePanel gamePanel;

	public CollisionChecker(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	
	public void checkTile(Entity entity) {
		double entityLeftWorldX = entity.worldX + entity.solidArea.x;
		double entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
		double entityTopWorldY = entity.worldY + entity.solidArea.y;
		double entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
	
		double entityLeftCol = entityLeftWorldX / gamePanel.tileSize;
		double entityRightCol = entityRightWorldX / gamePanel.tileSize;
		double entityTopRow = entityTopWorldY / gamePanel.tileSize;
		double entityBottomRow = entityBottomWorldY / gamePanel.tileSize;
		
		double tileNum1,tileNum2;
		
		switch (entity.direction) {
		case UP:
			entityTopRow = (entityTopWorldY - entity.speed) / gamePanel.tileSize;
			tileNum1 = gamePanel.tileManager.mapTileNum[(int) entityLeftCol][(int) entityTopRow];
			tileNum2 = gamePanel.tileManager.mapTileNum[(int) entityRightCol][(int) entityTopRow];
			if (gamePanel.tileManager.tile[(int) tileNum1].collision == true || gamePanel.tileManager.tile[(int) tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case DOWN:
			entityBottomRow = (entityBottomWorldY + entity.speed) / gamePanel.tileSize;
			tileNum1 = gamePanel.tileManager.mapTileNum[(int) entityLeftCol][(int) entityBottomRow];
			tileNum2 = gamePanel.tileManager.mapTileNum[(int) entityRightCol][(int) entityBottomRow];
			if (gamePanel.tileManager.tile[(int) tileNum1].collision == true || gamePanel.tileManager.tile[(int) tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case LEFT:
			entityLeftCol = (entityLeftWorldX - entity.speed) / gamePanel.tileSize;
			tileNum1 = gamePanel.tileManager.mapTileNum[(int) entityLeftCol][(int) entityTopRow];
			tileNum2 = gamePanel.tileManager.mapTileNum[(int) entityLeftCol][(int) entityBottomRow];
			if (gamePanel.tileManager.tile[(int) tileNum1].collision == true || gamePanel.tileManager.tile[(int) tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case RIGHT:
			entityRightCol = (entityRightWorldX - entity.speed) / gamePanel.tileSize;
			tileNum1 = gamePanel.tileManager.mapTileNum[(int) entityRightCol][(int) entityTopRow];
			tileNum2 = gamePanel.tileManager.mapTileNum[(int) entityRightCol][(int) entityBottomRow];
			if (gamePanel.tileManager.tile[(int) tileNum1].collision == true || gamePanel.tileManager.tile[(int) tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		}
	}
}
