package entity;

import main.KeyHandler;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import enums.Direction;
import main.GamePanel;

public class Player extends Entity{
	GamePanel gamePanel;
	KeyHandler keyHandler;
	
	public final int screenX;
	public final int screenY;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		this.gamePanel = gp;
		this.keyHandler = keyH;
		
		screenX = gamePanel.screenWidth/2 - gamePanel.tileSize/2;
		screenY = gamePanel.screenHeight/2 - gamePanel.tileSize/2;
		
		solidArea = new Rectangle(8, 16, 32, 32);
		
		setDefaultValues();
		getPlayerImage();
	}
	
	public void setDefaultValues() {
		worldX = gamePanel.tileSize * 8;
		worldY = gamePanel.tileSize * 20;
		speed = 2;
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
		graph2D.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
	}
}
