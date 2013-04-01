package jge.render;

import java.awt.Graphics2D;
import jge.world.World;

public class Render2D{

	private World world;
	private int rendersPerSecond;
	private int msBetweenRenders;
	private final RenderFrame frame;
	
	public Render2D(RenderFrame frame, int rendersPerSecond){
		setRendersPerSecond(rendersPerSecond);
		this.frame = frame;
		frame.setRender(this);
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
	}
	
	public void paint(final Graphics2D g){
		world.render(g);
	}

}
