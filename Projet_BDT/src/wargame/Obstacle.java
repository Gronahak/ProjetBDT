package wargame;

import java.awt.Color;
import java.io.Serializable;

/**
 * Classe obstacle
 * @author Emilie, Hugo, Rémy
 * @version 11-11-2016
 */

public class Obstacle extends Element implements IConfig, Serializable{

	private static final long serialVersionUID = 1L;

	public enum TypeObstacle {
		ROCHER (COULEUR_ROCHER), FORET (COULEUR_FORET), EAU (COULEUR_EAU);
		private final Color COULEUR;
		TypeObstacle(Color couleur) { COULEUR = couleur; }
		public static TypeObstacle getObstacleAlea() {
			return values()[(int)(Math.random()*values().length)];
		}
	}

	private TypeObstacle TYPE;

	/*****************
	 * Constructeurs *
	 *****************/
	/**
	 * @param type eau, foret, rocher
	 * @param pos position sur la carte de l'obstacle
	 */
	Obstacle(TypeObstacle type, Position pos) { 
		TYPE = type; this.pos = pos;
		couleur=TYPE.COULEUR;
		estVide=false;
	}

	public String toString() {
		return ""+TYPE;
	}
}