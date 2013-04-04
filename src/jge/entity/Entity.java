package jge.entity;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import jge.render.Renderable;
import jge.world.CoordinateObject;
import jge.world.Coordinates;

public class Entity extends CoordinateObject implements Renderable, Behaving{

	private Image image;
	private HashMap<String, Behavior> behaviors = new HashMap<String, Behavior>();
	
	public Entity(Coordinates pos, Image image){
		super(pos);
		this.image = image;
	}
	
	public Entity(int x, int y, Image image){
		this(Coordinates.make(x, y), image);
	}
	
	public Entity(int x, int y, Image image, Behavior...behaviors){
		this(Coordinates.make(x, y), image, behaviors);
	}
	
	public Entity(Coordinates pos, Image image, Behavior...behaviors){
		super(pos);
		this.image = image;
		for(Behavior b : behaviors){
			addBehavior(b);
		}
	}

	@Override
	public void render(Graphics2D g){
		Coordinates onScreen = getOwningWorld().getScreenPosition(getPos());
		System.out.println(onScreen);
		g.drawImage(image, onScreen.getX(), onScreen.getY(), null);
	}
	
	public void addBehavior(Behavior b){
		behaviors.put(b.getName(), b);
		b.onStart();
	}
	
	public void removeBehavior(String name){
		if(!behaviors.containsKey(name)) throw new IllegalArgumentException(this.getClass().getName() + " does not contain a " + name + " object");
		Behavior b = behaviors.get(name);
		removeBehavior(b);
	}
	
	public void removeBehavior(Behavior b){
		if(b == null) throw new IllegalArgumentException(this.getClass().getName() + " does not contain a " + b + " object");
		behaviors.remove(b.getName());
		b.onEnd();
	}
	
	public void tickAllBehaviors(){
		Iterator<Entry<String, Behavior>> i = behaviors.entrySet().iterator();
		while(i.hasNext()){
			i.next().getValue().onTick();
		}
	}

}
