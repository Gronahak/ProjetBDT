package wargame;
import java.awt.Graphics;

/**
 * Interface pour la gestion de carte
 * @author Emilie, Hugo, Rémy
 * @version 11-11-2016
 */

public interface ICarte {
	Element getElement(Position pos);
	Position trouvePositionVide(int zone); 		// Trouve aléatoirement une position vide sur la carte
	Position trouvePositionVide(Position pos); 	// Trouve une position vide choisie aléatoirement parmi les 8 positions adjacentes de pos
	Heros trouveHeros(); 						// Trouve aléatoirement un héros sur la carte
	Heros trouveHeros(Position pos); 			// Trouve un héros choisi aléatoirement parmi les 8 positions adjacentes de pos
	boolean deplaceSoldat(Position pos, Soldat soldat);
	void mort(Soldat perso);
	boolean actionHeros(Position pos, Position pos2);
	void jouerSoldats(PanneauJeu pj);
	void toutDessiner(Graphics g); 
	
	public final static int GAUCHE=1;  // Ces trois constantes servent à instancier les divers éléments de la carte à des endroits précis :
	public final static int DROITE=2;  // les héros dans la partie gauche, les monstres à droite, et les obstacles partout
	public final static int TOUTE=3;
}