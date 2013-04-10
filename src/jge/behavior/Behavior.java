package jge.behavior;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import jge.input.MouseButton;

public class Behavior{

	final protected HashMap<ActionType, List<Method>> methods;
	
	private final String name;
	
	public Behavior(String name){
		this.name = name;
		methods = new HashMap<ActionType,List<Method>>();
		for(ActionType type : ActionType.values()){
			methods.put(type, new ArrayList<Method>());
		}
		for(Method m : this.getClass().getMethods()){
			Action act = m.getAnnotation(Action.class);
			if(act != null){
				ActionType type = act.type();
				List<Method> typemethods = methods.get(type);
				m.setAccessible(true);
				typemethods.add(m);
			}
		}
	}
	
	public String getName(){
		return name;
	}
	
	public String toString(){
		return "Behavior: " + this.getClass().getName();
	}
	
	public void printMethodReadout(){
		System.out.println(methods);
	}
	
	public void addAction(Runnable action){
		for(Method m : action.getClass().getMethods()){
			Action act = m.getAnnotation(Action.class);
			if(act != null){
				ActionType type = act.type();
				List<Method> typemethods = methods.get(type);
				m.setAccessible(true);
				typemethods.add(m);
			}
		}
	}
	
	public void action(ActionType type, Object additional, Behaving behaving){
		if(type.getType() == ActionType.Type.RUNTIME){ 
			for(Method m : methods.get(type)){
				try{
					m.invoke(this, behaving);
				} catch(Exception exe){ exe.printStackTrace(); };
			}
		}if(type.getType() == ActionType.Type.MOUSE){
			for(Method m : methods.get(type)){
				Action action = m.getAnnotation(Action.class);
				MouseButton mouse = action.mouse();
				if(mouse == MouseButton.NULL_NOT_RELEVANT_OR_IMPORTAINT_AT_ALL){
					try{
						m.invoke(this, behaving);
					} catch(Exception exe){ exe.printStackTrace(); };
				}else if(mouse == additional){
					try{
						m.invoke(this, behaving);
					} catch(Exception exe){ exe.printStackTrace(); };
				}
			}
		}
	}
	
	public void action(ActionType type, Behaving behaving){
		action(type, null, behaving);
	}
	
}
