package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.PageAttributes.OriginType;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import object.OBJ_Key;

public class UI {
	GamePanel gamePanel;
	Graphics2D graph2D;
	BufferedImage keyImage;
	Font arial_40, arial_80B;
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
		OBJ_Key key = new OBJ_Key(gamePanel);
		keyImage = key.image;
	}
	
	public void showMessage(String text) {
		message = text;
		messageOn = true;

	}
	public void draw(Graphics2D graph2D) {
		this.graph2D = graph2D;
		graph2D.setFont(arial_40);
		graph2D.setColor(Color.white);
		if (gameFinished) {
			drawFinishedScreen();
		}else {
			if (gamePanel.gameState == gamePanel.playState) {
				playTime += (double)1/120;
				// Do playstate stuff later
			}
			if (gamePanel.gameState == gamePanel.pauseState) {
				drawPauseScreen();
			}
		}
	}

	private void drawFinishedScreen() {
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
	}

	private void drawPauseScreen() {
		String text = "PAUSED";
		int x = getXforCenteredText(text);
		int y = gamePanel.screenHeight/2;
		
		graph2D.drawString(text, x, y);
		graph2D.drawImage(keyImage, gamePanel.tileSize/2, gamePanel.tileSize/2, gamePanel.tileSize, gamePanel.tileSize, null);
		graph2D.drawString("x "+gamePanel.player.hasKey, 74, 65);
		
		graph2D.drawString("Time:"+ dFormat.format(playTime), gamePanel.tileSize*11, 65);
	}

	private int getXforCenteredText(String text) {
		int length = (int)graph2D.getFontMetrics().getStringBounds(text, graph2D).getWidth();
		int x = gamePanel.screenWidth/2 - length/2;
		return x;
	}
}
