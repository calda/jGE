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
	
	public static Coordinates make(Coordinates c){
		return new Coordinates(c.getX(), c.getY());
	}
	
	public Coordinates add(int add){
		x += add;
		y += add;
		return this;
	}
	
	public Coordinates subtract(int sub){
		x -= sub;
		y -= sub;
		return this;
	}
	
	public Coordinates add(Coordinates add){
		x += add.getX();
		y += add.getY();
		return this;
	}
	
	public String toString(){
		return "Coordinates (" + x + "," + y + ")";
	}

}
