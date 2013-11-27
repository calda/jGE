package jge.world;

import java.awt.Point;
import java.awt.geom.AffineTransform;

public class Coordinates implements Cloneable{

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

	public void set(double newX, double newY){
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

	public Coordinates multiply(double mul){
		x *= mul;
		y *= mul;
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

	public Coordinates multiply(Coordinates mul){
		x *= mul.getX();
		y *= mul.getY();
		return this;
	}

	public Coordinates subtract(Coordinates sub){
		x -= sub.getX();
		y -= sub.getY();
		return this;
	}
	
	public String toString(){
		return "Coordinates (" + x + "," + y + ")";
	}

	public double distance(Coordinates c){
		if(c == null) return 0;
		double x1 = this.getX();
		double y1 = this.getY();
		double x2 = c.getX();
		double y2 = c.getY();
		return Math.sqrt(Math.pow(y2 - y1, 2) + Math.pow(x2 - x1, 2));
	}

	public static Coordinates make(Point p){
		return Coordinates.make(p.x, p.y);
	}

	@Override
	public Coordinates clone(){
		return Coordinates.make(this);
	}

	public void rotate(double angle, Coordinates around){
		double[] pt = {x, y};
		AffineTransform.getRotateInstance(Math.toRadians(angle), around.getX(), around.getY())
		  .transform(pt, 0, pt, 0, 1); // specifying to use this double[] to hold coords
		double newx = pt[0];
		double newy = pt[1];
		x = newx + 0;
		y = newy + 0;
	}

}
