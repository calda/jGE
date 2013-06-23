package jge.world;

import java.lang.reflect.Method;
import jge.behavior.*;
import jge.input.MouseButton;

public class WorldBehavior extends Behavior{

	private World owningWorld;
	
	public WorldBehavior(World owner){
		super("WORLD_BEHAVIOR");
		this.owningWorld = owner;
	}
	
	public WorldBehavior(){
		super("WORLD_BEHAVIOR");
	}
	
	public World getWorld(){
		return owningWorld;
	}
	
	protected void setWorld(World w){
		this.owningWorld = w;
	}

	@Override
	public void action(ActionType type, Object additional, Behaving behaving){
		if(type.getType() == ActionType.Type.RUNTIME){ 
			for(Method m : methods.get(type)){
				try{
					m.invoke(this, owningWorld);
				} catch(Exception exe){ exe.printStackTrace(); };
			}
		}if(type.getType() == ActionType.Type.MOUSE){
			for(Method m : methods.get(type)){
				Action action = m.getAnnotation(Action.class);
				MouseButton mouse = action.mouse();
				if(mouse == MouseButton.NULL_NOT_RELEVANT_OR_IMPORTAINT_AT_ALL){
					try{
						m.invoke(this, owningWorld);
					} catch(Exception exe){ exe.printStackTrace(); };
				}else if(mouse == additional){
					try{
						m.invoke(this, owningWorld);
					} catch(Exception exe){ exe.printStackTrace(); };
				}
			}
		}
	}
	
}
