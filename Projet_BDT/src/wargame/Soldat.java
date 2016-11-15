package wargame;

/**
 * Classe Soldat (Heros et Monstres)
 * @author emilie
 * @version 15/11/16
 */
public abstract class Soldat implements ISoldat{
	
	protected int points;
	protected int portee;
	protected int tir;
	protected int puissance;
	
	public Soldat(){
		points = 0;
		portee = 0;
		tir = 0;
		puissance = 0;
	}
	
	public int getTour(){
		return 0;
	}
	
	public void joueTour(int tour){
		
	}
	
	public void combat(Soldat soldat){
		
	}

	public void seDeplace(Position newPos){
		
	}
}