package jge.render.awt;

import jge.render.Render2D;
import jge.world.Coordinates;

public class RenderAWT extends Render2D{

	private int rendersPerSecond;
	private int msBetweenRenders;
	private final RenderFrame frame;

	public RenderAWT(RenderFrame frame, int rendersPerSecond){
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
}
