package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import enums.Direction;

public abstract class Entity {
	public int worldX, worldY;
	public int speed;
	
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
	public Direction direction;
	
	public int spriteCounter = 0;
	public int spriteNumber = 1;
	public Rectangle solidArea;
	public boolean collisionOn = false;
}