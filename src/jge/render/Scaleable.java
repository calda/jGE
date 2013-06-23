package jge.render;

import jge.world.Coordinates;

public interface Scaleable{

	public void setDimentions(Coordinates dim);
	public Coordinates getDimentions();
	public void setScale(double scale);
	public double getScale();
	
}
