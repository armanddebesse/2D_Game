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
		
		double tileNum1,tileNum2,tileNum3,tileNum4;
		
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
			
			
			
		case UP_LEFT:
			entityTopRow = (entityTopWorldY - entity.speed) / gamePanel.tileSize;
			tileNum1 = gamePanel.tileManager.mapTileNum[(int) entityLeftCol][(int) entityTopRow];
			tileNum2 = gamePanel.tileManager.mapTileNum[(int) entityRightCol][(int) entityTopRow];
			
			entityLeftCol = (entityLeftWorldX - entity.speed) / gamePanel.tileSize;
			tileNum3 = gamePanel.tileManager.mapTileNum[(int) entityLeftCol][(int) entityTopRow];
			tileNum4 = gamePanel.tileManager.mapTileNum[(int) entityLeftCol][(int) entityBottomRow];
			if (gamePanel.tileManager.tile[(int) tileNum1].collision == true || gamePanel.tileManager.tile[(int) tileNum2].collision == true || gamePanel.tileManager.tile[(int) tileNum3].collision == true || gamePanel.tileManager.tile[(int) tileNum4].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case UP_RIGHT:
			entityTopRow = (entityTopWorldY - entity.speed) / gamePanel.tileSize;
			tileNum1 = gamePanel.tileManager.mapTileNum[(int) entityLeftCol][(int) entityTopRow];
			tileNum2 = gamePanel.tileManager.mapTileNum[(int) entityRightCol][(int) entityTopRow];
			
			entityRightCol = (entityRightWorldX - entity.speed) / gamePanel.tileSize;
			tileNum3 = gamePanel.tileManager.mapTileNum[(int) entityRightCol][(int) entityTopRow];
			tileNum4 = gamePanel.tileManager.mapTileNum[(int) entityRightCol][(int) entityBottomRow];
			if (gamePanel.tileManager.tile[(int) tileNum1].collision == true || gamePanel.tileManager.tile[(int) tileNum2].collision == true || gamePanel.tileManager.tile[(int) tileNum3].collision == true || gamePanel.tileManager.tile[(int) tileNum4].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case DOWN_LEFT:
			entityBottomRow = (entityBottomWorldY + entity.speed) / gamePanel.tileSize;
			tileNum1 = gamePanel.tileManager.mapTileNum[(int) entityLeftCol][(int) entityBottomRow];
			tileNum2 = gamePanel.tileManager.mapTileNum[(int) entityRightCol][(int) entityBottomRow];
			
			entityLeftCol = (entityLeftWorldX - entity.speed) / gamePanel.tileSize;
			tileNum3 = gamePanel.tileManager.mapTileNum[(int) entityLeftCol][(int) entityTopRow];
			tileNum4 = gamePanel.tileManager.mapTileNum[(int) entityLeftCol][(int) entityBottomRow];
			if (gamePanel.tileManager.tile[(int) tileNum1].collision == true || gamePanel.tileManager.tile[(int) tileNum2].collision == true || gamePanel.tileManager.tile[(int) tileNum3].collision == true || gamePanel.tileManager.tile[(int) tileNum4].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case DOWN_RIGHT:
			entityBottomRow = (entityBottomWorldY + entity.speed) / gamePanel.tileSize;
			tileNum1 = gamePanel.tileManager.mapTileNum[(int) entityLeftCol][(int) entityBottomRow];
			tileNum2 = gamePanel.tileManager.mapTileNum[(int) entityRightCol][(int) entityBottomRow];
			
			entityRightCol = (entityRightWorldX - entity.speed) / gamePanel.tileSize;
			tileNum3 = gamePanel.tileManager.mapTileNum[(int) entityRightCol][(int) entityTopRow];
			tileNum4 = gamePanel.tileManager.mapTileNum[(int) entityRightCol][(int) entityBottomRow];
			if (gamePanel.tileManager.tile[(int) tileNum1].collision == true || gamePanel.tileManager.tile[(int) tileNum2].collision == true || gamePanel.tileManager.tile[(int) tileNum3].collision == true || gamePanel.tileManager.tile[(int) tileNum4].collision == true) {
				entity.collisionOn = true;
			}
			break;
		}
	}
	public int checkObject(Entity entity, boolean player) {
		int index = 999;
		
		for(int i = 0; i< gamePanel.obj.length; i++) {
			if(gamePanel.obj[i] != null) {
				entity.solidArea.x = (int) (entity.worldX + entity.solidArea.x);
				entity.solidArea.y = (int) (entity.worldY + entity.solidArea.y);
				
				gamePanel.obj[i].solidArea.x = gamePanel.obj[i].worldX + gamePanel.obj[i].solidArea.x;
				gamePanel.obj[i].solidArea.y = gamePanel.obj[i].worldY + gamePanel.obj[i].solidArea.y;
				
				switch(entity.direction) {
				case UP:
					entity.solidArea.y -= entity.speed;
					if(entity.solidArea.intersects(gamePanel.obj[i].solidArea)) {
						if(gamePanel.obj[i].collision == true) {
							entity.collisionOn = true;
						}
						if(player == true) {
							index = i;
						}
					}
					break;
				case DOWN:
					entity.solidArea.y += entity.speed;
					if(entity.solidArea.intersects(gamePanel.obj[i].solidArea)) {
						if(gamePanel.obj[i].collision == true) {
							entity.collisionOn = true;
						}
						if(player == true) {
							index = i;
						}
					}
					break;
				case LEFT:
					entity.solidArea.x -= entity.speed;
					if(entity.solidArea.intersects(gamePanel.obj[i].solidArea)) {
						if(gamePanel.obj[i].collision == true) {
							entity.collisionOn = true;
						}
						if(player == true) {
							index = i;
						}
					}
					break;
				case RIGHT:
					entity.solidArea.x += entity.speed;
					if(entity.solidArea.intersects(gamePanel.obj[i].solidArea)) {
						if(gamePanel.obj[i].collision == true) {
							entity.collisionOn = true;
						}
						if(player == true) {
							index = i;
						}
					}
					break;
				case UP_LEFT:
					entity.solidArea.y -= entity.speed/1.5;
					entity.solidArea.x -= entity.speed/1.5;
					if(entity.solidArea.intersects(gamePanel.obj[i].solidArea)) {
						if(gamePanel.obj[i].collision == true) {
							entity.collisionOn = true;
						}
						if(player == true) {
							index = i;
						}
					}
					break;
				case UP_RIGHT:
					entity.solidArea.y -= entity.speed/1.5;
					entity.solidArea.x += entity.speed/1.5;
					if(entity.solidArea.intersects(gamePanel.obj[i].solidArea)) {
						if(gamePanel.obj[i].collision == true) {
							entity.collisionOn = true;
						}
						if(player == true) {
							index = i;
						}
					}
					break;
				case DOWN_LEFT:
					entity.solidArea.y += entity.speed/1.5;
					entity.solidArea.x -= entity.speed/1.5;
					if(entity.solidArea.intersects(gamePanel.obj[i].solidArea)) {
						if(gamePanel.obj[i].collision == true) {
							entity.collisionOn = true;
						}
						if(player == true) {
							index = i;
						}
					}
					break;
				case DOWN_RIGHT:
					entity.solidArea.y += entity.speed/1.5;
					entity.solidArea.x += entity.speed/1.5;
					if(entity.solidArea.intersects(gamePanel.obj[i].solidArea)) {
						if(gamePanel.obj[i].collision == true) {
							entity.collisionOn = true;
						}
						if(player == true) {
							index = i;
						}
					}
					break;
				}
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				gamePanel.obj[i].solidArea.x = gamePanel.obj[i].solidDefaultAreaX;
				gamePanel.obj[i].solidArea.y = gamePanel.obj[i].solidDefaultAreaY;
			}
		}
		return index;
	}
	// NPC OR MONSTER
	public int checkEntity(Entity entity, Entity[] target) {
		int index = 999;
		
		for(int i = 0; i< target.length; i++) {
			if(target[i] != null) {
				entity.solidArea.x = (int) (entity.worldX + entity.solidArea.x);
				entity.solidArea.y = (int) (entity.worldY + entity.solidArea.y);
				
				target[i].solidArea.x = (int) (target[i].worldX + target[i].solidArea.x);
				target[i].solidArea.y = (int) (target[i].worldY + target[i].solidArea.y);
				 
				switch(entity.direction) {
				case UP:
					entity.solidArea.y -= entity.speed;
					if(entity.solidArea.intersects(target[i].solidArea)) {
						entity.collisionOn = true;
						index = i;
						
					}
					break;
				case DOWN:
					entity.solidArea.y += entity.speed;
					if(entity.solidArea.intersects(target[i].solidArea)) {
						entity.collisionOn = true;
						index = i;
					}
					break;
				case LEFT:
					entity.solidArea.x -= entity.speed;
					if(entity.solidArea.intersects(target[i].solidArea)) {
						entity.collisionOn = true;
						index = i;
					}
					break;
				case RIGHT:
					entity.solidArea.x += entity.speed;
					if(entity.solidArea.intersects(target[i].solidArea)) {
						entity.collisionOn = true;
						index = i;
					}
					break;
				case UP_LEFT:
					entity.solidArea.y -= entity.speed/1.5;
					entity.solidArea.x -= entity.speed/1.5;
					if(entity.solidArea.intersects(target[i].solidArea)) {
						entity.collisionOn = true;
						index = i;
					}
					break;
				case UP_RIGHT:
					entity.solidArea.y -= entity.speed/1.5;
					entity.solidArea.x += entity.speed/1.5;
					if(entity.solidArea.intersects(target[i].solidArea)) {
						entity.collisionOn = true;
						index = i;
					}
					break;
				case DOWN_LEFT:
					entity.solidArea.y += entity.speed/1.5;
					entity.solidArea.x -= entity.speed/1.5;
					if(entity.solidArea.intersects(target[i].solidArea)) {
						entity.collisionOn = true;
						index = i;
					}
					break;
				case DOWN_RIGHT:
					entity.solidArea.y += entity.speed/1.5;
					entity.solidArea.x += entity.speed/1.5;
					if(entity.solidArea.intersects(target[i].solidArea)) {
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
		entity.solidArea.x = (int) (entity.worldX + entity.solidArea.x);
		entity.solidArea.y = (int) (entity.worldY + entity.solidArea.y);
		
		gamePanel.player.solidArea.x = (int) (gamePanel.player.worldX + gamePanel.player.solidArea.x);
		gamePanel.player.solidArea.y = (int) (gamePanel.player.worldY + gamePanel.player.solidArea.y);
		 
		switch(entity.direction) {
		case UP:
			entity.solidArea.y -= entity.speed;
			if(entity.solidArea.intersects(gamePanel.player.solidArea)) {
				entity.collisionOn = true;				
			}
			break;
		case DOWN:
			entity.solidArea.y += entity.speed;
			if(entity.solidArea.intersects(gamePanel.player.solidArea)) {
				entity.collisionOn = true;
			}
			break;
		case LEFT:
			entity.solidArea.x -= entity.speed;
			if(entity.solidArea.intersects(gamePanel.player.solidArea)) {
				entity.collisionOn = true;
			}
			break;
		case RIGHT:
			entity.solidArea.x += entity.speed;
			if(entity.solidArea.intersects(gamePanel.player.solidArea)) {
				entity.collisionOn = true;
			}
			break;
		case UP_LEFT:
			entity.solidArea.y -= entity.speed/1.5;
			entity.solidArea.x -= entity.speed/1.5;
			if(entity.solidArea.intersects(gamePanel.player.solidArea)) {
				entity.collisionOn = true;
			}
			break;
		case UP_RIGHT:
			entity.solidArea.y -= entity.speed/1.5;
			entity.solidArea.x += entity.speed/1.5;
			if(entity.solidArea.intersects(gamePanel.player.solidArea)) {
				entity.collisionOn = true;
			}
			break;
		case DOWN_LEFT:
			entity.solidArea.y += entity.speed/1.5;
			entity.solidArea.x -= entity.speed/1.5;
			if(entity.solidArea.intersects(gamePanel.player.solidArea)) {
				entity.collisionOn = true;
			}
			break;
		case DOWN_RIGHT:
			entity.solidArea.y += entity.speed/1.5;
			entity.solidArea.x += entity.speed/1.5;
			if(entity.solidArea.intersects(gamePanel.player.solidArea)) {
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
