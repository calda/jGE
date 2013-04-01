package com.cal;

import java.awt.Color;
import jge.render.Render2D;
import jge.render.Screen;

public class test{

	public test(){}

	public static void main(String[] args){
		Screen s = new Screen();
		Render2D r2d = s.setWindowed("Woo a window.");
		r2d.setResizable(false);
		r2d.setBackground(Color.RED);
	}
	
}
