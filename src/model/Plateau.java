package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

/**
 * Contient les variables, le constructeur et les méthodes relatifs à un Plateau.
 *
 * @author William
 */
public class Plateau implements Parametres, java.io.Serializable {

    // Variables
    private Grille[] plateau;
    private boolean bloque;
    private int score;
    private boolean move;

    // Constructeur
    public Plateau() {
        this.plateau = new Grille[NBGRILLES];
        for (int i = 0; i < this.plateau.length; i++) {
            Grille g = new Grille();
            this.plateau[i] = g;
        }
        this.bloque = false;
        this.score = 0;
    }

    // Getter
    public Grille[] getPlateau() {
        return this.plateau;
    }

    public boolean getBloque() {
        return this.bloque;
    }

    public int getScore() {
        return this.score;
    }

    public boolean getMove() {
        return this.move;
    }

    // Setter
    public void setPlateau(Grille[] p) {
        this.plateau = p;
    }

    public void setBloque(boolean b) {
        this.bloque = b;
    }

    public void setScore(int s) {
        this.score = s;
    }

    public void setMove(boolean m) {
        this.move = m;
    }

    // METHODES
    /**
     * Représentation d'un plateau dans la console.
     * <br>Exemple :
     * <br>| [0, 0, 0] || [0, 0, 2] || [0, 0, 0] |
     * <br>| [4, 0, 0] || [0, 0, 0] || [0, 4, 0] |
     * <br>| [0, 0, 0] || [0, 0, 0] || [0, 0, 0] |
     *
     * @return String Affichage du plateau
     */
    @Override
    public String toString() {
        int[][][] tableau = new int[NBGRILLES][TAILLE][TAILLE];
        for (int i = 0; i < NBGRILLES; i++) {
            for (Case c : this.plateau[i].getGrille()) {
                tableau[i][c.getY()][c.getX()] = c.getValeur();
            }
        }
        String result = "";
        for (int y = 0; y < TAILLE; y++) {
            for (int g = 0; g < NBGRILLES; g++) {
                result += "| " + Arrays.toString(tableau[g][y]) + " |";
            }
            result += " \n";
        }
        //this.debugAffichage();

        return result;
    }

    /**
     * Affiche un message de victoire.
     */
    public void victory() {
        System.out.println("Victoire ! Vous avez atteint " + this.calculScore());
        System.exit(0);
    }

    /**
     * Affiche un message de défaite.
     */
    public void gameOver() {
        System.out.println("Perdu ! La partie est finie. Votre score est " + this.calculScore());
        System.exit(1);
    }

    /**
     * Calcul du score : plus haute valeur atteinte dans toutes les grilles du plateau.
     *
     * @return int plus haute valeur
     */
    public int calculScore() {
        int x = 0;
        for (Grille g : this.plateau) {
            if (g.getValeurMax() > x) {
                x = g.getValeurMax();
            }
        }
        return x;
    }

    /**
     * Vérifie si un déplacement est encore poossible sur AU MOINS une des grilles du plateau.
     *
     * @return boolean Retourne VRAI si un déplacement est possible, FAUX sinon
     */
    public boolean checkMovesPlateau() {
        boolean b = false;
        for (Grille g : this.plateau) {
            if (g.checkMoves() == true) {
                b = true;
            }
        }
        return b;
    }

    /**
     * Créé une nouvelle case pour chaque grille sauf si la grille en question n'est pas remplie.
     *
     * @return boolean Retourne FAUX si aucune case ne peut être créée (toutes les grilles sont pleines), VRAI sinon
     */
    public boolean nouvelleCasePlateau() {
        boolean b = false;
        for (Grille g : this.plateau) {
            if (g.nouvelleCase() == true) {
                b = true;
            }
        }
        return b;
    }

    /**
     * Lance le déplacement des cases dans toutes les grilles du plateau dans la direction donnée.
     *
     * @param direction Direction dans laquelle on déplace les cases du plateau
     * (HAUT, BAS, DROITE, GAUCHE)
     * @return boolean Retourne FAUX si aucun déplacement n'est possible (game
     * over), VRAI sinon
     */
    public boolean lanceurDeplacerPlateau(int direction) {
        boolean b = false;

        if (direction == UP || direction == DOWN) {
            if (lanceurDeplacerGrilles(direction)) {
                b = true;
            }
        } else {
            for (Grille g : this.plateau) {
                if (g.lanceurDeplacerCases(direction)) {
                    b = true;
                }
            }
        }

        return b;
    }

    /**
     * Lance le déplacement des cases d'une grille à l'autre dans la direction
     * donnée, sur l'ensemble du plateau.
     *
     * @param direction Direction dans laquelle on déplace les cases du plateau
     * (UP / DOWN)
     * @return boolean Retourne FAUX si aucun mouvement n'a été réalisé, VRAI
     * sinon
     */
    public boolean lanceurDeplacerGrilles(int direction) {
        move = false;
        for (int i = 0; i < NBGRILLES; i++) {
            switch (direction) {
                case UP:
                    this.deplacerGrillesRecursif(i, (i - 1), direction);
                    break;
                default:
                    this.deplacerGrillesRecursif((NBGRILLES - i - 1), (NBGRILLES - i), direction);
                    break;
            }
        }
        return move;
    }

    /**
     * Lance le déplacement récursif des cases d'une grille à l'autre dans la
     * direction donnée, pour 2 grilles seulement. Se répète tant qu'un
     * déplacement est possible.
     *
     * @param grilleADeplacer Grille qui contient les cases qui vont pouvoir se
     * déplacer jusqu'à la grille courante
     * @param compteur Compteur qu'on incrémente pour la récursivité
     * @param direction Direction dans laquelle on déplace les cases (UP / DOWN)
     */
    private void deplacerGrillesRecursif(int grilleADeplacer, int compteur, int direction) {
        switch (direction) {
            case UP:
                if (compteur > -1) {
                    // déplacement
                    int[][] temp = new int[TAILLE][TAILLE];     // tableau des cases de la grille à déplacer
                    HashSet<Case> temp2 = new HashSet<>();      // ensemble temporaire des cases à supprimer
                    // récupération la grille à faire bouger
                    for (Case c0 : this.plateau[grilleADeplacer].getGrille()) {
                        temp[c0.getX()][c0.getY()] = c0.getValeur();
                    }
                    // fusion dans la grille cible
                    for (Case c1 : this.plateau[compteur].getGrille()) {
                        // pour chaque case existante de la grille cible on check la valeur de la case
                        if (temp[c1.getX()][c1.getY()] == c1.getValeur()) {
                            // si les valeurs des cases situées aux mêmes coordonnées dans la grille cible et dans la grille à bouger
                            // sont les mêmes alors on double la valeur de la case de la grille cible
                            c1.setValeur(c1.getValeur() * 2);
                            // et on supprime la case dans la grille à bouger
                            temp[c1.getX()][c1.getY()] = -1;    // -1 = la case sera supprimée
                        } else {
                            // sinon la valeur de la case de la grille à bouger reste la même
                            temp[c1.getX()][c1.getY()] = 0;     // 0 = la case ne bougera pas
                        }
                    }
                    for (int x = 0; x < TAILLE; x++) {
                        for (int y = 0; y < TAILLE; y++) {
                            if (temp[x][y] > 0) {
                                Case ajout = new Case(x, y, temp[x][y]);
                                ajout.setGrille(this.plateau[compteur]);
                                this.plateau[compteur].getGrille().add(ajout);
                                temp[x][y] = -1;
                                move = true; // permet de récupérer l'information qu'un déplacement a été réalisé
                            }
                        }
                    }
                    // suppression des cases bougées dans grille à bouger
                    for (Case c2 : this.plateau[grilleADeplacer].getGrille()) {
                        if (temp[c2.getX()][c2.getY()] < 0) {   // si -1 alors on supprime la case car elle a bougé
                            temp2.add(c2);
                        }
                    }
                    for (Case c : temp2) {
                        this.plateau[grilleADeplacer].getGrille().remove(c);
                    }

                    // récursivité
                    this.deplacerGrillesRecursif(grilleADeplacer - 1, compteur - 1, direction);
                }
                break;

            default:    // SI direction = DOWN
                if (compteur < 3) {
                    // Même code que pour UP
                    // déplacement
                    int[][] temp = new int[TAILLE][TAILLE];
                    HashSet<Case> temp2 = new HashSet<>();
                    for (Case c0 : this.plateau[grilleADeplacer].getGrille()) {
                        temp[c0.getX()][c0.getY()] = c0.getValeur();
                    }
                    for (Case c1 : this.plateau[compteur].getGrille()) {
                        if (temp[c1.getX()][c1.getY()] == c1.getValeur()) {
                            c1.setValeur(c1.getValeur() * 2);
                            temp[c1.getX()][c1.getY()] = -1;
                        } else {
                            temp[c1.getX()][c1.getY()] = 0;
                        }
                    }
                    for (int x = 0; x < TAILLE; x++) {
                        for (int y = 0; y < TAILLE; y++) {
                            if (temp[x][y] > 0) {
                                Case ajout = new Case(x, y, temp[x][y]);
                                ajout.setGrille(this.plateau[compteur]);
                                this.plateau[compteur].getGrille().add(ajout);
                                temp[x][y] = -1;
                                move = true;
                            }
                        }
                    }
                    for (Case c2 : this.plateau[grilleADeplacer].getGrille()) {
                        if (temp[c2.getX()][c2.getY()] < 0) {
                            temp2.add(c2);
                        }
                    }
                    for (Case c : temp2) {
                        this.plateau[grilleADeplacer].getGrille().remove(c);
                    }
                    // récursivité
                    this.deplacerGrillesRecursif(grilleADeplacer + 1, compteur + 1, direction);
                }
                break;
        }
    }

    /**
     * (debug) Fonction qui a permi d'afficher chaque case du plateau pour repérer d'éventuelles erreurs.
     */
    public void debugAffichage() {
        System.out.println("[DEBUG] Affichage : ");
        for (Grille g : this.plateau) {
            for (Case c : g.getGrille()) {
                System.out.println("\t" + c);
            }
        }
    }
    
// FIN
}
