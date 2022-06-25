package entity;

import main.KeyHandler;

import java.awt.Rectangle;

import javax.swing.event.ListDataEvent;

import enums.Direction;
import enums.Type;
import main.GamePanel;

public class Player extends Entity{
	KeyHandler keyHandler;
	
	public final int screenX;
	public final int screenY;
	public int hasKey = 0;
	
	public Player(GamePanel gamePanel, KeyHandler keyHandler) {
		super(gamePanel);
		
		this.keyHandler = keyHandler;
		
		screenX = gamePanel.screenWidth/2- (gamePanel.tileSize/2);
		screenY = gamePanel.screenHeight/2- (gamePanel.tileSize/2);
		
		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 16;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 32;
		solidArea.height = 32;
		
		attackArea.width = 36;
		attackArea.height = 36;
		
		setDefaultValues();
		getImage();
		getAttackImage();
	}
	
	public void setDefaultValues() {
		type = Type.Player;
		
		worldX = gamePanel.tileSize * 23;
		worldY = gamePanel.tileSize * 21;
		speed = 2;// gamePanel.tileSize * gamePanel.maxWorldCol/1200;
		direction = Direction.DOWN;
		
		// PLAYER STATUS
		maxLife = 6;
		life = maxLife;
	}
	
	public void getImage() {
		up1 = setup("/player/boy_up_1", gamePanel.tileSize, gamePanel.tileSize);
		up2 = setup("/player/boy_up_2", gamePanel.tileSize, gamePanel.tileSize);
		down1 = setup("/player/boy_down_1", gamePanel.tileSize, gamePanel.tileSize);
		down2 = setup("/player/boy_down_2", gamePanel.tileSize, gamePanel.tileSize);
		right1 = setup("/player/boy_right_1", gamePanel.tileSize, gamePanel.tileSize);
		right2 = setup("/player/boy_right_2", gamePanel.tileSize, gamePanel.tileSize);
		left1 = setup("/player/boy_left_1", gamePanel.tileSize, gamePanel.tileSize);
		left2 = setup("/player/boy_left_2", gamePanel.tileSize, gamePanel.tileSize);
	}
	
	public void getAttackImage() {
		attackUp1 = setup("/player/boy_attack_up_1", gamePanel.tileSize, gamePanel.tileSize*2);
		attackUp2 = setup("/player/boy_attack_up_2", gamePanel.tileSize, gamePanel.tileSize*2);
		attackDown1 = setup("/player/boy_attack_down_1", gamePanel.tileSize, gamePanel.tileSize*2);
		attackDown2 = setup("/player/boy_attack_down_2", gamePanel.tileSize, gamePanel.tileSize*2);
		attackRight1 = setup("/player/boy_attack_right_1", gamePanel.tileSize*2, gamePanel.tileSize);
		attackRight2 = setup("/player/boy_attack_right_2", gamePanel.tileSize*2, gamePanel.tileSize);
		attackLeft1 = setup("/player/boy_attack_left_1", gamePanel.tileSize*2, gamePanel.tileSize);
		attackLeft2 = setup("/player/boy_attack_left_2", gamePanel.tileSize*2, gamePanel.tileSize);
	}
	
	public void update() {
		
		// CHECK TILE COLLISION
		collisionOn = false;
		gamePanel.collisionChecker.checkTile(this);
						
		// CHECK OBJECT COLLISION
		int objIndex = gamePanel.collisionChecker.checkObject(this, true);
		pickUpObject(objIndex);
			
		// CHECK NPC COLLISION
		int npcIndex = gamePanel.collisionChecker.checkEntity(this, gamePanel.npc);
		interactNPC(npcIndex);
			
		// CHECK NPC COLLISION
		int monsterIndex = gamePanel.collisionChecker.checkEntity(this, gamePanel.monster);
		contactMonster(monsterIndex);
		
		// CHECK EVENT
		gamePanel.eventHandler.checkEvent();			
			
		// CHECK ATTACKING
		attack();
				
		gamePanel.keyHandler.interactPressed = false;
		if(attacking) {
			attacking();
		}
		else if(keyHandler.upPressed || keyHandler.downPressed || keyHandler.leftPressed || keyHandler.rightPressed) {
			// DIRECTION
			if(keyHandler.upPressed && !keyHandler.downPressed && !keyHandler.leftPressed && !keyHandler.rightPressed) {
				direction = Direction.UP;
			}
			else if(!keyHandler.upPressed && keyHandler.downPressed && !keyHandler.leftPressed && !keyHandler.rightPressed) {
				direction = Direction.DOWN;
			}
			else if(!keyHandler.upPressed && !keyHandler.downPressed && keyHandler.leftPressed && !keyHandler.rightPressed) {
				direction = Direction.LEFT;
			}
			else if(!keyHandler.upPressed && !keyHandler.downPressed && !keyHandler.leftPressed && keyHandler.rightPressed) {
				direction = Direction.RIGHT;
			}
			else if(keyHandler.upPressed && !keyHandler.downPressed && keyHandler.leftPressed && !keyHandler.rightPressed) {
				direction = Direction.UP_LEFT;
			}
			else if(keyHandler.upPressed && !keyHandler.downPressed && !keyHandler.leftPressed && keyHandler.rightPressed) {
				direction = Direction.UP_RIGHT;
			}
			else if(!keyHandler.upPressed && keyHandler.downPressed && keyHandler.leftPressed && !keyHandler.rightPressed) {
				direction = Direction.DOWN_LEFT;
			}
			else if(!keyHandler.upPressed && keyHandler.downPressed && !keyHandler.leftPressed && keyHandler.rightPressed) {
				direction = Direction.DOWN_RIGHT;
			}
			else {;}

			//IF COLLISION IS FALSE, PLAYER CAN MOVE
			if(collisionOn == false) {
				switch(direction) {
				case UP:worldY -= speed;break;
				case DOWN:worldY += speed;break;
				case LEFT:worldX -= speed;break;
				case RIGHT:worldX += speed;break;
				case UP_LEFT:worldY -= speed/1.5;worldX -= speed/1.5;break;
				case UP_RIGHT:worldY -= speed/1.5;worldX += speed/1.5;break;
				case DOWN_LEFT:worldY += speed/1.5;worldX -= speed/1.5;break;
				case DOWN_RIGHT:worldY += speed/1.5;worldX += speed/1.5;break;
				}	
			}
			
			spriteCounter++;
			if(spriteCounter > 20) {
				if(spriteNumber == 1) {
					spriteNumber = 2;
				}
				else if(spriteNumber == 2) {
					spriteNumber = 1;
				}
				spriteCounter = 0;
			}
		}
		if (invincible) {
			invincibleCounter ++;
			if (invincibleCounter > 60) {
				invincible = false;
				invincibleCounter = 0;
			}
		}
	}
	


	public void pickUpObject(int i) {
		if (i != 999) {
			if (keyHandler.interactPressed) {
				String objectName = gamePanel.obj[i].name;
				switch(objectName) {
				case "Key":
					gamePanel.playSE(1);
					hasKey++;
					gamePanel.obj[i] = null;
					gamePanel.ui.showMessage("You got the key!");
					break;
				case "Door":
					if(hasKey > 0) {
						gamePanel.playSE(3);
						hasKey--; 
						gamePanel.obj[i] = null;
						gamePanel.ui.showMessage("You opened the door!");
					} else {
						gamePanel.ui.showMessage("You need a key!");
					}
					break;
				case "Boots":
					gamePanel.playSE(2);
					speed += 1;
					gamePanel.obj[i] = null;
					gamePanel.ui.showMessage("Speed up!");
					break;
				case "Chest":
					gamePanel.ui.gameFinished = true;
					gamePanel.stopMusic();
					gamePanel.playSE(4);

				}
			}
		}
	}
	public void interactNPC(int i) {
		if(i!=999) {
			if (keyHandler.interactPressed) {
				gamePanel.gameState = gamePanel.dialogueState;
				gamePanel.npc[i].speak();
			}
		}
	}
	
	private void contactMonster(int i) {
		if(i!=999) {
			if (!invincible) {
				life -= 1;
				invincible = true;
			}
		}
	}

	private void damageMonster(int i) {
		if(i!=999) {
			if (!gamePanel.monster[i].invincible) {
				gamePanel.monster[i].life -= 1;
				gamePanel.monster[i].invincible = true;
				
				if (gamePanel.monster[i].life <= 0) {
					gamePanel.monster[i] = null;
				}
			}
		}
	}
	
	private void attack() {
		if (keyHandler.attackPressed) {
			attacking = true;
		}
	}
	private void attacking() {
		spriteCounter++;
		if (spriteCounter <= 10) {
			spriteNumber = 1;
		}
		if (spriteCounter > 10 && spriteCounter <= 50) {
			spriteNumber = 2;
			
			// Save the current worldX, worldY, solidArea
			int currentworldX = (int)worldX;
			int currentworldY = (int)worldY;
			int solidAreaWidth = solidArea.width;
			int solidAreaHeight = solidArea.height;
			
			// Attack area become solid area
			switch (direction) {
			case UP:worldY -= attackArea.height;break;
			case DOWN:worldY += attackArea.height;break;
			case LEFT:worldX -= attackArea.width;break;
			case RIGHT:worldX += attackArea.width;break;
			case UP_LEFT:worldY -= attackArea.height;worldX -= attackArea.width;break;
			case UP_RIGHT:worldY -= attackArea.height;worldX += attackArea.width;break;
			case DOWN_LEFT:worldY += attackArea.height;worldX -= attackArea.width;break;
			case DOWN_RIGHT:worldY += attackArea.height;worldX += attackArea.width;break;
			}
			
			// Check monster collision
			int monsterIndex = gamePanel.collisionChecker.checkEntity(this, gamePanel.monster);
			damageMonster(monsterIndex);
			
			//restore original data
			
			worldX = currentworldX;
			worldY = currentworldY;
			solidArea.height = solidAreaHeight;
			solidArea.width = solidAreaWidth;
			
		}
		if (spriteCounter > 50) {
			spriteNumber = 1;
			spriteCounter = 0;
			attacking = false;
		}
	}

}
