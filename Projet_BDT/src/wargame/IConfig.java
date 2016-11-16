package wargame;
import java.awt.Color;

/**
 * Interface définissant les configurations du jeu
 * @author Rémy
 * @version 16/11/2016
 */
public interface IConfig {
//	Taille de carte
	/** Largeur maximale de la carte de jeu (en nombre de cases) */
	int LARGEUR_CARTE = 25;
	/** Hauteur maximale de la carte de jeu (en nombres de cases) */
	int HAUTEUR_CARTE = 15;
	
//	Pixels par case
	/** Nombre de pixels par case de jeu (case carrée) */
	int NB_PIX_CASE = 20;
	
//	Position de la fenêtre de jeu
	/** Position de la fenêtre de jeu (en x) */
	int POSITION_X = 100; 
	/** Position de la fenêtre de jeu (en y) */
	int POSITION_Y = 50;
	
//	Nombres d'éléments du jeu
	/** Nombre de héros au début du jeu */
	int NB_HEROS = 6; 
	/** Nombre de monstres au début du jeu */
	int NB_MONSTRES = 15; 
	/** Nombre d'obstacles au début du jeu */
	int NB_OBSTACLES = 20;
	
//	Couleurs de base des cases
	/** Couleur d'une case sans élément */
	Color COULEUR_VIDE = Color.white, /** Couleur d'une case inconnue */COULEUR_INCONNU = Color.lightGray;
	/** Couleur du texte */
	Color COULEUR_TEXTE = Color.black;
	/** Couleur des monstres */
	Color COULEUR_MONSTRES = Color.black, /** Couleur d'un héros */COULEUR_HEROS = Color.red, /** Couleur d'un héros ayant déjà joué pour ce tour */COULEUR_HEROS_DEJA_JOUE = Color.pink;
	/** Couleur d'un obstacle de type "eau" */
	Color COULEUR_EAU = Color.blue, /** Couleur d'un obstacle de type "forêt" */COULEUR_FORET = Color.green, /** Couleur d'un obstacle de type "rocher" */COULEUR_ROCHER = Color.gray;
}