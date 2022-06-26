package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.text.DecimalFormat;

public class UI {
	GamePanel gamePanel;
	Graphics2D graph2D;
	Font arial_40, arial_20;
	public boolean messageOn = false;
	String message = "";
	int messageCounter = 0;
	public boolean gameFinished = false;
	
	double playTime;
	DecimalFormat decimalFormat = new DecimalFormat("#0.00");
	
	public UI(GamePanel _gamePanel) {
		gamePanel = _gamePanel;
		arial_40 = new Font("Arial", Font.PLAIN, 40);
		arial_20 =  new Font("Arial", Font.BOLD, 20);
	}
	
	public void showMessage(String text) {
		message = text;
		messageOn = true;
	}
	
	public void draw(Graphics2D _graph2D) {
		graph2D = _graph2D;
		graph2D.setFont(arial_40);
		graph2D.setColor(Color.white);
		
		switch (gamePanel.gameState) {
		case playState:
			
			break;
		case pauseState:
			drawPauseScreen();
			break;
		default:
			break;
		}
	}
	
	public void drawPauseScreen() {
		String text = "PAUSED";
		int x = getXforCenteredText(text);
		int y = gamePanel.screenHeight/2 - 10;
		
		graph2D.drawString(text, x, y);
	}
	
	public int getXforCenteredText(String text) {
		int lenght = (int)graph2D.getFontMetrics().getStringBounds(text, graph2D).getWidth();
		return gamePanel.screenWidth/2 - lenght/2;
	}
}
