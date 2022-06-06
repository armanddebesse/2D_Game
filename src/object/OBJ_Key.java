package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Key extends SuperObject {

	GamePanel gamePanel;
	
	public OBJ_Key(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
		name = "Key";
		try {
			image =ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
			uTool.scaleImage(image, gamePanel.tileSize, gamePanel.tileSize);
		}catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
