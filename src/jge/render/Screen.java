package jge.render;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Screen{

	private GraphicsDevice graphics;
	private final boolean bit32;
	private final boolean widescreen;
	
	/**
	 * Create a Screen with default settings (16x9, 32bit depth)
	 */
	public Screen(){
		GraphicsEnvironment enviro = GraphicsEnvironment.getLocalGraphicsEnvironment();
		graphics = enviro.getDefaultScreenDevice();
		bit32 = true;
		widescreen = true;
	}
	
	/**
	 * Create a Screen
	 * @param use32bit Bit Depth. If true, will be 32, else is 16.
	 * @param usewidescreen Possibles dimention set. If true, all 16x9 dimensions will be avaliable. Else all non-16x9 will be avaliable.
	 */
	public Screen(boolean use32bit, boolean usewidescreen){
		GraphicsEnvironment enviro = GraphicsEnvironment.getLocalGraphicsEnvironment();
		graphics = enviro.getDefaultScreenDevice();
		this.bit32 = use32bit;
		this.widescreen = usewidescreen;
	}
	

	/**
	 * Set the screen to a fullscreen window with the maximum possible size
	 * @return the JFrame that you can work with
	 */
	@Deprecated
	public Render2D setFullscreen(){
		return null;
	}
	
	/**
	 * Adds a window in the center of the frame with the smallest applicable window size
	 * @param windowName the name of the window to create
	 * @return 
	 */
	public Render2D setWindowed(String windowName){
		DisplayMode mode = getSmallestMode();
		return setWindowed(windowName, mode.getWidth(), mode.getHeight(), 50);
	}
	
	/**
	 * Adds a window in the center of the frame with the smallest applicable window size
	 * @param windowName the name of the window to create
	 * @param rendersPerSecond number of renders to complete per second
	 * @return 
	 */
	public Render2D setWindowed(String windowName, int rendersPerSecond){
		DisplayMode mode = getSmallestMode();
		return setWindowed(windowName, mode.getWidth(), mode.getHeight(), 50);
	}
	
	/**
	 * Adds a window in the center of the frame with the window size
	 * @param windowName the name of the window to create
	 * @param x width of the window
	 * @param y height of the window
	 * @param rendersPerSecond number of renders to complete per second
	 * @return 
	 */
	public Render2D setWindowed(String windowName, int x, int y, int rendersPerSecond){
		RenderFrame f = new RenderFrame();
		DisplayMode mode = getSmallestMode();
		f.setTitle(windowName);
		f.setBounds(getPositionForCenter(x, graphics.getDisplayMode().getWidth()), 
					getPositionForCenter(y, graphics.getDisplayMode().getHeight()) - (System.getProperty("os.name").contains("Mac") ? 66 : 40),
					mode.getWidth(), mode.getHeight());
		f.setResizable(true);
		f.setEnabled(true);
		f.setVisible(true);
		Render2D render = new Render2D(f, rendersPerSecond);
		return render;
	}
	
	private static int getPositionForCenter(int window, int screen){
		int dif = screen - window;
		if(dif == 0) return dif;
		return dif/2;
	}
	
	public List<DisplayMode> getModes(){
		List<DisplayMode> modes = new ArrayList<DisplayMode>();
		for(DisplayMode mode : graphics.getDisplayModes()){
			int bitdepth = (bit32) ? 32 : 16;
			if(mode.getBitDepth() == bitdepth && (modeIsWidescreen(mode) == widescreen)) modes.add(mode);
		}return modes;
	}
	
	public DisplayMode getSmallestMode(List<DisplayMode> modes){
		DisplayMode smallest = modes.get(1);
		for(DisplayMode mode : modes){
			if(mode.getWidth() < smallest.getWidth()) smallest = mode;
		}return smallest;
	}
	
	public DisplayMode getSmallestMode(){
		return getSmallestMode(getModes());
	}
	
	public DisplayMode getLargestMode(List<DisplayMode> modes){
		DisplayMode largest = modes.get(1);
		for(DisplayMode mode : modes){
			if(mode.getWidth() > largest.getWidth()) largest = mode;
		}return largest;
	}
	
	public DisplayMode getLargestMode(){
		return getLargestMode(getModes());
	}
	
	/**
	 * for testing
	 */
	public static void printDisplayModes(List<DisplayMode> modes){
		for(DisplayMode mode : modes){
			printDisplayMode(mode);
		}
	}
	
	/**
	 * for testing
	 */
	public static void printDisplayMode(DisplayMode mode){
		System.out.println("DIM: (" + mode.getWidth() + "," + mode.getHeight() + ") \tBitDepth: " + mode.getBitDepth() + "   \tIsWidescreen: " + modeIsWidescreen(mode));
	}
	
	public static boolean modeIsWidescreen(DisplayMode mode){
		return ((float)mode.getWidth()/(float)mode.getHeight()) == (16f/9f);
	}

}
