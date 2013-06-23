package jge.group;

import java.util.*;
import jge.behavior.*;
import jge.world.*;

public class Group<T>{
	
	public boolean rendering = false;
	public boolean ticking = false;
	public boolean modifying = false;
	protected List<T> objects = new ArrayList<T>();
	protected List<T> willAdd = new ArrayList<T>();
	protected List<T> willRemove = new ArrayList<T>();
	private final World owningWorld;
	
	public Group(){
		if(this instanceof World) owningWorld = ((World)this);
		else owningWorld = null;
	}
	
	public Group(World world){
		this.owningWorld = world;
	}
	
	public Group(World world, T...ents){
		this.owningWorld = world;
		for(T ent : ents){
			this.add(ent);
		}
	}
	
	public void add(T ent){
		willAdd.add(ent);
		addWillAdd();
	}
	
	public void remove(T ent){
		if(!objects.contains(ent)) throw new IllegalArgumentException("Cannot remove an entity that is not a part of the group");
		willRemove.add(ent);
		removeWillRemove();
	}

	@SuppressWarnings("unchecked")
	public void addWillAdd(){
		if(!rendering && !ticking){
			modifying = true;
			for(T object : willAdd){
				objects.add(object);
				if(object instanceof CoordinateObject) ((CoordinateObject)object).setWorld(owningWorld);
				if(object instanceof Behaving) ((Behaving)object).actionRelevantBehaviors(ActionType.START);
				if(object instanceof Groupable) ((Groupable)object).setGroup((Group<Groupable>) this);
			}willAdd.clear();
			modifying = false;
		}
	}

	public void removeWillRemove(){
		if(!rendering && !ticking){
			modifying = true;
			for(T object : willRemove){
				objects.remove(object);
				if(object instanceof Behaving) ((Behaving)object).actionRelevantBehaviors(ActionType.END); 
				if(object instanceof CoordinateObject) ((CoordinateObject)object).setWorld(null);
				if(object instanceof Groupable) ((Groupable)object).setGroup(null);
			}willRemove.clear();
			modifying = false;
		}
	}
	
	public World getWorld(){
		return owningWorld;
	}
	
	public void doAll(GroupAction<T> ga){
		ticking = true;
		for(T object : objects){
			ga.run(object);
		}ticking = false;
	}
	
	public abstract class GroupAction<T2>{
		public abstract void run(T2 object);
	}
	
	/**
	 * Not advised for use.
	 * If handled incorrectely, could result in a 
	 * ConcurrentModificationException, which is what the Group
	 * object is designed to prevent.
	 * Proceed with caution.
	 * @return a list of all T objects in the group
	 */
	@Deprecated
	public List<T> getObjects(){
		return objects;
	}
}
