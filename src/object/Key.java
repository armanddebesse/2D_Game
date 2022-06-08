package object;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Key extends SuperObject {
	public Key(GamePanel _gamePanel) {
		name = "key";
		gamePanel = _gamePanel;
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
			uTool.scaleImage(image, gamePanel.tileSize, gamePanel.tileSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
