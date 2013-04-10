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
	private List<CoordinateObject> objects = new ArrayList<CoordinateObject>();
	Camera[] cams = new Camera[5];
	int activeCamera = 0;
	private final TickManager manager;
	private Render2D renderer;
	private final WorldBehavior worldBehave;
	private boolean rendering = false;
	private boolean ticking = false;
	private boolean removing = false;


	public World(int maxX, int maxY, int pixelsPerBlock, WorldBehavior worldBehavior){
		coordsX = maxX;
		coordsY = maxY;
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

	public void render(Graphics2D g){
		if(removing) return;
		rendering = true;
		g.clearRect(0, 0, 1920, 1080);
		List<Renderable> render = new ArrayList<Renderable>();
		for(CoordinateObject object : objects){
			if(object instanceof Renderable) render.add((Renderable)object);
		}for(Renderable r : render){
			r.render(g);
		}rendering = false;
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

	private List<CoordinateObject> willRemove = new ArrayList<CoordinateObject>();
	public void remove(CoordinateObject object){
		object.setWorld(null);
		willRemove.add(object);
	}

	private void removeWillRemove(){
		if(!rendering && !ticking){
			removing = true;
			System.out.println("Removing " + willRemove.size() + " objects");
			for(CoordinateObject object : willRemove){
				System.out.println("Removing " + object);
				objects.remove(object);
				if(object instanceof Behaving){
					System.out.println("Ending " + object);
					((Behaving)object).actionRelevantBehaviors(ActionType.END);
					System.out.println("Done ending " + object);
				}
			}willRemove.clear();
			removing = false;
		}
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
		if(removing) return;
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
	}

}
