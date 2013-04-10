package com.cal;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import jge.behavior.*;
import jge.entity.Entity;
import jge.world.Coordinates;

public class Peg extends Entity{

	public static List<Peg> pegs = new ArrayList<Peg>();
	private final Color basecolor = Peg.getRandomColor();
	private Color color = basecolor;
	public boolean pickedUp = false;
	public int size = 25;
	public Hole currentHole;
	
	public Peg(Coordinates pos){
		super(pos, "");
		currentHole = Hole.getClosestHoleToPoint(pos);
		pegs.add(this);
		this.addBehavior(new Behavior("pickUp"){
			@Action
			public void onTick(Behaving b){
				Peg p = (Peg) b;
				if(p.getPos().equals((Peg.getClosestPegToPoint(getOwningWorld().getRenderer().getMousePos()).getPos())) &&
						getOwningWorld().getRenderer().getMousePos().distance(p.getPos()) < 25){
					color = Color.WHITE;
				}else{
					color = basecolor;
				}
				if(p.pickedUp) p.updatePos(p.getOwningWorld().getRenderer().getMousePos().subtract(6));
			}
			@Action(type=ActionType.END)
			public void onDestroy(Behaving b){
				Peg p = (Peg) b;
				Peg.pegs.remove(p);
			}
		});
	}
	
	public void pickUp(){
		pickedUp = true;
	}
	
	@Override
	public void render(Graphics2D g){
		g.setColor(color);
		g.fillOval((int)getPos().getX() - 6, (int)getPos().getY() - 6, size, size);
	}
	
	public static Peg getClosestPegToPoint(Coordinates c){
		double distance = 9999999;
		Peg peg = null;
		for(Peg p : pegs){
			if(Coordinates.make(p.getPos()).add(10).distance(c) < distance){
				distance = Coordinates.make(p.getPos()).add(10).distance(c);
				peg = p;
			}
		}return peg;
	}
	
	private static Color[] colorful = {
		Color.BLUE, Color.CYAN, Color.GREEN,
		Color.MAGENTA, Color.RED
	};
	public static Color getRandomColor(){
		return colorful[(new Random()).nextInt(colorful.length)];
	}
	
}
