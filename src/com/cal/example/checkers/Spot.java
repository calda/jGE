package com.cal.example.checkers;

import jge.entity.Shape;
import jge.render.*;
import jge.world.Coordinates;

public class Spot extends Shape{

	public static Spot[][] spots = new Spot[8][8];
	
	public final int idx;
	public final int idy;
	public boolean filled = false;
	
	public Spot(int x, int y, Color color){
		super(Coordinates.make((x * 80) + 40, (y * 80) + 40), Coordinates.make(80, 80), ShapeType.RECTANGLE, color, Priority.LOWEST);
		idx = x;
		idy = y;
	}
	
	@Override
	public String toString(){
		return super.toString().replaceAll("Shape", "Spot");
	}
	
}
