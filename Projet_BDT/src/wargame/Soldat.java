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
		setCarte(carte);
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
		int forceDeFrappe;
		if (this.pos.estVoisine(soldat.pos)){
			forceDeFrappe=(int)Math.floor(Math.random()*this.getPuissance());
			soldat.diminuerPointsVieActuels(forceDeFrappe);
			if (soldat.getPointsVieActuels()<=0) carte.mort(soldat);
			else {
				forceDeFrappe=(int)Math.floor(Math.random()*soldat.getPuissance());
				this.diminuerPointsVieActuels(forceDeFrappe);
				if (this.getPointsVieActuels()<=0) carte.mort(this);
			}
		}
		else {
			forceDeFrappe=(int)Math.floor(Math.random()*this.getTir());
			soldat.diminuerPointsVieActuels(forceDeFrappe);
			if (soldat.getPointsVieActuels()<=0) carte.mort(soldat);
			else {
				if (soldat.estAPortee(this)){
					forceDeFrappe=(int)Math.floor(Math.random()*soldat.getPuissance());
					this.diminuerPointsVieActuels(forceDeFrappe);
					if (this.getPointsVieActuels()<=0) carte.mort(this);
				}
			}
		}
		
	}

	public void seDeplace(Position newPos){
		if (newPos.estValide()&&carte.estVide(newPos)) {
			this.setPosition(newPos);
		
			System.out.println("Soldat seDeplace" + pos);
		}
	}

	public int getPoints() {
		return pointsVieMax;
	}
	public int getPointsVieActuels() {
		return pointsVieActuels;
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
	public void diminuerPointsVieActuels(int pv){
		pointsVieActuels-=pv;
	}
	public String toString(){
		return " "+race+" "+nom+" (" + pointsVieActuels + "PV/" + pointsVieMax + ")";
	}
	
	public Boolean estAPortee(Soldat ennemi){
		if (this.pos.distanceX(ennemi.pos)<=this.getPortee()&&this.pos.distanceY(ennemi.pos)<=this.getPortee()) return true;
		return false;
	}
}