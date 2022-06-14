package main;

import entity.NPC_OldMan;
import object.OBJ_Boots;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Key;

public class AssetSetter {
GamePanel gamePanel;

	public AssetSetter(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	public void setObject() {
		gamePanel.obj[0] = new OBJ_Key(gamePanel);
		gamePanel.obj[0].worldX = 23 * gamePanel.tileSize;
		gamePanel.obj[0].worldY = 7 * gamePanel.tileSize;
		
		gamePanel.obj[1] = new OBJ_Door(gamePanel);
		gamePanel.obj[1].worldX = 10 * gamePanel.tileSize;
		gamePanel.obj[1].worldY = 12 * gamePanel.tileSize;
		
		gamePanel.obj[2] = new OBJ_Chest(gamePanel);
		gamePanel.obj[2].worldX = 10 * gamePanel.tileSize;
		gamePanel.obj[2].worldY = 8 * gamePanel.tileSize;
		
		gamePanel.obj[3] = new OBJ_Boots(gamePanel);
		gamePanel.obj[3].worldX = 37 * gamePanel.tileSize;
		gamePanel.obj[3].worldY = 42 * gamePanel.tileSize;
		
	}
	
	public void setNPC() {
		gamePanel.npc[0]= new NPC_OldMan(gamePanel);
		gamePanel.npc[0].worldX = gamePanel.tileSize*38;
		gamePanel.npc[0].worldY = gamePanel.tileSize*8;;
	}
}
