package entity;

import main.KeyHandler;
import main.UtilityTool;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import enums.Direction;
import main.GamePanel;

public class Player extends Entity{
	KeyHandler keyHandler;
	
	public final int screenX;
	public final int screenY;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		super(gp);
		
		this.keyHandler = keyH;
		
		screenX = gamePanel.screenWidth/2 - gamePanel.tileSize/2;
		screenY = gamePanel.screenHeight/2 - gamePanel.tileSize/2;
		
		solidArea = new Rectangle(8, 12, 28, 28);
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		setDefaultValues();
		getPlayerImage();
	}
	
	public void setDefaultValues() {
		worldX = gamePanel.tileSize * 8;
		worldY = gamePanel.tileSize * 20;
		speed = 4;
		direction = Direction.DOWN;
	}
	
	public void getPlayerImage() {
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
		if(keyHandler.upPressed || keyHandler.downPressed || keyHandler.leftPressed || keyHandler.rightPressed) {

			if(keyHandler.upPressed) {
				direction = Direction.UP;
			}
			else if(keyHandler.downPressed) {
				direction = Direction.DOWN;
			}
			else if(keyHandler.leftPressed) {
				direction = Direction.LEFT;
			}
			else if(keyHandler.rightPressed) {
				direction = Direction.RIGHT;
			}
			
			//CHECK TILE COLLISION
			collisionOn = false;
			gamePanel.collisionHandler.checkTile(this);
			
			//CHECK OBJECT COLLISION
			pickUpObject(gamePanel.collisionHandler.checkObject(this, true));
			
			//CHECK ENTITY COLLISION
			interactNPC(gamePanel.collisionHandler.checkEntity(this, gamePanel.npc));
			
			//IF COLLISION IS FALSE PLAYER CAN MOVE
			if (!collisionOn) {
				switch(direction) {
				case UP:
					worldY -= speed;
					break;
				case DOWN:
					worldY += speed;
					break;
				case LEFT:
					worldX -= speed;
					break;
				case RIGHT:
					worldX += speed;
					break;
				}
			}
			
			spriteCounter++;
			if(spriteCounter > 10) {
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
	
	public void pickUpObject(int index) {
		if (index != 999) {

		}
	}
	
	public void interactNPC(int i) {
		if (i != 999) {
			
		}
	}
	
	public void draw(Graphics2D graph2D) {
		BufferedImage image = null;
		switch(direction) {
		case DOWN:
			if(spriteNumber == 1) {
				image = down1;
			}
			else if(spriteNumber == 2) {
				image = down2;
			}
			break;
		case LEFT:
			if(spriteNumber == 1) {
				image = left1;
			}
			else if(spriteNumber == 2) {
				image = left2;
			}
			break;
		case RIGHT:
			if(spriteNumber == 1) {
				image = right1;
			}
			else if(spriteNumber == 2) {
				image = right2;
			}
			break;
		case UP:
			if(spriteNumber == 1) {
				image = left1;
				image = up1;
			}
			else if(spriteNumber == 2) {
				image = up2;
			}
			break;
		default:
			break;
		}
		
		int x = screenX;
		int y = screenY;
		
		if (screenX > worldX) {
			x = worldX;
		}
		if (screenY > worldY) {
			y = worldY;
		}
		int rightOffset = gamePanel.screenWidth - screenX;
		if (rightOffset > gamePanel.worldWidth - worldX) {
			x = gamePanel.screenWidth - (gamePanel.worldWidth - worldX);
		}
		int bottomOffset = gamePanel.screenHeight - screenY;
		if (bottomOffset > gamePanel.worldHeight - worldY) {
			y = gamePanel.screenHeight - (gamePanel.worldWidth - worldY);
		}
		
		graph2D.drawImage(image, x, y, null);
	}
}
