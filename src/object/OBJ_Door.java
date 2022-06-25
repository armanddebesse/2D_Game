package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Door extends Entity {

	GamePanel gamePanel;
	
	public OBJ_Door(GamePanel gamePanel) {
		super(gamePanel);
		name = "Door";
		down1 = setup("/objects/door", gamePanel.tileSize, gamePanel.tileSize);
		collision = true;
		
		solidArea.x = 0;
		solidArea.y = 16;
		solidArea.width = 48;
		solidArea.height = 32;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
	}
}
