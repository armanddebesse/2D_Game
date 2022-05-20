package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class SuperObject {
	public BufferedImage image;
	public String name;
	public boolean collision = false;
	public int worldX, worldY;
	public Rectangle solidArea = new Rectangle(0,0,48,48);
	public int solidDefaultAreaX = 0;
	public int solidDefaultAreaY = 0;
	public void draw(Graphics2D g2, GamePanel gamePanel) {
		double screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
		double screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;
		
		if (worldX + gamePanel.tileSize > gamePanel.player.worldX - gamePanel.player.screenX &&
			worldX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX &&
			worldY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY &&
			worldY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY) {
			
			g2.drawImage(image, (int)screenX, (int)screenY, gamePanel.tileSize, gamePanel.tileSize, null);
		}
	}
}