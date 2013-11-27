package jge.gui;

import java.util.*;
import java.util.Map.Entry;
import jge.render.*;

public class GUI implements Renderable{

	private final HashMap<String, GUIElement> elements = new HashMap<String, GUIElement>();
	private final RenderGL renderer;
	private boolean render;
	
	public GUI(RenderGL renderer){
		this.renderer = renderer;
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
	
	public RenderGL getRenderer(){
		return renderer;
	}
	
	public GUIElement getElement(String name){
		GUIElement get = elements.get(name);
		if(get == null) throw new IllegalArgumentException("A GUI Element with that name doesn't exist");
		return get;
	}
	
	public void render(boolean render){
		this.render = render;
	}
	
	@Override
	public boolean renderObject(){
		return render;
	}
	
	@Override
	public void render(GraphicsWrapper g){
		Iterator<Entry<String, GUIElement>> i = elements.entrySet().iterator();
		while(i.hasNext()){
			i.next().getValue().render(g);
		}
	}
	
}
