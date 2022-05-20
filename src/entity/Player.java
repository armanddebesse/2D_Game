package entity;

import main.KeyHandler;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Player extends Entity{
	GamePanel gamePanel;
	KeyHandler keyHandler;
	
	public final int screenX;
	public final int screenY;
	int hasKey = 0;
	
	public Player(GamePanel gamePanel, KeyHandler keyHandler) {
		this.gamePanel = gamePanel;
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
		getPlayerImage();
	}
	
	public void setDefaultValues() {
		worldX = gamePanel.tileSize * 23;
		worldY = gamePanel.tileSize * 21;
		speed = gamePanel.worldWidth/1200;
		direction = Direction.DOWN;
	}
	
	public void getPlayerImage() {
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		if(keyHandler.upPressed || keyHandler.downPressed || keyHandler.leftPressed || keyHandler.rightPressed) {

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
			
			// CHECK TILE COLLISION
			collisionOn = false;
			gamePanel.collisionChecker.checkTile(this);
			
			//CHECK OBJECT COLLISION
			int objIndex = gamePanel.collisionChecker.checkObject(this, true);
			pickUpObject(objIndex);
			
			//IF COLLISION IS FALSE, PLAYER CAN MOVE
			if(collisionOn == false) {
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
				case UP_LEFT:
					worldY -= speed/1.5;
					worldX -= speed/1.5;
					break;
				case UP_RIGHT:
					worldY -= speed/1.5;
					worldX += speed/1.5;
					break;
				case DOWN_LEFT:
					worldY += speed/1.5;
					worldX -= speed/1.5;
					break;
				case DOWN_RIGHT:
					worldY += speed/1.5;
					worldX += speed/1.5;
					break;
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
			String objectName = gamePanel.obj[i].name;
			switch(objectName) {
			case "Key":
			hasKey++;
				gamePanel.obj[i] = null;
				break;
			case "Door":
				if(hasKey > 0) {
					gamePanel.obj[i] = null;
					hasKey--; 
				}
				break;
			
			}
		}
	}
	
	public void draw(Graphics2D graph2D) {
		BufferedImage image = null;
		switch(direction) {
		case UP:
			if(spriteNumber == 1) {
				image = up1;
			}
			else if(spriteNumber == 2) {
				image = up2;
			}
			break;
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
		case UP_LEFT:
			if(spriteNumber == 1) {
				image = up1;
			}
			else if(spriteNumber == 2) {
				image = up2;
			}
			break;
		case UP_RIGHT:
			if(spriteNumber == 1) {
				image = up1;
			}
			else if(spriteNumber == 2) {
				image = up2;
			}
			break;
		case DOWN_LEFT:
			if(spriteNumber == 1) {
				image = down1;
			}
			else if(spriteNumber == 2) {
				image = down2;
			}
			break;
		case DOWN_RIGHT:
			if(spriteNumber == 1) {
				image = down1;
			}
			else if(spriteNumber == 2) {
				image = down2;
			}
			break;
		default:
			break;
		}
		graph2D.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
	}
}
