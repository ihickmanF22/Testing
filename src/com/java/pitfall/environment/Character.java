package com.java.pitfall.environment;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.java.pitfall.constants.Constants;
import com.java.pitfall.environment.utils.Sound;

public class Character extends JFrame implements Constants {

	private boolean isMoveLeft;
	private static boolean left;
	private boolean isMoveRigth;
	private static boolean rigth;
	private boolean stage0;
	private boolean isStop;
	private boolean jump;
	private boolean fall;
	private String path;
	private Image image;
	private int numJump;
	public int x;
	public int y;
	private URL resource;
	private int contRigth;
	private int contStage;
	private int initX;
	public int spriteWidth;
	public int spriteHeigth;
	private Sound song;
	private int sound;
	private boolean end;
	public Character() throws IOException{
		this.end = false;
		song = new Sound();
		Character.rigth = true;
		Character.left = false;
		this.x = 100;
		this.y = 350;
		this.spriteWidth = 256;
		this.spriteHeigth = 512;
		this.contStage = 0;
		this.initX = x;
		this.isStop = true;
		this.isMoveLeft = false;
		this.isMoveRigth = false;
		this.jump = false;
		this.fall = false;
		this.stage0 = false;
		this.sound = 0;
		setSprite();
	}


	@Override
	public void paint(Graphics g)  {
		super.paint(g);
		resource = getClass().getResource(this.path);
		try {
			image = ImageIO.read(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}

		g.drawImage(image, this.x, this.y,spriteWidth,this.spriteHeigth, this);
		/*if(left){
			g.drawRect(this.x + 100, this.y, spriteWidth - 444 ,this.spriteHeigth - 250);
		}else{
			g.drawRect( this.x + 70, this.y, spriteWidth - 200 ,this.spriteHeigth - 250);
		}*/
		g.setColor(Color.black);



	}


	public void setSprite(){
		if(end){
			this.path = "utils/vitoria.png";
		}
		if(isStop && !isMoveRigth && !isMoveLeft){
			if(rigth)	{
				this.spriteWidth = 256;
				this.spriteHeigth = 512;
				path = "utils/SpriteStop.png";

			}
			else if(left){
				this.spriteWidth = 500;
				this.spriteHeigth = 512;
				path = "utils/left.png";
			}


		}

		if(isMoveLeft){
			rigth = false;
			left = true;
		}

		if(isMoveRigth){
			rigth = true;
			left = false;
		}
	}


	public void move(){

		if(fall){
			System.out.println("Caiu!");
			this.y +=(int) 20 * GRAVITY;
		}
		if(isMoveRigth){
			if(contRigth == 0){
				this.spriteWidth = 256;
				this.spriteHeigth = 512;
				this.path = "utils/runRigth.png";
				this.x += 40;
				contRigth++;
			}else if(contRigth > 0){
				this.spriteWidth = 256;
				this.spriteHeigth = 512;
				this.path = "utils/SpriteStop.png";
				this.x += 40;
				contRigth--;
			}

		}
		if(isMoveLeft){
			if(contRigth == 0){
				this.spriteWidth = 256;
				this.spriteHeigth = 512;
				this.path = "utils/runLeft.png";
				this.x -= 40;
				contRigth++;
			}else if(contRigth > 0){
				this.spriteWidth = 500;
				this.spriteHeigth = 512;
				this.path = "utils/left.png";
				this.x -= 40;
				contRigth--;
			}
		}
		if (jump){

			if(sound == 0){
				song.song("src/com/java/pitfall/environment/utils/songs/jump.wav");
				sound++;
			}
			if(isMoveLeft){
				this.spriteWidth = 256;
				this.spriteHeigth = 512;
				this.path = "utils/runLeft.png";
				contRigth = 4;

			}

			else if(isMoveRigth){
				this.spriteWidth = 256;
				this.spriteHeigth = 512;
				this.path = "utils/runRigth.png";
				contRigth = 4;
			}


			if(numJump > 120){

				this.y += (int) 40 * GRAVITY;
				this.numJump += (int) 40 * GRAVITY;
				if(numJump > 240){
					this.spriteWidth = 256;
					this.spriteHeigth = 512;
					this.numJump = 0;
					this.jump = false;
					this.y = 350;
					contRigth = 0;
					sound = 0;
				}
			}else{
				this.y -= (int) 40 * GRAVITY;
				this.numJump += (int) 40 * GRAVITY;

			}
		}


	}

	public void corda(boolean corda, boolean mudaY,int x){
		if(corda && mudaY){
			this.y = 10000;
			this.x = 100;
			if(x >= 700)
				this.x = x;
			else if(x < 600)
				this.x = 160;
		}else if(!corda && mudaY && this.y == 10000){
			this.y = 350;

		}
	}

	public void changeStage(){
		if(contStage == 0){
			stage0 = true;
		}else{
			stage0 = false;
		}
		if(this.x > 860 && contStage != 4){
			this.contStage++;
			this.x = this.initX - 20;
		}else if(this.x < -100 && stage0){
			this.x = -100;
		}
		else if (this.x < -100 && !stage0){
			this.contStage--;
			this.x = 850;
		}else if(this.x > 860 && contStage > 3){
			this.x = 850;
		}
	}

	public void end(){
		JOptionPane.showMessageDialog(null, "Fim");
		System.exit(0);
	}

	public void vitoriaGame(){
		if(this.x > 860){
			this.x = 850;
		}else if(this.x < -100){
			this.x = -100;
		}
	}

		
			
	
	public int getStage(){
		System.out.println("fase -  " + contStage);
		return contStage;
	}

	public void setRigth(boolean rigth){
		this.isMoveRigth = rigth;


	}

	public void setLeft(boolean left){
		this.isMoveLeft = left;

	}

	public void setJump(boolean jump){
		this.jump = true;
	}

	public void setFall(boolean fall){

		this.fall = jump;
	}

	public static boolean getRigth(){
		return rigth;
	}

	public static boolean getLeft(){
		return left;
	}

	public Rectangle playerDim(){
		if(left){
			return (new Rectangle( this.x + 100, this.y, spriteWidth - 444 ,this.spriteHeigth - 250));
		}else{
			return (new Rectangle( this.x + 70, this.y, spriteWidth - 200 ,this.spriteHeigth - 250));
		}


	}
}