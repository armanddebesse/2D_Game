package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public abstract class Entity {
	
	public double worldX, worldY;
	public double speed;
	
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
	public Direction direction;
	
	public int spriteCounter = 0;
	public int spriteNumber = 1;
	public Rectangle solidArea;
	public int solidAreaDefaultX, solidAreaDefaultY;
	public boolean collisionOn = false;
}