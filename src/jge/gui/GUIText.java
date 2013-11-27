package jge.gui;

import java.awt.*;
import jge.render.GraphicsWrapper;
import jge.world.Coordinates;

@SuppressWarnings("unused")
public class GUIText extends GUIElement{

	private String text;
	private Font font = Font.getFont("Arial");
	private Color color;
	private boolean render;
	
	public GUIText(String name, String text, Coordinates pos, Color c){
		super(name, pos);
		this.text = text;
		this.color = c;
	}
	
	public void setFont(String newFontName, int point){
		Font newFont = new Font(newFontName, point, point);
		if(newFont == null || !newFont.canDisplay('a')) throw new IllegalArgumentException("That font is invalid or unavaliable");
		font = newFont;
	}
	
	public void setText(String text){
		this.text = text;
	}
	
	public String getText(){
		return text;
	}

	public void render(boolean render){
		this.render = render;
	}
	
	@Override
	public boolean renderObject(){
		return render;
	}
	
	@Deprecated
	public void render(GraphicsWrapper g){
		//g.drawText(text, null, color, getPos(), 0.0);
	}
	
	
}
