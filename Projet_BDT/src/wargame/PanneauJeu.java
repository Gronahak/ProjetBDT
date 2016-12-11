package wargame;

import java.awt.*;
import java.io.Serializable;

import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 * Classe panneauJeu
 * @author Emilie, Hugo, Rémy
 * @version 11-11-2016
 */

public class PanneauJeu extends JPanel implements IConfig , Serializable{

	private static final long serialVersionUID = 1L;
	protected Carte carte;

	/****************
	 * Constructeur *
	 ****************/
	PanneauJeu(){
		carte=new Carte();

		this.setBackground(Color.cyan); 
		this.setPreferredSize(new Dimension(LARGEUR_CARTE*NB_PIX_CASE,HAUTEUR_CARTE*NB_PIX_CASE));
		setBorder (new LineBorder(Color.blue)); 

		// this.add(vieArmeeHeros,BorderLayout.WEST);
		// this.add(carte,BorderLayout.CENTER);
		// this.add(vieArmeeMonstres,BorderLayout.EAST);
		// this.add(testcarte);
		// testcarte.toutDessiner(this.getGraphics());
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		// FenetreJeu.vieArmeeHeros.seDessiner(g);
		carte.toutDessiner(g);
		// FenetreJeu.vieArmeeMonstres.seDessiner(g);
	}
	
	/**
	 * Connaitre le type d'element a une position choisi
	 * @param pos position a observer
	 * @return Rien si position vide, information sur l'element sinon
	 */
	public String getInfos(Position pos){
		if (carte.getElement(pos).estVisible)
			return carte.getElement(pos).toString();
		return "";
	}
	
	/**
	 * Recuperer le nombre de soldat restant sur la carte
	 * @return nombre de soldat
	 */
	public String nbSoldats(){
		return carte.nombreSoldatsRestant();
	}
	
	/*************
	 * Mutateurs *
	 *************/
	/**
	 * Remplacer la carte par map
	 * @param map
	 */
	public void setCarte(Carte map){
		carte=map;
	}
	
	/**************
	 * Accesseurs *
	 **************/
	/**
	 * Recuperer la carte
	 * @return carte du jeu
	 */
	public Carte getCarte(){
		return carte;
	}
}
