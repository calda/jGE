package jge.input;

import jge.behavior.ActionType;
import jge.render.RenderGL;
import jge.world.Coordinates;
import org.lwjgl.input.Mouse;

public class MouseHandler{
	
	public static long maximumClickLength = 500L;
	
	private final RenderGL render;
	public MouseHandler(RenderGL render){
		this.render = render;
	}
	
	private boolean leftDownPrev;
	private boolean rightDownPrev;
	private boolean centerDownPrev;
	private boolean inWindowPrev;
	private long timeDownLeft;
	private long timeDownRight;
	private long timeDownCenter;
	
	public static Coordinates getPos(){
		return Coordinates.make(Mouse.getX(), Mouse.getY());
	}
	
	public void pollInput(){
		boolean leftDownNew = Mouse.isButtonDown(0);
		boolean rightDownNew = Mouse.isButtonDown(1);
		boolean centerDownNew = Mouse.isButtonDown(2);
		boolean inWindowNew = Mouse.isInsideWindow();
		
		//down
		if(!leftDownPrev && leftDownNew){
			render.getRenderingWorld().actionRelevantBehaviors(ActionType.MOUSE_DOWN, MouseButton.LEFT);
			timeDownLeft = System.currentTimeMillis();
		}if(!rightDownPrev && rightDownNew){
			render.getRenderingWorld().actionRelevantBehaviors(ActionType.MOUSE_DOWN, MouseButton.RIGHT);
			timeDownRight = System.currentTimeMillis();
		}if(!centerDownPrev && centerDownNew){
			render.getRenderingWorld().actionRelevantBehaviors(ActionType.MOUSE_DOWN, MouseButton.CENTER);
			timeDownCenter = System.currentTimeMillis();
		}
		
		//release
		if(leftDownPrev && !leftDownNew){
			render.getRenderingWorld().actionRelevantBehaviors(ActionType.MOUSE_RELEASE, MouseButton.LEFT);
			if((System.currentTimeMillis() - timeDownLeft) <= maximumClickLength) render.getRenderingWorld().actionRelevantBehaviors(ActionType.MOUSE_CLICK, MouseButton.LEFT);
		}if(rightDownPrev && !rightDownNew){
			render.getRenderingWorld().actionRelevantBehaviors(ActionType.MOUSE_RELEASE, MouseButton.RIGHT);
			if((System.currentTimeMillis() - timeDownRight) <= maximumClickLength) render.getRenderingWorld().actionRelevantBehaviors(ActionType.MOUSE_CLICK, MouseButton.RIGHT);
		}if(centerDownPrev && !centerDownNew){
			render.getRenderingWorld().actionRelevantBehaviors(ActionType.MOUSE_RELEASE, MouseButton.CENTER);
			if((System.currentTimeMillis() - timeDownCenter) <= maximumClickLength) render.getRenderingWorld().actionRelevantBehaviors(ActionType.MOUSE_CLICK, MouseButton.CENTER);
		}
		
		
		if(!inWindowPrev && inWindowNew) render.getRenderingWorld().actionRelevantBehaviors(ActionType.MOUSE_ENTER_WINDOW);
		if(inWindowPrev && !inWindowNew) render.getRenderingWorld().actionRelevantBehaviors(ActionType.MOUSE_EXIT_WINDOW);
		
		inWindowPrev = inWindowNew;
		leftDownPrev = leftDownNew;
		rightDownPrev = rightDownNew;
		centerDownPrev = centerDownNew;
	}
	
}
