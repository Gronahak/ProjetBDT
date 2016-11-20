package wargame;

import wargame.ISoldat.TypesM;

/**
 * Classe Heros
 * @author emilie
 * @version 15/11/16
 */

public class Heros extends Soldat{
	private TypesH TYPE;

	public Heros(Carte carte,TypesH type,String nom,Position pos){
		super(carte,type,nom,pos);
		TYPE=type;
		couleur=COULEUR_HEROS;
	}
	
	
}
