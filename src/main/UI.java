package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import object.OBJ_Key;

public class UI {
	GamePanel gamePanel;
	Font arial_40, arial_80B;
	BufferedImage keyImage;
	public boolean messageOn = false;
	public String message = "";
	int messagecounter = 0;
	public Boolean gameFinished = false;
	
	double playTime;
	DecimalFormat dFormat = new DecimalFormat("#0.00");
	
	public UI(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
		arial_40 = new Font("Arial", Font.PLAIN, 40);
		arial_80B = new Font("Arial", Font.BOLD, 80);
		
		OBJ_Key key = new OBJ_Key();
		keyImage = key.image;
	}
	
	public void showMessage(String text) {
		message = text;
		messageOn = true;

	}
	public void draw(Graphics2D graph2D) {
		
		if (gameFinished) {
			graph2D.setFont(arial_40);
			graph2D.setColor(Color.white);
			
			String text;
			int textLength;
			int x, y; 
			
			text = "You found the treasure!";
			textLength = (int)graph2D.getFontMetrics().getStringBounds(text, graph2D).getWidth();
			
			x = gamePanel.screenWidth/2 - textLength/2;
			y = gamePanel.screenHeight/2 ;
			
			graph2D.drawString(text, x, y);
			
			text = "Your time is :"+dFormat.format(playTime);
			textLength = (int)graph2D.getFontMetrics().getStringBounds(text, graph2D).getWidth();
			
			x = gamePanel.screenWidth/2 - textLength/2;
			y = gamePanel.screenHeight/2 - (gamePanel.tileSize*4);
			
			graph2D.drawString(text, x, y);
			
			graph2D.setFont(arial_80B);
			graph2D.setColor(Color.yellow);
			
			text = "Congratulations!";
			textLength = (int)graph2D.getFontMetrics().getStringBounds(text, graph2D).getWidth();
			
			x = gamePanel.screenWidth/2 - textLength/2;
			y = gamePanel.screenHeight/2 + (gamePanel.tileSize*2);
			
			graph2D.drawString(text, x, y);
			
			gamePanel.gameThread = null;
			
		} else {			
			graph2D.setFont(arial_40);
			graph2D.setColor(Color.white);
			graph2D.drawImage(keyImage, gamePanel.tileSize/2, gamePanel.tileSize/2, gamePanel.tileSize, gamePanel.tileSize, null);
			graph2D.drawString("x "+gamePanel.player.hasKey, 74, 65);
			
			// TIME
			playTime += (double)1/60;
			graph2D.drawString("Time:"+ dFormat.format(playTime), gamePanel.tileSize*11, 65);
			
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
}
