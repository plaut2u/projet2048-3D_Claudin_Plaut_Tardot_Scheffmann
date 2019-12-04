package model;

/**
 * Contient les variables, le constructeur et les méthodes relatifs à une Case.
 * 
 * @author William
 */
public class Case implements Parametres, java.io.Serializable {

    // Variables
    private int x, y, valeur; // x = abscisse ; y = ordonnée
    private Grille grille;

    // Constructeur
    public Case(int abs, int ord, int v) {
        this.x = abs;
        this.y = ord;
        this.valeur = v;
    }

    // Getter
    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
    public int getValeur() {
        return this.valeur;
    }
    public Grille getGrille() {
        return this.grille;
    }

    // Setter
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setValeur(int v) {
        this.valeur = v;
    }
    public void setGrille(Grille g) {
        this.grille = g;
    }

    // METHODES :
    /**
     * Représentation d'une case dans la console.
     * <br>Exemple : Case [X:0][Y:2]{Grille:1}(Valeur:2048)
     * 
     * @return String affichage de la case
     */
    @Override
    public String toString() {
        return "Case [X:" + this.x + "][Y:" + this.y + "]{Grille:"+this.getGrille().getNumero()+"}(Valeur:" + this.valeur + ").";
    }

    /**
     * Détermination du code de la case. Chaque case d'une grille à un code unique.
     * <br>Exemple : code de la case (2,1) = 27
     * 
     * @return int hashcode de la case
     */
    @Override
    public int hashCode() {
        if(this.grille != null){
            return this.x * 7 + this.y * 13 + this.grille.getNumero()*41;
        }else{
            return this.x * 7 + this.y * 13;
        }
    }
    
    /**
     * Vérifie si une case est égale à un objet passé en paramètre.
     * 
     * @param obj
     * @return 
     */
    @Override
    public boolean equals(Object obj) {
        // on vérifie si l'objet en paramètre est bien une case
        if (obj instanceof Case) {
            Case c = (Case) obj;
            // on vérifie si la case en paramètre n'existe pas déjà
            return (this.x == c.x && this.y == c.y);
        } else {
            return false;
        }
    }
    
    /**
     * Retourne VRAI si la case en paramètre a la même valeur que la case courante, FAUX sinon.
     * 
     * @param c     La case avec laquelle on compare la case courante.
     * @return boolean Retourne VRAI si la valeur est égale, FAUX sinon.
     */
    public boolean valeurEgale(Case c) {
        // on vérifie si la case en paramètre n'est pas nulle
        if (c != null) {
            // on compare les valeurs des deux cases
            return this.valeur == c.valeur;
        } else {
            return false;
        }
    }
    
    /**
     * Retourne la première case rencontrée dans une direction donnée en partant de la case courante.
     * 
     * @param direction Direction dans laquelle on regarde si la case a un voisin
     * @return Case La case voisine à la case courante si elle existe, null sinon
     */
    public Case getVoisinDirect(int direction) {
        if (direction == HAUT) {
            for (int i = this.y - 1; i >= 0; i--) {
                for (Case c : grille.getGrille()) {
                    if (c.getX() == this.x && c.getY() == i) {
                        return c;
                    }
                }
            }
        } else if (direction == BAS) {
            for (int i = this.y + 1; i < TAILLE; i++) {
                for (Case c : grille.getGrille()) {
                    if (c.getX() == this.x && c.getY() == i) {
                        return c;
                    }
                }
            }
        } else if (direction == GAUCHE) {
            for (int i = this.x - 1; i >= 0; i--) {
                for (Case c : grille.getGrille()) {
                    if (c.getX() == i && c.getY() == this.y) {
                        return c;
                    }
                }
            }
        } else if (direction == DROITE) {
            for (int i = this.x + 1; i < TAILLE; i++) {
                for (Case c : grille.getGrille()) {
                    if (c.getX() == i && c.getY() == this.y) {
                        return c;
                    }
                }
            }
        }
        return null;
    }
    
// FIN
}
