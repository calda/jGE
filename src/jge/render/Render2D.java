package jge.render;

import java.awt.Color;

import jge.gui.GUI;
import jge.input.MouseHandler;
import jge.world.Coordinates;
import jge.world.World;

public abstract class Render2D{

	private World world;
	private GUI gui;
	private MouseHandler mouse;
	
	public Render2D(){
		mouse = new MouseHandler();
		mouse.attributeTo(this);
		gui = new GUI(this);
	}
	
	public World getRenderingWorld(){
		return world;
	}
	
	public void setRenderingWorld(World world){
		this.world = world;
		world.setRenderer(this);
	}
	
	public void paint(final GraphicsWrapper g){
		if(world != null) world.render(g);
		else g.clear(Color.WHITE);
		gui.render(g);
	}
	
	public GUI getGUI(){
		return gui;
	}
	
	public MouseHandler getMouseHandler(){
		return mouse;
	}
	
	public abstract Coordinates getMousePos();
	
}
