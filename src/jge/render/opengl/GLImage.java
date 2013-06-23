package jge.render.opengl;

import java.awt.*;
import java.awt.image.*;
import java.io.IOException;
import jge.util.*;

public class GLImage extends Image{

	private BufferedImage src;
	private Texture gltex;
	
	public GLImage(String filepath){
		try{
			gltex = GLTextureLoader.get.getTexture(filepath);
			src = GLTextureLoader.get.loadImage(filepath);
		} catch(IOException exe){
			exe.printStackTrace();
		}
	}
	
	public BufferedImage getSourceImage(){
		return src;
	}
	
	public Texture getGLTexture(){
		return gltex;
	}
	
	public int getHeight(){
		return src.getHeight();
	}
	
	public int getWidth(){
		return src.getWidth();
	}
	
	public BufferedImage getBufferedImage(){
		return src;
	}
	
	@Override
	public Graphics getGraphics(){
		return src.getGraphics();
	}

	@Override
	public int getHeight(ImageObserver arg0){
		return src.getHeight(arg0);
	}

	@Override
	public Object getProperty(String arg0, ImageObserver arg1){
		return src.getProperty(arg0, arg1);
	}

	@Override
	public ImageProducer getSource(){
		return src.getSource();
	}

	@Override
	public int getWidth(ImageObserver arg0){
		return src.getWidth(arg0);
	}

}
