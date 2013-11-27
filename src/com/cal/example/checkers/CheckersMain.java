
package com.cal.example.checkers;

import jge.animation.*;
import jge.behavior.*;
import jge.entity.*;
import jge.input.*;
import jge.render.*;
import jge.world.*;

public class CheckersMain{

	@SuppressWarnings("deprecation")
	public static void main(String[] args){
		RenderGL gl = new RenderGL(815, 640, 60);
		final World w = new World(815, 640);
		w.setActiveCamera(w.addCamera(new Camera()));
		w.setBackgroundColor(Color.GRAY);
		gl.setRenderingWorld(w);
		final Shape teamIden = new Shape(Coordinates.make(725, 320), Coordinates.make(130, 300),
				ShapeType.RECTANGLE, Team.currentTurn.lighter, Priority.LOW);
		w.add(teamIden);
		
		boolean black = false;
		for(int x = 0; x < 8; x++){
			black = !black;
			for(int y = 0; y < 8; y++){
				Spot spot = new Spot(x, y, (black ? Color.BLACK : Color.LIGHT_GRAY));
				Spot.spots[x][y] = spot;
				w.add(spot);
				if(black){
					Piece p = null;
					if(y == 0 || y == 1 || y == 2) p = new Piece(x, y, Team.RED);
					if(y == 5 || y == 6 || y == 7) p = new Piece(x, y, Team.BLUE);
					if(p != null){
						p.addBehavior(new PieceBehave());
						w.add(p);
						p.setPriority(Priority.LOW);
						Spot.spots[x][y].filled = true;
					}
				}
				black = !black;
			}
		}
		
		final Dummy d = new Dummy();
		d.addBehavior(new Behavior("dummy"){
			@Action(type=ActionType.MOUSE_DOWN, mouse=MouseButton.LEFT)
			public void onPickup(Behaving b){
				if(Team.redCap == 12 || Team.blueCap == 12) return;
				Coordinates mouse = MouseHandler.getPos();
				double shortest = 99999;
				Piece closest = null;
				for(Piece p : Piece.pieces){
					double distance = mouse.distance(p.getPos());
					if(distance < shortest){
						shortest = distance;
						closest = p;
					}
				}if(closest == null) return;
				if(closest.getScale() == 0.3) return;
				if(closest.team != Team.currentTurn) return;
				if(shortest <= 40){
					closest.attached = true;
					closest.setPriority(Priority.HIGHEST);
					closest.origPos = closest.getPos();
				}
			}
			
			@Action(type=ActionType.MOUSE_RELEASE, mouse=MouseButton.LEFT)
			public void onDrop(Behaving b){
				if(Team.redCap == 12 || Team.blueCap == 12) return;
				Piece p = null;
				for(Piece pe : Piece.pieces){
					if(p == null && pe.attached) p = pe;
				}if(p == null) return;
				p.attached = false;
				double shortest = 99999;
				Spot closest = null;
				for(Spot[] ss : Spot.spots){
					for(Spot s : ss){
						if(s.getColor() == Color.BLACK){
							double distance = s.getPos().distance(p.getPos());
							if(distance < shortest){
								shortest = distance;
								closest = s;
							}
						}
					}
				}if(shortest <= 50 && p.canMoveTo(closest)){
					if(p.setSlot(closest.idx, closest.idy)){
						Animation a = new Animation();
						AnimationFrame.getNew().set(FrameData.RUNNABLE, new Runnable(){
							public void run(){
								finishTurn(w, teamIden);
							}
						}).addTo(a, 61);
						d.animate(a);
					}else finishTurn(w, teamIden);
				}else if(shortest <= 50 && p.canJumpTo(closest)){
					Spot prev = Spot.spots[p.idx][p.idy];
					Spot between = Spot.spots[(prev.idx + closest.idx)/2][(prev.idy + closest.idy)/2];
					if(between.filled){
						for(Piece pp : Piece.pieces){
							if(pp.team != p.team && pp.idx == between.idx && pp.idy == between.idy){
								pp.getSpot().filled = false;
								pp.idx = -1;
								pp.idy = -1;
								Animation a = new Animation();
								AnimationFrame.getNew().set(FrameData.POSITION, Team.getDeadPieceLocation(pp.team, w))
								.set(FrameData.SCALE, 0.3).addTo(a, (p.setSlot(closest.idx, closest.idy) ? 60 : 40));
								AnimationFrame.getNew().set(FrameData.RUNNABLE, new Runnable(){
									public void run(){
										finishTurn(w, teamIden);
									}
								}).addTo(a, 41);
								pp.animate(a);
							}else if(pp.team == p.team && pp.idx == between.idx && pp.idy == between.idy){
								Animation a = new Animation();
								AnimationFrame.getNew().set(FrameData.POSITION, p.origPos).addTo(a, 20);
								p.animate(a);
							}
						}
					}else{
						Animation a = new Animation();
						AnimationFrame.getNew().set(FrameData.POSITION, p.origPos).addTo(a, 20);
						p.animate(a);
					}
				}else{
					Animation a = new Animation();
					AnimationFrame.getNew().set(FrameData.POSITION, p.origPos).set(FrameData.SCALE, 1.0).addTo(a, 20);
					AnimationFrame.getNew().set(FrameData.SCALE, 1.2).addTo(a, 10);
					p.animate(a);
				}p.setPriority(Priority.LOW);
			}
		});
		w.add(d);
		w.getTickManager().startNewTickThread();
		gl.startRendering();
	}

	public static void finishTurn(World w, Shape teamIden){
		if(Team.blueCap == 12){
			victory(w, Team.RED);
			return;
		}else if(Team.redCap == 12){
			victory(w, Team.BLUE);
			return;
		}final Animation rotateBlue = new Animation();
		AnimationFrame.getNew().set(FrameData.ROTATION, w.getActiveCamera().getRotation()).addTo(rotateBlue, 10);
		AnimationFrame.getNew().set(FrameData.ROTATION, w.getActiveCamera().getRotation() + 180).addTo(rotateBlue, 40);
		w.getActiveCamera().animate(rotateBlue);
		Team.currentTurn = Team.currentTurn.getOpposite();
		Animation transformColor = new Animation();
		AnimationFrame.getNew().set(FrameData.COLOR, Team.currentTurn.getOpposite().lighter).addTo(transformColor, 10);
		AnimationFrame.getNew().set(FrameData.COLOR, Team.currentTurn.lighter).addTo(transformColor, 40);
		teamIden.animate(transformColor);
	}
	
	public static void victory(final World w, final Team winner){
		Animation win = new Animation();
		final Animation piece = new Animation();
		AnimationFrame.getNew().set(FrameData.SCALE, 3.0).addTo(piece, 10);
		AnimationFrame.getNew().set(FrameData.SCALE, 1.0).addTo(piece, 60);
		int nextTick = 0;
		for(Spot[] spots : Spot.spots){
			for(final Spot s : spots){
				nextTick += 10;
				AnimationFrame.getNew().set(FrameData.RUNNABLE, new Runnable(){
					public void run(){
						if(!s.filled){
							Piece p = new Piece(s.idx, s.idy, winner);
							w.add(p);
							p.animate(piece);
							p.kinged = true;
						}else{
							for(Piece p : Piece.pieces){
								if(p.idx == s.idx && p.idy == s.idy){
									p.kinged = true;
									p.animate(piece);
								}
							}
						}
					}
				}).addTo(win, nextTick);
			}
		}w.getActiveCamera().animate(win);
	}

}