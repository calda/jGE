package jge.entity;

public abstract class Behavior{
	
	private final Entity owner;
	private final String name;
	
	public Behavior(Entity owner, String name){
		this.owner = owner;
		this.name = name;
	}
	
	public abstract void onStart();
	public abstract void onTick();
	public abstract void onEnd();
	
	public Entity getBehaving(){
		return owner;
	}
	
	public String getName(){
		return name;
	}
	
	public String toString(){
		return "Behavior: " + this.getClass().getName();
	}
	
}
