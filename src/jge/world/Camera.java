package jge.world;

public class Camera extends CoordinateObject{

	public static double rotation = 0;
	
	@Deprecated
	public Camera(){
		super(null);
	}
	
	public Camera(Coordinates pos){
		super(pos);
	}
	
	public Camera(double x, double y){
		super(x, y);
	}
	
	@Override
	public void setRotation(double rot){
		super.setRotation(rot);
		rotation = rot;
	}
	
}
