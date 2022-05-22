package object;

import javax.imageio.ImageIO;

public class Key extends SuperObject {
	public Key() {
		name = "key";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
