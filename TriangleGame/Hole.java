package com.cal;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import jge.entity.Entity;
import jge.world.Coordinates;

public class Hole extends Entity{

	public Hole(Coordinates pos){
		super(pos, "");
		holes.add(this);
	}
	
	public boolean mostRecent = false;
	
	@Override
	public void render(Graphics2D g){
		if(mostRecent) g.setColor(Color.GREEN);
		else g.setColor(Color.BLACK);
		g.fillOval((int)getPos().getX(), (int)getPos().getY(), 20, 20);
	}

	public static List<Hole> holes = new ArrayList<Hole>();
	
	public static Hole getClosestHoleToPoint(Coordinates c){
		double distance = 9999999;
		Hole hole = null;
		for(Hole h : holes){
			if(Coordinates.make(h.getPos()).add(10).distance(c) < distance){
				distance = Coordinates.make(h.getPos()).add(10).distance(c);
				hole = h;
			}
		}return hole;
	}
	
}
