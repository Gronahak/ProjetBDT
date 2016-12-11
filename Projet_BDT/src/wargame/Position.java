package wargame;

import java.io.Serializable;

/**
 * Classe position
 * @author Emilie, Hugo, Rémy
 * @version 11-11-2016
 */

public class Position implements IConfig, Serializable {

	private static final long serialVersionUID = 1L;
	private int x, y;
	
	/*****************
	 * Constructeurs *
	 *****************/
	Position(int x, int y) { 
		this.x = x; this.y = y;
	}
	
	Position(){
		this(0,0);
	}
	
	/**************
	 * Accesseurs *
	 **************/
	public int getX() { 
		return x; 
	}
	
	public int getY() { 
		return y; 
	}
	
	/*************
	 * Mutateurs *
	 *************/
	public void setX(int x) { 
		this.x = x; 
	}
	
	public void setY(int y) { 
		this.y = y; 
	}
	
	/**
	 * Verifier qu'une position est valide
	 */
	public boolean estValide() {
		if (x<0 || x>=LARGEUR_CARTE || y<0 || y>=HAUTEUR_CARTE) 
			return false; 
		else return true;
	}
	
	/**
	 * Afficher une position
	 */
	public String toString() { 
		return "("+x+","+y+")"; 
	}
	
	/**
	 * Verifier qu'une position soit voisine
	 * @param pos
	 * @return true elle est voisine
	 * @return false elle n'est pas voisine
	 */
	public boolean estVoisine(Position pos) {
		return ((Math.abs(x-pos.x)<=1) && (Math.abs(y-pos.y)<=1));
	}
	
	/**
	 * Retourner la difference des X de deux positions
	 * @param pos
	 * @return
	 */
	public int distanceX(Position pos){
		return (Math.abs(this.getX()-pos.getX()));
	}
	
	/**
	 * Retourner la difference des Y de deux positions
	 * @param pos
	 * @return
	 */
	public int distanceY(Position pos){
		return (Math.abs(this.getY()-pos.getY()));
	}
}