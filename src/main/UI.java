package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import object.OBJ_Key;

public class UI {
	GamePanel gamePanel;
	Font arial_40;
	BufferedImage keyImage;
	public boolean messageOn = false;
	public String message = "";
	int messagecounter = 0;
	public Boolean gameFinished = false;
	
	public UI(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
		arial_40 = new Font("Arial", Font.PLAIN, 40);
		
		OBJ_Key key = new OBJ_Key();
		keyImage = key.image;
	}
	
	public void showMessage(String text) {
		message = text;
		messageOn = true;

	}
	public void draw(Graphics2D graph2D) {
		graph2D.setFont(arial_40);
		graph2D.setColor(Color.white);
		graph2D.drawImage(keyImage, gamePanel.tileSize/2, gamePanel.tileSize/2, gamePanel.tileSize, gamePanel.tileSize, null);
		graph2D.drawString("x "+gamePanel.player.hasKey, 74, 65);
		
		// MESSAGE
		if(messageOn == true) {
			graph2D.setFont(graph2D.getFont().deriveFont(30F));
			graph2D.drawString(message, gamePanel.tileSize/2, gamePanel.tileSize*5);
			messagecounter++;
			if (messagecounter > 120) {
				messagecounter = 0;
				messageOn = false;
			}
		}
	}
}
