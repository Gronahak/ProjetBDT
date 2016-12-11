package wargame;

import java.awt.Font;
import java.awt.Graphics;
import java.io.Serializable;

/**
 * Classe soldat
 * @author Emilie, Hugo, Rémy
 * @version 11-11-2016
 */

public  class Soldat  extends Element implements ISoldat, Serializable  {

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

	/*****************
	 * Constructeurs * 
	 *****************/
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

	/**************
	 * Accesseurs *
	 **************/
	public int getTour(){
		return 0;
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

	public void joueTour(int tour){

	}

	/**
	 * Action a faire pour un soldat choisi
	 * @param soldat
	 */
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

	/**
	 * Deplacer un element a une nouvelle position choisie
	 * @param newPos nouvelle position
	 */
	public void seDeplace(Position newPos){
		if (newPos.estValide()&&carte.estVide(newPos)) {
			carte.elements[pos.getX()][pos.getY()].setEstVide(true);
			this.setPosition(newPos);
			carte.elements[pos.getX()][pos.getY()]=this;
			carte.elements[pos.getX()][pos.getY()].setEstVide(false);

		}
	}

	/**
	 * Diminuer les pv actuels
	 * @param pv nombre de pv a enlever
	 */
	public void diminuerPointsVieActuels(int pv){
		pointsVieActuels-=pv;
		if (pointsVieActuels<0)pointsVieActuels=0;
	}
	
	/**
	 * Augmenter les pv actuels
	 * @param pv nombre de pv a ajouter
	 */
	public void augmentererPointsVieActuels(int pv){
		pointsVieActuels+=pv;
		if (pointsVieActuels>pointsVieMax)pointsVieActuels=pointsVieMax;
	}
	
	public String toString(){
		return " "+race+" "+nom+" (" + pointsVieActuels + "PV/" + pointsVieMax + ")"+" (Victimes : "+nombreVictimes+" )";
	}

	/**
	 * Tester si un ennemi est a portee
	 * @param ennemi soldat a tester
	 * @return true il est a portee
	 * @return false il n'est pas a portee
	 */
	public Boolean estAPortee(Soldat ennemi){
		if (this.pos.distanceX(ennemi.pos)<=this.getPortee()&&this.pos.distanceY(ennemi.pos)<=this.getPortee()) return true;
		return false;
	}

	/**
	 * Dessiner dans le graphic choisi
	 * @param g graphic ou dessiner
	 */
	public void seDessiner(Graphics g) {
		super.seDessiner(g);
		g.setColor(COULEUR_EAU);
		Font police=new Font("Arial",7, 28);
		g.setFont(police);
		g.setColor(COULEUR_EAU);
		if (this.estVisible)g.drawString(""+identifiant, pos.getX()*NB_PIX_CASE, NB_PIX_CASE*2/3+pos.getY()*NB_PIX_CASE);
	}
}