package jge.behavior;

public enum ActionType{

	TICK(Type.RUNTIME),
	START(Type.RUNTIME),
	END(Type.RUNTIME),
	MOUSE_PRESS(Type.MOUSE),
	MOUSE_RELEASE(Type.MOUSE),
	MOUSE_DOWN(Type.MOUSE),
	MOUSE_ENTER_WINDOW(Type.MOUSE),
	MOUSE_EXIT_WINDOW(Type.MOUSE),
	MOUSE_OVER(Type.MOUSE),
	MOUSE_DOWN_OVER(Type.MOUSE),
	MOUSE_OVER_ENTER(Type.MOUSE),
	MOUSE_OVER_EXIT(Type.MOUSE),
	KEY_PRESS(Type.KEYBOARD),
	KEY_RELEASE(Type.KEYBOARD);
	
	Type type;
	
	private ActionType(Type type){
		this.type = type;
	}
	
	public Type getType(){
		return type;
	}
	
	public enum Type{
		
		RUNTIME,
		MOUSE,
		KEYBOARD;
		
	}
	
}
