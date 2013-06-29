package jge.entity;

import jge.behavior.Behavior;
import jge.render.GraphicsWrapper;

public class Dummy extends Entity{

	public Dummy(){
		super(null, null, "");
	}
	
	public Dummy(Behavior...behaviors){
		super(null, null, "", behaviors);
	}
	
	@Override
	public void render(GraphicsWrapper g){
		return;
	}
	
}
