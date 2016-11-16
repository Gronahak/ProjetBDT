package wargame;


/**
 * Classe Monstre
 * @author emilie
 * @version 15/11/16
 */
public class Monstre  extends Soldat{
	private TypesM TYPE;
	
	public Monstre(TypesM type,Position pos){
	 super(type,pos);
	 TYPE=type;
		couleur=COULEUR_MONSTRES;
	}

}
