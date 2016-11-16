package wargame;
/**
 * Interface pour les Soldats du jeu.
 * @author Rémy
 * @version 16/11/2016
 */
public interface ISoldat {
	public static enum TypesH {
		HUMAIN(40, 3, 10, 2), NAIN(80, 1, 20, 0), ELF(70, 5, 10, 6), HOBBIT(20, 3, 5, 2);
		private final int POINTS_DE_VIE, PORTEE_VISUELLE, PUISSANCE, TIR;

		TypesH(int points, int portee, int puissance, int tir) {
			POINTS_DE_VIE = points;
			PORTEE_VISUELLE = portee;
			PUISSANCE = puissance;
			TIR = tir;
		}

		public int getPoints() {
			return POINTS_DE_VIE;
		}

		public int getPortee() {
			return PORTEE_VISUELLE;
		}

		public int getPuissance() {
			return PUISSANCE;
		}

		public int getTir() {
			return TIR;
		}

		public static TypesH getTypeHAlea() {
			return values()[(int) (Math.random() * values().length)];
		}
	}

	public static enum TypesM {
		TROLL(100, 1, 30, 0), ORC(40, 2, 10, 3), GOBELIN(20, 2, 5, 2);
		private final int POINTS_DE_VIE, PORTEE_VISUELLE, PUISSANCE, TIR;

		TypesM(int points, int portee, int puissance, int tir) {
			POINTS_DE_VIE = points;
			PORTEE_VISUELLE = portee;
			PUISSANCE = puissance;
			TIR = tir;
		}

		public int getPoints() {
			return POINTS_DE_VIE;
		}

		public int getPortee() {
			return PORTEE_VISUELLE;
		}

		public int getPuissance() {
			return PUISSANCE;
		}

		public int getTir() {
			return TIR;
		}

		public static TypesM getTypeMAlea() {
			return values()[(int) (Math.random() * values().length)];
		}
	}
	
	/**
	 * Récupère les points de vie du soldat courant.
	 * @return les points de vie (entier)
	 */
	int getPoints();
	
	/**
	 * Récupère la puissance physique du soldat courant.
	 * @return la puissance (entier)
	 */
	int getPuissance();
	
	/**
	 * Récupère la puissance de tir du soldat courant.
	 * @return le tir (entier)
	 */
	int getTir();
	
	/**
	 * Récupère le champ de vision du soldat courant.
	 * @return la portée (entier)
	 */
	int getPortee();

	/**
	 * ?
	 * @return un entier
	 */
	int getTour();

	/**
	 * Fais jouer le soldat courant pour le tour {@code tour}.
	 * @param tour le tour de jeu
	 */
	void joueTour(int tour);

	/**
	 * Fais combattre le soldat courant avec le soldat en paramètre.
	 * @param soldat le soldat avec qui se battre
	 */
	void combat(Soldat soldat);

	/**
	 * Déplace le soldat courant à la position en paramètre.
	 * @param newPos la nouvelle position du soldat
	 */
	void seDeplace(Position newPos);
}