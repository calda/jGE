package jge.render;

import java.awt.*;
import jge.world.Coordinates;

public interface GraphicsWrapper{

	public void drawRectangle(Color fill, double x, double y, double width, double height, double rot);
	public void drawRectangle(Color fill, Coordinates pos, Coordinates dim, double rot);
	public void drawOval(Color fill, double x, double y, double width, double height, double rot);
	public void drawOval(Color fill, Coordinates pos, Coordinates dim, double rot);
	public void drawPolygon(Color fill, double rot, Coordinates...vertex);
	
	public void drawImage(Image image, double x, double y, double width, double height, double rot);
	public void drawImage(Image image, Coordinates pos, Coordinates dim, double rot);
	
	public void drawText(String text, Font f, Color fill, Coordinates pos, double rot);
	public void drawText(String text, Font f, Color fill, double x, double y, double rot);
	
	
	public void clear(Color background);
}
