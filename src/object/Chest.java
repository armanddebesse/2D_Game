package object;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Chest extends SuperObject {
	public Chest(GamePanel _gamePanel) {
		name = "chest";
		gamePanel = _gamePanel;
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/chest.png"));
			uTool.scaleImage(image, gamePanel.tileSize, gamePanel.tileSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
