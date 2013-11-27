package jge.animation;

public interface Animatable{

	public void animate(Animation a);
	public void doneAnimating();
	public boolean isAnimating();
	public Animation getActiveAnimation();
	public void tickAnimation();
	
}
