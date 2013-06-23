package jge.entity;

import java.util.*;
import java.util.Map.Entry;
import jge.behavior.*;
import jge.group.*;
import jge.render.*;
import jge.world.*;

public class GroupEntity extends Entity{

	final Group<Entity> group;
	
	
	public GroupEntity(World w, Coordinates pos){
		super(pos, Coordinates.make(0,0), "");
		group = new Group<Entity>(w);
	}
	
	public GroupEntity(World w, Coordinates pos, Entity...ents){
		super(pos, Coordinates.make(0,0), "");
		group = new Group<Entity>(w, ents);
	}
	
	public void add(Entity ent){
		group.add(ent);
	}
	
	public void remove(Entity ent){
		group.remove(ent);
	}
	
	public Group<Entity> getGroupObject(){
		return group;
	}
	
	private Group<Entity> groupAtLastRender;
	@SuppressWarnings("deprecation")
	@Override
	public void render(GraphicsWrapper g){
		group.rendering = true;
		if(group.modifying) renderWith(g, groupAtLastRender);
		else{
			renderWith(g, group);
			groupAtLastRender = new Group<Entity>(group.getWorld(), group.getObjects().toArray(new Entity[0]));
		}group.rendering = false;
	}
	
	@SuppressWarnings("deprecation")
	protected void renderWith(GraphicsWrapper g, Group<Entity> group){
		List<Renderable> render = new ArrayList<Renderable>();
		for(Entity object : group.getObjects()){
			if(object instanceof Renderable) render.add((Renderable)object);
		}Collections.sort(render, new RenderPriorityComparator());
		for(Entity ent : group.getObjects()){
			Coordinates relative = ent.getPos();
			Coordinates renderAt = relative.clone().add(super.getPos()); 
			ent.setPos(renderAt);
			ent.render(g);
			ent.setPos(relative);
		}
	}

	@Override
	@SuppressWarnings("deprecation")
	public void actionRelevantBehaviors(ActionType type, Object additional){
		Iterator<Entry<String, Behavior>> i = behaviors.entrySet().iterator();
		while(i.hasNext()){
			i.next().getValue().action(type, additional, this);
		}group.ticking = true;
		for(Entity ent : group.getObjects()){
			ent.actionRelevantBehaviors(type, additional);
		}group.ticking = false;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public String toString(){
		return "Group at " + getPos() + " with " + behaviors.size() + " behavior(s) and priority of " + getPriority() + " containing " + group.getObjects().size() + " entities";
	}
	
}
