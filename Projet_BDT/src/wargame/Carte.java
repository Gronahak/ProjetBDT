package wargame;

import java.awt.Graphics;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;

//import javax.swing.JLabel;
import javax.swing.JPanel;

public class Carte extends JPanel implements ICarte, IConfig , Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Element[][] elements;
	private Heros[] heros;
	private Monstre[] monstres ;
	public static HashSet<Heros> hsHeros;
	public static HashSet<Monstre> hsMonstres;
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
		
		/*
		heros = new Heros[NB_HEROS];
		for (i = 0; i < NB_HEROS; i++) {
			heros[i] = new Heros(this,ISoldat.TypesH.getTypeHAlea(),"Y",trouvePositionVide(GAUCHE));
			
			elements[heros[i].getPosition().getX()][heros[i].getPosition().getY()] = heros[i];
		}
		*/
		/*
		monstres = new Monstre[NB_MONSTRES];
		for (i = 0; i < NB_MONSTRES; i++) {
			monstres[i] = new Monstre(this,ISoldat.TypesM.getTypeMAlea(),"Y",trouvePositionVide(DROITE));
			
			elements[monstres[i].getPosition().getX()][monstres[i].getPosition().getY()] = monstres[i];
		}
		*/
		hsHeros=new HashSet<Heros>();
		char id='A';
		int sommeHP=0,soldatHP=0;
		for (i = 0; i < NB_HEROS; i++) {
			Heros h = new Heros(this,ISoldat.TypesH.getTypeHAlea(),id,"Y",trouvePositionVide(GAUCHE));
			System.out.println("Caractère "+i+ " : "+id);
			id++;

			soldatHP=h.getPoints();
			FenetreJeu.vieArmeeHeros.addHp(soldatHP);
			sommeHP+=soldatHP;
			hsHeros.add(h);
			elements[h.getPosition().getX()][h.getPosition().getY()] = h;
		}
		id='1';
		FenetreJeu.vieArmeeHeros.setHp(sommeHP);
		sommeHP=0;
		soldatHP=0;
		hsMonstres=new HashSet<Monstre>();
		for (i = 0; i < NB_MONSTRES; i++) {
			System.out.println("Caractère "+i+ " : "+(id+i));

			Monstre m = new Monstre(this,ISoldat.TypesM.getTypeMAlea(),id,"Y",trouvePositionVide(DROITE));
	//		System.out.println("CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC"+m.estVide());
id++;
			soldatHP=m.getPoints();

			FenetreJeu.vieArmeeMonstres.addHp(soldatHP);
			sommeHP+=soldatHP;
			hsMonstres.add(m);
			elements[m.getPosition().getX()][m.getPosition().getY()] = m;
		}			
		FenetreJeu.vieArmeeMonstres.setHp(sommeHP);

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
			System.out.println("jjjjj"+resultat);
		} while (!(resultat.estValide() && elements[resultatX][resultatY].estVide   ));
		return elements[resultatX][resultatY].getPosition();
	}

	public Heros trouveHeros() { // Trouve aléatoirement un héros sur la carte
		
		heros=(Heros[]) hsHeros.toArray();
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
		if (pos.estVoisine(soldat.pos) && pos.estValide() && elements[pos.getX()][pos.getY()].estVide()) {
			elements[soldat.pos.getX()][soldat.pos.getY()]=/*soldat;*/new Element(soldat.pos);
			System.out.println(elements[soldat.pos.getX()][soldat.pos.getY()]+""+elements[soldat.pos.getX()][soldat.pos.getY()].couleur+""+elements[soldat.pos.getX()][soldat.pos.getY()].pos);

			//System.out.println("1\t"+soldat.getPosition());
		//	System.out.println("WTF1 : pos de soldat: "+soldat.pos+"WTF pos de elements"+elements[pos.getX()][pos.getY()].pos);
//System.out.println("On est d'accord y'a rien dans elem:"+elements[pos.getX()][pos.getY()].couleur);
			soldat.seDeplace(pos);
			elements[pos.getX()][pos.getY()].setEstVide(false);;
			
	//		System.out.println("WTF2 : pos de soldat: "+soldat.pos+"WTF pos de elements"+elements[pos.getX()][pos.getY()].pos);
			
		//	elements[pos.getX()][pos.getY()]=soldat;
		//	System.out.println("WTF3 : pos de soldat: "+soldat.pos+"WTF pos de elements"+elements[pos.getX()][pos.getY()].pos);
			
//((Soldat)elements[pos.getX()][pos.getY()]).seDeplace(pos);
			//System.out.println("2\t"+soldat.getPosition());
		//	System.out.println(elements[pos.getX()][pos.getY()]+""+elements[pos.getX()][pos.getY()].couleur+""+elements[pos.getX()][pos.getY()].pos);

			actualiserChampDeVision();
			return true;
		}
		return false;
	}

	public void mort(Soldat perso) {
		elements[perso.getPosition().getX()][perso.getPosition().getY()]=new Element(new Position(perso.getPosition().getX(),perso.getPosition().getY()));
		hsHeros.remove(perso);
		hsMonstres.remove(perso);
		/* utiliser tables de hash, au moins pour les 2 tableaux monstres et héros et virer le soldat du tableau le cas échéant quand il meurt pour perdre la vision autour du-dit soldat*/
		actualiserChampDeVision();
		FenetreJeu.encoreEnVie.setText("Il reste "+nombreSoldatsRestant());

	}

	public boolean actionHeros(Position pos, Position pos2) {

		//if (hsHeros.contains(elements[pos.getX()][pos.getY()])) System.out.println("HHHHHHOH");
		//else System.out.println("iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiOH");
		Iterator<Heros> iterateurHeros = hsHeros.iterator();
	/*	while(iterateurHeros.hasNext()){
		     // System.out.println(iterateurHeros.next());
		//    Object her=iterateurHeros.next();
		 //   System.out.println("itemgle: "+her);
			 
		   // if (her==elements[pos.getX()][pos.getY()])System.out.println("mouiii");else System.out.println("mmnooooon");
		}*/
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
				){
			((Soldat)elements[pos.getX()][pos.getY()]).combat((Soldat)elements[pos2.getX()][pos2.getY()]);
			elements[pos.getX()][pos.getY()].setCouleur(COULEUR_HEROS_DEJA_JOUE);
			return true;

		}
		
		return false;
	}

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
		    System.out.println("AHUDIZHUDIZHUIDHZAUIPDH ZAUIPFHZAUPHDAUZIP HZUPDHZAUIFP ZHAUIFP ZUIFP ZAUIDP ZAHUFIP ZEHFUZIP FUIZEP FHUIZPHFUIZPHF UIZPHFUE PHFEUP");
		    
		}
		    
	}

	public void toutDessiner(Graphics g) {
		super.paintComponent(g);
		int i, j;

		actualiserChampDeVision();
		for (j = 0; j < HAUTEUR_CARTE; j++) {
			for (i = 0; i < LARGEUR_CARTE; i++){
			
				System.out.print(""+(elements[i][j].estVide?'t':'f'));
				elements[i][j].seDessiner(g);
				}
		System.out.println("");
		}
		System.out.println("______________________");

		//Object[] heros = hsHeros.toArray();
//		monstres =(Monstre[]) hsMonstres.toArray();
		
	//	for(Object o : heros)

	//	      System.out.println(o);
	}

	public boolean estVide(Position pos) {
		return (elements[pos.getX()][pos.getY()].couleur == COULEUR_VIDE);
	}

	public String nombreSoldatsRestant(){
		Object[] heros = hsHeros.toArray();
		Object[] monstres = hsMonstres.toArray();
//		monstres =(Monstre[]) hsMonstres.toArray();
		
		for(Object o : heros)

		      System.out.println(o);
		return ""+heros.length+" héros et "+monstres.length+" monstres";

	}
	public void actualiserChampDeVision(){
		int k,l;
		Object[] heros = hsHeros.toArray();

		for (k=0;k<LARGEUR_CARTE;k++)
			for(l=0;l<HAUTEUR_CARTE;l++)
				elements[k][l].estVisible=false;
		//Heros[] heross=(Heros[])heros;
		for (Object h:heros){
			int porteeVisuelle=((Soldat) h).getPortee();
			Position positionTmp=new Position(0,0);
			for (int i=((Element) h).getPosition().getX()-porteeVisuelle;i<=((Element) h).getPosition().getX()+porteeVisuelle;i++){
				for (int j=((Element) h).getPosition().getY()-porteeVisuelle;j<=((Element) h).getPosition().getY()+porteeVisuelle;j++){
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
	/*
	private void tourMonstres(){
		Object[] monstres = hsMonstres.toArray();
		for(Object o : monstres){
			Position deplacement = new Position();
			System.out.println("jj"+((Element) o).getPosition());

			deplacement =trouvePositionVide(((Element) o).getPosition());

			deplaceSoldat(deplacement,(Soldat) o);
			
		}

	}

	*/
	private void tourMonstres(){
		Object[] monstres = hsMonstres.toArray();
		for(Object o : monstres){
			Position deplacement = new Position();
			System.out.println("jj"+((Element) o).getPosition());

			deplacement =trouvePositionVide(((Element) o).getPosition());
			elements[((Element) o).getPosition().getX()][((Element) o).getPosition().getY()].setEstVide(true);
			deplaceSoldat(deplacement,(Soldat) o);
		}
		repaint();

	}
}