package jge.world;

public class CoordinateObject{
	
	private Coordinates pos;
	private World owningWorld;
	
	public CoordinateObject(int x, int y){
		pos = new Coordinates(x, y);
	}
	
	public CoordinateObject(Coordinates pos){
		this.pos = pos;
	}
	
	public Coordinates getPos(){
		return pos;
	}
	
	protected void setWorld(World w){
		this.owningWorld = w;
	}
	
	protected World getOwningWorld(){
		return owningWorld;
	}
	
}
