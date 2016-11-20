package wargame;

import java.awt.Graphics;

import javax.swing.JPanel;

public class Carte extends JPanel implements ICarte, IConfig {
	private Element[][] elements;
	private Heros[] heros;
	private Monstre[] monstres ;
	private Obstacle[] obstacles;
	private final static int GAUCHE=1;
	private final static int DROITE=2;
	private final static int TOUTE=3;
	Carte() {
		int i, j;
		elements = new Element[LARGEUR_CARTE][HAUTEUR_CARTE];
		for (i = 0; i < LARGEUR_CARTE; i++)
			for (j = 0; j < HAUTEUR_CARTE; j++) {
				elements[i][j] = new Element(new Position(i, j));
			}
		heros = new Heros[NB_HEROS];
		for (i = 0; i < NB_HEROS; i++) {
			heros[i] = new Heros(this,ISoldat.TypesH.getTypeHAlea(),"Y",trouvePositionVide(GAUCHE));
		
			elements[heros[i].getPosition().getX()][heros[i].getPosition().getY()] = heros[i];
		}
		monstres = new Monstre[NB_MONSTRES];
		for (i = 0; i < NB_MONSTRES; i++) {
			monstres[i] = new Monstre(this,ISoldat.TypesM.getTypeMAlea(),"Y",trouvePositionVide(DROITE));
			
			elements[monstres[i].getPosition().getX()][monstres[i].getPosition().getY()] = monstres[i];

		}
		obstacles= new Obstacle[NB_OBSTACLES];
		for (i = 0; i < NB_OBSTACLES; i++) {
			obstacles[i] = new Obstacle(Obstacle.TypeObstacle.getObstacleAlea(),trouvePositionVide(TOUTE));
			
			elements[obstacles[i].getPosition().getX()][obstacles[i].getPosition().getY()] = obstacles[i];

		}
	}

	public Element getElement(Position pos) {
		return elements[pos.getX()][pos.getY()];
	}

	public Position trouvePositionVide(int zone) { // Trouve aléatoirement une position
											// vide sur la carte dans la zone spécifiée (Gauche, Droite, ou toute la carte)
		int x, y, delta=2,decalage=0;
		if (zone==GAUCHE||zone==DROITE){delta =1;}
		if (zone==DROITE){decalage=LARGEUR_CARTE/2;}
		do {
			x = (int) Math.floor(Math.random() * LARGEUR_CARTE*delta/2+decalage);
			y = (int) Math.floor(Math.random() * HAUTEUR_CARTE);
		} while ((elements[x][y] instanceof Soldat) || (elements[x][y] instanceof Obstacle));

		return elements[x][y].pos;

	}

	public Position trouvePositionVide(Position pos) { // Trouve une position
														// vide choisie
														// aléatoirement parmi
														// les 8 positions
														// adjacentes de pos

		int dx, dy;
		int resultatX, resultatY;
		Position resultat;
		do {
			dx = (int) Math.floor(Math.random() * 3) - 1;
			dy = (int) Math.floor(Math.random() * 3) - 1;
			resultatX = pos.getX() + dx;
			resultatY = pos.getY() + dy;
			resultat = new Position(resultatX, resultatY);
		} while (elements[resultatX][resultatY] != null && resultat.estValide());
		return elements[resultatX][resultatY].getPosition();
	}

	public Heros trouveHeros() { // Trouve aléatoirement un héros sur la carte
		return heros[(int) Math.floor(Math.random()) * heros.length];
	}

	public Heros trouveHeros(Position pos) { // Trouve un héros choisi
												// aléatoirement parmi les 8
												// positions adjacentes de pos

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

	public boolean deplaceSoldat(Position pos, Soldat soldat) {
		if (pos.estValide() && elements[pos.getX()][pos.getY()].estVide()) {
			soldat.seDeplace(pos);
			return true;
		}
		return false;
	}

	public void mort(Soldat perso) {
		perso = null;
	}

	public boolean actionHeros(Position pos, Position pos2) {
		return true;
	}

	public void jouerSoldats(PanneauJeu pj) {
	}

	public void toutDessiner(Graphics g) {
		super.paintComponent(g);
		int i, j;

		for (i = 0; i < LARGEUR_CARTE; i++)
			for (j = 0; j < HAUTEUR_CARTE; j++) {
				elements[i][j].seDessiner(g);
				//System.out.println(elements[i][j]);
			}

	}

	public boolean estVide(Position pos) {
		return (elements[pos.getX()][pos.getY()] == null);
	}

	public String nombreSoldatsRestant(){
		return ""+heros.length+" héros et "+monstres.length+" monstres";
	}
}