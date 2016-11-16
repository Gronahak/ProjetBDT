package wargame;

import java.awt.Graphics;
/**
 * Classe gérant la carte du jeu et les éléments présents sur celle-ci.
 * @author Hugo
 * @version 16/11/2016
 * @see IConfig ICarte
 */
public class Carte implements ICarte,IConfig{
	/** Carte du jeu : Matrice d'{@linkplain Element} */ 
	private Element[][] elements = new Element[LARGEUR_CARTE][HAUTEUR_CARTE];
	
	public Element getElement(Position pos){
		return elements[pos.getX()][pos.getY()];
	}
	
	public Position trouvePositionVide(){
		return new Position(0, 0); //position nulle 
	}
	Position trouvePositionVide(Position pos); 	
	Heros trouveHeros(); 						
	Heros trouveHeros(Position pos); 
	boolean deplaceSoldat(Position pos, Soldat soldat);
	void mort(Soldat perso);
	boolean actionHeros(Position pos, Position pos2);
	void jouerSoldats(PanneauJeu pj);
	void toutDessiner(Graphics g); 
}