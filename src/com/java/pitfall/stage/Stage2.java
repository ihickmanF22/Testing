package com.java.pitfall.stage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.java.pitfall.constants.Constants;

import com.java.pitfall.environment.Character;
public class Stage2 extends JPanel implements Constants, Runnable{
	private Image water;
	private URL resource;
	private URL resource2;
	private Image charCorda;
	//private int RectX;
	//private int RectY;
	private int x;
	private int y;
	static boolean isChar;
	private double angle;
	private int length;
	private Thread corda;
	int contador;
	public Stage2(){
		contador = 0;
		this.length = 250;
		this.angle  = Math.PI / 3;

		corda = new Thread(this);
		corda.start();
		setDoubleBuffered(true);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		resource = getClass().getResource("../environment/utils/agua.png");

		try {
			water = ImageIO.read(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		g.drawImage(water, 250, 480,480,200, this);
		//g.fillRect(340, 520, 340, 50);
		//DESENHO DA CORDA!
		g.setColor(Color.black);
		int anchorX = 500, anchorY = 150;  //Define arco 
		int RectX = anchorX + (int) (Math.sin(angle) * length);  //Pega a posição do x
		int RectY= anchorY + (int) (Math.cos(angle) * length); //Pega a posição do y
		g.drawLine(anchorX, anchorY, RectX, RectY);
		g.setColor(new Color(12,74,28));
		g.fillOval(anchorX - 3, anchorY - 4, 7, 7);
		g.setColor(Color.black);
		g.fillRect(RectX - 7, RectY - 7, 14, 14);
		setDim(RectX - 7, RectY - 7);
		//Movimentação na corda!
		if(isChar && Character.getRigth()){
			contador++;
			resource2 = getClass().getResource("../environment/utils/corda.png");
			try {
				charCorda = ImageIO.read(resource2);
			} catch (IOException e) {
				e.printStackTrace();
			}
			g.drawImage(charCorda, RectX - 130, RectY - 70, 256, 300, this);
		
			if(RectX == 716)
				setChar(false);
		}else if(isChar && Character.getLeft()){
			contador++;
			resource2 = getClass().getResource("../environment/utils/cordaLeft.png");
			try {
				charCorda = ImageIO.read(resource2);
			} catch (IOException e) {
				e.printStackTrace();
			}
			g.drawImage(charCorda, RectX - 130, RectY - 70, 256, 300, this);
			
			if(RectX < 300)
				setChar(false);
		}
	}

	public void run(){	//Thread para rodar a corda 
		double angleAccel, angleVelocity = 0, dt = 0.1;
		while (true) {
			angleAccel = -9.81 / length * Math.sin(angle);
			angleVelocity += angleAccel * dt;
			angle += (angleVelocity * dt);
			repaint();
			try {
				Thread.sleep(15);
			} catch (InterruptedException ex) {
			}
		}

	}

	public Dimension getPreferredSize() {
		return new Dimension(2 * length + 50, length / 2 * 3);
	}
	
	public void setChar(boolean isChar){
		Stage2.isChar = isChar;
	}
	
	public boolean getChar(){
		return Stage2.isChar;
	}

	public void setDim(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int getX(){
		return this.x;
	}
	public Rectangle cordaBounds(){
		return (new Rectangle(this.x - 7, this.y - 7, 25, 14));
	}
	
	public Rectangle waterBounds(){
		return(new Rectangle(340, 520, 340, 50));
	}
	
	public Rectangle charCordaBounds(){
		if(isChar && Character.getRigth()){
			return (new Rectangle(this.x, this.y, 256, 300));
		}else if(isChar && Character.getLeft()){
			return (new Rectangle(this.x, this.y, 256, 300));
		}
		
		return (new Rectangle(0,0,0,0));
	}

}