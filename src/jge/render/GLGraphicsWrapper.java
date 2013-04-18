package jge.render;

import java.awt.*;
import jge.world.Coordinates;

/**
 * For use with OpenGL
 */
@Deprecated
public class GLGraphicsWrapper implements GraphicsWrapper{

	@Override
	public void drawRectangle(Color fill, double x, double y, double width, double height){}

	@Override
	public void drawRectangle(Color fill, Coordinates pos, Coordinates dim){}

	@Override
	public void drawOval(Color fill, double x, double y, double width, double height){}

	@Override
	public void drawOval(Color fill, Coordinates pos, Coordinates dim){}

	@Override
	public void drawImage(Image image, double x, double y, double width, double height){}

	@Override
	public void drawImage(Image image, Coordinates pos, Coordinates dim){}

	@Override
	public void drawPolygon(Color fill, Coordinates... vertex){}

	@Override
	public void drawText(String text, Font f, Color fill, Coordinates pos){}

	@Override
	public void drawText(String text, Font f, Color fill, double x, double y){}

	@Override
	public void clear(Color background){}

}
