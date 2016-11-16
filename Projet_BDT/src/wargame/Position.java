package wargame;
/**
 * Classe définissant une position sur la carte de jeu. Implémente IConfig
 * @author Rémy
 * @version 16/11/2016
 * @see IConfig
 */
public class Position implements IConfig {
	/******************** Variables ********************/
//	Coordonnées de la position
	private int x, y;
	
	/******************** Constructeur ********************/
	
	/**
	 * Construit une nouvelle position.
	 * @param x La coordonnée {@code x} de la position
	 * @param y La coordonnée {@code y} de la position
	 */
	Position(int x, int y) { 
		this.x = x; this.y = y;
	}
	
	/******************** Accesseurs et Mutateurs ********************/
	/**
	 * Renvoie la coordonée x de la position courante.
	 * @return la coordonnée x
	 */
	public int getX() {
		return x; 
	}
	/**
	 * Renvoie la coordonée y de la position courante.
	 * @return la coordonnée y
	 */
	public int getY() { 
		return y; 
	}
	/**
	 * Définit la nouvelle coordonnée x de la poition courante.
	 * @param x la nouvelle coordonnée x
	 */
	public void setX(int x) { 
		this.x = x; 
	}
	/**
	 * Définit la nouvelle coordonnée y de la position courante.
	 * @param y la nouvelle coordonnée y
	 */
	public void setY(int y) { 
		this.y = y; 
	}
	
	/******************** Méthodes ********************/
	
	/**
	 * Vérifie si la position courante est sur la carte.
	 * @return {@code true} si la position courante est dans la carte, {@code false} sinon
	 */
	public boolean estValide() {
		if (x<0 || x>=LARGEUR_CARTE || y<0 || y>=HAUTEUR_CARTE) 
			return false; 
		else 
			return true;
	}
	
	/**
	 * Vérifie si la position courante est adjacente à la position en paramètre.
	 * @param pos la position à laquelle comparée la position courante
	 * @return {@code true} si les deux positions sont adjacentes, {@code false} sinon
	 */
	public boolean estVoisine(Position pos) {
		return ((Math.abs(x-pos.x)<=1) && (Math.abs(y-pos.y)<=1));
	}
	
	/**
	 * Affichage pour une Position
	 */
	public String toString() { 
		return "("+x+","+y+")"; 
	}
}