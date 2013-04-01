package jge.world;

public class Coordinates{

	private int x;
	private int y;
	
	public Coordinates(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void setX(int newX){
		this.x = newX;
	}
	
	public void setY(int newY){
		this.y = newY;
	}
	
	public void setPosition(int newX, int newY){
		setX(newX);
		setY(newY);
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public static Coordinates make(int x, int y){
		return new Coordinates(x, y);
	}

}
