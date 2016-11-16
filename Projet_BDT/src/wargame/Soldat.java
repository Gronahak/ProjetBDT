package wargame;

/**
 * Classe Soldat (Heros et Monstres)
 * @author emilie
 * @version 15/11/16
 */
public  class Soldat  extends Element implements ISoldat  {
	private final int pointsVieMax;
	private final int portee;
	private final int tir;
	private final int puissance;
	private int pointsVieActuels;
	

	public Soldat(TypesM type,Position pos){
		super(pos);
		pointsVieMax = type.getPoints();
		portee = type.getPortee();
		tir = type.getTir();
		puissance = type.getPuissance();
	}
	public Soldat(TypesH type,Position pos){
		super(pos);
		pointsVieMax = type.getPoints();
		portee = type.getPortee();
		tir = type.getTir();
		puissance = type.getPuissance();
	}
	
	public int getTour(){
		return 0;
	}
	
	public void joueTour(int tour){
		
	}
	
	public void combat(Soldat soldat){
		
	}

	public void seDeplace(Position newPos){
		
	}

	public int getPoints() {
		return pointsVieMax;
	}

	public int getPuissance() {
		return puissance;
	}

	public int getTir() {
		return tir;
	}

	public int getPortee() {
		return portee;
	}
}