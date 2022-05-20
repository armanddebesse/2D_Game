package main;

import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Key;

public class AssetSetter {
GamePanel gamePanel;

	public AssetSetter(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	public void setObject() {
		gamePanel.obj[0] = new OBJ_Key();
		gamePanel.obj[0].worldX = 23 * gamePanel.tileSize;
		gamePanel.obj[0].worldY = 7 * gamePanel.tileSize;
		
		gamePanel.obj[1] = new OBJ_Door();
		gamePanel.obj[1].worldX = 10 * gamePanel.tileSize;
		gamePanel.obj[1].worldY = 11 * gamePanel.tileSize;
		
		gamePanel.obj[2] = new OBJ_Chest();
		gamePanel.obj[2].worldX = 10 * gamePanel.tileSize;
		gamePanel.obj[2].worldY = 7 * gamePanel.tileSize;
		
	}
}
