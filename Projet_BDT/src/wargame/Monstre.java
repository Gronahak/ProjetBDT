package wargame;

import java.io.Serializable;

/**
 * Classe Monstre
 * @author emilie 
 * @version 15/11/16
 */
public class Monstre  extends Soldat implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Monstre(Carte carte,TypesM type,char ident,String nom,Position pos){
	 super(carte,type,nom,pos);
		couleur=COULEUR_MONSTRES;
		identifiant=""+('A'-ident);
	}

}
