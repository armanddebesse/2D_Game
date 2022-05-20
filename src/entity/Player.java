package entity;

import main.KeyHandler;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Player extends Entity{
	GamePanel gamePanel;
	KeyHandler keyHandler;
	
	public final int screenX;
	public final int screenY;
	
	public Player(GamePanel gamePanel, KeyHandler keyHandler) {
		this.gamePanel = gamePanel;
		this.keyHandler = keyHandler;
		
		screenX = gamePanel.screenWidth/2- (gamePanel.tileSize/2);
		screenY = gamePanel.screenHeight/2- (gamePanel.tileSize/2);
		
		setDefaultValues();
		getPlayerImage();
	}
	
	public void setDefaultValues() {
		worldX = gamePanel.tileSize * 23;
		worldY = gamePanel.tileSize * 21;
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
				worldY -= speed;
			}
			else if(keyHandler.downPressed) {
				direction = Direction.DOWN;
				worldY += speed;
			}
			else if(keyHandler.leftPressed) {
				direction = Direction.LEFT;
				worldX -= speed;
			}
			else if(keyHandler.rightPressed) {
				direction = Direction.RIGHT;
				worldX += speed;
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
