package entity;

import java.awt.image.BufferedImage;

public abstract class Entity {
	public int x, y;
	public int speed;
	
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
	public Direction direction;
	
	public int spriteCounter = 0;
	public int spriteNumber = 1;
}

enum Direction{
	RIGHT,
	LEFT,
	DOWN,
	UP
}