package com.cal;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import jge.entity.Entity;
import jge.entity.TickBehavior;
import jge.input.MouseListen;
import jge.render.Render2D;
import jge.render.Screen;
import jge.world.Coordinates;
import jge.world.World;

public class test{

	public test(){}

	public static void main(String[] args){
		Screen s = new Screen();
		Render2D r2d = s.setWindowed("Woo a window.");
		r2d.setRendersPerSecond(100);
		r2d.getRenderFrame().addMouseListener(new MouseListen());
		World world = new World(r2d.getRenderFrame().getWidth(), r2d.getRenderFrame().getHeight(), 1);
		System.out.println(world);
		r2d.setRenderingWorld(world);
		System.out.println(r2d.getRenderingWorld());
		BufferedImage image = null;
		try{
			image = ImageIO.read(new File("images.jpg"));
		}catch(IOException exe){ exe.printStackTrace(); }
		Entity entity = new Entity(100, 100, image);
		entity.addBehavior(new TickBehavior(entity, "main"){
			int direction = 10;
			public void onTick(){
				Entity ent = this.getBehaving();
				ent.getPos().add(direction);
				if(!ent.getOwningWorld().withinMapBounds(Coordinates.make(ent.getPos()).add(direction))){
					direction *= -1;
				}
			}
		});
		world.add(entity);
		
		world.getTickManager().startNewTickThread(100);
		r2d.getRenderFrame().repaint();
	}
	
}
