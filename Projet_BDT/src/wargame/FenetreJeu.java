package wargame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import wargame.PanneauJeu;

public class FenetreJeu  /*extends JPanel */implements IConfig {
	public static int abs=0;
	public static int ord=0;
	public static JLabel encoreEnVie;
	private static Boolean soldatSelectionne=false;
	private static Position curseur1;
	public static JaugeVie vieArmeeHeros;
	public static JaugeVie vieArmeeMonstres;
	public static PanneauJeu panneauJeu;
	public static void main(String[] args){
		
		JFrame frame = new JFrame("Wargame");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		JPanel infosTop = new JPanel();
		JLabel infosBot = new JLabel("vide");
	
		((JComponent) frame.getContentPane()).setBorder (new LineBorder(Color.green)); 

		vieArmeeHeros = new JaugeVie();
		vieArmeeMonstres = new JaugeVie();
		//vieArmeeHeros.setVisible(true);
		//vieArmeeHeros.setOpaque(true);
		panneauJeu = new PanneauJeu();

		
		JButton finDuTour=new JButton("Fin du tour");
		finDuTour.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				panneauJeu.carte.jouerSoldats(panneauJeu);
				panneauJeu.repaint();
			}
		});
		encoreEnVie=new JLabel();
		encoreEnVie.setText("Il reste "+panneauJeu.nbSoldats());
		
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
		frame.getContentPane().add(vieArmeeHeros,BorderLayout.WEST);
		frame.getContentPane().add(vieArmeeMonstres,BorderLayout.EAST);

		frame.setLocation(POSITION_X, POSITION_Y);
		frame.pack();
		frame.setVisible(true);
		
	}
	/*
	public void paintComponent(Graphics g){

		super.paintComponent(g);
	//	vieArmeeHeros.seDessiner(g);
	//	vieArmeeMonstres.seDessiner(g);
	}*/
}
