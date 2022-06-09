package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Iterator;

import javax.swing.JPanel;

import entity.Player;
import enums.GameState;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{

	// SCREEN SETTINGS
	final int originalTileSize = 16;
	final int scale = 3;
	public final int tileSize = originalTileSize * scale;
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol;
	public final int screenHeight = tileSize * maxScreenRow;
	
	//WORLD SETTINGS
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	public final int worldWidth = tileSize * maxWorldCol;
	public final int worldHeight = tileSize * maxWorldRow;

	
	// FPS
	int FPS = 60;
	
	//SYSTEM
	TileManager tileManager = new TileManager(this);
	KeyHandler keyHandler = new KeyHandler(this);
	Sound music = new Sound();
	Sound soundEffect = new Sound();
	public CollisionHandler collisionHandler = new CollisionHandler(this);
	public ObjectHandler objectHandler = new ObjectHandler(this);
	public UI ui = new UI(this);
	Thread gameThread;
	
	//ENTITY AND OBJET
	public Player player = new Player(this, keyHandler);
	public SuperObject obj[] = new SuperObject[10];
	
	//GAME STATE
	public GameState gameState;
	
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyHandler);
		this.setFocusable(true);
		gameState = GameState.playState;
	}
	
	public void setupGame() {
		objectHandler.setObject();
		playMusic(0);
		//gameState = GameState.playState;
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
		switch (gameState) {
		case playState:
			player.update();
			break;
		case pauseState:
			
			break;
		default:
			break;
		}
	}
	
	public void paintComponent(Graphics graph) {
		super.paintComponent(graph);
		Graphics2D graph2D = (Graphics2D)graph;
		
		//TILE
		tileManager.draw(graph2D);
		
		//OBJECT
		for (SuperObject superObject : obj) {
			if (superObject != null) {
				superObject.draw(graph2D, this);
			}
		}
		
		//PLAYER
		player.draw(graph2D);
		
		//UI
		ui.draw(graph2D);
		
		graph2D.dispose();
	}
	
	public void playMusic(int i) {
		music.setfile(i);
		music.play();
		music.loop();
	}
	
	public void stopMusic() {
		music.stop();
	}
	
	public void playSoundEffect(int i) {
		soundEffect.setfile(i);
		soundEffect.play();
	}
}
