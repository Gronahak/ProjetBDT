package wargame;
import java.awt.*;
import javax.swing.*;

public class PanneauJeu extends JPanel implements IConfig {

	
	private static final long serialVersionUID = 1L;
	Carte testcarte=new Carte();

	PanneauJeu(){
	//	this.setOpaque(true);
	//	this.setBackground(Color.GRAY); 
		this.setPreferredSize(new Dimension(LARGEUR_CARTE*NB_PIX_CASE,HAUTEUR_CARTE*NB_PIX_CASE));

		
	//	this.add(testcarte);
	//testcarte.toutDessiner(this.getGraphics());

	}
	public void paintComponent(Graphics g){

		super.paintComponent(g);
		testcarte.toutDessiner(g);

	}
}
