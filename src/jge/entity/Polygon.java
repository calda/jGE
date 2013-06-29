package jge.entity;

import java.util.*;
import jge.render.*;
import jge.world.Coordinates;

public class Polygon extends Shape{

	public enum VertexMode{
		RELATIVE,WORLD;
	}
	
	private List<Coordinates> vertexes = new ArrayList<Coordinates>();
	private VertexMode mode;
	
	public Polygon(Coordinates pos, Color color, VertexMode mode){
		super(pos, null, ShapeType.POLYGON, color);
		this.mode = mode;
	}
	
	public Polygon(Coordinates pos, Color color){
		this(pos, color, VertexMode.RELATIVE);
	}
	
	public void setVertexMode(VertexMode mode){
		this.mode = mode;
	}
	
	public List<Coordinates> getVertexes(){
		return vertexes;
	}
	
	public void addVertex(Coordinates newVertex){
		vertexes.add(newVertex);
	}
	
	public void addVertex(int x, int y){
		vertexes.add(Coordinates.make(x, y));
	}
	
	public void addVertexes(Coordinates...newVertex){
		for(Coordinates c : newVertex){
			vertexes.add(c);
		}
	}
	
	public void addVertexes(ArrayList<Coordinates> newVertex){
		for(Coordinates c : newVertex){
			vertexes.add(c);
		}
	}
	
	public void setVertexes(Coordinates...newVertex){
		vertexes = new ArrayList<Coordinates>();
		for(Coordinates c : newVertex){
			vertexes.add(c);
		}
	}
	
	public void setVertexes(ArrayList<Coordinates> newVertex){
		vertexes = new ArrayList<Coordinates>();
		for(Coordinates c : newVertex){
			vertexes.add(c);
		}
	}
	
	@Override
	public void render(GraphicsWrapper g){
		Coordinates onScreen = getOwningWorld().getScreenPosition(getOwningWorld().makeWithinMapBounds(getPos()));
		Coordinates[] vpass = new Coordinates[vertexes.size() + (g instanceof GraphicsWrapper ? 1 : 0)];
		if(g instanceof GraphicsWrapper) vpass[0] = onScreen;
		if(mode == VertexMode.RELATIVE){
			for(int i = 0; i > vpass.length; i++){
				vpass[i] = vertexes.get(i + (g instanceof GraphicsWrapper ? 1 : 0)).add(onScreen);
			}
		}else{
			for(int i = 0; i > vpass.length; i++){
				vpass[i] = getOwningWorld().getScreenPosition(vertexes.get(i + (g instanceof GraphicsWrapper ? 1 : 0)));
			}
		}g.drawPolygon(getColor(), getRotation(), vpass);
	}
	
	@Override
	public String toString(){
		return "Polygon with " + vertexes.size() + (mode == VertexMode.RELATIVE ? " relative" : " world") + " vertexes at " + getPos() + " with " + behaviors.size() + " behavior(s) and priority of " + getPriority();
	}

}
