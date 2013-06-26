package com.cal.example.TriangleGame;

import java.util.*;
import jge.behavior.*;
import jge.input.MouseButton;
import jge.world.World;
import jge.world.WorldBehavior;

public class LightUp extends WorldBehavior{

	@Action
	public void tick(World w){
		//Hole closest = Hole.getClosestHoleToPoint(w.getRenderer().getMousePos());
		//closest.mostRecent = true;
		//((GUIText)w.getRenderer().getGUI().getElement("OUT")).setText(" " + Coordinates.make(closest.getPos()).add(10).distance(w.getRenderer().getMousePos()));
	}
	
	@Action(type=ActionType.MOUSE_PRESS, mouse=MouseButton.LEFT)
	public void click(World w){
		Peg closest = Peg.getClosestPegToPoint(w.getRenderer().getMousePos());
		double distance = closest.getPos().distance(w.getRenderer().getMousePos());
		if(distance < 25) closest.pickedUp = true;
	}
	
	@Action(type=ActionType.MOUSE_RELEASE, mouse=MouseButton.LEFT)
	public void release(World w){
		List<Peg> pegs = new ArrayList<Peg>();
		for(Peg p : Peg.pegs){
			pegs.add(p);
		}
		for(Peg p : pegs){
			if(p.pickedUp == true){
				p.pickedUp = false;
				Hole closest = Hole.getClosestHoleToPoint(p.getPos());
				if(closest.getPos().distance(p.getPos()) < 35){
					boolean closestOccupied = false;
					for(Peg peg : Peg.pegs){
						if(peg.currentHole.getPos() == closest.getPos()) closestOccupied = true;
					}if(closestOccupied){
						p.updatePos(p.currentHole.getPos());
					}else{
						System.out.println(closest.getPos());
						ID newHoleID = ID.getIDFromPos(closest.getPos());
						ID currentHoleID = ID.getIDFromPos(p.currentHole.getPos());
						if(!Arrays.asList(currentHoleID.getPossibleJumps()).contains(newHoleID)){
							p.updatePos(p.currentHole.getPos());
						}else{
							ID holeBetweenID = ID.getBetween(currentHoleID, newHoleID);
							boolean between = false;
							Peg toDestroy = null;
							for(Peg peg : Peg.pegs){
								if(peg.getPos().getX() == holeBetweenID.pos.getX() &&
										peg.getPos().getY() == holeBetweenID.pos.getY()){
									between = true;
									toDestroy = peg;
								}
							}if(toDestroy != null) toDestroy.destroy();
							if(between){
								p.updatePos(closest.getPos());
								p.currentHole = closest;
							}else{
								p.updatePos(p.currentHole.getPos());
							}
						}
					}
				}else{
					p.updatePos(p.currentHole.getPos());
				}
			}
		}
	}
	
}
