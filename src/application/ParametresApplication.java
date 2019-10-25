package application;

/*
 * @author William
 */
public interface ParametresApplication {
    // CONSTANTES :

    // Directions :
    static final int HAUT = 1;
    static final int DROITE = 2;
    static final int BAS = -1;
    static final int GAUCHE = -2;
    static final int UP = 3;
    static final int DOWN = -3;

    // Taille de la grille :
    static final int TAILLE = 3;
    
    // Nombre de grilles dans le plateau :
    static final int NBGRILLES = 3;

    // Objectif :
    static final int OBJECTIF = 2048;
    
    //Graphique
    static final int HAUTEURTUILE = 97;
    static final int LARGEURTUILE = 107;
    static final int DEBUTGRILLEX = 68;
    static final int DEBUTGRILLEY = 201;
    static final int ESPACE = 40;
}