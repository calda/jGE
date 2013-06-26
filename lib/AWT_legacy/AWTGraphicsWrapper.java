package jge.render.awt;

import java.awt.*;
import jge.render.GraphicsWrapper;
import jge.world.Coordinates;

/**
 * For use with the java.awt package
 * NOT CONFIGURED TO WORK WITH ROTATION
 */
public class AWTGraphicsWrapper implements GraphicsWrapper{

	private final Graphics2D g;
	
	public AWTGraphicsWrapper(Graphics2D g){
		System.out.println("Please remember, object rotation is not supported with AWTGraphicsWrapper. If you require rotation support, us the GLGraphicsWrapper implementation.");
		this.g = g;
	}
	
	@Override
	public void drawRectangle(Color fill, double x, double y, double width, double height, double rot){
		g.setColor(fill);
		g.fillRect((int)x, (int)y, (int)width, (int)height);
	}

	@Override
	public void drawRectangle(Color fill, Coordinates pos, Coordinates dim, double rot){
		drawRectangle(fill, pos.getX(), pos.getY(), dim.getX(), dim.getY(), 0);
	}

	@Override
	public void drawOval(Color fill, double x, double y, double width, double height, double rot){
		g.setColor(fill);
		g.fillOval((int)x, (int)y, (int)width, (int)height);
	}

	@Override
	public void drawOval(Color fill, Coordinates pos, Coordinates dim, double rot){
		drawOval(fill, pos.getX(), pos.getY(), dim.getX(), dim.getY(), 0);
	}

	@Override
	public void drawPolygon(Color fill, double rot, Coordinates...vertex){
		int[] x = new int[vertex.length];
		int[] y = new int[vertex.length];
		for(int i = 0; i < vertex.length; i++){
			x[i] = (int)vertex[i].getX();
			y[i] = (int)vertex[i].getY();
		}g.setColor(fill);
		g.fillPolygon(x, y, vertex.length);
	}

	
	@Override
	public void drawImage(Image image, double x, double y, double width, double height, double rot){
		g.drawImage(image, (int)x, (int)y, (int)width, (int)height, null);
	}

	@Override
	public void drawImage(Image image, Coordinates pos, Coordinates dim, double rot){
		drawImage(image, pos.getX(), pos.getY(), dim.getX(), dim.getY(), 0);
	}

	@Override
	public void drawText(String text, Font f, Color fill, Coordinates pos, double rot){
		drawText(text, f, fill, pos.getX(), pos.getY(), 0);
	}

	@Override
	public void drawText(String text, Font f, Color fill, double x, double y, double rot){
		g.setFont(f);
		g.setColor(fill);
		g.drawString(text, (int)x, (int)y);
	}

	@Override
	public void clear(Color background){
		drawRectangle(background, 0, 0, 3000, 3000, 0);
	}
	
	public Graphics2D getInternalGraphicsObject(){
		return g;
	}

}
