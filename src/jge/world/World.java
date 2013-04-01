package jge.world;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import jge.render.Renderable;

public class World implements Renderable{

	final private int coordsX;
	final private int coordsY;
	final private int pixelsPerBlock;
	List<CoordinateObject> objects = new ArrayList<CoordinateObject>();
	Camera[] cams = new Camera[5];
	int activeCamera = 0;
	
	public World(int coordsX, int coordsY, int pixelsPerBlock){
		this.coordsX = coordsX;
		this.coordsY = coordsY;
		this.pixelsPerBlock = pixelsPerBlock;
		for(int i = 0; i < cams.length; i++){
			cams[i] = null;
		}
	}
	
	public String toString(){
		return "World (" + coordsX + "," + coordsY + ")";
	}
	
	public void render(Graphics2D g){
		List<CoordinateObject> objectsList = new ArrayList<CoordinateObject>();
		for(CoordinateObject object : objects){
			objectsList.add(object);
		}for(CoordinateObject object : objectsList){
			if(object instanceof Renderable){
				((Renderable) object).render(g);
			}
		}
	}
	
	public int getMaxCoordsX(){
		return coordsX;
	}
	
	public int getMaxCoordsY(){
		return coordsY;
	}
	
	public int getPixelsPerBlock(){
		return pixelsPerBlock;
	}
	
	public void add(CoordinateObject object){
		if(object instanceof Camera) throw new IllegalArgumentException("You must add a Camera through the setCamera() method");
		objects.add(object);
		object.setWorld(this);
	}
	
	
	public void setCamera(Camera cam, int ref){
		if(ref > cams.length || ref < 1) throw new IllegalArgumentException("Camera Reference can only be between 1 and " + cams.length);
		ref -= 1;
		cams[ref] = cam;
	}
	
	public int addCamera(Camera cam){
		int ref = -1;
		for(int i = cams.length; i >= 0; i--){
			if(cams[i] == null) ref = i;
		}if(ref == -1) throw new IllegalArgumentException("Only " + cams.length + " cameras can be active at once.");
		cams[ref] = cam;
		return ref += 1;
	}
	
	public Camera getActiveCamera(){
		return cams[activeCamera];
	}
	
	public Camera setActiveCamera(int ref){
		if(ref > cams.length || ref < 1) throw new IllegalArgumentException("Camera Reference can only be between 1 and " + cams.length);
		if(cams[ref] == null) if(ref > cams.length || ref < 1) throw new IllegalArgumentException("Camera " + ref + " has not been added yet.");
		activeCamera = ref;
		return cams[ref];
	}
	
}
