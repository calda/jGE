package jge.input;

import jge.behavior.ActionType;
import org.lwjgl.input.Mouse;

public class MouseHandler{

	private boolean leftDownPrev;
	private boolean rightDownPrev;
	private boolean centerDownPrev;
	private boolean inWindowPrev;
	
	public void pollInput(){
		boolean leftDownNew = Mouse.isButtonDown(0);
		boolean rightDownNew = Mouse.isButtonDown(1);
		boolean centerDownNew = Mouse.isButtonDown(2);
		boolean inWindowNew = Mouse.isInsideWindow();
		
		if(!leftDownPrev && leftDownNew) super.render.getRenderingWorld().actionRelevantBehaviors(ActionType.MOUSE_PRESS, MouseButton.LEFT);
		if(!rightDownPrev && rightDownNew) super.render.getRenderingWorld().actionRelevantBehaviors(ActionType.MOUSE_DOWN, MouseButton.RIGHT);
		if(!centerDownPrev && centerDownNew) super.render.getRenderingWorld().actionRelevantBehaviors(ActionType.MOUSE_DOWN, MouseButton.CENTER);
		if(!inWindowPrev && inWindowNew) super.render.getRenderingWorld().actionRelevantBehaviors(ActionType.MOUSE_ENTER_WINDOW);
		if(inWindowPrev && !inWindowNew) super.render.getRenderingWorld().actionRelevantBehaviors(ActionType.MOUSE_EXIT_WINDOW);
		if(!leftDownPrev && leftDownNew) System.out.println("left");
		if(!rightDownPrev && rightDownNew) System.out.println("right");
		if(!centerDownPrev && centerDownNew) System.out.println("center");
		if(!inWindowPrev && inWindowNew) System.out.println("in");
		if(inWindowPrev && !inWindowNew) System.out.println("out");
		
		inWindowPrev = inWindowNew;
		leftDownPrev = leftDownNew;
		rightDownPrev = rightDownNew;
		centerDownPrev = centerDownNew;
	}
	
}
