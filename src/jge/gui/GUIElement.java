package jge.gui;

import jge.render.Renderable;
import jge.world.CoordinateObject;
import jge.world.Coordinates;

public abstract class GUIElement extends CoordinateObject implements Renderable{

	final private String name;
	
	public GUIElement(String name, Coordinates screenPos){
		super(screenPos);
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
}
