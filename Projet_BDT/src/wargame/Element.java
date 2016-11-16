package wargame;

import java.awt.Color;

/**
 * Classe abstraite définissant les élements de la carte du jeu.
 * @author Rémy
 */
public abstract class Element implements IConfig{
	/******************** Variables ********************/
	
	/** Position de l'élément*/
	public Position pos;
	/** Couleur de l'élément.
	 * Par défaut, est initilaisé à {@code IConfig#COULEUR_VIDE} */
	public Color couleur;
	
	/******************** Constructeurs ********************/
	
	/**
	 * Construit un {@linkplain Element}
	 * @param p_pos La position du nouvel Element
	 */
	// inutile ?????
	public Element(Position p_pos){
		this.pos = p_pos;
		this.couleur = COULEUR_VIDE;
	}
	
	/**
	 * Construit un {@linkplain Element} avec sa couleur initiale
	 * @param p_pos La position du nouvel Element
	 * @param p_color Couleur du nouvel Element (définies dans {@link IConfig}
	 */
	public Element(Position p_pos, Color p_color){
		this(p_pos);
		this.couleur = p_color;
	}
	
	/******************** Accesseurs ********************/
	
	/**
	 * Acceseur de la position de l'élément.
	 * @return La position de l'élément courant
	 */
	public Position getPos(){
		Position p = new Position(pos.getX(), pos.getY());
		return p;
	}
	
	/**
	 * Accesseur de la couleur de l'élément.
	 * @return La couleur de l'élément courant
	 */
	public Color getCouleur(){
		Color c = this.couleur;
		return c;
	}
}
