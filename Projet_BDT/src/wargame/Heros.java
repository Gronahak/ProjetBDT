package wargame;

/**
 * Classe Heros
 * @author emilie
 * @version 15/11/16
 */

public class Heros extends Soldat{
	public Heros(){
		super();
		TypesH type = TypesH.getTypeHAlea();
		points = type.getPoints();
		portee = type.getPortee();
		puissance = type.getPuissance();
		tir = type.getTir(); 
	}
	
	public int getPoints(){
		return points;
	}

	public int getPortee(){
		return portee;
	}
	
	public int getPuissance(){
		return puissance;
	}

	public int getTir(){
		return tir;
	}
}
