package model;

import java.util.Arrays;
import static model.Parametres.TAILLE;

/*
 * @author William
 */
public class Plateau implements Parametres, java.io.Serializable {

    // Variables
    private Grille[] plateau;
    private boolean bloque;
    private int score;

    // Constructeur
    public Plateau() {
        this.plateau = new Grille[NBGRILLES];
        for (int i = 0; i < plateau.length; i++) {
            Grille g = new Grille();
            plateau[i] = g;
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

    // METHODES
    // Affichage
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
        return result;
    }

    // Message de victoire
    public void victory() {
        System.out.println("Victoire ! Vous avez atteint " + this.calculScore());
        System.exit(0);
    }

    // Message de défaite
    public void gameOver() {
        System.out.println("Perdu ! La partie est finie. Votre score est " + this.calculScore());
        System.exit(1);
    }

    // Calcul du score : plus haute valeur atteinte dans toutes les grilles du plateau
    public int calculScore() {
        int x = 0;
        for (Grille g : this.plateau) {
            if (g.getValeurMax() > x) {
                x = g.getValeurMax();
            }
        }
        return x;
    }

    // Vérifie si un déplacement est encore poossible sur AU MOINS une des grilles du plateau
    // Retourne VRAI si des mouvements sont possibles dans AU MOINS une grille
    public boolean checkMovesPlateau() {
        boolean b = false;
        for (Grille g : this.plateau) {
            if (g.checkMoves() == true) {
                b = true;
            }
        }
        return b;
    }

    // Créé une nouvelle case pour chaque grille sauf si la grille en question n'est pas remplie
    // Retourne FAUX si aucune case ne peut être créée (toutes les grilles sont pleines)
    public boolean nouvelleCasePlateau() {
        boolean b = false;
        for (Grille g : this.plateau) {
            if (g.nouvelleCase() == true) {
                b = true;
            }
        }
        return b;
    }

    // Lance le déplacement des cases dans toutes les grilles du plateau dans la direction donnée
    // Retourne FAUX si aucun déplacement n'est possible (game over)
    public boolean lanceurDeplacerCasePlateau(int direction) {
        boolean b = false;
        for (Grille g : this.plateau) {
            if (g.lanceurDeplacerCases(direction)) {
                b = true;
            }
        }
        return b;
    }
    
    //methode pour savoir si une case est présente sur le plateau de 3 grilles
    public boolean contains(Case elem) {
        return true;
    }
// FIN

    
}
