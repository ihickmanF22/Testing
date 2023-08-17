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
import com.java.pitfall.main.Screen;

public class Stage4 extends JFrame implements Constants{
	private Image gold;
	private URL resource;
	public static int goldY;
	private boolean finalGame;
	private int rochaX;
	private int rochaY;
	private int contY;
	private int contFase;
	private boolean start;
	public static boolean move;
	public Stage4(){
		Stage4.move = false;
		this.start = false;
		this.contY = 1;
		this.contFase = 0;
		this.finalGame = false;
		this.rochaX = 1150;
		Stage4.goldY = 480;
		this.rochaY = 250;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		resource = getClass().getResource("../environment/utils/gold.png");

		try {
			gold = ImageIO.read(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		g.drawImage(gold, 800, Stage4.goldY,64,64, this);
		g.drawImage(gold, 850, Stage4.goldY, this);
		g.drawImage(gold, 850, Stage4.goldY - 10, this);
		g.drawImage(gold, 845, Stage4.goldY - 10, this);
		if(finalGame){
			g.setColor(Color.red);
			g.drawString("VOCE RECUPEROU O TESOURO! AGORA FUJA!", 300, 100);
			g.setColor(Color.black);
			g.fillOval(this.rochaX + 850, rochaY, 300, 300);
			g.drawString("X - Rocha" + rochaX, 400, 250);
			if(!start)
				this.start = true;
		}

	}

	public void moveRocha(boolean inicioFase){
		
		if(!move){
			if(contFase == 0){
				this.rochaX = 1150;
				this.contFase++;
			}else{
				if(inicioFase){
					this.rochaX = 600;
				}else{
					
				}
				if(Screen.player.getStage() != 2){
					this.rochaX -= 20;
				}else
					this.rochaX -= 8;

				if(contY >= 4){
					this.rochaY -= 3;
					contY++;
					if(contY >= 8)
						contY = 0;
				}else
					this.rochaY += 3;
				contY++;	
			}
		}



	}

	public void setFinal(boolean finalGame){
		this.finalGame = finalGame;
	}

	public boolean returnFinal(){
		return this.finalGame;
	}

	public boolean isStart(){
		return this.start;
	}
	public Rectangle goldBounds(){
		return(new Rectangle(800, Stage4.goldY,64,64));
	}

	public Rectangle rochaBounds(){
		return(new Rectangle(this.rochaX + 850, rochaY, 300, 300));
	}

}