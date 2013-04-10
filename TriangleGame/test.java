package com.cal;
import jge.behavior.Action;
import jge.behavior.Behaving;
import jge.entity.Entity;
import jge.render.GUIText;
import jge.render.Render2D;
import jge.render.Screen;
import jge.world.Coordinates;
import jge.world.World;

public class test{

	public test(){}

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
		Entity ent = new Entity(Coordinates.make(30, 30), "images.jpg", b);
		Render2D render = Screen.addWindow("BEHAVIORS YAY", 800, 450);
		World world = new World(800, 450, 1);
		render.setRenderingWorld(world);
		world.add(ent);
		System.out.println(render.getRendersPerSecond());
		world.getTickManager().startNewTickThread();
		GUIText text = new GUIText("OUT", Coordinates.make(50, 50), "ahsgfkhdgsfk");
		render.getGUI().addGUIElement(text);
		render.startRendering();
	}
}
