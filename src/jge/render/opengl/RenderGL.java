package jge.render.opengl;
import java.io.File;

import jge.render.Render2D;
import jge.world.Coordinates;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.*;

public class RenderGL extends Render2D{
	
	private final GLGraphicsWrapper graphics;
	final int fps;
	
	public RenderGL(final int x, final int y, final int fps) {
		this.fps = fps;
		this.graphics = new GLGraphicsWrapper();
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
		super.getRenderingWorld().render(graphics);
	}

	public void start(){
		while(!Display.isCloseRequested()) {
			render();
			Display.update();
			Display.sync(fps);
		}Display.destroy();
	}
	
	@Override
	public Coordinates getMousePos() {
		return Coordinates.make(Mouse.getX(), Mouse.getY());
	}
}
