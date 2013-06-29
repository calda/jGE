package jge.render;

import java.awt.*;
import java.io.*;

import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.ResourceLoader;

public class GLFont{
	
	int size;
	Font awtFont;
	TrueTypeFont ttfont;
	
	public GLFont(String font, FontStyle style, int size){
		if(!font.contains(".")){
			awtFont = new Font(font, style.getStyleCode(), size);
			ttfont = new TrueTypeFont(awtFont, false);
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
					throw new IllegalArgumentException("Fonts can only be of the .ttf type.");
				}catch(IOException exe){
					throw new IllegalArgumentException("Font \"" + font + "\"does not exists in jar or in folder system.");
				}
			}setSize(size);
		}
	}
	
	public void setSize(int size){
		this.size = size;
		awtFont = awtFont.deriveFont(size);
		ttfont = new TrueTypeFont(awtFont, false);
	}
	
	public int getSize(){
		return size;
	}
	
	public Font getAWTFont(){
		return awtFont;
	}
	
	public TrueTypeFont getTTFont(){
		return ttfont;
	}
	
}
