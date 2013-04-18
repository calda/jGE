package com.cal.example.TriangleGame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import jge.entity.*;
import jge.render.GraphicsWrapper;
import jge.world.Coordinates;

public class Hole extends Entity{

	public Hole(Coordinates pos){
		super(pos, Coordinates.make(0,0), "");
		holes.add(this);
	}
	
	public boolean mostRecent = false;
	
	@Override
	public void render(GraphicsWrapper g){
		g.drawOval(Color.BLACK, getPos(), Coordinates.make(20, 20));
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
