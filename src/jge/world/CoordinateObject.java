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
	
	public void updatePos(Coordinates c){
		pos = c;
	}
	
	public void updatePos(int x, int y){
		pos = Coordinates.make(x, y);
	}
	
	protected void setWorld(World w){
		this.owningWorld = w;
	}
	
	public World getOwningWorld(){
		return owningWorld;
	}
	
}
