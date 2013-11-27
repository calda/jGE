package jge.gui;

import jge.render.*;
import jge.world.Coordinates;

@Deprecated
public class GUIPicture extends GUIElement{
	
	private boolean render;
	
	public GUIPicture(String name, Coordinates screenPos){
		super(name, screenPos);
	}

	@Override
	public void render(GraphicsWrapper g){}
	

	private Priority priority = Priority.NORMAL;
	public void setPriority(Priority p){
		this.priority = p;
	}
	public Priority getPriority(){
		return priority;
	}
	
	public void render(boolean render){
		this.render = render;
	}
	
	@Override
	public boolean renderObject(){
		return render;
	}

}
