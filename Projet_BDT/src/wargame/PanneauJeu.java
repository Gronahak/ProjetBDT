package wargame;
import java.awt.*;
import javax.swing.*;

public class PanneauJeu extends JPanel implements IConfig {

	
	private static final long serialVersionUID = 1L;
	Carte carte;

	PanneauJeu(){
		carte=new Carte();
	//	this.setOpaque(true);
	//	this.setBackground(Color.GRAY); 
		this.setPreferredSize(new Dimension(LARGEUR_CARTE*NB_PIX_CASE,HAUTEUR_CARTE*NB_PIX_CASE));

		
	//	this.add(testcarte);
	//testcarte.toutDessiner(this.getGraphics());

	}
	public void paintComponent(Graphics g){

		super.paintComponent(g);
		carte.toutDessiner(g);

	}
	public String getInfos(Position pos){
		if (carte.getElement(pos).estVisible)
		return carte.getElement(pos).toString();
		return "";
	}
	public String nbSoldats(){
		return carte.nombreSoldatsRestant();
	}
}