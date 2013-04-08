package jge.behavior;

import java.lang.annotation.*;
import jge.input.MouseButton;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Action{

	ActionType type() default ActionType.TICK;
	MouseButton mouse() default MouseButton.NULL_NOT_RELEVANT_OR_IMPORTAINT_AT_ALL;
	
}
