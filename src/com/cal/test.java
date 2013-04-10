package com.cal;
import java.awt.Color;
import jge.behavior.Action;
import jge.behavior.Behaving;
import jge.entity.Entity;
import jge.entity.Shape;
import jge.render.*;
import jge.world.Coordinates;
import jge.world.World;

public class Test{

	public Test(){}

	public static void main(String[] args){
		jge.behavior.Behavior b = (new jge.behavior.Behavior("test"){
			@Action
			public void onTick(Behaving b){
				Entity e = (Entity) b;
				if(e.getOwningWorld().getRenderer().getMouseHandler().leftDown){
					e.updatePos(e.getOwningWorld().getRenderer().getMousePos());
				}
			}
		});
		Shape s = new Shape(Coordinates.make(30, 30), Coordinates.make(100, 100), ShapeType.OVAL, Color.DARK_GRAY);
		s.addBehavior(b);
		Render2D render = Screen.addWindow("shapes", 800, 450);
		World world = new World(800, 450, 1);
		render.setRenderingWorld(world);
		world.add(s);
		System.out.println(render.getRendersPerSecond());
		world.getTickManager().startNewTickThread();
		GUIText text = new GUIText("OUT", Coordinates.make(50, 50), "ahsgfkhdgsfk");
		render.getGUI().addGUIElement(text);
		render.startRendering();
	}
}
