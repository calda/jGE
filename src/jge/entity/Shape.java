package jge.entity;

import java.awt.Color;
import jge.render.*;
import jge.world.Coordinates;

public class Shape extends Entity{

	private final ShapeType shape;
	private Color color;
	
	public Shape(Coordinates pos, Coordinates dim, ShapeType shape, Color color, Priority p){
		super(pos, dim, "");
		if(shape == ShapeType.POLYGON && !(this instanceof Polygon)) throw new IllegalArgumentException("Use POLYGON class for things other than rectangles and ovals");
		this.shape = shape;
		this.color = color;
		setScale(1);
		setPriority(p);
	}
	
	public Shape(Coordinates pos, Coordinates dim, ShapeType shape, Color color){
		this(pos, dim, shape, color, Priority.NORMAL);
	}
	
	public ShapeType getType(){
		return shape;
	}
	
	public Color getColor(){
		return color;
	}
	
	public void setColor(Color c){
		color = c;
	}
	
	@Override
	public void render(GraphicsWrapper g){
		Coordinates onScreen = getOwningWorld().getScreenPosition(getOwningWorld().makeWithinMapBounds(getPos()));
		Coordinates scaledDim = Coordinates.make(this.getDimentions()).multiply(this.getScale());
		onScreen = onScreen.subtract(Coordinates.make(scaledDim).multiply(0.5));
		if(shape == ShapeType.RECTANGLE) g.drawRectangle(color, onScreen, scaledDim, getRotation());
		else if(shape == ShapeType.OVAL) g.drawOval(color, onScreen, scaledDim, getRotation());
	}
	
	@Override
	public String toString(){
		return "Shape (" + shape + ") at " + getPos() + " with " + behaviors.size() + " behavior(s) and priority of " + getPriority();
	}

}
