package wargame;

import java.io.Serializable;

//import wargame.ISoldat.TypesM;

/**
 * Classe Heros
 * @author emilie
 * @version 15/11/16
 */

public class Heros extends Soldat implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Heros(Carte carte,TypesH type,char ident,String nom,Position pos){
		super(carte,type,nom,pos);
		couleur=COULEUR_HEROS;
		identifiant=""+ident;
	}
	
	public void seReposer(){
		this.augmentererPointsVieActuels(1);
		this.setCouleur(COULEUR_HEROS_DEJA_JOUE);
		FenetreJeu.vieArmeeHeros.refresh();
		FenetreJeu.vieArmeeHeros.repaint();
		
	}
	
	
}
