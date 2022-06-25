package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
	
	public boolean upPressed, downPressed, leftPressed, rightPressed, interactPressed, attackPressed ;
	
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
		if(gamePanel.gameState==gamePanel.titleState) {
			if(gamePanel.ui.titleScreenState == 0) {
				if(keyCode == KeyEvent.VK_Z) {
					gamePanel.ui.commandNum --;
					if (gamePanel.ui.commandNum < 0) {
						gamePanel.ui.commandNum = 2;
					}
				}
				if(keyCode == KeyEvent.VK_S) {
					gamePanel.ui.commandNum ++;
					if (gamePanel.ui.commandNum > 2) {
						gamePanel.ui.commandNum = 0;
					}
				}
				if(keyCode == KeyEvent.VK_F) {
					if (gamePanel.ui.commandNum == 0) {
						gamePanel.ui.titleScreenState = 1;
						//gamePanel.playMusic(0);
					}
					if (gamePanel.ui.commandNum == 1) {
						// add later
					}
					if (gamePanel.ui.commandNum == 2) {
						System.exit(0);	
					}
				}
			}
			else if (gamePanel.ui.titleScreenState == 1) {
				if(keyCode == KeyEvent.VK_Z) {
					gamePanel.ui.commandNum --;
					if (gamePanel.ui.commandNum < 0) {
						gamePanel.ui.commandNum = 3;
					}
				}
				if(keyCode == KeyEvent.VK_S) {
					gamePanel.ui.commandNum ++;
					if (gamePanel.ui.commandNum > 3) {
						gamePanel.ui.commandNum = 0;
					}
				}
				if(keyCode == KeyEvent.VK_F) {
					if (gamePanel.ui.commandNum == 0) {
						gamePanel.gameState = gamePanel.playState;
						gamePanel.playMusic(0);
					}
					if (gamePanel.ui.commandNum == 1) {
						gamePanel.gameState = gamePanel.playState;
						gamePanel.playMusic(0);
					}
					if (gamePanel.ui.commandNum == 2) {
						gamePanel.gameState = gamePanel.playState;
						gamePanel.playMusic(0);
					}
					if (gamePanel.ui.commandNum == 3) {
						gamePanel.ui.commandNum = 0;
						gamePanel.ui.titleScreenState = 0;	
					}
				}
			}
		}
	
		else if(gamePanel.gameState==gamePanel.playState) {
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
			if(keyCode == KeyEvent.VK_SPACE) {
				attackPressed = true;
			}
			if(keyCode == KeyEvent.VK_P) {
				gamePanel.gameState = gamePanel.pauseState;
			}	
		}
		else if(gamePanel.gameState==gamePanel.pauseState) {
			if(keyCode == KeyEvent.VK_P) {
				gamePanel.gameState = gamePanel.playState;
			}	
		}
		else if (gamePanel.gameState == gamePanel.dialogueState) {
			if(keyCode == KeyEvent.VK_F) {
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
		if (keyCode == KeyEvent.VK_SPACE) {
			attackPressed = false;
		}
	}

}
