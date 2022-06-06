package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
	
	public boolean upPressed, downPressed, leftPressed, rightPressed, interactPressed;
	
	GamePanel gamePanel;

	public KeyHandler(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		
		//player movement
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
		if(keyCode == KeyEvent.VK_F) {
			interactPressed = true;
		}
		if(keyCode == KeyEvent.VK_P) {
			if (gamePanel.gameState == gamePanel.playState) {
				gamePanel.gameState = gamePanel.pauseState;
			} else if (gamePanel.gameState == gamePanel.pauseState) {
				gamePanel.gameState = gamePanel.playState;				
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
		if(keyCode == KeyEvent.VK_F) {
			interactPressed = false;
		}
		
	}

}
