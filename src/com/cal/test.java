package com.cal;

import jge.render.Render2D;
import jge.render.Screen;
import jge.world.World;

public class test{

	public test(){}

	public static void main(String[] args){
		Screen s = new Screen();
		Render2D r2d = s.setWindowed("Woo a window.");
		World world = new World(100, 100, 1);
		System.out.println(world);
		r2d.setRenderingWorld(world);
		r2d.getRenderFrame().repaint();
		System.out.println(r2d.getRenderingWorld());
	}
	
}
