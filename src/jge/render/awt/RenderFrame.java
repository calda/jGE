package jge.render.awt;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import jge.render.Screen;

@SuppressWarnings("serial")
public class RenderFrame extends JFrame{

	private Render2D render;

	public RenderFrame(){
		/*addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				dispose();
				System.exit(0); 
			}
		});*/
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	}

	protected void setRender(Render2D render){
		this.render = render;
	}

	private boolean first = true;

	@Override
	public void paint(Graphics g){
		if(first){
			((Graphics2D)g).setColor(Color.BLACK);
			((Graphics2D)g).fillRect(0, 0, Screen.getX(), Screen.getY());
			first = false;
			return;
		}
		//g.clearRect(0, 0, 1920, 1080);
		long start = System.currentTimeMillis();
		render.paint(new AWTGraphicsWrapper((Graphics2D)g));
		long renderTime = System.currentTimeMillis() - start;
		//System.out.println("Render complete, taking " + renderTime + "ms to complete");
		try{
			long sleep = render.getMsBetweenRenders() - renderTime;
			//System.out.println("waiting " + sleep + " due to " + render.getMsBetweenRenders() + " between render");
			Thread.sleep((sleep > 0) ? sleep : 0);
		} catch(InterruptedException exe){}
		this.repaint();
	}

}
