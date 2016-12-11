package wargame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.Serializable;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;


/**
 * Classe jaudeVie
 * @author Emilie, Hugo, Rémy
 * @version 11-11-2016
 */

public class JaugeVie extends JPanel implements IConfig , Serializable{

	private static final long serialVersionUID = 1L;

	private int hpTotaux;//=1;		/* Points de vie totaux */
	private int hpCourants;//=1;	/* Points de vie courants */

	/*****************
	 * Constructeurs *
	 *****************/
	
	JaugeVie(){
		super();
		this.setBackground(Color.DARK_GRAY);

		this.setPreferredSize(new Dimension( 3* NB_PIX_CASE, HAUTEUR_CARTE * NB_PIX_CASE));
		setBorder (new LineBorder(Color.black)); 
	}

	/*************
	 * Mutateurs *
	 *************/
	
	/**
	 * Initialiser les pv totaux et courants
	 * @param hp nombre de pv a attribuer
	 */
	public void setHp(int hp){
		hpTotaux=hp;
		hpCourants=hp;
	}
	
	/**
	 * Reduire les pv courants
	 * @param dHp nombre de pv a soustraire
	 */
	public void decHp(int dHp){
		System.out.println("ça baisse de "+dHp);
		hpCourants-=dHp;
	}
	
	/**
	 * Augmenter les pv courants
	 * @param dHp nombre de pv a ajouter
	 */
	public void addHp(int dHp){ /* Augmenter les pv */
		hpCourants+=dHp;
	}

	/**
	 * Modifier les pv courants
	 * @param hp nombre de pv a attribuer
	 */
	public void setHpCourants (int hp){
		hpCourants=hp;
		System.out.println("Je set les hp à "+hp);
	}

	/**
	 * Rafraichir la carte selon les pv des heros et des monstres
	 */
	public void refresh (){
		Object[] heros = Carte.hsHeros.toArray();
		int sommeHp = 0;
		for (final Object h : heros) {
			sommeHp += ((Soldat) h).getPointsVieActuels();

		}
		FenetreJeu.vieArmeeHeros.setHpCourants(sommeHp);
		Object[] monstre = Carte.hsMonstres.toArray();
		sommeHp = 0;
		for (final Object m : monstre) {
			sommeHp += ((Soldat) m).getPointsVieActuels();
		}
		FenetreJeu.vieArmeeMonstres.setHpCourants(sommeHp);

	}

	public void paintComponent(Graphics g) { // On va ici dessiner un rectangle rouge vertical de plus en plus bas, et de moins en moins grand pour simuler les barres de vies des 2 armées

		super.paintComponent(g);
		g.setColor(Color.red);

		g.fillRect(NB_PIX_CASE / 2, (int) (HAUTEUR_CARTE * NB_PIX_CASE * (float) (hpTotaux - hpCourants) / hpTotaux),
				NB_PIX_CASE, (int) (HAUTEUR_CARTE * NB_PIX_CASE * (1 - ((float) (hpTotaux - hpCourants) / hpTotaux))));
			}

}
