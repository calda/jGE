package com.cal.example.checkers;

import jge.animation.*;
import jge.entity.Shape;
import jge.render.*;
import jge.world.*;

public enum Team{

	RED(Color.RED, new Color("FF5252")),
	BLUE(Color.CYAN, new Color("B9FFFF"));
	
	private final Color c;
	public final Color lighter;
	
	private Team(Color c, Color l){
		this.c = c;
		lighter = l;
	}
	
	public Color getColor(){
		return c;
	}
	
	public Team getOpposite(){
		if(this == RED) return BLUE;
		else return RED;
	}
	
	protected static int blueCap = 0;
	protected static int redCap = 0;
	
	public static Coordinates getDeadPieceLocation(Team t, World w){
		if(t == Team.RED){
			int x = 675 + (25 * (redCap % 5));
			int y = 600 - (25 * ((redCap / 5)));
			redCap+=1;
			if(redCap == 1){
				Shape s = new Shape(Coordinates.make(725, 575), Coordinates.make(130, 80), ShapeType.RECTANGLE, w.getBackgroundColor(), Priority.LOWEST);
				w.add(s);
				Animation a = new Animation();
				AnimationFrame.getNew().set(FrameData.COLOR, Color.BLACK).addTo(a, 60);
				s.animate(a);
			}
			return Coordinates.make(x, y);
		}else{
			int x = 675 + (25 * (blueCap % 5));
			int y = 40 + (25 * ((blueCap / 5) ));
			blueCap+=1;
			if(blueCap == 1){
				Shape s = new Shape(Coordinates.make(725, 65), Coordinates.make(130, 80), ShapeType.RECTANGLE, w.getBackgroundColor(), Priority.LOWEST);
				w.add(s);
				Animation a = new Animation();
				AnimationFrame.getNew().set(FrameData.COLOR, Color.BLACK).addTo(a, 60);
				s.animate(a);
			}
			return Coordinates.make(x, y);
		}
	}
	
	public static Team currentTurn = Team.RED;
	
}
