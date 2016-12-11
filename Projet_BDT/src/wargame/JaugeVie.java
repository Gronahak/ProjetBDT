package wargame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class JaugeVie extends JPanel implements IConfig{

	private int hpTotaux;//=1; /* Points de vie totaux*/
	private int hpCourants;//=1;
	JaugeVie(){
		super();
		this.setBackground(Color.DARK_GRAY);
		this.setPreferredSize(new Dimension( 3* NB_PIX_CASE, HAUTEUR_CARTE * NB_PIX_CASE));
		//this.setOpaque(false);
		 setBorder (new LineBorder(Color.black)); 
		// Graphics g=getGraphics();
	}
	
	public void setHp(int hp){ /* Initialiser les pv */
		hpTotaux=hp;
		hpCourants=hp;
	}
	public void decHp(int dHp){ /* Réduire les pv */
		System.out.println("ça baisse de "+dHp);
		hpCourants-=dHp;
	}
	public void addHp(int dHp){ /* Augmenter les pv */
		hpCourants+=dHp;
		
	}
	
	public void setHpCourants (int hp){
		hpCourants=hp;
		System.out.println("Je set les hp à "+hp);
	
	}

	public void refresh (){
		Object[] heros = Carte.hsHeros.toArray();
int sommeHp=0;
		for (final Object h:heros){
			System.out.println("HeeeeermmmmmmHermrmmmmm"+sommeHp);
			sommeHp+=((Soldat)h).getPointsVieActuels();
		}
		FenetreJeu.vieArmeeHeros.setHpCourants(sommeHp);
		Object[] monstre = Carte.hsMonstres.toArray();
		sommeHp=0;
				for (final Object m:monstre){
					sommeHp+=((Soldat)m).getPointsVieActuels();
				}
		FenetreJeu.vieArmeeMonstres.setHpCourants(sommeHp);

	}
	public void paintComponent(Graphics g){

		super.paintComponent(g);
	//
	//	FenetreJeu.vieArmeeMonstres.seDessiner(g);/g=getGraphics();
g.setColor(Color.red);

		g.fillRect( NB_PIX_CASE/2, (int)(HAUTEUR_CARTE*NB_PIX_CASE*(float)(hpTotaux-hpCourants)/hpTotaux), NB_PIX_CASE,(int) (HAUTEUR_CARTE*NB_PIX_CASE*(1-((float)(hpTotaux-hpCourants)/hpTotaux))));
//		g.fill
		System.out.println("oooooooooook hpcour "+hpCourants+" tot : "+hpTotaux+"bousin "+ ((float)(hpTotaux-hpCourants)/hpTotaux)+"\t"+HAUTEUR_CARTE*NB_PIX_CASE*(1-((float)(hpTotaux-hpCourants)/hpTotaux))+"\t" +(int)(NB_PIX_CASE*HAUTEUR_CARTE*(float)(hpTotaux-hpCourants)/hpTotaux*100));
	}
}
