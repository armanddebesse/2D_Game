package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class UtilityTool {
	public BufferedImage scaleImage(BufferedImage original, int width, int height) {
		BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
		Graphics2D graphics2d = scaledImage.createGraphics();
		graphics2d.drawImage(original, 0, 0, width, height, null);
		graphics2d.dispose();
		
		return scaledImage;
		
	}
}
