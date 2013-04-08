package com.cal;

import jge.entity.Entity;
import jge.render.GUIText;
import jge.render.Render2D;
import jge.render.Screen;
import jge.world.Coordinates;
import jge.world.World;

public class TriangleGame{

	public static void main(String[] args){
		
		Coordinates[] holes = {
				Coordinates.make(234, 103),
				Coordinates.make(199, 160), Coordinates.make(266, 160),
				Coordinates.make(164, 210), Coordinates.make(234, 210), Coordinates.make(302, 210),
				Coordinates.make(125, 275), Coordinates.make(199, 275), Coordinates.make(266, 275), Coordinates.make(325, 275),
				Coordinates.make(93, 337), Coordinates.make(164, 337), Coordinates.make(234, 337), Coordinates.make(302, 337), Coordinates.make(365, 337)
		};
		
		Entity ent = new Entity(Coordinates.make(30, 30), "triangle.png");
		ent.addBehavior(new LightUp());
		Render2D render = Screen.addWindow("Triangle Game", 800, 450);
		World world = new World(800, 450, 1);
		render.setRenderingWorld(world);
		world.add(ent);
		for(Coordinates c : holes){
			world.add(new Hole(c));
		}
		System.out.println(render.getRendersPerSecond());
		GUIText text = new GUIText("OUT", Coordinates.make(50, 50), "ahsgfkhdgsfk");
		render.getGUI().addGUIElement(text);
		world.getTickManager().startNewTickThread();
		render.startRendering();
		
	}
	
}