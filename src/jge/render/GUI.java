package jge.render;

import java.awt.Graphics2D;
import java.util.*;
import java.util.Map.Entry;

public class GUI implements Renderable{

	private final HashMap<String, GUIElement> elements = new HashMap<String, GUIElement>();
	private final Render2D render;
	
	
	public GUI(Render2D render){
		this.render = render;
	}
	
	public void addGUIElement(GUIElement elem){
		if(elements.containsKey(elem.getName())) throw new IllegalArgumentException("A GUI Element with that name already exists");
		elements.put(elem.getName(), elem);
	}
	
	public GUIElement removeGUIElement(String name){
		GUIElement rem = elements.get(name);
		if(rem == null) throw new IllegalArgumentException("A GUI Element with that name doesn't exist");
		elements.remove(name);
		return rem;
	}
	
	public void clearGUIElements(){
		elements.clear();
	}
	
	public Render2D getRenderer(){
		return render;
	}
	
	public GUIElement getElement(String name){
		GUIElement get = elements.get(name);
		if(get == null) throw new IllegalArgumentException("A GUI Element with that name doesn't exist");
		return get;
	}
	
	@Override
	public void render(Graphics2D g){
		Iterator<Entry<String, GUIElement>> i = elements.entrySet().iterator();
		while(i.hasNext()){
			i.next().getValue().render(g);
		}
	}
	
}
