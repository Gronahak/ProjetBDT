package wargame;

import java.awt.Color;
<<<<<<< HEAD
import java.awt.Graphics;

public class Element implements IConfig{

	protected Position pos;
	protected Color couleur=COULEUR_VIDE;
	
	Element (){
		pos=new Position(0,0);
	}
	Element(Position pos){
		super();
		this.pos=pos;
	}
	public void setPosition(Position position){
		pos=position;
	}
	public Position getPosition(){
		return pos;
	}
	public boolean estVide(){
		return (this==null);
	}
	
	public void seDessiner(Graphics g) {
		
		g.setColor(couleur);
		g.fillRect(pos.getX()*NB_PIX_CASE, pos.getY()*NB_PIX_CASE, NB_PIX_CASE, NB_PIX_CASE);
		g.setColor(Color.black);
		g.drawRect(pos.getX()*NB_PIX_CASE, pos.getY()*NB_PIX_CASE, NB_PIX_CASE, NB_PIX_CASE);
		g.setColor(COULEUR_VIDE);

	}
	public String toString(){
		return "Je dessine un "+this.getClass().getSimpleName()+" en case "+pos;
	}

=======

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
>>>>>>> branch 'develop' of https://github.com/Gronahak/ProjetBDT.git
}
