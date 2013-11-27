package jge.entity;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import jge.animation.*;
import jge.behavior.ActionType;
import jge.behavior.Behaving;
import jge.behavior.Behavior;
import jge.group.Groupable;
import jge.render.*;
import jge.world.*;

public class Entity extends CoordinateObject implements Renderable, Behaving, Scaleable, Prioritizable, Groupable, Animatable{

	private GLImage image;
	private Coordinates dim;
	private double scale = 1;
	protected HashMap<String, Behavior> behaviors = new HashMap<String, Behavior>();
	private Priority priority;
	private boolean render;

	public Entity(Coordinates pos, Coordinates dim, GLImage image){
		this(pos, dim, image, Priority.NORMAL);
	}

	public Entity(Coordinates pos, Coordinates dim, GLImage image, Priority p){
		super(pos);
		this.image = image;
		this.dim = dim;
		setPriority(p);
	}

	public Entity(Coordinates pos, Coordinates dim, String filePath, Priority p){
		super(pos);
		if(!filePath.equals("") && filePath != null) this.image = new GLImage(filePath);
		this.dim = dim;
		setPriority(p);
	}

	public Entity(Coordinates pos, Coordinates dim, String filePath){
		this(pos, dim, filePath, Priority.NORMAL);
	}

	public Entity(Coordinates pos, Coordinates dim, GLImage image, Priority p, Behavior...behaviors){
		super(pos);
		this.image = image;
		this.dim = dim;
		for(Behavior b : behaviors){
			addBehavior(b);
		}setPriority(p);
	}

	public Entity(Coordinates pos, Coordinates dim, GLImage image, Behavior...behaviors){
		this(pos, dim, image, Priority.NORMAL, behaviors);
	}

	public Entity(Coordinates pos, Coordinates dim, String filePath, Priority p, Behavior...behaviors){
		super(pos);
		if(!filePath.equals("") && filePath != null) this.image = new GLImage(filePath);
		this.dim = dim;
		for(Behavior b : behaviors){
			addBehavior(b);
		}setPriority(p);
	}

	public Entity(Coordinates pos, Coordinates dim, String filePath, Behavior...behaviors){
		this(pos, dim, filePath, Priority.NORMAL, behaviors);
	}

	public void render(boolean render){
		this.render = render;
	}
	
	@Override
	public boolean renderObject(){
		return render;
	}
	
	@Override
	public void render(GraphicsWrapper g){
		Coordinates onScreen = getOwningWorld().getScreenPosition(getOwningWorld().makeWithinMapBounds(getPos()));
		Coordinates scaledDim = Coordinates.make(this.getDimentions()).multiply(this.getScale());
		onScreen = onScreen.subtract(Coordinates.make(scaledDim).multiply(0.5));
		g.drawImage(image, onScreen, scaledDim, getRotation());
	}

	public void addBehavior(Behavior b){
		behaviors.put(b.getName(), b);
		b.action(ActionType.START, this);
	}

	public void removeBehavior(String name){
		if(!behaviors.containsKey(name)) throw new IllegalArgumentException(this.getClass().getName() + " does not contain a " + name + " object");
		Behavior b = behaviors.get(name);
		removeBehavior(b);
	}

	public void removeBehavior(Behavior b){
		if(b == null) throw new IllegalArgumentException(this.getClass().getName() + " does not contain a " + b + " object");
		behaviors.remove(b.getName());
		b.action(ActionType.END, this);
	}

	public void tickAllBehaviors(){
		actionRelevantBehaviors(ActionType.TICK);
	}

	public void actionRelevantBehaviors(ActionType type, Object additional){
		Iterator<Entry<String, Behavior>> i = behaviors.entrySet().iterator();
		while(i.hasNext()){
			i.next().getValue().action(type, additional, this);
		}
	}

	public void actionRelevantBehaviors(ActionType type){
		actionRelevantBehaviors(type, null);
	}

	public void destroy(){
		getOwningWorld().remove(this);
	}

	public void setDimentions(Coordinates dim){
		this.dim = dim;
	}

	public Coordinates getDimentions(){
		return dim;
	}

	public void setScale(double scale){
		this.scale = scale;
	}

	public double getScale(){
		return scale;
	}

	public void setPriority(Priority p){
		this.priority = p;
	}

	public Priority getPriority(){
		return priority;
	}

	/**
	 * Accounts for rotation
	 * @param vertex When unrotated, will be the vertex in the nth quadrant. I (1) -> 45d, II (2) -> 135d, III (3) -> 225d, IV (4) -> 315d
	 */
	public Coordinates coordinatesOfVertex(int vertex){
		if(dim.getX() == dim.getY()){
			double angle = vertex * 90 - 45; //identified unrotated vertex angle
			angle += getRotation(); //adds shape's rotation angle to vertex's angle
			System.out.println(angle);
			double r = dim.getX()/2;
			r = Math.sqrt((r*r) + (r*r)); //edges become on 45d verticies instead of on 90d
			double x = r * Math.cos(Math.toRadians(angle)); // CoodinatesA = (r*cosA, r*sinA)
			double y = r * Math.sin(Math.toRadians(angle));
			return Coordinates.make(x + super.getPos().getX(), y + super.getPos().getY());
		}return Coordinates.make(0,0);
	}
	
	public Coordinates coordinatesOfAngle(double angle){
		if(dim.getX() == dim.getY()){
			double r = dim.getX()/2;
			r = Math.sqrt((r*r) + (r*r)); //edges become on 45d verticies instead of on 90d
			double x = r * Math.cos(Math.toRadians(angle)); // CoodinatesA = (r*cosA, r*sinA)
			double y = r * Math.sin(Math.toRadians(angle));
			return Coordinates.make(x + super.getPos().getX(), y + super.getPos().getY());
		}return Coordinates.make(0,0);
	}

	public boolean pointWithinBBC(Coordinates check){
		return false;
	}

	public boolean checkCollisionBBC(Entity other){
		return false;
	}

	@Deprecated
	public boolean checkCollisionPPC(Entity other){
		return false;
	}

	@Override
	public String toString(){
		return "Entity at " + getPos() + " with " + behaviors.size() + " behavior(s) and priority of " + getPriority();
	}

}
