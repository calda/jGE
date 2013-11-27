package com.cal.test;

import jge.animation.*;
import jge.entity.Shape;
import jge.render.*;
import jge.world.*;

public class Test{

	public static void main(String[] args){
		RenderGL gl = new RenderGL(800,600,60);
		World w = new World(800,600);
		gl.setRenderingWorld(w);
		Shape s = new Shape(Coordinates.make(300, 300), Coordinates.make(50,50), ShapeType.RECTANGLE, Color.LIGHT_GREEN);
		w.add(s);
		Animation a = new Animation();
		for(int i = 1; i < 100; i++){
			AnimationFrame.getNew().set(FrameData.SCALE, Math.random() * 10).set(FrameData.ROTATION, i * 360).addTo(a, 60 * i);
			AnimationFrame.getNew().set(FrameData.COLOR, Color.randomColor()).addTo(a, 25 * i);
			if(i % 2 == 0) AnimationFrame.getNew().set(FrameData.POSITION, Coordinates.make(0, 300)).addTo(a, 96 * i);
			else AnimationFrame.getNew().set(FrameData.POSITION, Coordinates.make(800, 300)).addTo(a, 96 * i);
			
		}
		s.animate(a);
		w.getTickManager().startNewTickThread();
		gl.startRendering();
	}
	
}
