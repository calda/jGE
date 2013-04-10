package jge.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import jge.behavior.ActionType;
import jge.render.Render2D;
import jge.world.Coordinates;

public class MouseHandler implements MouseListener{

	private Render2D render;
	public boolean leftDown = false;
	public boolean rightDown = false;
	public Coordinates mostRecentMouse = Coordinates.make(0, 0);
	
	@Override
	public void mouseClicked(MouseEvent e){
	}

	@Override
	public void mouseEntered(MouseEvent e){
		render.getRenderingWorld().actionRelevantBehaviors(ActionType.MOUSE_ENTER_WINDOW);
	}

	@Override
	public void mouseExited(MouseEvent e){
		render.getRenderingWorld().actionRelevantBehaviors(ActionType.MOUSE_EXIT_WINDOW);
	}

	@Override
	public void mousePressed(MouseEvent e){
		MouseButton button;
		switch(e.getButton()){
			case 1: button = MouseButton.LEFT; leftDown = true; break;
			case 3: button = MouseButton.RIGHT; rightDown = true; break;
			default: button = null;
		}
		render.getRenderingWorld().actionRelevantBehaviors(ActionType.MOUSE_PRESS, button);
	}

	@Override
	public void mouseReleased(MouseEvent e){
		MouseButton button;
		switch(e.getButton()){
			case 1: button = MouseButton.LEFT; leftDown = false; break;
			case 3: button = MouseButton.RIGHT; rightDown = false; break;
			default: button = null;
		}render.getRenderingWorld().actionRelevantBehaviors(ActionType.MOUSE_RELEASE, button);
	}
	
	public void attributeTo(Render2D render){
		this.render = render;
		render.getRenderFrame().addMouseListener(this);
	}

}
