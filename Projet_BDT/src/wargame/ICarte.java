package wargame;
import java.awt.Graphics;
/**
 * Interface pour la gestion de carte
 * @author Emilie, Hugo, Rémy
 * @version 16/11/2016
 */
public interface ICarte {
	/** 
	 * Récupère l'élément à la position {@code pos}.
	 * @param pos La position de l'élément souhaité
	 * @return l'élément sur la carte à cette position
	 */
	Element getElement(Position pos);
	
	/**
	 * Trouve aléatoirement une position vide sur la carte
	 * @return une position (case vide)
	 */
	Position trouvePositionVide();
	
	/**
	 * Trouve aléatoirement une position vide sur la carte parmi les cases adjacentes (8) à {@code pos}.
	 * @param pos La position à partir de laquelle on recherche une position aléatoire
	 * @return une position (case vide)
	 */
	Position trouvePositionVide(Position pos);
	
	/**
	 * Trouve aléatoirement un héros sur la carte
	 * @return un héros
	 */
	Heros trouveHeros();
	
	/**
	 * Trouve aléatoirement un héros sur la carte parmi les cases adjacentes (8) à {@code pos}.
	 * @param pos la position à partir de laquelle on recherche un héros 
	 * @return un héros
	 */
	Heros trouveHeros(Position pos);
	
	/* ????????????????
	 * Ajout d'une méthode pour trouver un monstre ? Un obstacle ?
	 *??????????????*/
	
	/**
	 * Déplace le soldat {@code soldat} à la position {@code pos}.
	 * @param pos la position à laquelle déplacer le soldat
	 * @param soldat le soldat à déplacer
	 * @return {@code true} si le soldat a pu être déplacé, {@code faux} sinon.
	 */
	boolean deplaceSoldat(Position pos, Soldat soldat);
	
	/**
	 * Tue le soldat en paramètre.
	 * @param perso le soldat à tuer
	 */
	void mort(Soldat perso);
	
	/**
	 * Vérifie si/Réalise ? l'action du héros est possible d'une première position à une seconde position
	 * @param pos première position de 'action
	 * @param pos2 seconde postion de l'action
	 * @return {@code true} si l'action est réalisée/peut être réalisée, {@code false} sinon
	 */
	boolean actionHeros(Position pos, Position pos2);
	
	/**
	 * Fais jouer l'ensemble des soldats
	 * @param pj le panneau de jeu
	 */
	void jouerSoldats(PanneauJeu pj);
	
	/**
	 * Dessine l'ensemble de la carte
	 * @param g le graphics à dessiner
	 */
	void toutDessiner(Graphics g); 
}