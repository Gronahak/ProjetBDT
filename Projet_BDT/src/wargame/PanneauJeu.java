package wargame;
import java.awt.*;
import java.io.Serializable;

import javax.swing.*;

public class PanneauJeu extends JPanel implements IConfig, Serializable {
	
	/** Numéro de sérialisation */
	private static final long serialVersionUID = 9136903518084884716L;
	Carte carte;

	PanneauJeu(){
		carte=new Carte();
	//	this.setOpaque(true);
	//	this.setBackground(Color.GRAY); 
		this.setPreferredSize(new Dimension(LARGEUR_CARTE*NB_PIX_CASE,HAUTEUR_CARTE*NB_PIX_CASE));

		
	//	this.add(testcarte);
	//testcarte.toutDessiner(this.getGraphics());

	}
	public void paintComponent(Graphics g){

		super.paintComponent(g);
		carte.toutDessiner(g);

	}
	public String getInfos(Position pos){
		return carte.getElement(pos).toString();
	}
	public String nbSoldats(){
		return carte.nombreSoldatsRestant();
	}
}
