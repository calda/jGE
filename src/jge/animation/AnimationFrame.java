package jge.animation;

import java.util.HashMap;

public class AnimationFrame{
	
	private HashMap<FrameData, Object> data = new HashMap<FrameData, Object>();
	
	public AnimationFrame set(FrameData fd, Object o){
		if(fd.meetsDataReq(o) || (fd.getDataReq() == Double.class && o instanceof Integer)){
			if((fd.getDataReq() == Double.class && o instanceof Integer)) data.put(fd, ((Integer)o).doubleValue() + 0.0);
			else data.put(fd, o);
		}else throw new IllegalArgumentException(fd.toString() + " can only accept data that is the class or a superclass of " + fd.getDataReq().toString());
		return this;
	}
	
	public void printData(){
		System.out.println(data);
	}
	
	public static AnimationFrame getNew(){
		return new AnimationFrame();
	}
	
	public AnimationFrame addTo(Animation animation, int tick){
		animation.addFrame(tick, this);
		return this;
	}
	
	public Object getData(FrameData fd){
		if(data.containsKey(fd)) return data.get(fd);
		else return null;
	}
	
	public boolean hasData(FrameData fd){
		return data.containsKey(fd);
	}
	
	public String toString(){
		return "AnimationFrame{" + data + "}";
	}
	
	
	
}
