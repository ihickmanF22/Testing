package com.java.pitfall.stage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import com.java.pitfall.constants.Constants;

public class Stage3 extends JFrame implements Constants {
	private Image water;
	private URL resource;
	private int widthFall;
	private boolean rigth;
	private int contFall;
	public Stage3(){
		this.widthFall = 0;
		this.rigth = true;
	}
	
	
	@Override
	public void paint(Graphics g) {
		
		super.paint(g);
		resource = getClass().getResource("../environment/utils/fall.png");

		try {
			water = ImageIO.read(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		g.setColor(Color.RED);
		
		g.fillRect(700, 200,this.widthFall,200);
		g.drawImage(water, 700, 480,this.widthFall,200, this);
		
		
	}
	
	public void moveFall(){
		contFall++;
		if(rigth && contFall > 15){
			System.out.println(contFall);
			this.widthFall -= 30;
			
			if(contFall > 30){	
				rigth = false;
				contFall = 0;
			}
				
		}else if(!rigth && contFall > 15){
			contFall++;
			this.widthFall += 30;
			if(contFall > 48){
				this.widthFall = 0;
				rigth = true;
				contFall = 0;
			}
		}
	}
	
	public int Width(){
		return this.widthFall;
	}
	
	public Rectangle fallBounds(){
		System.out.println(this.widthFall- 700);
		return (new Rectangle(700 + widthFall, 480,this.widthFall*(-1),200));
	}
}