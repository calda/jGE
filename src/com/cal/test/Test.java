package com.cal.test;

import jge.behavior.*;
import jge.behavior.prefab.MoveToMouseOnClick;
import jge.entity.*;
import jge.input.*;
import jge.render.*;
import jge.world.*;

public class Test{

	public static void main(String[] args){
		RenderGL gl = new RenderGL(500,500,60);
		gl.setRenderingWorld(new World(500,500));
		Shape s = new Shape(Coordinates.make(100,100), Coordinates.make(100,100), ShapeType.RECTANGLE, Color.YELLOW);
		s.addBehavior(new Behavior("change color"){
			@Action(type=ActionType.TICK)
			public void move(Behaving b){
				((Shape)b).setPos(MouseHandler.getPos());
			}@Action(type=ActionType.MOUSE_CLICK,mouse=MouseButton.LEFT)
			public void changeColor(Behaving b){
				Color c = Color.randomColor();
				c.setAlpha(0);
				((Shape)b).setColor(Color.randomColor());
			}
		});
		gl.getRenderingWorld().add(s);
		gl.getRenderingWorld().getTickManager().startNewTickThread();
		gl.startRendering();
	}
	
}
