package jge.world;

public class Coordinates{

	private double x;
	private double y;
	
	public Coordinates(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public void setX(double newX){
		this.x = newX;
	}
	
	public void setY(double newY){
		this.y = newY;
	}
	
	public void setPosition(double newX, double newY){
		setX(newX);
		setY(newY);
	}
	
	public double getX(){
		return x;
	}
	
	public double getY(){
		return y;
	}
	
	public static Coordinates make(double x, double y){
		return new Coordinates(x, y);
	}
	
	public static Coordinates make(Coordinates c){
		return new Coordinates(c.getX(), c.getY());
	}
	
	public Coordinates add(double add){
		x += add;
		y += add;
		return this;
	}
	
	public Coordinates subtract(double sub){
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
