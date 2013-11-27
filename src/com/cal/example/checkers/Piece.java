package com.cal.example.checkers;

import java.util.*;
import jge.animation.*;
import jge.entity.Shape;
import jge.render.*;
import jge.world.Coordinates;

public class Piece extends Shape{

	public static List<Piece> pieces = new ArrayList<Piece>();
	public Team team;
	public boolean attached = false;
	public Coordinates origPos = null;
	public boolean kinged = false;

	public int idx;
	public int idy;

	public static final Animation kingMe = new Animation();
	static{
		AnimationFrame.getNew().set(FrameData.SCALE, 3.0).addTo(kingMe, 10);
		AnimationFrame.getNew().set(FrameData.SCALE, 1.0).addTo(kingMe, 60);
	}
	
	public Piece(int x, int y, Team t){
		super(Coordinates.make((x * 80) + 40, (y * 80) + 40), Coordinates.make(50, 50), ShapeType.OVAL, t.getColor(), Priority.NORMAL);
		pieces.add(this);
		idx = x;
		idy = y;
		team = t;
	}

	public Spot getSpot(){
		return Spot.spots[idx][idy];
	}

	@Override
	public void render(GraphicsWrapper g){
		super.render(g);
		Priority prev = getPriority();
		setPriority(Priority.HIGHEST);
		setColor(team.lighter);
		setScale(getScale() * 0.8);
		super.render(g);
		setPriority(prev);
		setColor(team.getColor());
		setScale(getScale() / 0.8);
		if(kinged){
			setRotation(getRotation()+1);
			g.drawLine(getPos().getX() + (15 * getScale()), getPos().getY() + (15 * getScale()), 
					getPos().getX() - (15 * getScale()), getPos().getY() - (15 * getScale()), 4.0 * getScale(), team.getColor(), getRotation());
			g.drawLine(getPos().getX() - (15 * getScale()), getPos().getY() + (15 * getScale()),
					getPos().getX() + (15 * getScale()), getPos().getY() - (15 * getScale()), 4.0 * getScale(), team.getColor(), getRotation());
		}
	}

	public boolean setSlot(int x, int y){
		super.setPos(Coordinates.make((x * 80) + 40, (y * 80) + 40));
		Spot.spots[idx][idy].filled = false;
		idx = x; idy = y;
		Spot.spots[idx][idy].filled = true;
		if(!kinged && idy == 0 && team == Team.BLUE){
			kinged = true;
			animate(kingMe);
			return true;
		}
		if(!kinged && idy == 7 && team == Team.RED){
			kinged = true;
			animate(kingMe);
			return true;
		}return false;
	}

	public boolean canMoveTo(Spot to){
		if(to.filled) return false;
		int distancex = Math.abs(to.idx - idx);
		int distancey = Math.abs(to.idy - idy);
		boolean up = to.idy - idy > 0;
		if(distancex + distancey == 2 && distancex != 2 && distancey != 2){
			if(kinged) return true;
			if(up && team == Team.BLUE) return false;
			if(!up && team == Team.RED) return false;
			return true;
		}return false;
	}

	public boolean canMoveTo(int x, int y){
		return canMoveTo(Spot.spots[x][y]);
	}

	public boolean canJumpTo(Spot to){
		if(to.filled) return false;
		int distancex = Math.abs(to.idx - idx);
		int distancey = Math.abs(to.idy - idy);
		boolean up = to.idy - idy > 0;
		if(distancex + distancey == 4 && distancex != 4 && distancey != 4){
			if(kinged) return true;
			if(up && team == Team.BLUE) return false;
			if(!up && team == Team.RED) return false;
			return true;
		}return false;
	}
}
