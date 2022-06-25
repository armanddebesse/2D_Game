package main;

import entity.NPC_OldMan;

public class AssetSetter {
	GamePanel gamePanel;
	
	public AssetSetter(GamePanel _gamePanel) {
		gamePanel = _gamePanel;
	}
	
	public void setObject() {
		
	}
	
	public void setNPC() {
		gamePanel.npc[0] = new NPC_OldMan(gamePanel);
		gamePanel.npc[0].worldX = gamePanel.tileSize*15;
		gamePanel.npc[0].worldY = gamePanel.tileSize*20;
	}
}
