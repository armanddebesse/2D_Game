package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class UtilityTool {
	public BufferedImage scaleImage(BufferedImage image, int width, int height) {
		BufferedImage scaledImage = new BufferedImage(width, height, image.getType());
		Graphics2D graph2D = scaledImage.createGraphics();
		graph2D.drawImage(image, 0, 0, width, height, null);
		graph2D.dispose();
		return scaledImage;
	}
}
