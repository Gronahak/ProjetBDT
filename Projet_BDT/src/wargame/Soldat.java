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
	private final String nom;
	private final String race;

	private int pointsVieActuels;
	

	public Soldat(Carte carte,TypesM type,String nom,Position pos){
		super(pos);
		pointsVieMax = type.getPoints();
		pointsVieActuels=pointsVieMax;
		portee = type.getPortee();
		tir = type.getTir();
		puissance = type.getPuissance();
		this.nom=nom;
		this.race=type.name();
	}
	public Soldat(Carte carte,TypesH type,String nom,Position pos){
		super(pos);
		pointsVieMax = type.getPoints();
		pointsVieActuels=pointsVieMax;
		portee = type.getPortee();
		tir = type.getTir();
		puissance = type.getPuissance();
		this.nom=nom;
		this.race=type.name();

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
	public String toString(){
		return " "+race+" "+nom+" (" + pointsVieActuels + "PV/" + pointsVieMax + ")";
	}
}