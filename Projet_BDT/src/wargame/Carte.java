package wargame;

import java.awt.Graphics;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;

import javax.swing.JPanel;

/**
 * Classe carte
 * @author Emilie, Hugo, Rémy
 * @version 11-11-2016
 */

public class Carte extends JPanel implements ICarte, IConfig , Serializable {

	private static final long serialVersionUID = 1L;
	protected Element[][] elements; /* C'est ce tableau d'éléments qui constitue la carte à proprement parler */

	private Heros[] heros; /* Obsolète maintenant qu'on utilise les HashSets*/
	private Monstre[] monstres ; /* idem */ 

	public static HashSet<Heros> hsHeros; /* Ces deux HashSet permettent de retrouver plu facilement les héros et les monstres ( sans avoir à parcourir l'intégralité des Éléments de la carte */
	public static HashSet<Monstre> hsMonstres;
	private Obstacle[] obstacles;
	Carte() {
		int i, j;

		/******************
		 * Initialisation *		
		 ******************/

		elements = new Element[LARGEUR_CARTE][HAUTEUR_CARTE];
		for (i = 0; i < LARGEUR_CARTE; i++)
			for (j = 0; j < HAUTEUR_CARTE; j++) {
				elements[i][j] = new Element(new Position(i, j));
			}

		/*******************
		 * Ajout des heros *
		 *******************/

		hsHeros=new HashSet<Heros>();
		char id='A';
		int sommeHP=0,soldatHP=0; /* Ces 2 variables servent à initialiser la jauge de l'armée des Gentils ( à gauche )*/
		for (i = 0; i < NB_HEROS; i++) {
			Heros h = new Heros(this,ISoldat.TypesH.getTypeHAlea(),id,"Y",trouvePositionVide(GAUCHE));
			id++;
			soldatHP=h.getPoints();
			FenetreJeu.vieArmeeHeros.addHp(soldatHP);
			sommeHP+=soldatHP;
			hsHeros.add(h); /* On ajoute les héros au Hset et au tableau d'éléments */
			elements[h.getPosition().getX()][h.getPosition().getY()] = h;
		}
		id='1';
		FenetreJeu.vieArmeeHeros.setHp(sommeHP);

		/**********************
		 * Ajout des monstres *
		 **********************/

		sommeHP=0;
		soldatHP=0;
		hsMonstres=new HashSet<Monstre>();
		for (i = 0; i < NB_MONSTRES; i++) {
			Monstre m = new Monstre(this,ISoldat.TypesM.getTypeMAlea(),id,"Y",trouvePositionVide(DROITE));
			id++;
			soldatHP=m.getPoints();
			FenetreJeu.vieArmeeMonstres.addHp(soldatHP);
			sommeHP+=soldatHP;
			hsMonstres.add(m);
			elements[m.getPosition().getX()][m.getPosition().getY()] = m;
		}			
		FenetreJeu.vieArmeeMonstres.setHp(sommeHP);

		/***********************
		 * Ajout des obstacles *
		 ***********************/

		obstacles= new Obstacle[NB_OBSTACLES];
		for (i = 0; i < NB_OBSTACLES; i++) {
			obstacles[i] = new Obstacle(Obstacle.TypeObstacle.getObstacleAlea(),trouvePositionVide(TOUTE));
			elements[obstacles[i].getPosition().getX()][obstacles[i].getPosition().getY()] = obstacles[i];
		}
	}

	/**
	 * Recuperer un element sur une position
	 * @param pos
	 */
	public Element getElement(Position pos) { // Retourne l'element du tableau à la position <pos>
		return elements[pos.getX()][pos.getY()];
	}

	/**
	 * Trouver une position vide selon une zone
	 * @param zone gauche, droite
	 */
	public Position trouvePositionVide(int zone) {
		int x, y, delta=2,decalage=0;
		if (zone==GAUCHE||zone==DROITE){delta =1;}
		if (zone==DROITE){decalage=LARGEUR_CARTE/2;}
		do {
			x = (int) Math.floor(Math.random() * LARGEUR_CARTE*delta/2+decalage);
			y = (int) Math.floor(Math.random() * HAUTEUR_CARTE);
		} while ((elements[x][y] instanceof Soldat) || (elements[x][y] instanceof Obstacle));

		return elements[x][y].pos;
	}

	/**
	 * Trouver une position vide autour d'une position
	 * @param pos
	 */
	public Position trouvePositionVide(Position pos) {
		int dx, dy;
		int passerTour=0;
		int resultatX, resultatY;
		Position resultat;
		do {
			passerTour++;
			dx = (int) Math.floor(Math.random() * 3) - 1;
			dy = (int) Math.floor(Math.random() * 3) - 1;
			resultatX = pos.getX() + dx;
			resultatY = pos.getY() + dy;
			resultat = new Position(resultatX, resultatY);
		} while (!(resultat.estValide() && elements[resultatX][resultatY].estVide  && passerTour < 50 ));
		return elements[resultatX][resultatY].getPosition();
	}

	/**
	 * Trouver un heros sur la carte
	 */
	public Heros trouveHeros() {
		heros=(Heros[]) hsHeros.toArray();
		return heros[(int) Math.floor(Math.random()) * heros.length];
	}

	/**
	 * Trouver un heros autour d'une positin
	 * @param pos
	 */
	public Heros trouveHeros(Position pos) {
		Heros[] listeHerosVoisins = {};
		Position check = new Position(pos.getX(), pos.getY());
		int i, j, k = 0;
		for (i = -1; i <= 1; i++) {
			for (j = -1; j <= 1; j++) {
				check.setX(check.getX() + i);
				check.setY(check.getY() + j);
				if (!check.estValide() || (i == 0 && j == 0))
					continue;
				if (elements[check.getX()][check.getY()] instanceof Heros)
					listeHerosVoisins[k++] = (Heros) elements[check.getX()][check.getY()];
			}
		}
		if (listeHerosVoisins.length == 0)
			return null;
		return listeHerosVoisins[(int) Math.floor(Math.random() * listeHerosVoisins.length)];
	}

	/**
	 * Deplacer un soldat
	 * @param pos nouvelle position
	 * @param soldat soldat a deplacer
	 */
	public boolean deplaceSoldat(Position pos, Soldat soldat) {
		if (pos.estVoisine(soldat.pos) && pos.estValide() && elements[pos.getX()][pos.getY()].estVide()) {
			elements[soldat.pos.getX()][soldat.pos.getY()]=new Element(soldat.pos);

			soldat.seDeplace(pos);
			elements[pos.getX()][pos.getY()].setEstVide(false);;

			actualiserChampDeVision();
			return true;
		}
		return false;
	}

	/**
	 * Tuer un soldat
	 * @param perso soldat a tuer
	 */
	public void mort(Soldat perso) {
		elements[perso.getPosition().getX()][perso.getPosition().getY()].setCouleur(COULEUR_VIDE);
		elements[perso.getPosition().getX()][perso.getPosition().getY()]=new Element(new Position(perso.getPosition().getX(),perso.getPosition().getY()));
		hsHeros.remove(perso);
		hsMonstres.remove(perso);
		actualiserChampDeVision();
		FenetreJeu.encoreEnVie.setText("Il reste "+nombreSoldatsRestant());
	}

	/**
	 * Gerer les actions d'un heros
	 */
	public boolean actionHeros(Position pos, Position pos2) {

		if (!(elements[pos.getX()][pos.getY()] instanceof Heros)){
			return false;
		}

		if (elements[pos.getX()][pos.getY()].getCouleur()==COULEUR_HEROS_DEJA_JOUE)return false;
		
		if (elements[pos2.getX()][pos2.getY()].estVide()&&pos2.estVoisine(pos)) {
			deplaceSoldat(pos2,(Soldat) elements[pos.getX()][pos.getY()]);
			elements[pos2.getX()][pos2.getY()].setCouleur(COULEUR_HEROS_DEJA_JOUE);
			return true;
		}
		
		else if (elements[pos2.getX()][pos2.getY()] instanceof Monstre
				&& (((Soldat)elements[pos.getX()][pos.getY()]).estAPortee((Monstre)elements[pos2.getX()][pos2.getY()]))
				){			elements[pos.getX()][pos.getY()].setCouleur(COULEUR_HEROS_DEJA_JOUE);

				((Soldat)elements[pos.getX()][pos.getY()]).combat((Soldat)elements[pos2.getX()][pos2.getY()]);
				return true;
		}
		return false;
	}

	/**
	 * Gerer le tour d'un soldat
	 */
	public void jouerSoldats(PanneauJeu pj) {
		Iterator<Heros> iterateurHeros = hsHeros.iterator();
		while(iterateurHeros.hasNext()){
			Object herosN=iterateurHeros.next();
			if (((Element )herosN).getCouleur()!=COULEUR_HEROS_DEJA_JOUE){
				((Heros) herosN).seReposer();
			}
		}
		repaint();
		tourMonstres();
		iterateurHeros = hsHeros.iterator();
		while(iterateurHeros.hasNext()){
			Object herosN=iterateurHeros.next();
			((Element)herosN).setCouleur(COULEUR_HEROS);		    
		}

	}

	public void toutDessiner(Graphics g) {
		super.paintComponent(g);
		int i, j;

		actualiserChampDeVision();
		for (j = 0; j < HAUTEUR_CARTE; j++) {
			for (i = 0; i < LARGEUR_CARTE; i++){

				elements[i][j].seDessiner(g);
			}
		}
	}

	public boolean estVide(Position pos) {
		return (elements[pos.getX()][pos.getY()].couleur == COULEUR_VIDE);
	}

	public String nombreSoldatsRestant(){
		Object[] heros = hsHeros.toArray();
		Object[] monstres = hsMonstres.toArray();		
		return ""+heros.length+" héros et "+monstres.length+" monstres";
	}
	
	public void actualiserChampDeVision(){
		int k,l;
		Object[] heros = hsHeros.toArray();
		for (k=0;k<LARGEUR_CARTE;k++)
			for(l=0;l<HAUTEUR_CARTE;l++)
				elements[k][l].estVisible=false;
		for (Object h:heros){
			int porteeVisuelle=((Soldat) h).getPortee();
			Position positionTmp=new Position(0,0);
			for (int i=((Element) h).getPosition().getX()-porteeVisuelle;i<=((Element) h).getPosition().getX()+porteeVisuelle;i++){
				for (int j=((Element) h).getPosition().getY()-porteeVisuelle;j<=((Element) h).getPosition().getY()+porteeVisuelle;j++){
					positionTmp.setX(i);
					positionTmp.setY(j);
					if (!positionTmp.estValide())continue;
					elements[positionTmp.getX()][positionTmp.getY()].estVisible=true;
					repaint();
				}
			}
		}
	}
	
	private void tourMonstres(){
		Object[] monstres = hsMonstres.toArray();
		for(Object o : monstres){
			Position deplacement = new Position();
			deplacement =trouvePositionVide(((Element) o).getPosition());
			elements[((Element) o).getPosition().getX()][((Element) o).getPosition().getY()].setEstVide(true);
			deplaceSoldat(deplacement,(Soldat) o);
		}
		repaint();
	}
}