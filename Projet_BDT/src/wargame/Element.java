package wargame;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public class Element implements IConfig, Serializable{

	private static final long serialVersionUID = -6903291678115693478L;
	protected Position pos;
	protected Color couleur;;
	protected Boolean estVisible=false;
	static Carte carte=null;
	
	Element (){
		couleur=COULEUR_VIDE;
		pos=new Position(0,0);
	}
	Element(Position pos){
		this();
		this.pos=pos;
	}
	public void setPosition(Position position){
		pos=position;
	}
	public Position getPosition(){
		return pos;
	}
	public boolean estVide(){
		return (couleur==COULEUR_VIDE);
	}
	public void setCarte(Carte car){
		carte=car;
	}
	public void seDessiner(Graphics g) {
		
		if (estVisible)
			g.setColor(couleur);
		else g.setColor(COULEUR_INCONNU);
		g.fillRect(pos.getX()*NB_PIX_CASE, pos.getY()*NB_PIX_CASE, NB_PIX_CASE, NB_PIX_CASE);
		g.setColor(Color.black);
		g.drawRect(pos.getX()*NB_PIX_CASE, pos.getY()*NB_PIX_CASE, NB_PIX_CASE, NB_PIX_CASE);
		g.setColor(COULEUR_VIDE);

	}
	public String toString(){
		//return "Je dessine un "+this.getClass().getSimpleName()+" en case "+pos;
		return "";
	}
	

}
