package wargame;
import java.awt.Color;
import java.io.Serializable;
public class Obstacle extends Element implements IConfig, Serializable{
	private static final long serialVersionUID = -931512914138420499L;
	public enum TypeObstacle {
		ROCHER (COULEUR_ROCHER), FORET (COULEUR_FORET), EAU (COULEUR_EAU);
		private final Color COULEUR;
		TypeObstacle(Color couleur) { COULEUR = couleur; }
		public static TypeObstacle getObstacleAlea() {
			return values()[(int)(Math.random()*values().length)];
		}
	}
	
	private TypeObstacle TYPE;
	
	Obstacle(TypeObstacle type, Position pos) { 
		TYPE = type; this.pos = pos;
		couleur=TYPE.COULEUR;
	}
	public String toString() {
		return ""+TYPE;
	}
}