package wargame;

import java.io.Serializable;

/**
 * Classe monstre
 * @author Emilie, Hugo, Rémy
 * @version 11-11-2016
 */

public class Monstre  extends Soldat implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/*****************
	 * Constructeurs *
	 *****************/
	
	public Monstre(Carte carte,TypesM type,char ident,String nom,Position pos){
		super(carte,type,nom,pos);
		couleur=COULEUR_MONSTRES;
		identifiant=""+('A'-ident);
		System.out.println("TEST ident"+('A'-ident));
	}

}
