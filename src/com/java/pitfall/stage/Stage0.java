package com.java.pitfall.stage;

import java.awt.Graphics;

import javax.swing.JFrame;

import com.java.pitfall.constants.Constants;

public class Stage0 extends JFrame implements  Constants {
	
	public Stage0(){
		
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.fillRect(400, 200, 200, 200);
	}
}