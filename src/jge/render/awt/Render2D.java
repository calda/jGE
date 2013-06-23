package jge.render.awt;

import java.awt.*;
import jge.gui.GUI;
import jge.input.MouseHandler;
import jge.render.GraphicsWrapper;
import jge.world.Coordinates;
import jge.world.World;

public class Render2D{

	private World world;
	private int rendersPerSecond;
	private int msBetweenRenders;
	private final RenderFrame frame;
	private MouseHandler mouse;
	private GUI gui;
	
	public Render2D(RenderFrame frame, int rendersPerSecond){
		setRendersPerSecond(rendersPerSecond);
		this.frame = frame;
		frame.setRender(this);
		mouse = new MouseHandler();
		mouse.attributeTo(this);
		gui = new GUI(this);
	}
	
	public RenderFrame getRenderFrame(){
		return frame;
	}
	
	public void setRendersPerSecond(int rps){
		rendersPerSecond = rps;
		msBetweenRenders = 1000/rps;
	}
	
	public int getRendersPerSecond(){
		return rendersPerSecond;
	}
	
	protected int getMsBetweenRenders(){
		return msBetweenRenders;
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
	
	public void startRendering(){
		this.getRenderFrame().repaint();
	}
	
	public Coordinates getMousePos(){
		try{
			return Coordinates.make(this.getRenderFrame().getMousePosition().x, this.getRenderFrame().getMousePosition().y);
		}catch(Exception e){
			return this.getMouseHandler().mostRecentMouse;
		}
	}
	
	public MouseHandler getMouseHandler(){
		return mouse;
	}
	
	public GUI getGUI(){
		return gui;
	}

}
