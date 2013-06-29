package com.cal.example.TriangleGame;

import java.util.Random;
import jge.entity.Entity;
import jge.render.Screen;
import jge.render.awt.RenderAWT;
import jge.render.RenderGL;
import jge.world.Coordinates;
import jge.world.World;

public class TriangleMain{

	public static void main(String[] args){
		
		Coordinates[] holes = {
				Coordinates.make(234, 103),
				Coordinates.make(199, 160), Coordinates.make(266, 160),
				Coordinates.make(164, 210), Coordinates.make(234, 210), Coordinates.make(302, 210),
				Coordinates.make(125, 275), Coordinates.make(199, 275), Coordinates.make(266, 275), Coordinates.make(325, 275),
				Coordinates.make(93, 337), Coordinates.make(164, 337), Coordinates.make(234, 337), Coordinates.make(302, 337), Coordinates.make(365, 337)
		};
		
		RenderAWT render = (RenderAWT) Screen.addWindow("Triangle Game!", 800, 450, 60);
		Entity ent = new Entity(Coordinates.make(235, 235), Coordinates.make(100,100), "triangle.png");
		World world = new World(800, 450, new LightUp());
		render.setRenderingWorld(world);
		world.add(ent);
		for(Coordinates c : holes){
			world.add(new Hole(c));
		}
		int not = (new Random()).nextInt(holes.length);
		not = 0;
		for(int i = 0; i < holes.length; i += 1){
			if(not != i){
				world.add(new Peg(holes[i]));
			}
		}
		world.getTickManager().startNewTickThread();
		render.startRendering();
		world.printObjectReadout();
	}
	
}
