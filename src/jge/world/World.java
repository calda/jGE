package jge.world;

import java.awt.Color;
import java.util.*;
import jge.behavior.ActionType;
import jge.behavior.Behaving;
import jge.group.Group;
import jge.render.*;
import jge.render.Render2D;

public class World extends Group<CoordinateObject> implements Renderable{

	final protected int coordsX;
	final protected int coordsY;
	protected boolean rendering = false;
	protected boolean ticking = false;
	protected boolean modifying = false;
	final private int pixelsPerBlock;
	Camera[] cams = new Camera[5];
	int activeCamera = 0;
	private final TickManager manager;
	private Render2D renderer;
	private final WorldBehavior worldBehave;


	public World(int maxX, int maxY, int pixelsPerBlock, WorldBehavior worldBehavior){
		this.coordsX = maxX;
		this.coordsY = maxY;
		this.pixelsPerBlock = pixelsPerBlock;
		for(int i = 0; i < cams.length; i++){
			cams[i] = null;
		}manager = new TickManager(this);
		if(worldBehavior == null) worldBehave = new WorldBehavior(this);
		else{
			worldBehave = worldBehavior;
			worldBehave.setWorld(this);
		}
	}

	public World(int maxX, int maxY, int pixelsPerBlock){
		this(maxX, maxY, pixelsPerBlock, null);
	}

	public World(int maxX, int maxY){
		this(maxX, maxY, 1, null);
	}

	public World(int maxX, int maxY, WorldBehavior worldBehavior){
		this(maxX, maxY, 1, worldBehavior);
	}

	public WorldBehavior getWorldBehavior(){
		return worldBehave;
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

	public void render(GraphicsWrapper g){
		if(modifying) return;
		rendering = true;
		g.clear(Color.WHITE);
		List<Renderable> render = new ArrayList<Renderable>();
		for(CoordinateObject object : super.objects){
			if(object instanceof Renderable) render.add((Renderable)object);
		}Collections.sort(render, new RenderPriorityComparator());
		for(Renderable r : render){
			r.render(g);
		}rendering = false;
	}

	public int getPixelsPerBlock(){
		return pixelsPerBlock;
	}

	@Deprecated
	public void setCamera(Camera cam, int ref){
		if(ref > cams.length || ref < 1) throw new IllegalArgumentException("Camera Reference can only be between 1 and " + cams.length);
		ref -= 1;
		cams[ref] = cam;
	}

	@Deprecated
	public int addCamera(Camera cam){
		int ref = -1;
		for(int i = cams.length; i >= 0; i--){
			if(cams[i] == null) ref = i;
		}if(ref == -1) throw new IllegalArgumentException("Only " + cams.length + " cameras can be active at once.");
		cams[ref] = cam;
		return ref += 1;
	}

	@Deprecated
	public Camera getActiveCamera(){
		return cams[activeCamera];
	}

	@Deprecated
	public Camera setActiveCamera(int ref){
		if(ref > cams.length || ref < 1) throw new IllegalArgumentException("Camera Reference can only be between 1 and " + cams.length);
		if(cams[ref] == null) if(ref > cams.length || ref < 1) throw new IllegalArgumentException("Camera " + ref + " has not been added yet.");
		activeCamera = ref;
		return cams[ref];
	}

	public Coordinates getScreenPosition(Coordinates onMap){
		if(!withinMapBounds(onMap)) throw new IllegalArgumentException("Coordinates must be within map bounds (" + coordsX + "," + coordsY + ") and was (" + onMap.getX() + "," + onMap.getY() + ")");
		return Coordinates.make(onMap.getX() * pixelsPerBlock, onMap.getY() * pixelsPerBlock);
	}

	public void tickAllBehaviors(){
		getRenderer().getMouseHandler().mostRecentMouse = getRenderer().getMousePos();
		actionRelevantBehaviors(ActionType.TICK);
	}

	public void actionRelevantBehaviors(ActionType type, Object additional){
		if(modifying) return;
		ticking = true;
		List<Behaving> tick = new ArrayList<Behaving>();
		for(CoordinateObject object : objects){
			if(object instanceof Behaving) tick.add(((Behaving)object));
		}
		for(Behaving b : tick){
			b.actionRelevantBehaviors(type, additional);
		}worldBehave.action(type, additional, null);
		ticking = false;
		removeWillRemove();
		addWillAdd();
	}

	public void actionRelevantBehaviors(ActionType type){
		actionRelevantBehaviors(type, null);
	}

	public TickManager getTickManager(){
		return manager;
	}
	
	public void destroy(World newWorld){
		manager.stopTickThread();
		this.getRenderer().setRenderingWorld(newWorld);
		objects.clear();
	}
	
	public int getMaxCoordsX(){
		return coordsX;
	}

	public int getMaxCoordsY(){
		return coordsY;
	}
	
	public void printObjectReadout(){
		System.out.println(super.objects);
	}

	public boolean withinMapBounds(Coordinates onMap){
		if(onMap.getX() <= coordsX && onMap.getX() >= 0){
			if(onMap.getY() <= coordsY && onMap.getY() >= 0){
				return true;
			}
		}return false;
	}
	
	public Coordinates makeWithinMapBounds(Coordinates onMap){
		if(withinMapBounds(onMap)) return onMap;
		Coordinates clone = Coordinates.make(onMap);
		if(clone.getX() > coordsX) clone.setX(coordsX);
		if(clone.getX() < 0) clone.setX(0);
		if(clone.getY() > coordsY) clone.setY(coordsY);
		if(clone.getY() < 0) clone.setY(0);
		return clone;
	}

}
