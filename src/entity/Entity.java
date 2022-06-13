package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public abstract class Entity {
	
	GamePanel gamePanel;
	public double worldX, worldY;
	public double speed;
	public int actionLockCounter = 0;
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
	public String direction;
	
	public int spriteCounter = 0;
	public int spriteNumber = 1;
	public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
	public int solidAreaDefaultX, solidAreaDefaultY;
	public boolean collisionOn = false;
	String dialogues[] = new String[20];
	int dialogueIndex = 0;

	
	// CHARACTER STATUS
	public int maxLife;
	public int life;
	
	public Entity(GamePanel gamePanel) {
	this.gamePanel = gamePanel;
	}
	
	public BufferedImage setup(String imagePath) {
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;
		try {
			image = ImageIO.read(getClass().getResourceAsStream(imagePath+".png"));
			image = uTool.scaleImage(image, gamePanel.tileSize, gamePanel.tileSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return image;
	}
	public void setAction() {}
	public void speak() {
		if (dialogues[dialogueIndex] == null) {
			dialogueIndex = 0;
			gamePanel.gameState = gamePanel.playState;
		}
		gamePanel.ui.currentDialogue = dialogues[dialogueIndex];
		dialogueIndex ++;
		
		switch (gamePanel.player.direction) {
		case "UP":direction = "DOWN";break;
		case "DOWN":direction = "UP";break;
		case "LEFT":direction = "RIGHT";break;
		case "RIGHT":direction = "LEFT";break;
		case "UP_LEFT":direction = "DOWN";break;
		case "UP_RIGHT":direction = "DOWN";break;
		case "DOWN_LEFT":direction = "UP";break;
		case "DOWN_RIGHT":direction = "UP";break;
		
		}
	}
	public void update() {
		setAction();
		collisionOn = false;
		gamePanel.collisionChecker.checkTile(this);
		gamePanel.collisionChecker.checkObject(this, false);
		gamePanel.collisionChecker.checkPlayer(this);
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
	public void draw(Graphics2D graph2D) {
		double screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
		double screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;
		
		BufferedImage image = null;
		
		if (worldX + gamePanel.tileSize > gamePanel.player.worldX - gamePanel.player.screenX &&
				worldX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX &&
				worldY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY &&
				worldY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY) {
			
			switch(direction) {
			case "UP":
				if(spriteNumber == 1) {
					image = up1;
				}
				else if(spriteNumber == 2) {
					image = up2;
				}
				break;
			case "DOWN":
				if(spriteNumber == 1) {
					image = down1;
				}
				else if(spriteNumber == 2) {
					image = down2;
				}
				break;
			case "LEFT":
				if(spriteNumber == 1) {
					image = left1;
				}
				else if(spriteNumber == 2) {
					image = left2;
				}
				break;
			case "RIGHT":
				if(spriteNumber == 1) {
					image = right1;
				}
				else if(spriteNumber == 2) {
					image = right2;
				}
				break;
			case "UP_LEFT":
				if(spriteNumber == 1) {
					image = up1;
				}
				else if(spriteNumber == 2) {
					image = up2;
				}
				break;
			case "UP_RIGHT":
				if(spriteNumber == 1) {
					image = up1;
				}
				else if(spriteNumber == 2) {
					image = up2;
				}
				break;
			case "DOWN_LEFT":
				if(spriteNumber == 1) {
					image = down1;
				}
				else if(spriteNumber == 2) {
					image = down2;
				}
				break;
			case "DOWN_RIGHT":
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
				graph2D.drawImage(image, (int)screenX, (int)screenY, gamePanel.tileSize, gamePanel.tileSize, null);
			}
		
	}
}