package jge.animation;

import jge.entity.*;
import jge.render.*;
import jge.world.*;

@SuppressWarnings("rawtypes")
public enum FrameData{

	SCALE(Scaleable.class, Double.class),
	DIMENTIONS(Scaleable.class, Coordinates.class),
	COLOR(Shape.class, Color.class),
	POSITION(CoordinateObject.class, Coordinates.class),
	ROTATION(CoordinateObject.class, Double.class),
	RUNNABLE(Animatable.class, Runnable.class);
	
	private final Class applicable;
	private final Class dataReq;
	private FrameData(Class clazz, Class data){
		applicable = clazz;
		dataReq = data;
	}
	
	@SuppressWarnings("unchecked")
	public boolean meetsDataReq(Object o){
		return dataReq.isAssignableFrom(o.getClass());
	}
	
	@SuppressWarnings("unchecked")
	public boolean canBeApplied(Animatable a){
		return applicable.isAssignableFrom(a.getClass());
	}
	
	public Class getDataReq(){
		return dataReq;
	}
	
	public Class getApplicableClass(){
		return applicable;
	}
	
	public static int interpolateInts(int current, int future, int ticksToGo){
		return current += (future - current)/ticksToGo;
	}
	
	public static double interpolateDoubles(double current, double future, int ticksToGo){
		return current += (future - current)/((double)ticksToGo);
	}
	
}
