package com.java.pitfall.stage;
import com.java.pitfall.constants.*;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;

public class DefaultStage extends JFrame implements Constants{
	/*
	 * 		Classe para guardar desenho da fase
	 * 
	 * */
	
	public DefaultStage(){
		
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		g.setColor(new Color(112, 162, 46));		//Primeira parte do fundo
		g.fillRect(BLOCO_FUNDO_X, BLOCO_FUNDO_POSI, WIDTH_SCREEN, BLOCO_FUNDO_HEIGTH);
		g.setColor(new Color(255, 197, 29));
		g.fillRect(BLOCO_FUNDO_X, BLOCO_FUNDO_AMARELO, WIDTH_SCREEN, BLOCO_FUNDO_AMARELO_HEIGTH);
		g.setColor(new Color(200, 95, 29));
		g.fillRect(BLOCO_FUNDO_X, BLOCO_FUNDO_LARANJA, WIDTH_SCREEN, BLOCO_FUNDO_LARANJA_HEIGTH);
		g.setColor(new Color(19, 19, 19));
		g.fillRect(BLOCO_FUNDO_X, BLOCO_FUNDO_PRETO, WIDTH_SCREEN, BLOCO_FUNDO_PRET_HEIGTH);
		g.setColor(new Color(57, 23, 1));
		g.fillRect(TRONCO_X_A, TRONCO_Y, TRONCO_WIDTH, TRONCO_HEIGTH);
		g.fillRect(TRONCO_X_B, TRONCO_Y, TRONCO_WIDTH, TRONCO_HEIGTH);
		g.fillRect(TRONCO_X_C, TRONCO_Y, TRONCO_WIDTH, TRONCO_HEIGTH);
		//Desenho das folhas da arvore
		g.setColor(new Color(12,74,28));
		for (int i = 0; i < FOLHAS_X.length; i++) {
			g.fillRect(FOLHAS_X[i], FOLHAS_Y[i], FOLHAS_WIDTH[i], FOLHAS_HEIGTH[i]);
		}
		
		
		
		
	}
}