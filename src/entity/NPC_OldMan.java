package entity;

import java.util.Random;

import enums.Direction;
import main.GamePanel;

public class NPC_OldMan extends Entity{

	public NPC_OldMan(GamePanel gamePanel) {
		super(gamePanel);
		direction = Direction.DOWN;
		speed = 1;
		
		getImage();
		setDialogue();
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
	public void setDialogue() {
		dialogues[0] = "Hello, lad.";
		dialogues[1] = "So you've come to this island to find the treasure?";
		dialogues[2] = "I used to be a great wizard but now...\nI'm a bit old for taking an adventure";
		dialogues[3] = "Well, good luck on you.";
	}
	public void setAction() {
		
		actionLockCounter ++;
		
		if (actionLockCounter == 120) {
			Random random = new Random();
			int i = random.nextInt(100)+1;
			if (i <= 25) {
				direction = Direction.UP;
			}
			if (i>25 && i<=50) {
				direction = Direction.DOWN;
			}
			if (i>50 && i<=75) {
				direction = Direction.LEFT;
			}
			if (i>75&& i<=100) {
				direction = Direction.RIGHT;
			}
			actionLockCounter = 0;
		}
	}
	public void speak() {
		super.speak();
	}
 }
