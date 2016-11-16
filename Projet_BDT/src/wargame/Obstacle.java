package wargame;
import java.awt.Color;
<<<<<<< HEAD
=======
import java.awt.Graphics;

/**
 * Classe définissant les obsatcles du jeu. Hérite de {@linkplain Element} et implémente {@linkplain IConfig}.
 * @author Rémy
 * @version 16/11/2016
 * @see IConfig Element
 */
>>>>>>> branch 'develop' of https://github.com/Gronahak/ProjetBDT.git
public class Obstacle extends Element implements IConfig{
	/**
	 * Définit le type possible d'un obstacle.
	 * @author Rémy
	 * @version 16/11/2016
	 */
	public enum TypeObstacle {
		/** Obstacle de type rocher */
		ROCHER (COULEUR_ROCHER), /** Obstacle de type forêt */FORET (COULEUR_FORET), /** Obstacle de type eau */EAU (COULEUR_EAU);
		private final Color COULEUR; // couleur de l'élément
		/**
		 * Construit un nouveau TypeObstacle
		 * @param couleur la couleur donné à l'obsatcle (correspondant à son type)
		 */
		TypeObstacle(Color couleur) { 
			COULEUR = couleur; 
		}
		/**
		 * Définit un TypeObstacle de manière aléatoire.
		 * @return un nouveau TypeObstacle aléatoire
		 */
		public static TypeObstacle getObstacleAlea() {
			return values()[(int)(Math.random()*values().length)];
		}
	}
	
<<<<<<< HEAD
=======
//	Type dl'obstacle (obtenu avec enum)
>>>>>>> branch 'develop' of https://github.com/Gronahak/ProjetBDT.git
	private TypeObstacle TYPE;
	
<<<<<<< HEAD
=======
	/**
	 * Construit un nouvel obstacle.
	 * @param type le type de l'obstacle à créer
	 * @param pos la position de l'obstacle à créer
	 */
>>>>>>> branch 'develop' of https://github.com/Gronahak/ProjetBDT.git
	Obstacle(TypeObstacle type, Position pos) { 
<<<<<<< HEAD
		TYPE = type; this.pos = pos;
		couleur=TYPE.COULEUR;
=======
		super(pos, type.COULEUR);
		TYPE = type; this.pos = pos; 
>>>>>>> branch 'develop' of https://github.com/Gronahak/ProjetBDT.git
	}
	
	/**
	 * Affichage pour un obstacle
	 */
	public String toString() {
		return ""+TYPE;
	}
}