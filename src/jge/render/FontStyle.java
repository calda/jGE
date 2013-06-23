package jge.render;

import java.awt.Font;

public enum FontStyle{

	PLAIN(Font.PLAIN),
	ITALIC(Font.ITALIC),
	BOLD(Font.BOLD);
	
	private final int style;
	
	private FontStyle(int style){
		this.style = style;
	}
	
	public int getStyleCode(){
		return style;
	}
	
}
