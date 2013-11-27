package com.cal.example.terrarigen;

import java.util.Random;
import jge.behavior.*;
import jge.entity.*;
import jge.input.MouseButton;
import jge.render.*;
import jge.world.*;

public class TerrariGen{

	public static void main(String[] args){
		final RenderGL gl = new RenderGL(400, 400, 60);
		final World w = new World(800, 800);
		gl.setRenderingWorld(w);
		generateTerrain(w);
		w.setBackgroundColor(Color.CYAN);
		final Dummy d = new Dummy();
		d.addBehavior(new Behavior("click"){
			@Action(type=ActionType.MOUSE_DOWN,mouse=MouseButton.LEFT)
			public void onClick(Behaving b){
				World neww = new World(800, 800);
				neww.add(d);
				neww.setBackgroundColor(Color.CYAN);
				generateTerrain(neww);
				w.destroy(neww);
			}
		});w.add(d);
		gl.startRendering();

	}

	public static void generateTerrain(World w){
		Random r = new Random();
		int yprev = r.nextInt(100) + 225;
		for(int x = 0; x <= w.getMaxCoordsX(); x++){
			yprev += (r.nextInt(4) == 0 ? r.nextInt(8) - 4 : 0);
			Shape grass = new Shape(Coordinates.make(x,yprev+20), Coordinates.make(1,20), ShapeType.RECTANGLE, Color.DARK_GREEN);
			Shape dirt = new Shape(Coordinates.make(x,yprev-20), Coordinates.make(1,75), ShapeType.RECTANGLE, Color.BROWN);
			Shape stone = new Shape(Coordinates.make(x,yprev-120), Coordinates.make(1,200), ShapeType.RECTANGLE, Color.LIGHT_GRAY);
			Shape stone2 = new Shape(Coordinates.make(x,yprev-320), Coordinates.make(1,200), ShapeType.RECTANGLE, Color.LIGHT_GRAY);
			w.add(grass);
			w.add(dirt);
			w.add(stone);
			w.add(stone2);
			if(r.nextInt(55) == 10){
				int height = r.nextInt(20) + 20;
				Shape wood = new Shape(Coordinates.make(x,yprev + 25 + height/2), Coordinates.make(5,height), ShapeType.RECTANGLE, Color.BROWN);
				Shape leaves = new Shape(Coordinates.make(x,yprev + 25 + height), Coordinates.make(20,15), ShapeType.RECTANGLE, 
						Color.DARK_GREEN);
				w.add(wood);
				w.add(leaves);
			}
		}
	}

}
