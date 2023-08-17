package com.java.pitfall.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.JOptionPane;
import com.java.pitfall.environment.utils.Sound;
import com.java.pitfall.stage.Stage4;

public class Collisions extends Screen{
	private Sound song;
	private int countSound;
	private int pontos;
	private int countBarrilSound;
	public Collisions() {
		//super();
		this.countBarrilSound = 0;
		this.pontos = 100;
		song = new Sound();
		this.countSound = 0;


	}

	public void checkCollision(){
		if(pontos < 0){
			if(countSound == 0){
				song.song("src/com/java/pitfall/environment/utils/songs/death.wav");
				this.countSound++;
			}	
			JOptionPane.showMessageDialog(null, "Game Over!");
			System.exit(0);
		}
		Rectangle fall =   Collisions.stageOne.buraco();
		Rectangle p1   =   Collisions.player.playerDim();
		Rectangle barril1 = Collisions.stageOne.barril();
		Rectangle barril2 = Collisions.stageOne.barril2();
		Rectangle cordaStage2 = Collisions.stageTwo.cordaBounds();
		Rectangle agua1 = Collisions.stageTwo.waterBounds();
		Rectangle fall2 = Collisions.stageThree.fallBounds();
		Rectangle gold = Collisions.stageFour.goldBounds();
		Rectangle rocha = Collisions.stageFour.rochaBounds();
		Collisions.stageTwo.charCordaBounds();
		if(rocha.intersects(p1) && stageNum > 0 && stageFour.returnFinal()){
			song.song("/home/bigboss/death.wav");
			JOptionPane.showMessageDialog(null, "Game Over!");
			System.exit(0);
		}else if(fall.intersects(rocha) && stage1){
			if(countSound == 0){
				song.song("src/com/java/pitfall/environment/utils/songs/rock.wav");
				this.countSound++;
			}
			Stage4.move = true;
			vitoria = true;

		}
		if(stage1){
			if(barril1.intersects(p1)){
				if(countBarrilSound == 0){
					song.song("src/com/java/pitfall/environment/utils/songs/barril.wav");
					this.countBarrilSound++;
				}
				
				pontos -= 10;
			}

			if(barril2.intersects(p1)){
				if(countBarrilSound == 0){
					song.song("src/com/java/pitfall/environment/utils/songs/barril.wav");
					this.countBarrilSound++;
				}
				pontos -= 10;
			}
			if(!barril1.intersects(p1) || !barril2.intersects(p1)){
				this.countBarrilSound = 0;
			}

			if(fall.intersects(p1)){
				song.song("src/com/java/pitfall/environment/utils/songs/death.wav");
				//player.y += 10;
				player.y = 580;

				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "Game Over!");
				System.exit(0);

			}
		}else if (stage2){
			if(cordaStage2.intersects(p1)){
				stageTwo.setChar(true);
				song.song("src/com/java/pitfall/environment/utils/songs/corda.wav");
				player.corda(true,true, stageTwo.getX());
			}else if(agua1.intersects(p1)){
				System.out.println("Colidiu agua");


				song.song("src/com/java/pitfall/environment/utils/songs/death.wav");
				//player.y += 10;
				player.y = 1000000;
				player.spriteHeigth -= 20;
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "Game Over!");
				System.exit(0);

			}
		}else if(stage3){
			if(fall2.intersects(p1)){
				System.out.println("Colidiu agua");
				song.song("src/com/java/pitfall/environment/utils/songs/death.wav");
				//player.y += 10;
				player.y = 1000000;
				player.spriteHeigth -= 20;
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "Game Over!");
				System.exit(0);
			}
		}else if(stage4){
			if(gold.intersects(p1)){
				song.song("src/com/java/pitfall/environment/utils/songs/Super Mario Bros.-Coin Sound Effect-RfkcI8dhfsQ.wav");
				Stage4.goldY = 10000;
				stageFour.setFinal(true);
			}

		}

	}

	@Override
	public void paint(Graphics g) {
		//pontos -= cont*6;
		g.drawString("Pontos - " + pontos, 50, 100);

	}

}