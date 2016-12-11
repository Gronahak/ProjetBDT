package wargame;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

/**
 * Classe element
 * @author Emilie, Hugo, Rémy
 * @version 11-11-2016
 */

public class Element implements IConfig , Serializable{

	private static final long serialVersionUID = 1L;
	protected Position pos;
	protected Color couleur;
	protected Boolean estVisible=false;
	protected Boolean estVide;//=true;
	static Carte carte=null;

	/*****************
	 * Constructeurs *
	 *****************/

	Element (){
		couleur=COULEUR_VIDE;
		pos=new Position(0,0);
		estVide=true;
	}

	Element(Position pos){
		this();
		this.pos=pos;
	}

	/**************
	 * Accesseurs *
	 **************/
	public Position getPosition(){
		return pos;
	}

	public Color getCouleur(){
		return couleur;
	}


	/*************
	 * Mutateurs *
	 *************/
	public void setPosition(Position position){
		pos=position;
	}

	public void setCouleur(Color c){
		couleur=c;
	}

	public void setCarte(Carte car){
		carte=car;
	}

	public void setEstVide(boolean b){
		estVide=b;
	}
	
	public boolean estVide(){
		return estVide;
	}
	
	public void seDessiner(Graphics g) {
		if (estVide)
			g.setColor(COULEUR_VIDE);
		if (estVisible)
			g.setColor(couleur);
		else g.setColor(COULEUR_INCONNU);

		g.fillRect(pos.getX()*NB_PIX_CASE, pos.getY()*NB_PIX_CASE, NB_PIX_CASE, NB_PIX_CASE);
		g.setColor(Color.black);
		g.drawRect(pos.getX()*NB_PIX_CASE, pos.getY()*NB_PIX_CASE, NB_PIX_CASE, NB_PIX_CASE);
		g.setColor(COULEUR_VIDE);
	}
	
	public String toString(){
		return "";
	}


}
