package jge.render;

import java.awt.*;
import jge.world.Coordinates;

public interface GraphicsWrapper{

	public void drawRectangle(Color fill, double x, double y, double width, double height);
	public void drawRectangle(Color fill, Coordinates pos, Coordinates dim);
	public void drawOval(Color fill, double x, double y, double width, double height);
	public void drawOval(Color fill, Coordinates pos, Coordinates dim);
	public void drawPolygon(Color fill, Coordinates...vertex);
	
	public void drawImage(Image image, double x, double y, double width, double height);
	public void drawImage(Image image, Coordinates pos, Coordinates dim);
	
	public void drawText(String text, Font f, Color fill, Coordinates pos);
	public void drawText(String text, Font f, Color fill, double x, double y);
	
	
	public void clear(Color background);
}
