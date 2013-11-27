package com.cal.example.checkers;

import jge.behavior.*;
import jge.input.MouseHandler;

public class PieceBehave extends Behavior{

	public PieceBehave(){
		super("Piece Behaviors");
	}
	
	@Action
	public void mouse(Behaving b){
		Piece p = (Piece) b;
		if(p.getScale() == 1.0 && MouseHandler.getPos().distance(p.getPos()) < 40 && p.team == Team.currentTurn) p.setScale(1.2);
		else if(p.getScale() == 1.2 && MouseHandler.getPos().distance(p.getPos()) > 40) p.setScale(1.0);
		if(p.attached) p.setPos(MouseHandler.getPos());
	}
	

}
