package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import enums.GameState;

public class KeyHandler implements KeyListener{
	GamePanel gamePanel;
	public boolean upPressed, downPressed, leftPressed, rightPressed;
	
	public KeyHandler(GamePanel _gamePanel) {
		gamePanel = _gamePanel;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		
		if(keyCode == KeyEvent.VK_Z) {
			upPressed = true;
		}
		if(keyCode == KeyEvent.VK_S) {
			downPressed = true;
		}
		if(keyCode == KeyEvent.VK_Q) {
			leftPressed = true;
		}
		if(keyCode == KeyEvent.VK_D) {
			rightPressed = true;
		}
		
		if(keyCode == KeyEvent.VK_ESCAPE) {
			if (gamePanel.gameState == GameState.playState) {
				gamePanel.gameState = GameState.pauseState;
				gamePanel.stopMusic();
			}
			else if (gamePanel.gameState == GameState.pauseState) {
				gamePanel.gameState = GameState.playState;
				gamePanel.playMusic(0);
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		
		if(keyCode == KeyEvent.VK_Z) {
			upPressed = false;
		}
		if(keyCode == KeyEvent.VK_S) {
			downPressed = false;
		}
		if(keyCode == KeyEvent.VK_Q) {
			leftPressed = false;
		}
		if(keyCode == KeyEvent.VK_D) {
			rightPressed = false;
		}
		
	}

}
