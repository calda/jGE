package jge.render.opengl;
import java.awt.*;
import java.io.File;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.*;
import org.lwjgl.opengl.*;
import org.lwjgl.opengl.DisplayMode;

public class GLDisplay{
	GLImage gli;
	public void start() {
		System.setProperty("org.lwjgl.librarypath", new File("lib/natives").getAbsolutePath());
		try {
			Display.setDisplayMode(new DisplayMode(800, 600));
			Display.create(new PixelFormat(8, 0, 0, 8));
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}

		initGL(); // init OpenGL
		gli = new GLImage("triangle.png");
		//getDelta(); // call once before loop to initialise lastFrame
		//lastFPS = getTime(); // call before loop to initialise fps timer

		while (!Display.isCloseRequested()) {
			//int delta = getDelta();

			//update(delta);
			renderGL();

			Display.update();
			Display.sync(60); // cap fps to 60fps
		}
		Display.destroy();
	}

	public void initGL() {
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, 800, 0, 600, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}

	int x = 100;
	int y = 100;
	int rotation = 0;

	public void renderGL(){
		if(Mouse.isButtonDown(0)){
			rotation += 1;
		}if(Mouse.isButtonDown(1))rotation -= 1;
		x = Mouse.getX();
		y = Mouse.getY();
		GLGraphicsWrapper gl = new GLGraphicsWrapper();
		gl.clear(Color.black);
		gl.drawRectangle(Color.red, x-51, y+51, 50, 50, rotation);
		gl.drawOval(Color.white, x, y, 50, 30, rotation);
		gl.drawRectangle(Color.red, x + 51, y, 50, 50, rotation);
		gl.drawRectangle(Color.red, x, y - 51, 50, 50, rotation);
		gl.drawRectangle(Color.blue, x - 51, y, 50, 50, rotation);
		gl.drawRectangle(Color.white, x - 51, y - 51, 50, 50, rotation);
		gl.drawRectangle(Color.white, x + 51, y + 51, 50, 50, rotation);
		gl.drawRectangle(Color.blue, x+51, y-51, 50, 50, rotation); 
		gl.drawRectangle(Color.blue, x, y+51, 50, 50, rotation);
		gl.drawImage(gli, x + 100, y + 100, 100, 100, rotation);
	}

	public static void main(String[] argv) {
		GLDisplay d = new GLDisplay();
		d.start();
	}

}
