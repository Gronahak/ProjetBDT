package wargame;

import java.awt.Graphics;

import javax.swing.JPanel;

public class Carte extends JPanel implements ICarte, IConfig {
	private Element[][] elements;
	private Heros[] heros;
	private Monstre[] monstres ;
	private Obstacle[] obstacles;
//	private static int nbH = NB_HEROS;
//	private static int nbM = NB_MONSTRES;
	private final static int GAUCHE=1;
	private final static int DROITE=2;
	private final static int TOUTE=3;
	Carte() {
		int i, j;
		//initialisation tableau elements
		elements = new Element[LARGEUR_CARTE][HAUTEUR_CARTE];
		for (i = 0; i < LARGEUR_CARTE; i++)
			for (j = 0; j < HAUTEUR_CARTE; j++) {
				elements[i][j] = new Element(new Position(i, j));
			}
		//initialisation tableau heros
		heros = new Heros[NB_HEROS];
		for (i = 0; i < NB_HEROS; i++) {
			heros[i] = new Heros(this,ISoldat.TypesH.getTypeHAlea(),"Y",trouvePositionVide(GAUCHE));
		
			elements[heros[i].getPosition().getX()][heros[i].getPosition().getY()] = heros[i];
		}
		//initialisation tableau monstres
		monstres = new Monstre[NB_MONSTRES];
		for (i = 0; i < NB_MONSTRES; i++) {
			monstres[i] = new Monstre(this,ISoldat.TypesM.getTypeMAlea(),"Y",trouvePositionVide(DROITE));
			
			elements[monstres[i].getPosition().getX()][monstres[i].getPosition().getY()] = monstres[i];

		}
		//initialisation tableau obstacles
		obstacles= new Obstacle[NB_OBSTACLES];
		for (i = 0; i < NB_OBSTACLES; i++) {
			obstacles[i] = new Obstacle(Obstacle.TypeObstacle.getObstacleAlea(),trouvePositionVide(TOUTE));
			
			elements[obstacles[i].getPosition().getX()][obstacles[i].getPosition().getY()] = obstacles[i];

		}
	}

	public Element getElement(Position pos) {
		return elements[pos.getX()][pos.getY()];
	}
	
	public void setElement(Element e, Position pos){
		this.elements[pos.getX()][pos.getY()] = e;
	}

	public void echangeElement(Position pos1, Position pos2){
		Element tmp = getElement(pos1);
		setElement(getElement(pos2), pos1);
		setElement(tmp, pos2);
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

	public Position trouvePositionVide(Position pos) { 	// Trouve une position
														// vide choisie
														// aléatoirement parmi
														// les 8 positions
														// adjacentes de pos
		int dx, dy;
		//int resultatX, resultatY;
		Position resultat;
		do {
			dx = (int) Math.floor(Math.random() * 3) - 1;
			dy = (int) Math.floor(Math.random() * 3) - 1;
//			resultatX = pos.getX() + dx;
//			resultatY = pos.getY() + dy;
//			resultat = new Position(resultatX, resultatY);
//		} while (!resultat.estValide() || !elements[resultatX][resultatY].estVide());
//		return elements[resultatX][resultatY].getPosition();
			resultat = new Position(pos.getX() + dx, pos.getY() + dy);
			} while (!resultat.estValide() || !elements[pos.getX() + dx][pos.getY() + dy].estVide());
			return elements[pos.getX() + dx][pos.getY() + dy].getPosition();
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
			elements[soldat.pos.getX()][soldat.pos.getY()]=new Element(soldat.pos);
			System.out.println("1 "+elements[soldat.pos.getX()][soldat.pos.getY()]+"\n2 "+elements[soldat.pos.getX()][soldat.pos.getY()].couleur+"\n3 "+elements[soldat.pos.getX()][soldat.pos.getY()].pos);

			//System.out.println("1\t"+soldat.getPosition());
			System.out.println("WTF1 : pos de soldat: "+soldat.pos+"WTF pos de elements"+elements[pos.getX()][pos.getY()].pos);
			System.out.println("On est d'accord y'a rien dans elem:"+elements[pos.getX()][pos.getY()].couleur);
			soldat.seDeplace(pos);
			System.out.println("WTF2 : pos de soldat: "+soldat.pos+"WTF pos de elements"+elements[pos.getX()][pos.getY()].pos);
			
			elements[pos.getX()][pos.getY()]=soldat;
			System.out.println("WTF3 : pos de soldat: "+soldat.pos+"WTF pos de elements"+elements[pos.getX()][pos.getY()].pos);
			
			//((Soldat)elements[pos.getX()][pos.getY()]).seDeplace(pos);
			//System.out.println("2\t"+soldat.getPosition());
			System.out.println(elements[pos.getX()][pos.getY()]+""+elements[pos.getX()][pos.getY()].couleur+""+elements[pos.getX()][pos.getY()].pos);

			soldat.setTour(false);
			actualiserChampDeVision();
			return true;
		}
		return false;
	}

	public void mort(Soldat perso) {
		elements[perso.getPosition().getX()][perso.getPosition().getY()]=new Element(new Position(perso.getPosition().getX(),perso.getPosition().getY()));
//		if (perso instanceof Heros){
//			nbH --;	
//		}
//		if (perso instanceof Monstre){
//			nbM --;	
//		}
		/* utiliser tables de hash, au moins pour les 2 tableaux monstres et héros et virer le soldat du tableau le cas échéant quand il meurt pour perdre la vision autour du-dit soldat*/
		actualiserChampDeVision();

	}

	public boolean actionHeros(Position pos, Position pos2) {

		if (!(elements[pos.getX()][pos.getY()] instanceof Heros)){
			return false;
		}
		
		if(((Soldat)elements[pos.getX()][pos.getY()]).getTour()){		//verifie que le soldat n'a pas joué ce tour
			((Soldat)elements[pos.getX()][pos.getY()]).setTour(false);	//previent que le soldat joue ce tour

			if (elements[pos2.getX()][pos2.getY()].estVide() && pos2.estVoisine(pos)) {
																//ajout ^ pour se deplacer d'une seule case
				deplaceSoldat(pos2,(Soldat) elements[pos.getX()][pos.getY()]);
				return true;
	
			}
			else if (elements[pos2.getX()][pos2.getY()] instanceof Monstre
					&& (((Soldat)elements[pos.getX()][pos.getY()]).estAPortee((Monstre)elements[pos2.getX()][pos2.getY()]))
					){
				((Soldat)elements[pos.getX()][pos.getY()]).combat((Soldat)elements[pos2.getX()][pos2.getY()]);
				return true;
	
			}
		}
		
		return false;
	}

	public void jouerSoldats(PanneauJeu pj) {
	}

	public void toutDessiner(Graphics g) {
		super.paintComponent(g);
		int i, j;

		actualiserChampDeVision();
		for (i = 0; i < LARGEUR_CARTE; i++)
			for (j = 0; j < HAUTEUR_CARTE; j++) {
				//System.out.println("("+i+" "+j+"(");
				elements[i][j].seDessiner(g);
				}
		System.out.println("______________________");

	}

	public boolean estVide(Position pos) {
		return (elements[pos.getX()][pos.getY()].couleur == COULEUR_VIDE);
	}

	public String nombreSoldatsRestant(){
		//return ""+nbH+" héros et "+nbM+" monstres";
		return ""+heros.length+" héros et "+monstres.length+" monstres";
	}
	public void actualiserChampDeVision(){
		int k,l;
		for (k=0;k<LARGEUR_CARTE;k++)
			for(l=0;l<HAUTEUR_CARTE;l++)
				elements[k][l].estVisible=false;
		for (final Heros h:heros){
			int porteeVisuelle=h.getPortee();
			Position positionTmp=new Position(0,0);
			for (int i=h.getPosition().getX()-porteeVisuelle;i<=h.getPosition().getX()+porteeVisuelle;i++){
				for (int j=h.getPosition().getY()-porteeVisuelle;j<=h.getPosition().getY()+porteeVisuelle;j++){
					positionTmp.setX(i);
					positionTmp.setY(j);
					if (!positionTmp.estValide())continue;
					elements[positionTmp.getX()][positionTmp.getY()].estVisible=true;
				//	System.out.println("elements ("+positionTmp.getX()+","+positionTmp.getY()+""+elements[positionTmp.getX()][positionTmp.getY()].estVisible);
					repaint();
				}
				
			}
		}
	}
	
	public void finTourH(){
		for(int i=0; i<NB_HEROS; i++){
			heros[i].setTour(true);
		}
	}
	
	public void tourMonstre(){
		for(int i=0; i<NB_MONSTRES; i++){
			int j;
//			Element e = elements[monstres[i].getPosition().getX()][monstres[i].getPosition().getY()];
			for(j=0; j<NB_HEROS; j++){
				if(monstres[i].estAPortee(heros[j])){ 	//attaque si heros a portée
					System.out.println(i+" attaque "+j);
					monstres[i].combat(heros[j]);
					j=NB_HEROS;
				}else{
					if(j==NB_HEROS-1){ 					//sinon se deplace
						System.out.println(i+" se deplace");
						//echangeElement(monstres[i].pos, trouvePositionVide(monstres[i].pos));
						monstres[i].seDeplace(trouvePositionVide(monstres[i].pos));
					}
				}
			}
			try {
				Thread.sleep(150); //laisse le temps de voir les deplacements des monstres
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}