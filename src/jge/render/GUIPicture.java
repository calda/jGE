package jge.render;

import java.awt.Graphics2D;
import jge.world.Coordinates;

@Deprecated
public class GUIPicture extends GUIElement{

	public GUIPicture(String name, Coordinates screenPos){
		super(name, screenPos);
	}

	@Override
	public void render(Graphics2D g){}

}
