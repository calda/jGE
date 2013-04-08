package jge.entity;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import jge.behavior.ActionType;
import jge.behavior.Behaving;
import jge.behavior.Behavior;
import jge.render.Renderable;
import jge.util.Util;
import jge.world.CoordinateObject;
import jge.world.Coordinates;

public class Entity extends CoordinateObject implements Renderable, Behaving{

	public Image image;
	private HashMap<String, Behavior> behaviors = new HashMap<String, Behavior>();

	public Entity(Coordinates pos, Image image){
		super(pos);
		this.image = image;
	}
	
	public Entity(Coordinates pos, String filePath){
		super(pos);
		this.image = Util.imageFromPath(filePath);
	}

	public Entity(Coordinates pos, Image image, Behavior...behaviors){
		super(pos);
		this.image = image;
		for(Behavior b : behaviors){
			addBehavior(b);
		}
	}
	
	public Entity(Coordinates pos, String filePath, Behavior...behaviors){
		super(pos);
		this.image = Util.imageFromPath(filePath);
		for(Behavior b : behaviors){
			addBehavior(b);
		}
	}

	@Override
	public void render(Graphics2D g){
		Coordinates onScreen = getOwningWorld().getScreenPosition(getPos());
		g.drawImage(image, (int)onScreen.getX(), (int)onScreen.getY(), null);
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

}
