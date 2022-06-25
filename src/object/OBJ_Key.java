package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Key extends Entity {

	GamePanel gamePanel;
	
	public OBJ_Key(GamePanel gamePanel) {
		super(gamePanel);

		name = "Key";
		down1= setup("/objects/key", gamePanel.tileSize, gamePanel.tileSize);
	}
}
