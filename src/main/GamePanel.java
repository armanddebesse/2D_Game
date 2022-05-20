package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import Tile.TileManager;
import entity.Player;
import object.SuperObject;

public class GamePanel extends JPanel implements Runnable{

	// SCREEN SETTINGS
	final int originalTileSize = 16;
	final int scale = 3;
	
	public int tileSize = originalTileSize * scale;
	public int maxScreenCol = 24;
	public int maxScreenRow = 16;
	public int screenWidth = tileSize * maxScreenCol;
	public int screenHeight = tileSize * maxScreenRow;
	
	//WORLD SETTINGS
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	public final int worldWidth = tileSize * maxWorldCol;
	public final int worldHeight = tileSize * maxWorldRow;
	
	// FPS
	int FPS = 120;
	
	TileManager tileManager = new TileManager(this);
	KeyHandler keyHandler = new KeyHandler(this);
	Thread gameThread;
	public CollisionChecker collisionChecker = new CollisionChecker(this);
	public AssetSetter assetSetter = new AssetSetter(this);
	public Player player = new Player(this, keyHandler);
	public SuperObject obj[] = new SuperObject[10];
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyHandler);
		this.setFocusable(true);
	}
	
	public void setupGame() {
		assetSetter.setObject();
	}
	
	public void zoomInOut(int i) {
		
		int oldWorldWidth = tileSize * maxWorldCol;
		tileSize += i;	
		int newWorldWidth = tileSize * maxWorldCol;
		
		double multiplier = (double)newWorldWidth/oldWorldWidth;
		player.speed = newWorldWidth/1200;

		double newPlayerWorldX = player.worldX * multiplier;
		double newPlayerWorldY = player.worldY * multiplier;
		
		player.worldX = newPlayerWorldX;
		player.worldY = newPlayerWorldY;
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
		player.update();
	}
	
	public void paintComponent(Graphics graph) {
		super.paintComponent(graph);
		Graphics2D graph2D = (Graphics2D)graph;
		
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

		graph2D.dispose();
	}
}
