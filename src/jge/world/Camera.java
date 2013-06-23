package jge.world;

import java.awt.Graphics2D;
import jge.render.Priority;
import jge.render.Renderable;

public abstract class Camera extends CoordinateObject implements Renderable{

	public Camera(Coordinates pos){
		super(pos);
	}
	
	public Camera(double x, double y){
		super(x, y);
	}

	public void render(Graphics2D g){
		
	}
	
	private Priority priority = Priority.NORMAL;
	public void setPriority(Priority p){
		this.priority = p;
	}
	public Priority getPriority(){
		return priority;
	}

}
