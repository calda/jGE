package jge.behavior.prefab;

import jge.behavior.*;
import jge.entity.Entity;
import jge.input.MouseButton;
import jge.world.Coordinates;

public class MoveToMouseOnClick extends Behavior{

	public MoveToMouseOnClick(){
		super("mtmoc");
	}
	
	public MoveToMouseOnClick(String name){
		super(name);
	}

	@Action(type=ActionType.MOUSE_PRESS, mouse=MouseButton.LEFT)
	public void onMousePress(Behaving b){
		Entity e = (Entity)b;
		Coordinates coor = e.getOwningWorld().getRenderer().getMousePos();
		e.updatePos(coor);
	}
	
}
