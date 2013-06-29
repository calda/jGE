package jge.render;

import java.util.*;

public class Color{

	//presets
	public final static Color WHITE = newHexColor("FFFFFF");
	public final static Color PINK = newHexColor("FF00FF");
	public final static Color MAGENTA = newHexColor("FF00FF");
	public final static Color RED = newHexColor("FF0000");
	public final static Color MAROON = newHexColor("800000");
	public final static Color ORANGE = newHexColor("FFA500");
	public final static Color BROWN = newHexColor("A52A2A");
	public final static Color YELLOW = newHexColor("FFFF00");
	public final static Color LIGHT_GRAY = newHexColor("C0C0C0");
	public final static Color GRAY = newHexColor("808080");
	public final static Color DARK_GRAY = newHexColor("383838");
	public final static Color DARK_GREEN = newHexColor("008000");
	public final static Color LIGHT_GREEN = newHexColor("00FF00");
	public final static Color OLIVE_GREEN = newHexColor("808000");
	public final static Color CYAN = newHexColor("00FFFF");
	public final static Color BLUE = newHexColor("0000FF");
	public final static Color DARK_BLUE = newHexColor("0000A0");
	public final static Color LIGHT_BLUE = newHexColor("ADD8E6");
	public final static Color VIOLET = newHexColor("800080");
	public final static Color PURPLE = newHexColor("800080");
	public final static Color BLACK = newHexColor("000000");
	public final static Color[] colors = {WHITE, PINK, MAGENTA, RED, MAROON, ORANGE, BROWN, YELLOW, LIGHT_GRAY, 
		GRAY, DARK_GRAY, LIGHT_GREEN, OLIVE_GREEN, CYAN, BLUE, DARK_BLUE, LIGHT_BLUE, VIOLET, PURPLE, BLACK};
	public final static HashMap<Color, String> colorMap = new HashMap<Color, String>();
	static{
		colorMap.put(WHITE, "WHITE"); colorMap.put(LIGHT_GREEN, "LIGHT_GREEN"); colorMap.put(YELLOW, "YELLOW");
		colorMap.put(PINK,"PINK"); colorMap.put(OLIVE_GREEN, "OLIVE_GREEN"); colorMap.put(LIGHT_GRAY, "LIGHT_GRAY");
		colorMap.put(MAGENTA, "MAGENTA"); colorMap.put(CYAN, "CYAN"); colorMap.put(GRAY, "GRAY");
		colorMap.put(RED, "RED"); colorMap.put(BLUE, "BLUE"); colorMap.put(DARK_GRAY, "DARK_GRAY");
		colorMap.put(MAROON, "MAROON"); colorMap.put(DARK_BLUE, "DARK_BLUE"); colorMap.put(DARK_GREEN, "DARK_GREEN");
		colorMap.put(ORANGE, "ORANGE"); colorMap.put(LIGHT_BLUE, "LIGHT_BLUE"); colorMap.put(BLACK, "BLACK");
		colorMap.put(BROWN, "BROWN"); colorMap.put(VIOLET, "VIOLET");
	}//end presets
	
	public static Color newHexColor(String hex){
		return new Color(hex);
	}final private static Random rand = new Random();
	
	public static Color randomColor(){
		return new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256), 255);
	}
	
	public static Color randomPreset(){
		return colors[rand.nextInt(colors.length)];
	}
	
	private int r;
	private int g;
	private int b;
	private int a;
	
	public Color(int red, int green, int blue, int alpha){
		setRed(red);
		setGreen(green);
		setBlue(blue);
		setAlpha(alpha);
	}
	
	public Color(int red, int green, int blue){
		this(red, green, blue, 255);
	}
	
	public Color(String hexa){
		this(hexa, 255);
	}
	
	public Color(String hexa, int alpha){
		if(hexa.length() != 7 && hexa.length() != 6) throw new IllegalArgumentException("String must be of the format #xxXXxx or xxXXxx where X stands for a hexadecimal digit [0-F]");
		char[] chars = hexa.toCharArray();
		int base = (chars[0] == '#' ? 1 : 0);
		String r = chars[base] + "" + chars[base + 1];
		String g = chars[base + 2] + "" + chars[base + 3];
		String b = chars[base + 4] + "" + chars[base + 5];
		try{
			setRed(Integer.parseInt(r, 16));
			setGreen(Integer.parseInt(g, 16));
			setBlue(Integer.parseInt(b, 16));
			setAlpha(alpha);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void setRed(int val){
		if(val > 255 || val < 0) throw new IllegalArgumentException("Red Color Values must be in range [0-255]");
		r = val;
	}
	
	public void setGreen(int val){
		if(val > 255 || val < 0) throw new IllegalArgumentException("Green Color Values must be in range [0-255]");
		g = val;
	}
	
	public void setBlue(int val){
		if(val > 255 || val < 0) throw new IllegalArgumentException("Blue Color Values must be in range [0-255]");
		b = val;
	}
	
	public void setAlpha(int val){
		if(val > 255 || val < 0) throw new IllegalArgumentException("Alpha Color Values must be in range [0-255]");
		a = val;
	}
	
	public int getRed(){
		return r;
	}
	
	public int getGreen(){
		return g;
	}
	
	public int getBlue(){
		return b;
	}
	
	public int getAlpha(){
		return a;
	}
	
	public String toHexString(){
		return ((Integer.toHexString(r).length() == 1 ? "0" : "") + Integer.toHexString(r) + 
				(Integer.toHexString(g).length() == 1 ? "0" : "") + Integer.toHexString(g) + 
				(Integer.toHexString(b).length() == 1 ? "0" : "") + Integer.toHexString(b)).toUpperCase();
	}
	
	public String toString(){
		return "Color[r=" + r + ",g=" + g + ",b=" + b + ",a=" + a + ",hexa=" + toHexString() + "]";
	}
	
	public String getApproxName(){
		String mostSimilar = null;
		double diff = 255;
		for(Color c : colors){
			double diffc = (Math.abs(c.getRed() - r) + Math.abs(c.getGreen() - g) + Math.abs(c.getBlue() - b));
			if(diffc < diff){
				mostSimilar = colorMap.get(c);
				diff = diffc;
			}
		}return mostSimilar;
	}
	
}
