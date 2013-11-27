package jge.world;

import jge.animation.*;
import jge.group.*;

public class CoordinateObject implements Groupable, Animatable{
	
	private Coordinates pos;
	private World owningWorld;
	private Group<Groupable> group;
	private double rotation = 0;
	
	public CoordinateObject(double x, double y){
		pos = new Coordinates(x, y);
	}
	
	public CoordinateObject(Coordinates pos){
		this.pos = pos;
	}
	
	public Coordinates getPos(){
		return pos;
	}
	
	public void setPos(Coordinates c){
		pos = c;
	}
	
	public void setPos(double x, double y){
		setPos(Coordinates.make(x, y));
	}
	
	public void updatePos(Coordinates c){
		setPos(c);
	}
	
	public void updatePos(double x, double y){
		setPos(x, y);
	}
	
	public void setWorld(World w){
		this.owningWorld = w;
	}
	
	public World getOwningWorld(){
		return owningWorld;
	}

	@Override
	public void setGroup(Group<Groupable> group){
		this.group = group;
	}

	@Override
	public Group<Groupable> getGroup(){
		return group;
	}
	
	public void setRotation(double rot){
		this.rotation = rot;
	}
	
	public double getRotation(){
		return rotation;
	}
	
	public void rotate(double rot){
		setRotation(rotation + rot);
	}
	
	protected boolean animating = false;
	protected Animation animation = null;
	
	@Override
	public void animate(Animation a){
		if(a.canAnimate(this)){
			animation = a.duplicate();
			animation.startOn(this);
		}else throw new IllegalArgumentException(this.getClass().toString() + " cannot run this animation");
		animating = true;
	}
	
	@Deprecated
	public void doneAnimating(){
		animating = false;
		animation = null;
	}

	@Override
	public boolean isAnimating(){
		return animating;
	}

	@Override
	public Animation getActiveAnimation(){
		return animation;
	}
	
	public void tickAnimation(){
		animation.tick();
	}
	
}
