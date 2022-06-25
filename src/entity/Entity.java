package entity;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import enums.Direction;
import enums.Type;
import main.GamePanel;
import main.UtilityTool;

public abstract class Entity {
	
	GamePanel gamePanel;
	public double worldX, worldY;

	// CHARACTER STATUS
	public double speed;
	public int maxLife;
	public int life;

	public int actionLockCounter = 0;
	public int spriteCounter = 0;
	public int spriteNumber = 1;
	public int solidAreaDefaultX, solidAreaDefaultY;
	public int invincibleCounter = 0;
	int dialogueIndex = 0;

	public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
	public Rectangle attackArea = new Rectangle(0, 0, 0, 0);

	String dialogues[] = new String[20];
	public String name;
	
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
	public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1, attackRight2;
	public BufferedImage image, image2, image3;

	public Direction direction = Direction.DOWN;
	public Type type;
	
	public boolean collisionOn = false;
	public boolean collision = false;
	public boolean invincible = false;
	boolean attacking = false;
	
	
	public Entity(GamePanel gamePanel) {
	this.gamePanel = gamePanel;
	}
	
	public BufferedImage setup(String imagePath, int width, int height) {
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;
		try {
			image = ImageIO.read(getClass().getResourceAsStream(imagePath+".png"));
			image = uTool.scaleImage(image, width, height);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return image;
	}
	public void setAction() {}
	public void speak() {
		if (dialogues[dialogueIndex] == null) {
			dialogueIndex = 0;
		}
		gamePanel.ui.currentDialogue = dialogues[dialogueIndex];
		dialogueIndex ++;
		
		switch (gamePanel.player.direction) {
		case UP:direction = Direction.DOWN;break;
		case DOWN:direction = Direction.UP;break;
		case LEFT:direction = Direction.RIGHT;break;
		case RIGHT:direction = Direction.LEFT;break;
		case UP_LEFT:direction = Direction.DOWN;break;
		case UP_RIGHT:direction = Direction.DOWN;break;
		case DOWN_LEFT:direction = Direction.UP;break;
		case DOWN_RIGHT:direction = Direction.UP;break;
		default:
			break;
		
		}
	}
	public void update() {
		setAction();
		collisionOn = false;
		gamePanel.collisionChecker.checkTile(this);
		gamePanel.collisionChecker.checkObject(this, false);
		gamePanel.collisionChecker.checkEntity(this, gamePanel.npc);
		gamePanel.collisionChecker.checkEntity(this, gamePanel.monster);
		boolean contactPlayer = gamePanel.collisionChecker.checkPlayer(this);
		
		if (this.type.equals(Type.Monster) && contactPlayer) {
			if(!gamePanel.player.invincible) {
				gamePanel.player.life -= 1;
				gamePanel.player.invincible = true;
				
			}
		}
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
		
		if (invincible) {
			invincibleCounter ++;
			if (invincibleCounter > 40) {
				invincible = false;
				invincibleCounter = 0;
			}
		}
	}
	public void draw(Graphics2D graph2D) {
		double screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
		double screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;
		
		BufferedImage image = null;
		
		int tempScreenX = (int)screenX , tempScreenY = (int)screenY;
		
		if (worldX + gamePanel.tileSize > gamePanel.player.worldX - gamePanel.player.screenX &&
				worldX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX &&
				worldY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY &&
				worldY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY) {
			
			switch(direction) {
			case UP:
				if (!attacking) {
					if(spriteNumber == 1) {image = up1;}
					if(spriteNumber == 2) {image = up2;}					
				}
				if (attacking) {
					tempScreenY -= gamePanel.tileSize; 
					if(spriteNumber == 1) {image = attackUp1;}
					if(spriteNumber == 2) {image = attackUp2;}
				}
				break;
				
			case DOWN:
				if (!attacking) {
					if(spriteNumber == 1) {image = down1;}
					if(spriteNumber == 2) {image = down2;}					
				}
				if (attacking) {
					if(spriteNumber == 1) {image = attackDown1;}
					if(spriteNumber == 2) {image = attackDown2;}
				}
				break;
				
			case LEFT:
				if (!attacking) {
					if(spriteNumber == 1) {image = left1;}
					if(spriteNumber == 2) {image = left2;}					
				}
				if (attacking) {
					tempScreenX -= gamePanel.tileSize;
					if(spriteNumber == 1) {image = attackLeft1;}
					if(spriteNumber == 2) {image = attackLeft2;}
				}
				break;
				
			case RIGHT:
				if (!attacking) {
					if(spriteNumber == 1) {image = right1;}
					if(spriteNumber == 2) {image = right2;}					
				}
				if (attacking) {
					if(spriteNumber == 1) {image = attackRight1;}
					if(spriteNumber == 2) {image = attackRight2;}
				}
				break;
				
			case UP_LEFT:
				if (!attacking) {
					if(spriteNumber == 1) {image = up1;}
					if(spriteNumber == 2) {image = up2;}					
				}
				if (attacking) {
					tempScreenY -= gamePanel.tileSize;
					if(spriteNumber == 1) {image = attackUp1;}
					if(spriteNumber == 2) {image = attackUp2;}
				}
				break;
				
			case UP_RIGHT:
				if (!attacking) {
					if(spriteNumber == 1) {image = up1;}
					if(spriteNumber == 2) {image = up2;}					
				}
				if (attacking) {
					tempScreenY -= gamePanel.tileSize;
					if(spriteNumber == 1) {image = attackUp1;}
					if(spriteNumber == 2) {image = attackUp2;}
				}
				break;
				
			case DOWN_LEFT:
				if (!attacking) {
					if(spriteNumber == 1) {image = down1;}
					if(spriteNumber == 2) {image = down2;}					
				}
				if (attacking) {
					if(spriteNumber == 1) {image = attackDown1;}
					if(spriteNumber == 2) {image = attackDown2;}
				}
				break;
				
			case DOWN_RIGHT:
				if (!attacking) {
					if(spriteNumber == 1) {image = down1;}
					if(spriteNumber == 2) {image = down2;}					
				}
				if (attacking) {
					if(spriteNumber == 1) {image = attackDown1;}
					if(spriteNumber == 2) {image = attackDown2;}
				}
				break;
			default:
				break;
			}
			
			if (invincible) {
				graph2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.5f));
			}
			graph2D.drawImage(image, tempScreenX, tempScreenY, null);
			graph2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f));
		}
	}
}