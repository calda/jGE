package jge.behavior;

import jge.behavior.Behavior;

public interface Behaving{

	public void addBehavior(Behavior b);
	public void removeBehavior(String name);
	public void removeBehavior(Behavior b);
	public void tickAllBehaviors();
	public void actionRelevantBehaviors(ActionType action, Object additional);
	public void actionRelevantBehaviors(ActionType action);
	public void destroy();
	
}
