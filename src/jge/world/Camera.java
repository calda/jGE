package jge.world;

import java.awt.Graphics2D;
import jge.render.Renderable;

public class Camera extends CoordinateObject implements Renderable{

	public Camera(Coordinates pos){
		super(pos);
	}
	
	public Camera(double x, double y){
		super(x, y);
	}

	public void render(Graphics2D g){
		
	}

}
