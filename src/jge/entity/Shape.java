package jge.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import jge.render.ShapeType;
import jge.world.Coordinates;

public class Shape extends Entity{

	private final ShapeType shape;
	private Color color;
	
	public Shape(Coordinates pos, Coordinates dim, ShapeType shape, Color color){
		super(pos, dim, "");
		if(shape == ShapeType.TRIANGLE) throw new IllegalArgumentException("Only four point shapes supported");
		this.shape = shape;
		this.color = color;
		setScale(1);
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
	public void render(Graphics2D g){
		Coordinates onScreen = getOwningWorld().getScreenPosition(getPos());
		Coordinates scaledDim = Coordinates.make(this.getDimentions()).multiply(this.getScale());
		onScreen = onScreen.subtract(Coordinates.make(scaledDim).multiply(0.5));
		g.setColor(color);
		if(shape == ShapeType.RECTANGLE){
			g.fillRect((int)onScreen.getX(), (int)onScreen.getY(), (int)scaledDim.getX(), (int)scaledDim.getY());
		}else if(shape == ShapeType.OVAL){
			g.fillRect((int)onScreen.getX(), (int)onScreen.getY(), (int)scaledDim.getX(), (int)scaledDim.getY());
		}
	}
	

}
