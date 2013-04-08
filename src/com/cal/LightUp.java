package com.cal;

import jge.behavior.*;
import jge.entity.Entity;
import jge.input.MouseButton;
import jge.render.GUIText;
import jge.world.Coordinates;

public class LightUp extends Behavior{

	public LightUp(){
		super("LIGHTUP");
	}
	
	@Action
	public void tick(Behaving b){
		for(Hole h : Hole.holes){
			h.mostRecent = false;
		}
		Entity e = (Entity) b;
		Hole closest = Hole.getClosestHoleToPoint(e.getOwningWorld().getRenderer().getMousePos());
		closest.mostRecent = true;
		((GUIText)e.getOwningWorld().getRenderer().getGUI().getElement("OUT")).setText(" " + Coordinates.make(closest.getPos()).add(10).distance(e.getOwningWorld().getRenderer().getMousePos()));
	}
	
	@Action(type=ActionType.MOUSE_DOWN, mouse=MouseButton.LEFT)
	public void onMouseDown(Behaving b){
		System.out.println("\n\n\n\n\n\n\n\n\n\n\nhiiiii");
		Entity e = (Entity) b;
		Hole closest = Hole.getClosestHoleToPoint(e.getOwningWorld().getRenderer().getMousePos());
		((GUIText)e.getOwningWorld().getRenderer().getGUI().getElement("OUT")).setText(" " + closest.getPos().distance(e.getOwningWorld().getRenderer().getMousePos()));
	}
	
}
