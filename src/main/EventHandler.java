package main;

import java.awt.Rectangle;

import javax.swing.event.ListDataEvent;

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
		if(hit(27,16,"RIGHT") && gamePanel.keyHandler.rightPressed) {
			damagePit(gamePanel.dialogueState);
		}
		if(hit(23,12,"UP")) {
			healingPool(gamePanel.dialogueState);
		}
		if(hit(19,16,"ANY")) {
			teleport(gamePanel.dialogueState);
		}
	}


	public boolean hit(int eventCol, int eventRow, String reqDirection) {
		boolean hit = false;
		gamePanel.player.solidArea.x = (int) (gamePanel.player.worldX + gamePanel.player.solidArea.x);
		gamePanel.player.solidArea.y = (int) (gamePanel.player.worldY + gamePanel.player.solidArea.y);

		eventRectangle.x = eventCol*gamePanel.tileSize + eventRectangle.x;
		eventRectangle.y = eventRow*gamePanel.tileSize + eventRectangle.y;
		
		if (gamePanel.player.solidArea.intersects(eventRectangle)) {
			if (gamePanel.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("ANY")) {
				hit = true;
			}
		}
		
		gamePanel.player.solidArea.x = gamePanel.player.solidAreaDefaultX;
		gamePanel.player.solidArea.y = gamePanel.player.solidAreaDefaultY;
		eventRectangle.x = eventRectangleDefaultX;
		eventRectangle.y = eventRectangleDefaultY;
		
		return hit;
	}
	
	private void damagePit(int gameState) {
		gamePanel.gameState = gameState;
		gamePanel.ui.currentDialogue = "You fall into a pit!";
		gamePanel.player.life -= 1;
	}
	
	private void healingPool(int gameState) {
		if (gamePanel.keyHandler.interactPressed) {
			gamePanel.gameState = gameState;
			gamePanel.ui.currentDialogue = "You drink the water.\nYour life has been recovered.";			
			gamePanel.player.life = gamePanel.player.maxLife;
		}
	}
	
	private void teleport(int gameState) {
		gamePanel.gameState = gameState;
		gamePanel.ui.currentDialogue = "Teleport!";
		gamePanel.player.worldX = gamePanel.tileSize * 37;
		gamePanel.player.worldY = gamePanel.tileSize * 10;
	}
}
