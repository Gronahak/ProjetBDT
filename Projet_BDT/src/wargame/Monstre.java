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
//	private TypesM TYPE;
	public Monstre(Carte carte,TypesM type,char ident,String nom,Position pos){
	 super(carte,type,nom,pos);
	// TYPE=type;
		couleur=COULEUR_MONSTRES;
		identifiant=ident;
		System.out.println("BHDBHBDHZBDHZBHDBZHDBZHDBZHBDHZBDHZB"+ident);
	}

}
