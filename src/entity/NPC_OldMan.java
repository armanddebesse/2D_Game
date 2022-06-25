package entity;

import java.util.Random;

import enums.Direction;
import main.GamePanel;

public class NPC_OldMan extends Entity{
	public NPC_OldMan(GamePanel _gamePanel) {
		super(_gamePanel);
		
		direction = Direction.DOWN;
		speed = 1;
		
		getImage();
	}
	
	public void getImage() {
		up1 = setup("/npc/oldman_up_1");
		up2 = setup("/npc/oldman_up_2");
		down1 = setup("/npc/oldman_down_1");
		down2 = setup("/npc/oldman_down_2");
		right1 = setup("/npc/oldman_right_1");
		right2 = setup("/npc/oldman_right_2");
		left1 = setup("/npc/oldman_left_1");
		left2 = setup("/npc/oldman_left_2");
	}
	
	public void setAction() {
		actionLockCounter ++;
		if (actionLockCounter == 120) {
			Random random = new Random();
			int i = random.nextInt(4);
			
			switch (i) {
			case 0:
				direction = Direction.UP;
				break;
			case 1:
				direction = Direction.DOWN;
				break;
			case 2:
				direction = Direction.LEFT;
				break;
			case 3:
				direction = Direction.RIGHT;
				break;
			default:
				break;
			}
			actionLockCounter = 0;
		}
	}
}
