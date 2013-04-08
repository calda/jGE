package jge.render;

import java.awt.Font;
import java.awt.Graphics2D;
import jge.world.Coordinates;

public class GUIText extends GUIElement{

	private String text;
	private Font font = Font.getFont("Arial");
	
	public GUIText(String name, Coordinates pos, String text){
		super(name, pos);
		this.text = text;
	}
	
	public void setFont(String newFontName){
		Font newFont = Font.getFont(newFontName);
		if(!newFont.canDisplay('a')) throw new IllegalArgumentException("That font is invalid or unavaliable");
		font = newFont;
	}
	
	public void setText(String text){
		this.text = text;
	}
	
	public String getText(){
		return text;
	}

	@Override
	public void render(Graphics2D g){
		g.setFont(font);
		g.drawString(text, (int)getPos().getX(), (int)getPos().getY());
	}
	
	
}
