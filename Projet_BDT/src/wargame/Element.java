package wargame;

import java.awt.Color;
import java.awt.Graphics;

public class Element implements IConfig{

	protected Position pos;
	protected Color couleur=COULEUR_VIDE;
	
	Element (){
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
		return (this==null);
	}
	
	public void seDessiner(Graphics g) {
		
		g.setColor(couleur);
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
