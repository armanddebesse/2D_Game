package main;

import object.Chest;
import object.Door;
import object.Key;

public class ObjectHandler {
	GamePanel gamePanel;
	
	public ObjectHandler(GamePanel _gamePanel) {
		gamePanel = _gamePanel;
	}
	
	public void setObject() {
		gamePanel.obj[0] = new Key();
		gamePanel.obj[0].worldX = 10 * gamePanel.tileSize;
		gamePanel.obj[0].worldY = 23 * gamePanel.tileSize;

		gamePanel.obj[1] = new Key();
		gamePanel.obj[1].worldX = 20 * gamePanel.tileSize;
		gamePanel.obj[1].worldY = 8 * gamePanel.tileSize;

		gamePanel.obj[2] = new Door();
		gamePanel.obj[2].worldX = 10 * gamePanel.tileSize;
		gamePanel.obj[2].worldY = 35 * gamePanel.tileSize;

		gamePanel.obj[3] = new Chest();
		gamePanel.obj[3].worldX = 12 * gamePanel.tileSize;
		gamePanel.obj[3].worldY = 37 * gamePanel.tileSize;
	}
}
