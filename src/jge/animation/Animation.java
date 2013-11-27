package jge.animation;

import java.util.*;
import java.util.Map.Entry;
import jge.entity.*;
import jge.render.*;
import jge.world.*;


public class Animation{

	private HashMap<Integer, AnimationFrame> frames = new HashMap<Integer, AnimationFrame>();
	private final boolean isDuplicate;

	public Animation(){
		this.isDuplicate = false;
	}

	protected Animation(HashMap<Integer, AnimationFrame> frames){
		this.isDuplicate = true;
		this.frames = frames;
	}

	public boolean canAnimate(Animatable a){
		List<FrameData> cannot = new ArrayList<FrameData>();
		for(FrameData fd : FrameData.values()){
			if(!fd.canBeApplied(a)) cannot.add(fd);
		}if(cannot.size() == 0) return true;
		for(AnimationFrame af : frames.values()){
			for(FrameData fd2 : cannot){
				if(af.hasData(fd2)) return false;
			}
		}return true;
	}

	public void addFrame(int tick, AnimationFrame frame){
		if(frames.containsKey(tick)) System.out.println("Overriding frame already set at tick " + tick);
		frames.put(tick, frame);
		if(active) setNexts(); 
	}

	public void printData(){
		System.out.println("Animation:\n" + frames);
	}

	private boolean active = false;
	private Animatable ani = null;
	private int tick = 0;
	private HashMap<FrameData, AnimationFrame> nextFrames = new HashMap<FrameData, AnimationFrame>();
	private HashMap<FrameData, Integer> nextTicks = new HashMap<FrameData, Integer>();

	public void startOn(Animatable a){
		if(active) throw new IllegalArgumentException("Use through (Entity).animate() instead of this method");
		if(!isDuplicate) throw new IllegalArgumentException("Use through (Entity).animate() instead of this method");
		active = true;
		ani = a;
		tick = 0;
		setNexts();
	}

	public void tick(){
		if(active){
			tick++;
			if(nextTicks.containsKey(FrameData.SCALE)){
				int nextTick = nextTicks.get(FrameData.SCALE);
				if(nextTick == tick) remove(FrameData.SCALE);
				else{
					AnimationFrame nextFrame = nextFrames.get(FrameData.SCALE);
					Scaleable s = (Scaleable) ani;
					s.setScale(FrameData.interpolateDoubles(s.getScale(), (Double)nextFrame.getData(FrameData.SCALE), nextTick - tick));
				}
			}if(nextTicks.containsKey(FrameData.COLOR)){
				int nextTick = nextTicks.get(FrameData.COLOR);
				if(nextTick == tick) remove(FrameData.COLOR);
				else{
					AnimationFrame nextFrame = nextFrames.get(FrameData.COLOR);
					Shape s = (Shape) ani;
					Color current = s.getColor();
					Color next = (Color) nextFrame.getData(FrameData.COLOR);
					int r = FrameData.interpolateInts(current.getRed(), next.getRed(), nextTick - tick);
					int g = FrameData.interpolateInts(current.getGreen(), next.getGreen(), nextTick - tick);
					int b = FrameData.interpolateInts(current.getBlue(), next.getBlue(), nextTick - tick);
					int a = FrameData.interpolateInts(current.getAlpha(), next.getAlpha(), nextTick - tick);
					s.setColor(new Color(r, g, b, a));
				}
			}if(nextTicks.containsKey(FrameData.POSITION)){
				int nextTick = nextTicks.get(FrameData.POSITION);
				if(nextTick == tick) remove(FrameData.POSITION);
				else{
					AnimationFrame nextFrame = nextFrames.get(FrameData.POSITION);
					CoordinateObject co = (CoordinateObject) ani;
					Coordinates current = co.getPos();
					Coordinates later = (Coordinates)nextFrame.getData(FrameData.POSITION);
					current.setX(FrameData.interpolateDoubles(current.getX(), later.getX(), nextTick - tick));
					current.setY(FrameData.interpolateDoubles(current.getY(), later.getY(), nextTick - tick));
				}
			}if(nextTicks.containsKey(FrameData.ROTATION)){
				int nextTick = nextTicks.get(FrameData.ROTATION);
				if(nextTick == tick) remove(FrameData.ROTATION);
				else{
					AnimationFrame nextFrame = nextFrames.get(FrameData.ROTATION);
					CoordinateObject ent = (CoordinateObject) ani;
					ent.setRotation(FrameData.interpolateDoubles(ent.getRotation(), 
							(Double)nextFrame.getData(FrameData.ROTATION), nextTick - tick));
				}
			}if(nextTicks.containsKey(FrameData.RUNNABLE)){
				int nextTick = nextTicks.get(FrameData.RUNNABLE);
				if(nextTick == tick){
					AnimationFrame nextFrame = nextFrames.get(FrameData.RUNNABLE);
					((Runnable)nextFrame.getData(FrameData.RUNNABLE)).run();
					remove(FrameData.RUNNABLE);
				}
			}

		}
	}

	private void setNexts(){
		for(FrameData fd : FrameData.values()){
			setNexts(fd);
		}
	}
	
	private void setNexts(FrameData fd){
		Iterator<Entry<Integer, AnimationFrame>> i = frames.entrySet().iterator();
		Entry<Integer, AnimationFrame> soonest = null;
		while(i.hasNext()){
			Entry<Integer, AnimationFrame> e = i.next();
			if(e.getValue().hasData(fd) && e.getKey() > tick && 
					(soonest == null || soonest.getKey() > e.getKey())) soonest = e;
		}if(soonest != null){
			nextFrames.put(fd, soonest.getValue());
			nextTicks.put(fd, soonest.getKey());
		}
	}

	private void remove(FrameData fd){
		nextTicks.remove(fd);
		nextFrames.remove(fd);
		setNexts(fd);
		if(nextFrames.size() == 0) ani.doneAnimating();
	}
	
	public Animation duplicate(){
		return new Animation(frames);
	}

}
