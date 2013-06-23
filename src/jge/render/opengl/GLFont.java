package jge.render.opengl;

import java.awt.*;
import java.io.*;
import jge.render.FontStyle;
import org.newdawn.slick.util.ResourceLoader;

public class GLFont extends Font{
	
	float size;
	
	public GLFont(String font, FontStyle style, int size){
		if(font.contains(".")){
			awtFont = new Font(font, style.getStyleCode(), size);
		}else{
			try{
				InputStream stream = ResourceLoader.getResourceAsStream(font);
				awtFont = Font.createFont(Font.TRUETYPE_FONT, stream);
			}catch(Exception e){
				try{
					awtFont = Font.createFont(Font.TRUETYPE_FONT, new File(font));
				}catch(FileNotFoundException exe){
					throw new IllegalArgumentException("Font \"" + font + "\"does not exists in jar or in folder system.");
				}catch(FontFormatException exe){
					throw new IllegalArgumentException("Fonts can only be of the .ttf type. Create an AWT font and use the GLFont(awtFont) constructor if needed otherwise");
				}catch(IOException exe){
					throw new IllegalArgumentException("Font \"" + font + "\"does not exists in jar or in folder system.");
				}
			}
		}
	}
	
	public GLFont(Font awtFont){
		this.awtFont = awtFont;
	}
	
	public void setSize(float size){
		this.size = size;
		awtFont = awtFont.deriveFont(size);
	}
	
	
}
