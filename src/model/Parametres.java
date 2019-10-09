package model;

/*
 * @author William
 */
public interface Parametres {
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
}