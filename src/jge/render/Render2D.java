package jge.render;
import java.awt.Color;
import java.io.File;

import jge.input.MouseHandler;
import jge.world.Coordinates;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.*;

public class Render2D{
	
	private final GraphicsWrapper graphics;
	final int fps;
	
	public Render2D(final int x, final int y, final int fps) {
		mouse = new MouseHandler();
		mouse.attributeTo(this);
		this.fps = fps;
		this.graphics = new GraphicsWrapper();
		System.setProperty("org.lwjgl.librarypath", new File("lib/natives").getAbsolutePath());
		try {
			Display.setDisplayMode(new DisplayMode(800, 600));
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
	}
	
	public void render(){
		graphics.clear(Color.black);
		super.getRenderingWorld().render(graphics);
		//graphics.drawRectangle(Color.red, getMousePos(), Coordinates.make(100,100) ,0);
		//super.getGUI().render(graphics);
	}

	public void start(){
		if(getRenderingWorld() == null){
			new NullPointerException("Rendering World must be set before rendering can start").printStackTrace();
			System.exit(0);
		}
		while(!Display.isCloseRequested()) {
			render();
			((MouseHandler)super.getMouseHandler()).pollInput();
			Display.update();
			Display.sync(fps);
		}Display.destroy();
		System.exit(0);
	}
	
	@Override
	public Coordinates getMousePos() {
		return Coordinates.make(Mouse.getX(), Mouse.getY());
	}
}
