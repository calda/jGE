package com.cal.test;

import jge.behavior.*;
import jge.entity.Shape;
import jge.render.*;
import jge.world.*;

public class RotatingVerticies{

	public static void main(String[] args){
		
		RenderGL gl = new RenderGL(815, 640, 60);
		final World w = new World(815, 640);
		gl.setRenderingWorld(w);
	
		Shape mainShape = new Shape(Coordinates.make(400, 400), Coordinates.make(200, 200), ShapeType.RECTANGLE, Color.WHITE);
		w.add(mainShape);
		
		for(int i = 1; i <= 360; i++){
			Shape s4 = new Shape(mainShape.coordinatesOfAngle(i), Coordinates.make(5, 5), ShapeType.RECTANGLE, Color.RED);
			w.add(s4);
		}
		
		final Shape[] verticies = new Shape[4];
		for(int i = 0; i < 4; i++){
			System.out.println(mainShape.coordinatesOfVertex(i + 1));
			Shape vert = new Shape(mainShape.coordinatesOfVertex(i + 1), Coordinates.make(20, 20), ShapeType.RECTANGLE, Color.PURPLE);
			w.add(vert);
			vert.rotate(45);
			verticies[i] = vert;
		}
		
		mainShape.addBehavior(new Behavior("RotateAndUpdate"){
			
			@Action
			public void rotate(Behaving b){
				Shape main = (Shape) b;
				main.rotate(1);
				for(int i = 0; i < 4; i++){
					verticies[i].setPos(main.coordinatesOfVertex(i+1));
					verticies[i].rotate(1);
				}
			}
			
		});
		
		w.getTickManager().startNewTickThread();
		gl.startRendering();
	}
	
}
