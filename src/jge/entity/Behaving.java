package jge.entity;

public interface Behaving{

	public void addBehavior(Behavior b);
	public void removeBehavior(String name);
	public void removeBehavior(Behavior b);
	public void tickAllBehaviors();
	
}
