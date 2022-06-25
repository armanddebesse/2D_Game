package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Heart extends Entity{
	GamePanel gamePanel;
	
	public OBJ_Heart(GamePanel gamePanel) {
		super(gamePanel);
		
		name = "Heart";
		image = setup("/objects/heart_full", gamePanel.tileSize, gamePanel.tileSize);
		image2 = setup("/objects/heart_half", gamePanel.tileSize, gamePanel.tileSize);
		image3 = setup("/objects/heart_blank", gamePanel.tileSize, gamePanel.tileSize);
		
	}
}
