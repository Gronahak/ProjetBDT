package wargame;

import java.io.Serializable;

/**
 * Classe Monstre
 * @author emilie
 * @version 15/11/16
 */
public class Monstre  extends Soldat implements Serializable{
	
	private static final long serialVersionUID = -6033569829337469303L;
	private TypesM TYPE;
	
	public Monstre(Carte carte,TypesM type,String nom,Position pos){
	 super(carte,type,nom,pos);
	 TYPE=type;
		couleur=COULEUR_MONSTRES;
	}

}
