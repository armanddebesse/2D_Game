package object;

import javax.imageio.ImageIO;

public class Chest extends SuperObject {
	public Chest() {
		name = "chest";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/chest.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
