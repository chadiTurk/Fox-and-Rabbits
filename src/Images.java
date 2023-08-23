import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Images {
	
	private static BufferedImage [] blocks;
	
	public Images() {
		blocks = new BufferedImage[4];
		try {
		
			blocks[0] = ImageIO.read(new File("res/textures/grass.png"));
			blocks[1] = ImageIO.read(new File("res/textures/bush.png"));
			blocks[2] = ImageIO.read(new File("res/textures/fox.png"));
			blocks[3] = ImageIO.read(new File("res/textures/rabbit.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
				
	}
	
	public static BufferedImage getGrass() {
		return blocks[0];
	}
	
	public static BufferedImage getBush() {
		return blocks[1];
	}
	
	public static BufferedImage getFox() {
		return blocks[2];
	}
	
	public static BufferedImage getRabbit() {
		return blocks[3];
	}
	
	
}
