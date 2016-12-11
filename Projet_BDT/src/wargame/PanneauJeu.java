package wargame;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class PanneauJeu extends JPanel implements IConfig {

	
	private static final long serialVersionUID = 1L;
	protected Carte carte;

	PanneauJeu(){
		carte=new Carte();
		
	//	this.setOpaque(true);
		this.setBackground(Color.cyan); 
		this.setPreferredSize(new Dimension(LARGEUR_CARTE*NB_PIX_CASE,HAUTEUR_CARTE*NB_PIX_CASE));
	 setBorder (new LineBorder(Color.blue)); 

		//this.add(vieArmeeHeros,BorderLayout.WEST);
		//this.add(carte,BorderLayout.CENTER);

//		this.add(vieArmeeMonstres,BorderLayout.EAST);
	//	this.add(testcarte);
	//testcarte.toutDessiner(this.getGraphics());

	}
	public void paintComponent(Graphics g){

		super.paintComponent(g);
	//	FenetreJeu.vieArmeeHeros.seDessiner(g);
		carte.toutDessiner(g);
	//	FenetreJeu.vieArmeeMonstres.seDessiner(g);
	}
	public String getInfos(Position pos){
		if (carte.getElement(pos).estVisible)
		return carte.getElement(pos).toString();
		return "";
	}
	public String nbSoldats(){
		return carte.nombreSoldatsRestant();
	}
	public void setCarte(Carte map){
		carte=map;
	}
	public Carte getCarte(){
		return carte;
	}
}
