package jge.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Util{

	public static BufferedImage imageFromPath(String path){
		try{
			return ImageIO.read(new File(path));
		} catch(IOException exe){ return null; }
	}
	
}
