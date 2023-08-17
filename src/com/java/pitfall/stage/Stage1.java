package com.java.pitfall.stage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JFrame;

import com.java.pitfall.constants.Constants;

public class Stage1 extends JFrame implements Constants {
	private int barrilX;
	private int barrilY;
	private int contY;
	public Stage1() {
		this.contY = 0;
		this.barrilX = 800;
		this.barrilY = 500;
	}
	
	@Override
	public void paint(Graphics g) {
		//radio = 0;
		super.paint(g);
		Graphics2D gg = (Graphics2D) g;
		gg.setColor(new Color(19, 19, 19));
		gg.fillRect(400, 500, 80, 50); //Buraco
		gg.fillRect(400, 620, 80, 100);
		gg.setColor(new Color(115,77,38));
		gg.fillOval(barrilX, barrilY,60, 60);
		
		gg.setColor(new Color(115,77,38));
		gg.fillOval(barrilX + 250,  barrilY,60, 60);
		gg.setColor(Color.white);
		
		gg.setColor(Color.black);
		//gg.rotate(radio);
		
		barrilMove();
		
		
		
	}
	
	public void barrilMove(){
		this.barrilX -= 20;
		
		if(contY >= 4){
			this.barrilY -= 3;
			contY++;
			if(contY >= 8)
				contY = 0;
		}else
			this.barrilY += 3;
		contY++;
		
		if(this.barrilX < -200)
			this.barrilX = 1040;
	}
	
	
	
	
	
	public Rectangle buraco(){
		return (new Rectangle(420, 530, 40, 20));
	}
	
	public Rectangle barril(){
		return (new Rectangle(barrilX + 250,  barrilY,60, 60));
	}
	
	public Rectangle barril2(){
		return (new Rectangle(barrilX, barrilY, 60,60));
	}
	

	
}