package jge.world;

public class Coordinates3 extends Coordinates{

	private double z;
	
	public Coordinates3(double x, double y, double z){
		super(x, y);
		this.z = z;
	}

	public void setZ(double z){
		this.z = z;
	}
	
	public double getZ(){
		return z;
	}
	
	public void set(double x, double y, double z){
		setX(x);
		setY(y);
		setZ(z);
	}
	
	public static Coordinates3 make(double x, double y, double z){
		return new Coordinates3(x, y, z);
	}
	
	public static Coordinates3 make(Coordinates3 c){
		return new Coordinates3(c.getX(), c.getY(), c.getZ());
	}
	
	public static Coordinates3 make(Coordinates c){
		return new Coordinates3(c.getX(), c.getY(), 0);
	}
	
	public Coordinates3 add(double add){
		setX(getX() + add);
		setY(getY() + add);
		z += add;
		return this;
	}
	
	public Coordinates3 subtract(double sub){
		setX(getX() - sub);
		setY(getY() - sub);
		z -= sub;
		return this;
	}
	
	public Coordinates3 add(Coordinates3 add){
		setX(getX() + add.getX());
		setY(getY() + add.getY());
		z += add.getZ();
		return this;
	}
	
	public Coordinates3 add(Coordinates add){
		setX(getX() + add.getX());
		setY(getY() + add.getY());
		return this;
	}
	
	public Coordinates3 subtract(Coordinates3 sub){
		setX(getX() - sub.getX());
		setY(getY() - sub.getY());
		z -= sub.getZ();
		return this;
	}
	
	public Coordinates3 subtract(Coordinates sub){
		setX(getX() - sub.getX());
		setY(getY() - sub.getY());
		return this;
	}
	
}
