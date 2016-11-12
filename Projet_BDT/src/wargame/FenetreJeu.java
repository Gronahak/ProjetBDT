package wargame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import wargame.PanneauJeu;

public class FenetreJeu implements IConfig{

	public static void main(String[] args){
		
		JFrame frame = new JFrame("Wargame");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		PanneauJeu panneauJeu = new PanneauJeu();
		panneauJeu.setOpaque(true);
		panneauJeu.setBackground(Color.GRAY); 
		panneauJeu.setPreferredSize(new Dimension(LARGEUR_CARTE*NB_PIX_CASE,HAUTEUR_CARTE*NB_PIX_CASE));
	
		JPanel infosTop = new JPanel();
		JLabel infosBot = new JLabel();
		
		infosTop.setOpaque(true);
		infosTop.setPreferredSize(new Dimension(LARGEUR_CARTE*NB_PIX_CASE,2*NB_PIX_CASE));
		infosTop.setBackground(COULEUR_VIDE);
		infosBot.setOpaque(true);
		infosBot.setPreferredSize(new Dimension(LARGEUR_CARTE*NB_PIX_CASE,1*NB_PIX_CASE));
		infosBot.setBackground(COULEUR_VIDE);

		frame.getContentPane().add(infosTop,BorderLayout.NORTH);
		frame.getContentPane().add(panneauJeu,BorderLayout.CENTER);
		frame.getContentPane().add(infosBot,BorderLayout.SOUTH);

		frame.setLocation(POSITION_X, POSITION_Y);
		frame.pack();
		frame.setVisible(true);
	}
}
