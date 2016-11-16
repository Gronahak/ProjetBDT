package wargame;

import wargame.ISoldat.TypesM;

/**
 * Classe Heros
 * @author emilie
 * @version 15/11/16
 */

public class Heros extends Soldat{
	private TypesH TYPE;

	public Heros(TypesH type,Position pos){
		super(TypesH.getTypeHAlea(),pos);
		TYPE=type;
		couleur=COULEUR_HEROS;
	}
	
	
}
