package jge.world;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import jge.behavior.ActionType;
import jge.behavior.Behaving;
import jge.render.Render2D;
import jge.render.Renderable;

public class World implements Renderable{

	final private int coordsX;
	final private int coordsY;
	final private int pixelsPerBlock;
	List<CoordinateObject> objects = new ArrayList<CoordinateObject>();
	Camera[] cams = new Camera[5];
	int activeCamera = 0;
	private final TickManager manager;
	private Render2D renderer;
	
	public World(int maxX, int maxY, int pixelsPerBlock){
		coordsX = maxX;
		coordsY = maxY;
		this.pixelsPerBlock = pixelsPerBlock;
		for(int i = 0; i < cams.length; i++){
			cams[i] = null;
		}manager = new TickManager(this);
	}
	
	public void setRenderer(Render2D renderer){
		this.renderer = renderer;
	}
	
	public Render2D getRenderer(){
		return renderer;
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
	
	public Coordinates getScreenPosition(Coordinates onMap){
		if(!withinMapBounds(onMap)) throw new IllegalArgumentException("Coordinates must be within map bounds (" + coordsX + "," + coordsY + ")");
		return Coordinates.make(onMap.getX() * pixelsPerBlock, onMap.getY() * pixelsPerBlock);
	}
	
	public boolean withinMapBounds(Coordinates onMap){
		if(onMap.getX() <= coordsX && onMap.getX() >= 0){
			if(onMap.getY() <= coordsY && onMap.getY() >= 0){
				return true;
			}
		}return false;
	}
	
	public void tickAllBehaviors(){
		actionRelevantBehaviors(ActionType.TICK);
	}
	
	public void actionRelevantBehaviors(ActionType type, Object additional){
		for(CoordinateObject object : objects){
			if(object instanceof Behaving){
				Behaving b = (Behaving) object;
				b.actionRelevantBehaviors(type, additional);
			}
		}
	}

	public void actionRelevantBehaviors(ActionType type){
		actionRelevantBehaviors(type, null);
	}
	
	public TickManager getTickManager(){
		return manager;
	}
	
}
