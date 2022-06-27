package main;

import entity.Entity;

public class CollisionChecker {
	
	GamePanel gamePanel;

	public CollisionChecker(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	
	public void checkTile(Entity entity) {
		for (int i = 0; i < gamePanel.currentMap.layers.size(); i++) {
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
				tileNum1 = gamePanel.currentMap.layers.get(i)[(int) entityLeftCol][(int) entityTopRow];
				tileNum2 = gamePanel.currentMap.layers.get(i)[(int) entityRightCol][(int) entityTopRow];
				if (gamePanel.currentMap.tiles[(int) tileNum1].collision == true || gamePanel.currentMap.tiles[(int) tileNum2].collision == true) {
					entity.collisionOn = true;
				}
				break;
			case DOWN:
				entityBottomRow = (entityBottomWorldY + entity.speed) / gamePanel.tileSize;
				tileNum1 = gamePanel.currentMap.layers.get(i)[(int) entityLeftCol][(int) entityBottomRow];
				tileNum2 = gamePanel.currentMap.layers.get(i)[(int) entityRightCol][(int) entityBottomRow];
				if (gamePanel.currentMap.tiles[(int) tileNum1].collision == true || gamePanel.currentMap.tiles[(int) tileNum2].collision == true) {
					entity.collisionOn = true;
				}
				break;
			case LEFT:
				entityLeftCol = (entityLeftWorldX - entity.speed) / gamePanel.tileSize;
				tileNum1 = gamePanel.currentMap.layers.get(i)[(int) entityLeftCol][(int) entityTopRow];
				tileNum2 = gamePanel.currentMap.layers.get(i)[(int) entityLeftCol][(int) entityBottomRow];
				if (gamePanel.currentMap.tiles[(int) tileNum1].collision == true || gamePanel.currentMap.tiles[(int) tileNum2].collision == true) {
					entity.collisionOn = true;
				}
				break;
			case RIGHT:
				entityRightCol = (entityRightWorldX - entity.speed) / gamePanel.tileSize;
				tileNum1 = gamePanel.currentMap.layers.get(i)[(int) entityRightCol][(int) entityTopRow];
				tileNum2 = gamePanel.currentMap.layers.get(i)[(int) entityRightCol][(int) entityBottomRow];
				if (gamePanel.currentMap.tiles[(int) tileNum1].collision == true || gamePanel.currentMap.tiles[(int) tileNum2].collision == true) {
					entity.collisionOn = true;
				}
				break;
			case UP_LEFT:
				entityTopRow = (entityTopWorldY - entity.speed) / gamePanel.tileSize;
				tileNum1 = gamePanel.currentMap.layers.get(i)[(int) entityLeftCol][(int) entityTopRow];
				tileNum2 = gamePanel.currentMap.layers.get(i)[(int) entityRightCol][(int) entityTopRow];
				
				entityLeftCol = (entityLeftWorldX - entity.speed) / gamePanel.tileSize;
				tileNum3 = gamePanel.currentMap.layers.get(i)[(int) entityLeftCol][(int) entityTopRow];
				tileNum4 = gamePanel.currentMap.layers.get(i)[(int) entityLeftCol][(int) entityBottomRow];
				if (gamePanel.currentMap.tiles[(int) tileNum1].collision == true || gamePanel.currentMap.tiles[(int) tileNum2].collision == true || gamePanel.currentMap.tiles[(int) tileNum3].collision == true || gamePanel.currentMap.tiles[(int) tileNum4].collision == true) {
					entity.collisionOn = true;
				}
				break;
			case UP_RIGHT:
				entityTopRow = (entityTopWorldY - entity.speed) / gamePanel.tileSize;
				tileNum1 = gamePanel.currentMap.layers.get(i)[(int) entityLeftCol][(int) entityTopRow];
				tileNum2 = gamePanel.currentMap.layers.get(i)[(int) entityRightCol][(int) entityTopRow];
				
				entityRightCol = (entityRightWorldX - entity.speed) / gamePanel.tileSize;
				tileNum3 = gamePanel.currentMap.layers.get(i)[(int) entityRightCol][(int) entityTopRow];
				tileNum4 = gamePanel.currentMap.layers.get(i)[(int) entityRightCol][(int) entityBottomRow];
				if (gamePanel.currentMap.tiles[(int) tileNum1].collision == true || gamePanel.currentMap.tiles[(int) tileNum2].collision == true || gamePanel.currentMap.tiles[(int) tileNum3].collision == true || gamePanel.currentMap.tiles[(int) tileNum4].collision == true) {
					entity.collisionOn = true;
				}
				break;
			case DOWN_LEFT:
				entityBottomRow = (entityBottomWorldY + entity.speed) / gamePanel.tileSize;
				tileNum1 = gamePanel.currentMap.layers.get(i)[(int) entityLeftCol][(int) entityBottomRow];
				tileNum2 = gamePanel.currentMap.layers.get(i)[(int) entityRightCol][(int) entityBottomRow];
				
				entityLeftCol = (entityLeftWorldX - entity.speed) / gamePanel.tileSize;
				tileNum3 = gamePanel.currentMap.layers.get(i)[(int) entityLeftCol][(int) entityTopRow];
				tileNum4 = gamePanel.currentMap.layers.get(i)[(int) entityLeftCol][(int) entityBottomRow];
				if (gamePanel.currentMap.tiles[(int) tileNum1].collision == true || gamePanel.currentMap.tiles[(int) tileNum2].collision == true || gamePanel.currentMap.tiles[(int) tileNum3].collision == true || gamePanel.currentMap.tiles[(int) tileNum4].collision == true) {
					entity.collisionOn = true;
				}
				break;
			case DOWN_RIGHT:
				entityBottomRow = (entityBottomWorldY + entity.speed) / gamePanel.tileSize;
				tileNum1 = gamePanel.currentMap.layers.get(i)[(int) entityLeftCol][(int) entityBottomRow];
				tileNum2 = gamePanel.currentMap.layers.get(i)[(int) entityRightCol][(int) entityBottomRow];
				
				entityRightCol = (entityRightWorldX - entity.speed) / gamePanel.tileSize;
				tileNum3 = gamePanel.currentMap.layers.get(i)[(int) entityRightCol][(int) entityTopRow];
				tileNum4 = gamePanel.currentMap.layers.get(i)[(int) entityRightCol][(int) entityBottomRow];
				if (gamePanel.currentMap.tiles[(int) tileNum1].collision == true || gamePanel.currentMap.tiles[(int) tileNum2].collision == true || gamePanel.currentMap.tiles[(int) tileNum3].collision == true || gamePanel.currentMap.tiles[(int) tileNum4].collision == true) {
					entity.collisionOn = true;
				}
				break;
			default:
				break;
			}
		}
	}
	public int checkObject(Entity entity, boolean player) {
		int index = 999;
		
		for(int i = 0; i< gamePanel.obj.length; i++) {
			if(gamePanel.obj[i] != null) {
				entity.solidArea.x = (int) (entity.worldX + entity.solidArea.x);
				entity.solidArea.y = (int) (entity.worldY + entity.solidArea.y);
				
				gamePanel.obj[i].solidArea.x = (int) (gamePanel.obj[i].worldX + gamePanel.obj[i].solidArea.x);
				gamePanel.obj[i].solidArea.y = (int) (gamePanel.obj[i].worldY + gamePanel.obj[i].solidArea.y);
				
				switch(entity.direction) {
				case UP:entity.solidArea.y -= entity.speed;break;
				case DOWN:entity.solidArea.y += entity.speed;break;
				case LEFT:entity.solidArea.x -= entity.speed;break;
				case RIGHT:entity.solidArea.x += entity.speed;break;
				case UP_LEFT:entity.solidArea.y -= entity.speed/1.5;entity.solidArea.x -= entity.speed/1.5;break;
				case UP_RIGHT:entity.solidArea.y -= entity.speed/1.5;entity.solidArea.x += entity.speed/1.5;break;
				case DOWN_LEFT:entity.solidArea.y += entity.speed/1.5;entity.solidArea.x -= entity.speed/1.5;break;
				case DOWN_RIGHT:entity.solidArea.y += entity.speed/1.5;entity.solidArea.x += entity.speed/1.5;break;
				default:break;
				}
				if(entity.solidArea.intersects(gamePanel.obj[i].solidArea)) {
					if(gamePanel.obj[i].collision == true) {
						entity.collisionOn = true;
					}
					if(player == true) {
						index = i;
					}
				}
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				gamePanel.obj[i].solidArea.x = gamePanel.obj[i].solidAreaDefaultX;
				gamePanel.obj[i].solidArea.y = gamePanel.obj[i].solidAreaDefaultY;
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
				case UP:entity.solidArea.y -= entity.speed;break;
				case DOWN:entity.solidArea.y += entity.speed;break;
				case LEFT:entity.solidArea.x -= entity.speed;break;
				case RIGHT:entity.solidArea.x += entity.speed;break;
				case UP_LEFT:entity.solidArea.y -= entity.speed/1.5;entity.solidArea.x -= entity.speed/1.5;break;
				case UP_RIGHT:entity.solidArea.y -= entity.speed/1.5;entity.solidArea.x += entity.speed/1.5;break;
				case DOWN_LEFT:entity.solidArea.y += entity.speed/1.5;entity.solidArea.x -= entity.speed/1.5;break;
				case DOWN_RIGHT:entity.solidArea.y += entity.speed/1.5;entity.solidArea.x += entity.speed/1.5;break;
				default:break;
				}
				
				if(entity.solidArea.intersects(target[i].solidArea)) {
					if (target[i] != entity) {
						entity.collisionOn = true;
						index = i;						
					}
				}
				
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				target[i].solidArea.x = target[i].solidAreaDefaultX;
				target[i].solidArea.y = target[i].solidAreaDefaultY;
			}
		}
		return index;
	}
	public boolean checkPlayer(Entity entity) {
		
		boolean contactPlayer = false;
		
		entity.solidArea.x = (int) (entity.worldX + entity.solidArea.x);
		entity.solidArea.y = (int) (entity.worldY + entity.solidArea.y);
		
		gamePanel.player.solidArea.x = (int) (gamePanel.player.worldX + gamePanel.player.solidArea.x);
		gamePanel.player.solidArea.y = (int) (gamePanel.player.worldY + gamePanel.player.solidArea.y);
		 
		switch(entity.direction) {
		case UP:entity.solidArea.y -= entity.speed;break;
		case DOWN:entity.solidArea.y += entity.speed;break;
		case LEFT:entity.solidArea.x -= entity.speed;break;
		case RIGHT:entity.solidArea.x += entity.speed;break;
		case UP_LEFT:entity.solidArea.y -= entity.speed/1.5;entity.solidArea.x -= entity.speed/1.5;break;
		case UP_RIGHT:entity.solidArea.y -= entity.speed/1.5;entity.solidArea.x += entity.speed/1.5;break;
		case DOWN_LEFT:entity.solidArea.y += entity.speed/1.5;entity.solidArea.x -= entity.speed/1.5;break;
		case DOWN_RIGHT:entity.solidArea.y += entity.speed/1.5;entity.solidArea.x += entity.speed/1.5;break;
		default:break;
		}
		
		if(entity.solidArea.intersects(gamePanel.player.solidArea)) {
			entity.collisionOn = true;
			contactPlayer = true;
		}
		
		entity.solidArea.x = entity.solidAreaDefaultX;
		entity.solidArea.y = entity.solidAreaDefaultY;
		gamePanel.player.solidArea.x = gamePanel.player.solidAreaDefaultX;
		gamePanel.player.solidArea.y = gamePanel.player.solidAreaDefaultY;
	
		return  contactPlayer;
	}
}
