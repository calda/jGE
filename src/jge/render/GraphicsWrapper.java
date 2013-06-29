package jge.render;

import jge.render.Color;
import jge.util.*;
import jge.world.Coordinates;
import org.lwjgl.opengl.*;
import org.newdawn.slick.TrueTypeFont;

/**
 * For use with OpenGL via LWJGL
 */
public class GraphicsWrapper{

	public void drawRectangle(Color fill, double x, double y, double width, double height, double rot){
		GL11.glPushMatrix();
		GL11.glTranslated(x, y, 0);
		GL11.glRotated(rot, 0, 0, 1);
		GL11.glTranslated(-x, -y, 0);
		GL11.glBegin(GL11.GL_TRIANGLES);
		if(fill != null)GL11.glColor4d(fill.getRed()/256.0, fill.getGreen()/256.0, fill.getBlue()/256.0, fill.getAlpha()/256.0);
		double width2 = width/2;
		double height2 = height/2;
		GL11.glVertex2d(x - width2, y + height2);
		GL11.glVertex2d(x - width2, y - height2);
		GL11.glVertex2d(x + width2, y + height2);
		GL11.glEnd();
		GL11.glBegin(GL11.GL_TRIANGLES);
		if(fill != null)GL11.glColor4d(fill.getRed()/256.0, fill.getGreen()/256.0, fill.getBlue()/256.0, fill.getAlpha()/256.0);
		GL11.glVertex2d(x - width2, y - height2);
		GL11.glVertex2d(x + width2, y + height2);
		GL11.glVertex2d(x + width2, y - height2);
		GL11.glEnd();
		GL11.glPopMatrix();
	}

	public void drawRectangle(Color fill, Coordinates pos, Coordinates dim, double rot){
		this.drawRectangle(fill, pos.getX(), pos.getY(), dim.getX(), dim.getY(), rot);
	}

	public void drawOval(Color fill, double x, double y, double width, double height, double rot){
		System.out.println("Drawing oval at " + x + "," + y);
		GL11.glPushMatrix();
		GL11.glTranslated(x, y, 0);
		GL11.glRotated(rot, 0, 0, 1);
		GL11.glColor3d(fill.getRed(), fill.getGreen(), fill.getBlue());
		GL11.glBegin(GL11.GL_TRIANGLE_FAN);
		for(int i = 0; i < 360; i++){
			double rad = i * ((2.0 * 3.14159269357)/360);
			GL11.glVertex2d(Math.cos(rad) * width/2, Math.sin(rad) * height/2);
		}GL11.glEnd();
		GL11.glPopMatrix();
	}

	public void drawOval(Color fill, Coordinates pos, Coordinates dim, double rot){
		this.drawOval(fill, pos.getX(), pos.getY(), dim.getX(), dim.getY(), rot);
	}

	public void drawImage(GLImage image, double x, double y, double width, double height, double rot){
		rot += 180;
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GLImage gl = (GLImage) image;
		Texture texture = gl.getGLTexture();
		GL11.glPushMatrix();
		texture.bind();
		GL11.glTranslated(x, y, 0);		
		GL11.glRotated(rot, 0, 0, 1);
		GL11.glColor3f(1,1,1);
		GL11.glBegin(GL11.GL_QUADS);
		double width2 = width/2;
		double height2 = height/2;
		GL11.glTexCoord2f(0, 0);
		GL11.glVertex2d(-width2, -height2);
		GL11.glTexCoord2f(0, texture.getHeight());
		GL11.glVertex2d(-width2, height2);
		GL11.glTexCoord2f(texture.getWidth(), texture.getHeight());
		GL11.glVertex2d(width2,height2);
		GL11.glTexCoord2f(texture.getWidth(), 0);
		GL11.glVertex2d(width2,-height2);
		GL11.glEnd();
		GL11.glPopMatrix();
		GL11.glDisable(GL11.GL_TEXTURE_2D);
	}

	public void drawImage(GLImage image, Coordinates pos, Coordinates dim, double rot){
		drawImage(image, pos.getX(), pos.getY(), dim.getX(), dim.getY(), rot);
	}

	public void drawPolygon(Color fill, double rot, Coordinates... vertex){
		GL11.glPushMatrix();
		GL11.glTranslated(vertex[0].getX(), vertex[0].getY(), 0);
		GL11.glRotated(rot, 0, 0, 1);
		GL11.glTranslated(-vertex[0].getX(), -vertex[0].getY(), 0);
		GL11.glBegin(GL11.GL_TRIANGLE_FAN);
		for(Coordinates c : vertex){
			GL11.glVertex2d(c.getX(), c.getY());
		}GL11.glEnd();
		GL11.glPopMatrix();
	}

	public void drawText(String text, GLFont f, Color fill, Coordinates pos, double rot){
		drawText(text, f, fill, pos.getX(), pos.getY(), rot);
	}

	public void drawText(String text, GLFont f, Color fill, double x, double y, double rot){
		GLFont gl = (GLFont) f;
		TrueTypeFont ttf = gl.getTTFont();
		ttf.drawString((float)x, (float)y, text, new org.newdawn.slick.Color(fill.getRed(), fill.getGreen(), fill.getBlue(), fill.getAlpha()));
	}

	public void clear(Color background){
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		if(background != null) drawRectangle(background, 0, 0, 5000, 5000, 0);
	}
	
}
