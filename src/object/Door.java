package object;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Door extends SuperObject {
	public Door(GamePanel _gamePanel) {
		name = "door";
		gamePanel = _gamePanel;
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/door.png"));
			uTool.scaleImage(image, gamePanel.tileSize, gamePanel.tileSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		collision = true;
	}
}
