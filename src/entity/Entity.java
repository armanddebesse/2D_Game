package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import enums.Direction;
import main.GamePanel;
import main.UtilityTool;

public abstract class Entity {
	GamePanel gamePanel;
	public int worldX, worldY;
	public int speed;
	
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
	public Direction direction;
	
	public int spriteCounter = 0;
	public int spriteNumber = 1;
	public Rectangle solidArea = new Rectangle(0,0,48,48);
	public int solidAreaDefaultX, solidAreaDefaultY;
	public boolean collisionOn = false;
	public int actionLockCounter = 0;
	
	public Entity(GamePanel _gamePanel) {
		gamePanel = _gamePanel;
	}
	
	public void setAction() {}
	
	public void update() {
		setAction();
		
		collisionOn = false;
		gamePanel.collisionHandler.checkTile(this);
		gamePanel.collisionHandler.checkObject(this, false);
		gamePanel.collisionHandler.checkPlayer(this);
		
		//IF COLLISION IS FALSE ENTITY CAN MOVE
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
	
	public void draw(Graphics2D graph2D) {
		BufferedImage image = null;
		
		int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
		int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;
		
		if (worldX + gamePanel.tileSize> gamePanel.player.worldX - gamePanel.player.screenX &&
			worldX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX &&
			worldY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY &&
			worldY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY) {
			
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
	
	public BufferedImage setup(String imageName) {
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;
		try {
			image = ImageIO.read(getClass().getResourceAsStream(imageName+".png"));
			image =  uTool.scaleImage(image, gamePanel.tileSize, gamePanel.tileSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return image;
	}
}