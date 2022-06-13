package main;

import java.awt.Rectangle;

public class EventHandler {
	GamePanel gamePanel;
	Rectangle eventRectangle;
	int eventRectangleDefaultX, eventRectangleDefaultY;
	
	public EventHandler(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
		
		eventRectangle = new Rectangle();
		eventRectangle.x = 23;
		eventRectangle.y = 23;
		eventRectangle.width = 2;
		eventRectangle.height = 2;
		eventRectangleDefaultX = eventRectangle.x;
		eventRectangleDefaultY = eventRectangle.y;
	}
	
	public void checkEvent() {
		
	}
	public boolean hit(int eventCol, int eventRow, String reqDirection) {
		boolean hit = false;
		gamePanel.player.solidArea.x = (int) (gamePanel.player.worldX + gamePanel.player.solidArea.x);
		gamePanel.player.solidArea.y = (int) (gamePanel.player.worldX + gamePanel.player.solidArea.y);
		eventRectangle.x = eventCol*gamePanel.tileSize + eventRectangle.x;
		eventRectangle.y = eventRow*gamePanel.tileSize + eventRectangle.y;
		
		if (gamePanel.player.solidArea.intersects(eventRectangle)) {
			if (gamePanel.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
				hit = true;
			}
		}
		
		gamePanel.player.solidArea.x = gamePanel.player.solidAreaDefaultX;
		gamePanel.player.solidArea.y = gamePanel.player.solidAreaDefaultY;
		return hit;
	}
}
