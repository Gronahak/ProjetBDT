package wargame;

import wargame.ISoldat.TypesM;

/**
 * Classe Heros
 * @author emilie
 * @version 15/11/16
 */

public class Heros extends Soldat{
	private TypesH TYPE;

	public Heros(Carte carte,TypesH type,char ident,String nom,Position pos){
		super(carte,type,nom,pos);
		TYPE=type;
		couleur=COULEUR_HEROS;
		identifiant=ident;
	}
	
	public void seReposer(){
		this.augmentererPointsVieActuels(1);
		this.setCouleur(COULEUR_HEROS_DEJA_JOUE);
		FenetreJeu.vieArmeeHeros.refresh();
		FenetreJeu.vieArmeeHeros.repaint();
		
	}
	
	
}
