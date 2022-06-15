package main;

import java.awt.Rectangle;

import javax.swing.event.ListDataEvent;

import enums.Direction;

public class EventHandler {
	GamePanel gamePanel;
	EventRect eventRect[][];
	
	int previousEventX, previousEventY;
	boolean canTouchEvent = true;
	
	public EventHandler(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
		
		eventRect = new EventRect[gamePanel.maxWorldCol][gamePanel.maxWorldRow];

		for (int col = 0; col < gamePanel.maxWorldCol; col++) {
			for (int row = 0; row < gamePanel.maxWorldCol; row++) {
				eventRect[col][row] = new EventRect();
				eventRect[col][row].x = 23;
				eventRect[col][row].y = 23;
				eventRect[col][row].width = 2;
				eventRect[col][row].height = 2;
				eventRect[col][row].eventRectDefaultX = eventRect[col][row].x;
				eventRect[col][row].eventRectDefaultY = eventRect[col][row].y;
			}			
		}
		/*int col = 0;
		int row = 0;
		while (col < gamePanel.maxWorldCol && row < gamePanel.maxWorldCol) {
			eventRect[col][row] = new EventRect();
			eventRect[col][row].x = 23;
			eventRect[col][row].y = 23;
			eventRect[col][row].width = 2;
			eventRect[col][row].height = 2;
			eventRect[col][row].eventRectDefaultX = eventRect[col][row].x;
			eventRect[col][row].eventRectDefaultY = eventRect[col][row].y;
			
			col++;
			if(col == gamePanel.maxWorldCol) {
				col = 0;
				row++;
			}
		}*/
	}
	
	public void checkEvent() {
		
		int xDistance = (int) Math.abs(gamePanel.player.worldX - previousEventX);
		int yDistance = (int) Math.abs(gamePanel.player.worldY - previousEventY);
		int distance = Math.max(xDistance, yDistance);
		if (distance > gamePanel.tileSize) {
			canTouchEvent = true;
		}
		if (canTouchEvent) {
			if(hit(27,16,Direction.ANY) && gamePanel.keyHandler.rightPressed) {damagePit(27,16,gamePanel.dialogueState);}
			if(hit(23,12,Direction.UP)) {healingPool(23,12,gamePanel.dialogueState);}
			if(hit(19,16,Direction.ANY)) {teleport(19,16,gamePanel.dialogueState);}
		}
		
	}


	public boolean hit(int col, int row, Direction reqDirection) {
		boolean hit = false;
		gamePanel.player.solidArea.x = (int) (gamePanel.player.worldX + gamePanel.player.solidArea.x);
		gamePanel.player.solidArea.y = (int) (gamePanel.player.worldY + gamePanel.player.solidArea.y);

		eventRect[col][row].x = col*gamePanel.tileSize + eventRect[col][row].x;
		eventRect[col][row].y = row*gamePanel.tileSize + eventRect[col][row].y;
		
		if (gamePanel.player.solidArea.intersects(eventRect[col][row]) &&eventRect[col][row].eventDone == false) {
			if ((gamePanel.player.direction).equals(reqDirection) || reqDirection.equals(Direction.ANY)) {
				hit = true;
				previousEventX = (int) gamePanel.player.worldX;
				previousEventY = (int) gamePanel.player.worldY;
			}
		}
		
		gamePanel.player.solidArea.x = gamePanel.player.solidAreaDefaultX;
		gamePanel.player.solidArea.y = gamePanel.player.solidAreaDefaultY;
		eventRect[col][row].x = eventRect[col][row].eventRectDefaultX;
		eventRect[col][row].y = eventRect[col][row].eventRectDefaultY;
		
		return hit;
	}
	
	private void damagePit(int col, int row, int gameState) {
		gamePanel.gameState = gameState;
		gamePanel.ui.currentDialogue = "You fall into a pit!";
		gamePanel.player.life -= 1;
		//eventRect[col][row].eventDone = true;
		canTouchEvent = false;
	}
	
	private void healingPool(int col, int row, int gameState) {
		if (gamePanel.keyHandler.interactPressed) {
			gamePanel.gameState = gameState;
			gamePanel.ui.currentDialogue = "You drink the water.\nYour life has been recovered.";			
			gamePanel.player.life = gamePanel.player.maxLife;
			//eventRect[col][row].eventDone = true;
			canTouchEvent = false;
		}
	}
	
	private void teleport(int col, int row, int gameState) {
		gamePanel.gameState = gameState;
		gamePanel.ui.currentDialogue = "Teleport!";
		gamePanel.player.worldX = gamePanel.tileSize * 37;
		gamePanel.player.worldY = gamePanel.tileSize * 10;
		//eventRect[col][row].eventDone = true;
		canTouchEvent = false;
	}
}
