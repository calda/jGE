package jge.world;

import java.util.Timer;
import java.util.TimerTask;

public class TickManager{

	private final World owning;
	final private Timer timer = new Timer();
	
	public TickManager(World owning){
		this.owning = owning;
	}
	
	public World getOwningWorld(){
		return owning;
	}
	
	public void startNewTickThread(final float ticksPerSecond){
		System.out.println((long)(1.0f/ticksPerSecond * 1000f));
		timer.scheduleAtFixedRate(new TimerTask(){
			@Override
			public void run(){
				//System.out.println("tick");
				owning.tickAllBehaviors();
			}
		}, 1, (long)(1.0f/ticksPerSecond * 1000f));
	}
	
	public void stopTickThread(){
		timer.cancel();
	}
	
	public void startNewTickThread(){
		startNewTickThread(60);
	}
	
}
