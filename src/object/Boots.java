package object;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Boots extends SuperObject{
	public Boots(GamePanel _gamePanel) {
		name = "boots";
		gamePanel = _gamePanel;
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/boots.png"));
			uTool.scaleImage(image, gamePanel.tileSize, gamePanel.tileSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
