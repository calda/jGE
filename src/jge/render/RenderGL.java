package jge.render;
import java.io.File;

import jge.input.MouseHandler;
import jge.world.*;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.*;

public class RenderGL{
	
	private final GraphicsWrapper graphics;
	final int fps;
	private World world;
	private final MouseHandler mouse;
	public final Coordinates screenSize;
	
	public RenderGL(final int x, final int y, final int fps) {
		screenSize = Coordinates.make(x, y);
		mouse = new MouseHandler(this);
		this.fps = fps;
		this.graphics = new GraphicsWrapper();
		System.setProperty("org.lwjgl.librarypath", new File("lib/natives").getAbsolutePath());
		try {
			Display.setDisplayMode(new DisplayMode(x, y));
			Display.create(new PixelFormat(8, 0, 0, 8));
			initGL(x, y);
		}catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	public void initGL(int x, int y) {
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, x, 0, y, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
	}
	
	public void render(){
		world.render(graphics);
		//gui.render(graphics);
	}

	/**
	 * Must be last line in main method
	 */
	public void startRendering(){
		if(getRenderingWorld() == null){
			new NullPointerException("Rendering World must be set before rendering can start").printStackTrace();
			System.exit(0);
		}while(!Display.isCloseRequested()) {
			render();
			mouse.pollInput();
			Display.update();
			Display.sync(fps);
		}Display.destroy();
		System.exit(0);
	}

	public World getRenderingWorld(){
		return world;
	}
	
	public void setRenderingWorld(World w){
		world = w;
		world.setRenderer(this);
	}
	
	public Coordinates getScreenCenter(){
		return Coordinates.make(screenSize.getX() / 2, screenSize.getY() / 2);
	}
	
}
