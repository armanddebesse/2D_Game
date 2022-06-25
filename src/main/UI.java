package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

import entity.Entity;
import object.OBJ_Heart;
import object.OBJ_Key;


public class UI {
	GamePanel gamePanel;
	Graphics2D graph2D;
	BufferedImage keyImage;
	Font minecraft;
	BufferedImage heart_full, heart_half, heart_blank;
	public boolean messageOn = false;
	public String message = "";
	int messagecounter = 0;
	public Boolean gameFinished = false;
	public String currentDialogue;
	public int commandNum = 0;
	public int titleScreenState = 0;
	
	double playTime;
	DecimalFormat dFormat = new DecimalFormat("#0.00");
	
	public UI(GamePanel gamePanel) {
		this.gamePanel = gamePanel;

		try {
			InputStream inputStream = getClass().getResourceAsStream("/font/Minecraft.ttf");
			minecraft = Font.createFont(Font.TRUETYPE_FONT, inputStream);
		} catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		OBJ_Key key = new OBJ_Key(gamePanel);
		keyImage = key.image;
		
		Entity heart = new OBJ_Heart(gamePanel);
		heart_full = heart.image;
		heart_half = heart.image2;
		heart_blank = heart.image3;
	}
	
	public void showMessage(String text) {
		message = text;
		messageOn = true;

	}
	public void draw(Graphics2D graph2D) {
		this.graph2D = graph2D;
		graph2D.setFont(minecraft);
		graph2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		graph2D.setColor(Color.white);
		if (gameFinished) {
			drawFinishedScreen();
		}else {
			// TITLE STATE
			if (gamePanel.gameState == gamePanel.titleState) {
				drawTitleScreen();
			}
			// PLAY STATE
			if (gamePanel.gameState == gamePanel.playState) {
				playTime += (double)1/120;
				drawPlayerLife();
				// Do playstate stuff later
			}
			// PAUSE STATE
			if (gamePanel.gameState == gamePanel.pauseState) {
				drawPlayerLife();
				drawPauseScreen();
			}
			// DIALOGUE STATE
			if (gamePanel.gameState == gamePanel.dialogueState) {
				drawPlayerLife();
				drawDialogueScreen();
			}
		}
	}

	private void drawPlayerLife() {
		int x = gamePanel.tileSize/2;
		int y = gamePanel.tileSize/2;
		int	 i = 0;
		
		while (i < gamePanel.player.maxLife/2) {
			graph2D.drawImage(heart_blank, x, y, null);
			i++;
			x += gamePanel.tileSize;
		}
			
		x = gamePanel.tileSize/2;
		y = gamePanel.tileSize/2;
		i = 0;
		
		while (i < gamePanel.player.life) {
			graph2D.drawImage(heart_half, x, y, null);
			i++;
			if (i < gamePanel.player.life) {
				graph2D.drawImage(heart_full, x, y, null);
			}
			i++;
			x += gamePanel.tileSize;
			
		}
	}

	private void drawTitleScreen() {
		if (titleScreenState == 0) {
			
			// BACKGROUND
			graph2D.setColor(new Color(0, 0, 0));
			graph2D.fillRect(0, 0, gamePanel.screenWidth, gamePanel.screenHeight);
			
			// TITLE NAME
			graph2D.setFont(graph2D.getFont().deriveFont(Font.BOLD,96));
			String text = "Blue Boy Adventure";
			int x = getXforCenteredText(text);
			int y = gamePanel.tileSize * 3;
			
			// SHADOW
			graph2D.setColor(Color.GRAY);
			graph2D.drawString(text, x+5, y+5);
			
			// MAIN COLOR
			graph2D.setColor(Color.white);
			graph2D.drawString(text, x, y);
			
			// PLAYER IMAGE
			x = gamePanel.screenWidth/2 - (gamePanel.tileSize*2)/2;
			y += gamePanel.tileSize*2;
			graph2D.drawImage(gamePanel.player.down1, x, y, gamePanel.tileSize*2, gamePanel.tileSize*2, null);
			
			// MENU
			graph2D.setFont(graph2D.getFont().deriveFont(Font.BOLD, 48F));
			
			text = "NEW GAME";
			x = getXforCenteredText(text);
			y += gamePanel.tileSize*4;
			graph2D.drawString(text, x, y);
			if(commandNum == 0) {
				graph2D.drawString(">", x - gamePanel.tileSize, y);
			}
					
			text = "LOAD GAME";
			x = getXforCenteredText(text);
			y += gamePanel.tileSize;
			graph2D.drawString(text, x, y);
			if(commandNum == 1) {
				graph2D.drawString(">", x - gamePanel.tileSize, y);
			}
			
			text = "QUIT";
			x = getXforCenteredText(text);
			y += gamePanel.tileSize;		
			graph2D.drawString(text, x, y);
			
			if(commandNum == 2) {
				graph2D.drawString(">", x - gamePanel.tileSize, y);
			}
		}
		if (titleScreenState == 1) {
			
			// CLASS SELECTION SCREEN
			graph2D.setColor(Color.white);
			graph2D.setFont(graph2D.getFont().deriveFont(42F));
			
			String text = "Select your class!";
			int x = getXforCenteredText(text);
			int y = gamePanel.tileSize*3;
			graph2D.drawString(text, x, y);
					
			text = "Fighter";
			x = getXforCenteredText(text);
			y += gamePanel.tileSize;
			graph2D.drawString(text, x, y);
			if(commandNum == 0) {
				graph2D.drawString(">", x - gamePanel.tileSize, y);
			}
			
			text = "Thief";
			x = getXforCenteredText(text);
			y += gamePanel.tileSize;		
			graph2D.drawString(text, x, y);
			if(commandNum == 1) {
				graph2D.drawString(">", x - gamePanel.tileSize, y);
			}
			
			text = "Sorcerer";
			x = getXforCenteredText(text);
			y += gamePanel.tileSize;
			graph2D.drawString(text, x, y);
			if(commandNum == 2) {
				graph2D.drawString(">", x - gamePanel.tileSize, y);
			}
			
			text = "Back";
			x = getXforCenteredText(text);
			y += gamePanel.tileSize;		
			graph2D.drawString(text, x, y);
			if(commandNum == 3) {
				graph2D.drawString(">", x - gamePanel.tileSize, y);
			}
		}
	}

	private void drawDialogueScreen() {
		// WINDOW
		int x = gamePanel.tileSize*2;
		int y = gamePanel.tileSize;
		int width = gamePanel.screenWidth - (gamePanel.tileSize*5);
		int height= gamePanel.screenHeight- (gamePanel.tileSize*10);
		
		drawSubWindow(x, y, width, height);
		
		graph2D.setFont(graph2D.getFont().deriveFont(Font.PLAIN,28F));
		x+= gamePanel.tileSize;
		y+= gamePanel.tileSize;
		
		for (String line : currentDialogue.split("\n")) {			
			graph2D.drawString(line, x, y);
			y+=40;
		}
	}

	private void drawSubWindow(int x, int y, int width, int height) {
		Color color = new Color(0,0,0,200);
		graph2D.setColor(color);
		graph2D.fillRoundRect(x,y,width,height,35,35);
		graph2D.setColor(Color.white);
		graph2D.setStroke(new BasicStroke(5));
		graph2D.drawRoundRect(x+5,y+5,width-10,height-10,25,25);
	}

	private void drawFinishedScreen() {
		graph2D.setFont(graph2D.getFont().deriveFont(Font.PLAIN,28F));
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
		
		graph2D.setFont(minecraft);
		graph2D.setColor(Color.yellow);
		
		text = "Congratulations!";
		textLength = (int)graph2D.getFontMetrics().getStringBounds(text, graph2D).getWidth();
		
		x = gamePanel.screenWidth/2 - textLength/2;
		y = gamePanel.screenHeight/2 + (gamePanel.tileSize*2);
		
		graph2D.drawString(text, x, y);
		
		gamePanel.gameThread = null;
	}

	private void drawPauseScreen() {
		graph2D.setFont(graph2D.getFont().deriveFont(Font.PLAIN,32F));
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
