package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Iterator;

import javax.swing.JPanel;

import Tile.TileManager;
import entity.Entity;
import entity.Player;
import object.SuperObject;

public class GamePanel extends JPanel implements Runnable{

	// SCREEN SETTINGS
	final int originalTileSize = 16;
	final int scale = 3;
	
	public int tileSize = originalTileSize * scale;
	public int maxScreenCol = 20;
	public int maxScreenRow = 12;
	public int screenWidth = tileSize * maxScreenCol;
	public int screenHeight = tileSize * maxScreenRow;
	
	// WORLD SETTINGS
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	
	// FULL SCREEN MODE
	int screenWidth2 = screenWidth;
	int screeHeight2 = screenHeight;
	BufferedImage tempScreen;
	Graphics2D graph2D;
	
	// FPS
	int FPS = 120;
	
	// SYSTEM
	TileManager tileManager = new TileManager(this);
	public KeyHandler keyHandler = new KeyHandler(this);
	Sound music =  new Sound();
	Sound soundEffect =  new Sound();
	public CollisionChecker collisionChecker = new CollisionChecker(this);
	public AssetSetter assetSetter = new AssetSetter(this);
	public UI ui = new UI(this);
	public EventHandler eventHandler = new EventHandler(this);
	Thread gameThread;
	
	// ENTITY AND OBJECT
	public Player player = new Player(this, keyHandler);
	public SuperObject obj[] = new SuperObject[10];
	public  Entity npc[] = new Entity[10];
	
	// GAME STATE
	public int gameState;
	public final int titleState = 0;
	public final int playState = 1;
	public final int pauseState = 2;
	public final int dialogueState = 3;
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyHandler);
		this.setFocusable(true);
	}
	
	public void setupGame() {
		assetSetter.setObject();
		assetSetter.setNPC();
		//playMusic(0);
		gameState = titleState;
		
		tempScreen = new BufferedImage(screenWidth2, screeHeight2, BufferedImage.TYPE_INT_ARGB);
		graph2D = (Graphics2D)tempScreen.getGraphics();
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		
		while(gameThread != null) {
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime) / drawInterval;
			lastTime = currentTime;
			
			if(delta >= 1) {
				update();
				repaint();
				delta--;
			}
		}
	}
	
	public void update() {
		if (gameState == playState) {
			// PLAYER
			player.update();
			
			// NPC
			for (int i = 0; i < npc.length; i++) {
				if (npc[i]!=null) {
					npc[i].update();
				}
			}	
		}
		if (gameState == pauseState) {
			//nothing
		}
	}
	
	public void drawToTempScreen() {
		
	}
	public void paintComponent(Graphics graph) {
		super.paintComponent(graph);
		Graphics2D graph2D = (Graphics2D)graph;
		
		// TITLE SCREEN
		if (gameState == titleState) {
			ui.draw(graph2D);
		}
		
		// OTHERS
		else {
			// TILE
			tileManager.draw(graph2D);
			
			// OBJECT
			for(int i = 0; i < obj.length; i++) {	
				if(obj[i] != null) {
					obj[i].draw(graph2D, this);
				}
			}
			
			// PLAYER
			player.draw(graph2D);

			
			// NPC
			for (int i = 0; i < npc.length; i++) {
				if (npc[i]!=null) {
					npc[i].draw(graph2D);
				}
			}
			
			// UI
			ui.draw(graph2D);
			
		}
		graph2D.dispose();			
	}
	
	public void playMusic(int i) {
		music.setFile(i);
		music.play();
		music.loop();
	}
	public void stopMusic() {
		music.stop();
	}
	public void playSE(int i) {
		soundEffect.setFile(i);
		soundEffect.play();
	}
}
