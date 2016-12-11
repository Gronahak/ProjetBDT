package wargame;

import java.awt.Font;
import java.awt.Graphics;
import java.io.Serializable;

/**
 * Classe Soldat (Heros et Monstres)
 * @author emilie 
 * @version 15/11/16
 */
public  class Soldat  extends Element implements ISoldat, Serializable  {
	/**
	 * 
	 */
	private int nombreVictimes;
	private static final long serialVersionUID = 1L;
	private final int pointsVieMax;
	private final int portee;
	private final int tir;
	private final int puissance;
	private final String nom;
	private final String race;
	protected String identifiant;
	private int pointsVieActuels;
	

	public Soldat(Carte carte,TypesM type,String nom,Position pos){
		super(pos);
		nombreVictimes=0;
		pointsVieMax = type.getPoints();
		pointsVieActuels=pointsVieMax;
		portee = type.getPortee();
		tir = type.getTir();
		puissance = type.getPuissance();
		this.nom=nom;
		this.race=type.name();
		setCarte(carte);
		estVide=false;
	}
	public Soldat(Carte carte,TypesH type,String nom,Position pos){
		super(pos);
		nombreVictimes=0;
		pointsVieMax = type.getPoints();
		pointsVieActuels=pointsVieMax;
		portee = type.getPortee();
		tir = type.getTir();
		puissance = type.getPuissance();
		this.nom=nom;
		this.race=type.name();
		estVide=false;
		setCarte(carte);

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
			
		
			if (soldat.getPointsVieActuels()<=0) {carte.mort(soldat); this.nombreVictimes++;}
			else {
				forceDeFrappe=(int)Math.floor(Math.random()*soldat.getPuissance());
				this.diminuerPointsVieActuels(forceDeFrappe);
			
				if (this.getPointsVieActuels()<=0) {carte.mort(this); soldat.nombreVictimes++;}
			}
		}
		else {
			forceDeFrappe=(int)Math.floor(Math.random()*this.getTir());
			soldat.diminuerPointsVieActuels(forceDeFrappe);
	
			if (soldat.getPointsVieActuels()<=0) {carte.mort(soldat);this.nombreVictimes++;}
			else {
				if (soldat.estAPortee(this)){
					forceDeFrappe=(int)Math.floor(Math.random()*soldat.getTir());
					this.diminuerPointsVieActuels(forceDeFrappe);
		
					if (this.getPointsVieActuels()<=0) {carte.mort(this);soldat.nombreVictimes++;}
				}
			}
		}
		FenetreJeu.vieArmeeMonstres.refresh(); // Fonction qui recalcule la jauge de vie
		FenetreJeu.vieArmeeHeros.refresh();
		FenetreJeu.vieArmeeMonstres.repaint();
		FenetreJeu.vieArmeeHeros.repaint();
	}

	public void seDeplace(Position newPos){
		if (newPos.estValide()&&carte.estVide(newPos)) {
			carte.elements[pos.getX()][pos.getY()].setEstVide(true);
			this.setPosition(newPos);
			carte.elements[pos.getX()][pos.getY()]=this;
			carte.elements[pos.getX()][pos.getY()].setEstVide(false);
		
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
		if (pointsVieActuels<0)pointsVieActuels=0;
			
	}
	public void augmentererPointsVieActuels(int pv){
		pointsVieActuels+=pv;
		if (pointsVieActuels>pointsVieMax)pointsVieActuels=pointsVieMax;
			
	}
	public String toString(){
		return " "+race+" "+nom+" (" + pointsVieActuels + "PV/" + pointsVieMax + ")"+" (Victimes : "+nombreVictimes+" )";
	}
	
	public Boolean estAPortee(Soldat ennemi){
		if (this.pos.distanceX(ennemi.pos)<=this.getPortee()&&this.pos.distanceY(ennemi.pos)<=this.getPortee()) return true;
		return false;
	}
	public void seDessiner(Graphics g) {
		super.seDessiner(g);
		g.setColor(COULEUR_EAU);
		Font police=new Font("Arial",7, 28);
		g.setFont(police);
		g.setColor(COULEUR_EAU);
		if (this.estVisible)g.drawString(""+identifiant, pos.getX()*NB_PIX_CASE, NB_PIX_CASE*2/3+pos.getY()*NB_PIX_CASE);

	}

		
}