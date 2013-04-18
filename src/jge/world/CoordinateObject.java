package jge.world;

import jge.group.*;

public class CoordinateObject implements Groupable{
	
	private Coordinates pos;
	private World owningWorld;
	private Group<Groupable> group;
	
	public CoordinateObject(double x, double y){
		pos = new Coordinates(x, y);
	}
	
	public CoordinateObject(Coordinates pos){
		this.pos = pos;
	}
	
	public Coordinates getPos(){
		return pos;
	}
	
	public void setPos(Coordinates c){
		pos = c;
	}
	
	public void setPos(double x, double y){
		pos = Coordinates.make(x, y);
	}
	
	public void updatePos(Coordinates c){
		pos = c;
	}
	
	public void updatePos(double x, double y){
		pos = Coordinates.make(x, y);
	}
	
	public void setWorld(World w){
		this.owningWorld = w;
	}
	
	public World getOwningWorld(){
		return owningWorld;
	}

	@Override
	public void setGroup(Group<Groupable> group){
		this.group = group;
	}

	@Override
	public Group<Groupable> getGroup(){
		return group;
	}
	
}
