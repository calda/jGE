package jge.render;

import java.awt.Graphics2D;
import javax.swing.JFrame;
import jge.world.World;

@SuppressWarnings("serial")
public class Render2D extends JFrame{

	private World world;
	private int rendersPerSecond;
	private int msBetweenRenders;
	
	public Render2D(int rendersPerSecond){
		setRendersPerSecond(rendersPerSecond);
	}
	
	public Render2D(){
		this(50);
	}
	
	public void setRendersPerSecond(int rps){
		rendersPerSecond = rps;
		msBetweenRenders = 1000/rps;
	}
	
	public int getRendersPerSecond(){
		return rendersPerSecond;
	}
	
	
	public void paint(final Graphics2D g){
		world.render(g);
		this.repaint(msBetweenRenders);
	}

}
