package jge.util;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import jge.world.Coordinates;

public class Util{

	public static BufferedImage imageFromPath(String path){
		try{
			return ImageIO.read(new File(path));
		} catch(IOException exe){ return null; }
	}
	
	public static BufferedImage imageFromJarPath(String path){
		try{
			return ImageIO.read(Util.class.getResource(path));
		} catch(IOException exe){ return null; }
	}

	private static Color[] colors = {
		Color.BLACK, Color.BLUE, Color.CYAN,
		Color.CYAN, Color.DARK_GRAY, Color.GREEN,
		Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE,
		Color.PINK, Color.RED, Color.WHITE, Color.YELLOW
	};

	private static Color[] colorful = {
		Color.BLUE, Color.CYAN,
		Color.CYAN, Color.GREEN,
		Color.MAGENTA, Color.ORANGE,
		Color.PINK, Color.RED, Color.YELLOW
	};

	public static Color getRandomColor(){
		return colors[(new Random()).nextInt(colors.length)];
	}

	public static Color getRandomColorful(){
		return colorful[(new Random()).nextInt(colorful.length)];
	}

	private final static Random r = new Random();
	public static boolean randomOutOf(int options){
		return r.nextInt(options) == 0;
	}

	public Random random(){
		return r;
	}

	public static Coordinates getDimOfImage(BufferedImage img, double scale){
		return Coordinates.make(img.getWidth() * scale, img.getHeight() * scale);
	}

	public static Coordinates getDimOfImage(BufferedImage img){
		return getDimOfImage(img, 1);
	}

	public static Coordinates getDimOfImage(String img, double scale){
		return getDimOfImage(imageFromPath(img), scale);
	}

	public static Coordinates getDimOfImage(String img){
		return getDimOfImage(imageFromPath(img), 1);
	}
	
 }
