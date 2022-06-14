package entity;

import main.KeyHandler;

import java.awt.Rectangle;

import javax.swing.event.ListDataEvent;

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
		
		setDefaultValues();
		getImage();
	}
	
	public void setDefaultValues() {
		worldX = gamePanel.tileSize * 23;
		worldY = gamePanel.tileSize * 21;
		speed = 2;// gamePanel.tileSize * gamePanel.maxWorldCol/1200;
		direction = "DOWN";
		
		// PLAYER STATUS
		maxLife = 6;
		life = maxLife;
	}
	
	public void getImage() {
		up1 = setup("/player/boy_up_1");
		up2 = setup("/player/boy_up_2");
		down1 = setup("/player/boy_down_1");
		down2 = setup("/player/boy_down_2");
		right1 = setup("/player/boy_right_1");
		right2 = setup("/player/boy_right_2");
		left1 = setup("/player/boy_left_1");
		left2 = setup("/player/boy_left_2");
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
					
					// CHECK EVENT
					gamePanel.eventHandler.checkEvent();			
					
					gamePanel.keyHandler.interactPressed = false;

		if(keyHandler.upPressed || keyHandler.downPressed || keyHandler.leftPressed || keyHandler.rightPressed) {
			// DIRECTION
			if(keyHandler.upPressed && !keyHandler.downPressed && !keyHandler.leftPressed && !keyHandler.rightPressed) {
				direction = "UP";
			}
			else if(!keyHandler.upPressed && keyHandler.downPressed && !keyHandler.leftPressed && !keyHandler.rightPressed) {
				direction = "DOWN";
			}
			else if(!keyHandler.upPressed && !keyHandler.downPressed && keyHandler.leftPressed && !keyHandler.rightPressed) {
				direction = "LEFT";
			}
			else if(!keyHandler.upPressed && !keyHandler.downPressed && !keyHandler.leftPressed && keyHandler.rightPressed) {
				direction = "RIGHT";
			}
			else if(keyHandler.upPressed && !keyHandler.downPressed && keyHandler.leftPressed && !keyHandler.rightPressed) {
				direction = "UP_LEFT";
			}
			else if(keyHandler.upPressed && !keyHandler.downPressed && !keyHandler.leftPressed && keyHandler.rightPressed) {
				direction = "UP_RIGHT";
			}
			else if(!keyHandler.upPressed && keyHandler.downPressed && keyHandler.leftPressed && !keyHandler.rightPressed) {
				direction = "DOWN_LEFT";
			}
			else if(!keyHandler.upPressed && keyHandler.downPressed && !keyHandler.leftPressed && keyHandler.rightPressed) {
				direction = "DOWN_RIGHT";
			}
			else {;}

			//IF COLLISION IS FALSE, PLAYER CAN MOVE
			if(collisionOn == false) {
				switch(direction) {
				case "UP":worldY -= speed;break;
				case "DOWN":worldY += speed;break;
				case "LEFT":worldX -= speed;break;
				case "RIGHT":worldX += speed;break;
				case "UP_LEFT":worldY -= speed/1.5;worldX -= speed/1.5;break;
				case "UP_RIGHT":worldY -= speed/1.5;worldX += speed/1.5;break;
				case "DOWN_LEFT":worldY += speed/1.5;worldX -= speed/1.5;break;
				case "DOWN_RIGHT":worldY += speed/1.5;worldX += speed/1.5;break;
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
}
