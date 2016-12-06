package wargame;


/**
 * Classe Monstre
 * @author emilie
 * @version 15/11/16
 */
public class Monstre  extends Soldat{
	private TypesM TYPE;
	
	public Monstre(Carte carte,TypesM type,String nom,Position pos){
	 super(carte,type,nom,pos);
	 TYPE=type;
		couleur=COULEUR_MONSTRES;
	}

}