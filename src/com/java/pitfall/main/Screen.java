package com.java.pitfall.main;


import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JFrame;
import com.java.pitfall.constants.*; //Importa constantes
import com.java.pitfall.environment.Character; //Importa personagem
import com.java.pitfall.stage.*; //Importa fase

public class Screen extends JFrame implements Constants, Runnable, KeyListener{
	static boolean stage0;
	static boolean stage1;
	static boolean stage2;
	static boolean stage3;
	public static boolean stage4;
	private Thread th1;
	private DefaultStage stage;
	static Stage1 stageOne;
	static Stage2 stageTwo;
	static Stage3 stageThree;
	static Stage4 stageFour;
	static Stage0 stageZero;
	public static boolean vitoria;
	private Image img; //Concertar bug da tela piscando
	private Graphics gfx;
	public static Character player;
	private Collisions collisions;
	public int points;
	private int contRocha;
	public static int stageNum;

	public Screen() {
		
	}

	public void init() throws IOException, InterruptedException{
		this.contRocha = 3;
		this.setTitle("Pitfall");
		this.setSize(WIDTH_SCREEN,HEIGTH_SCREEN);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		//Corrigir bug da tela piscando
		Screen.stage0 = false;
		Screen.stage1 = false;
		Screen.stage2 = false;
		Screen.stage3 = false;
		Screen.stage4 = false;
		Screen.vitoria = false;
		img =createImage(WIDTH_SCREEN, HEIGTH_SCREEN);
		this.points = 0;
		collisions = new Collisions();
		gfx = img.getGraphics();
		player = new Character();
		stage = new DefaultStage();
		stageOne = new Stage1();
		stageTwo = new Stage2();
		stageThree = new Stage3();
		stageFour = new Stage4();
		this.addKeyListener(this);
		th1 = new Thread(this);
		th1.start();
	}


	@Override
	public void paint(Graphics g) {
		super.paint(gfx);
		//desenha fase
		stage.paint(gfx); 
		player.paint(gfx);
		collisions.paint(gfx);
		if(stageNum < 1 && vitoria){
			gfx.drawString("OBRIGADO POR JOGAR! FIM DE JOGO!  Aperte 'F' para finalizar", 200, 100);
			player.vitoriaGame();
			//JOptionPane.showMessageDialog(null, "Fim de jogo! obrigado por jogar!");
		}
		if(stage1){
			stageOne.paint(gfx);
			if(stageFour.returnFinal() && stageNum != 0)
				stageFour.paint(gfx);
		}else if(stage2){

			stageTwo.paint(gfx);
			if(stageFour.returnFinal() && stageNum != 0)
				stageFour.paint(gfx);
		}else if(stage3){
			stageThree.paint(gfx);
			System.out.println("Fase 3 !!!");
			if(stageFour.returnFinal() && stageNum != 0 )
				stageFour.paint(gfx);
		}else if((stage4 || stageFour.returnFinal()) && stageNum != 0 ){
			stageFour.paint(gfx);
		}




		g.drawImage(img, 0, 0, this);  //Corrigir bug da tela piscando


	}

	@Override
	public void run() {	//LEMBRAR DE FAZER UMA TELA DE INICIO
		while (true){
			repaint();
			player.move();
			player.setSprite();
			if(!vitoria || stageNum > 0){
				player.changeStage();
			}
			
			checkStage();
			

			if(stage1 && contRocha < 2 && stageNum != 0){

				if(stageFour.returnFinal() && contRocha == 1 && stageFour.isStart()){
					stageFour.moveRocha(true);
					this.contRocha--;
				}else{
					stageFour.moveRocha(false);
				}
			}

			if(stage2){
				if(stageFour.returnFinal() && contRocha == 2 && stageFour.isStart()){
					System.out.println("FUNCIONEI OLHA OLHA!");
					stageFour.moveRocha(true);
					this.contRocha--;
				}else{
					stageFour.moveRocha(false);
				}
				if(stageTwo.getChar())
					player.corda(true, true,stageTwo.getX());
				else
					player.corda(false, true,0);	
			}	
			
			if(stage3){
				stageThree.moveFall();
				if(stageFour.returnFinal() && contRocha == 3 && stageFour.isStart()){
					stageFour.moveRocha(true);
					this.contRocha--;
				}else{
					stageFour.moveRocha(false);
				}

			}

			else if(stage4 && stageFour.returnFinal()){
			//	player.finalAction();
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				stageFour.moveRocha(false);

			}
			collisions.checkCollision();

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}



	//Checar fase atual

	public void checkStage(){
		stageNum = player.getStage();
		System.out.println("Fase " + stageNum);

		if(stageNum == 0)
			stage0 = true;
		else
			stage0 = false;

		if(stageNum == 1)
			stage1 = true;
		else
			stage1 = false;

		if(stageNum == 2)
			stage2 = true;
		else
			stage2 = false;

		if(stageNum == 3)
			stage3 = true;

		else 
			stage3 = false;

		if(stageNum == 4)
			stage4 = true;

		else if(!stageFour.returnFinal())
			stage4 = false;
		else
			stage4 = true;
	}



	@Override
	public void keyPressed(KeyEvent e) {
		
			if(e.getKeyCode() == KeyEvent.VK_RIGHT){
				player.setRigth(true);
			}

			if(e.getKeyCode() == KeyEvent.VK_LEFT){
				player.setLeft(true);
			}

			if(e.getKeyCode() == KeyEvent.VK_SPACE){
				player.setJump(true);
			}
			
			if (e.getKeyCode() == KeyEvent.VK_F && vitoria){
				player.end();
			}
		}


	

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			player.setRigth(false);
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT){
			player.setLeft(false);
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}