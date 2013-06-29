package jge.behavior.prefab;

import jge.behavior.*;
import jge.entity.Entity;
import jge.input.*;

public class MoveToMouseOnClick extends Behavior{

	public MoveToMouseOnClick(){
		super("mtmoc");
	}
	
	public MoveToMouseOnClick(String name){
		super(name);
	}
	
	@Action(type=ActionType.MOUSE_CLICK, mouse=MouseButton.LEFT)
	public void onMousePress(Behaving b){
		Entity e = (Entity)b;
		e.updatePos(MouseHandler.getPos());
	}
	
}
