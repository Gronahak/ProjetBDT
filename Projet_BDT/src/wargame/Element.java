package wargame;

import java.awt.Color;
import java.awt.Graphics;

public class Element implements IConfig{

	protected Position pos;
	protected Color couleur;
	protected Boolean estVisible=false;
	protected Boolean estVide;//=true;
	static Carte carte=null;
	
	Element (){
		couleur=COULEUR_VIDE;
		pos=new Position(0,0);
		estVide=true;
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
	public void setCouleur(Color c){
		couleur=c;
	}
	public Color getCouleur(){
		return couleur;
	}
	public boolean estVide(){
		//return (couleur==COULEUR_VIDE);
		return estVide;
	}
	
	public void setCarte(Carte car){
		carte=car;
	}
	public void setEstVide(boolean b){
		estVide=b;
	}
	public void seDessiner(Graphics g) {
		if (estVide)
			g.setColor(COULEUR_VIDE);
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
