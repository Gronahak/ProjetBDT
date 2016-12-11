package wargame;

import java.io.Serializable;

/**
 * Classe heros
 * @author Emilie, Hugo, Rémy
 * @version 11-11-2016
 */

public class Heros extends Soldat implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	
	/*****************
	 * Constructeurs *
	 *****************/
	
	public Heros(Carte carte,TypesH type,char ident,String nom,Position pos){
		super(carte,type,nom,pos);
		couleur=COULEUR_HEROS;
		identifiant=""+ident;
	}
	
	/**
	 * Gestion "repos" des heros
	 */
	public void seReposer(){
		this.augmentererPointsVieActuels(1);
		this.setCouleur(COULEUR_HEROS_DEJA_JOUE);
		FenetreJeu.vieArmeeHeros.refresh();
		FenetreJeu.vieArmeeHeros.repaint();
	}
}
