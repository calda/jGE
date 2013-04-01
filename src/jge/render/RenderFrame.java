package jge.render;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class RenderFrame extends JFrame{

	private Render2D render;
	
	protected void setRender(Render2D render){
		this.render = render;
	}
	
	@Override
	public void paint(Graphics g){
		long start = System.currentTimeMillis();
		render.paint((Graphics2D)g);
		long renderTime = System.currentTimeMillis() - start;
		System.out.println("Render complete, taking " + renderTime + "ms to complete");
		this.repaint(render.getMsBetweenRenders() - renderTime);
	}
	
}
