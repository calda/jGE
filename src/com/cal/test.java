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
		Entity e = new Entity(Coordinates.make(90,90), Coordinates.make(50, 50), "TriangleGame/images.jpg");
		e.setScale(30);
		Shape s = new Shape(Coordinates.make(30, 30), Coordinates.make(100, 100), ShapeType.OVAL, Color.DARK_GRAY, Priority.HIGHEST);
		e.addBehavior(b);
		Shape s2 = new Shape(Coordinates.make(300, 300), Coordinates.make(150, 100), ShapeType.RECTANGLE, Color.GREEN, Priority.HIGHEST);
		Shape s3 = new Shape(Coordinates.make(300, 30), Coordinates.make(50, 25), ShapeType.OVAL, Color.MAGENTA);
		Shape s4 = new Shape(Coordinates.make(30, 300), Coordinates.make(50, 25), ShapeType.OVAL, Color.RED, Priority.LOWEST);
		Render2D render = Screen.addWindow("shapes", 800, 450);
		World world = new World(800, 450, 1);
		render.setRenderingWorld(world);
		world.add(e);
		world.add(s2);
		world.add(s3);
		world.add(s4);
		world.add(s);
		world.getTickManager().startNewTickThread();
		render.startRendering();
	}
}
