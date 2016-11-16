package wargame;

/**
 * Classe Monstre
 * @author emilie
 * @version 15/11/16
 */
public class Monstre  extends Soldat{
	private TypesM type;
	
	public Monstre(){
		type = TypesM.getTypeMAlea();
	}
	
	public int getPoints(){
		return type.getPoints();
	}

	public int getPortee(){
		return type.getPortee();
	}
	
	public int getPuissance(){
		return type.getPuissance();
	}

	public int getTir(){
		return type.getTir();
	}
}
