package jge.render;

import java.awt.Graphics2D;
import jge.input.MouseHandler;
import jge.world.Coordinates;
import jge.world.World;

public class Render2D{

	private World world;
	private int rendersPerSecond;
	private int msBetweenRenders;
	private final RenderFrame frame;
	private MouseHandler mouse;
	
	public Render2D(RenderFrame frame, int rendersPerSecond){
		setRendersPerSecond(rendersPerSecond);
		this.frame = frame;
		frame.setRender(this);
		mouse = new MouseHandler();
		mouse.attributeTo(this);
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
	
	public void paint(final Graphics2D g){
		world.render(g);
	}
	
	public Coordinates getMousePos(){
		return Coordinates.make(this.getRenderFrame().getMousePosition().x, this.getRenderFrame().getMousePosition().y);
	}
	
	public MouseHandler getMouseHandler(){
		return mouse;
	}

}
