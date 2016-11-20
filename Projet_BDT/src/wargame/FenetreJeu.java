package wargame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import wargame.PanneauJeu;

public class FenetreJeu  implements IConfig {
	public static int abs=0;
	public static int ord=0;
	private static Boolean soldatSelectionne=false;
	private static Position curseur1;
	public static void main(String[] args){
		
		JFrame frame = new JFrame("Wargame");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		PanneauJeu panneauJeu = new PanneauJeu();
		JPanel infosTop = new JPanel();
		JLabel infosBot = new JLabel("vide");
		
		JButton finDuTour=new JButton("Fin du tour");
		JLabel encoreEnVie=new JLabel("Il reste "+panneauJeu.nbSoldats());
		
		infosTop.add(finDuTour);
		infosTop.add(encoreEnVie);
		
		panneauJeu.addMouseMotionListener(new MouseAdapter(){
			public void mouseMoved(MouseEvent e){
				abs=e.getX()/NB_PIX_CASE;
				ord=e.getY()/NB_PIX_CASE;
				Position curseur=new Position(abs,ord);
				if (curseur.estValide())
			infosBot.setText(curseur+panneauJeu.getInfos(curseur));
			}
		})	;
		
		panneauJeu.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				abs=e.getX()/NB_PIX_CASE;
				ord=e.getY()/NB_PIX_CASE;
				curseur1=new Position(abs,ord);
				if (panneauJeu.carte.getElement(curseur1) instanceof Heros){
					soldatSelectionne=true;
				}
			}
			public void mouseReleased(MouseEvent e){
				abs=e.getX()/NB_PIX_CASE;
				ord=e.getY()/NB_PIX_CASE;
				Position curseur=new Position(abs,ord);
				if (soldatSelectionne==true){
					
						panneauJeu.carte.actionHeros(curseur1, curseur);
						soldatSelectionne=false;
						panneauJeu.repaint();
				}

			}
		})	;
		
		infosTop.setOpaque(true);
		infosTop.setPreferredSize(new Dimension(LARGEUR_CARTE*NB_PIX_CASE,2*NB_PIX_CASE));
		infosTop.setBackground(COULEUR_VIDE);
		infosBot.setOpaque(true);
		infosBot.setPreferredSize(new Dimension(LARGEUR_CARTE*NB_PIX_CASE,1*NB_PIX_CASE));
		infosBot.setBackground(COULEUR_VIDE);
		panneauJeu.setPreferredSize(new Dimension(LARGEUR_CARTE*NB_PIX_CASE+1,1+HAUTEUR_CARTE*NB_PIX_CASE));
		frame.getContentPane().add(panneauJeu,BorderLayout.CENTER);

		frame.getContentPane().add(infosTop,BorderLayout.NORTH);
		frame.getContentPane().add(infosBot,BorderLayout.SOUTH);


		frame.setLocation(POSITION_X, POSITION_Y);
		frame.pack();
		frame.setVisible(true);
		
		
	}
}
