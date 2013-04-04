package jge.entity;

public abstract class TickBehavior extends Behavior{

	public TickBehavior(Entity owner, String name){
		super(owner, name);
	}

	public void onStart(){ }
	
	public void onEnd(){ }

	public abstract void onTick();
	
}
