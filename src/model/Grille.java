package model;

// Bibliothèques
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

public class Grille implements Parametres {

    // Variables
    private HashSet<Case> grille;
    private int valeurMax = 0;
    private boolean deplacement;
    private static int nombreGrilles = 0;
    private int numero;

    // Constructeur
    public Grille() {
        this.grille = new HashSet<>();
        this.numero = ++nombreGrilles;
    }

    // Getter
    public HashSet<Case> getGrille() {
        return this.grille;
    }

    public boolean getDep() {
        return this.deplacement;
    }

    public int getValeurMax() {
        return this.valeurMax;
    }

    public int getNumero() {
        return this.numero;
    }

    // Setter
    public void setGrille(HashSet<Case> g) {
        this.grille = g;
    }

    public void setValeurMax(int v) {
        this.valeurMax = v;
    }

    // METHODES :
    // Représentation textuelle d'une grille
    @Override
    public String toString() {
        int[][] tableau = new int[TAILLE][TAILLE];
        for (Case c : this.grille) {
            tableau[c.getY()][c.getX()] = c.getValeur();
        }
        String result = "";
        for (int i = 0; i < tableau.length; i++) {
            result += Arrays.toString(tableau[i]) + "\n";
        }
        return result;
    }

    // Supprimer les cases donc la valeur vaut 0
    public void clean() {
        for (Case c : this.grille) {
            if (c.getValeur() == 0) {
                this.grille.remove(c);
            }
        }
    }

    // Création d'une nouvelle case, à condition qu'il reste au moins un emplacement vide dans la grille
    public boolean nouvelleCase() {
        if (this.grille.size() < TAILLE * TAILLE) {
            ArrayList<Case> casesLibres = new ArrayList<>();
            Random ra = new Random();
            int valeur = (1 + ra.nextInt(2)) * 2;
            // on crée toutes les cases encore libres
            for (int x = 0; x < TAILLE; x++) {
                for (int y = 0; y < TAILLE; y++) {
                    Case c = new Case(x, y, valeur);
                    if (!this.grille.contains(c)) { // contains utilise la méthode equals dans Case
                        casesLibres.add(c);
                    }
                }
            }
            // on en choisit une au hasard et on l'ajoute à la grille
            Case ajout = casesLibres.get(ra.nextInt(casesLibres.size()));
            ajout.setGrille(this);
            this.grille.add(ajout);
            if ((this.grille.size() == 1) || (this.valeurMax == 2 && ajout.getValeur() == 4)) { // Mise à jour de la valeur maximale présente dans la grille si c'est la première case ajoutée ou si on ajoute un 4 et que l'ancien max était 2
                this.valeurMax = ajout.getValeur();
            }
            System.out.println("[DEBUG] Nouvelle "+ajout);
            return true;
        } else {
            return false;
        }
    }

    // Retourne VRAI si des mouvements sont encore possibles sur la grille, retourne FAUX sinon
    public boolean checkMoves() {
        if (this.grille.size() < TAILLE * TAILLE) {
            return true;
        } else {
            for (Case c : this.grille) {
                for (int i = 1; i <= 2; i++) {
                    if (c.getVoisinDirect(i) != null) {
                        if (c.valeurEgale(c.getVoisinDirect(i))) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    // DEPLACEMENTS DES CASES :
    // Multiplie la valeur de la case par 2
    private void fusion(Case c) {
        c.setValeur(c.getValeur() * 2);
        if (this.valeurMax < c.getValeur()) {
            this.valeurMax = c.getValeur();
        }
        deplacement = true;
    }

    /* Retourne les 3 cases les plus proches de la direction choisie
    * Si direction = HAUT : retourne les 3 cases qui sont le plus en haut (une pour chaque colonne)
    * Si direction = DROITE : retourne les 3 cases qui sont le plus à droite (une pour chaque ligne)
    * Si direction = BAS : retourne les 3 cases qui sont le plus en bas (une pour chaque colonne)
    * Si direction = GAUCHE : retourne les 3 cases qui sont le plus à gauche (une pour chaque ligne)
    * Attention : le tableau retourné peut contenir des null si les lignes/colonnes sont vides
     */
    public Case[] getCasesExtremites(int direction) {
        Case[] result = new Case[TAILLE];
        for (Case c : this.grille) {
            switch (direction) {
                case HAUT:
                    if ((result[c.getX()] == null) || (result[c.getX()].getY() > c.getY())) { // si on n'avait pas encore de case pour cette rangée ou si on a trouvé un meilleur candidat
                        result[c.getX()] = c;
                    }
                    break;
                case BAS:
                    if ((result[c.getX()] == null) || (result[c.getX()].getY() < c.getY())) {
                        result[c.getX()] = c;
                    }
                    break;
                case GAUCHE:
                    if ((result[c.getY()] == null) || (result[c.getY()].getX() > c.getX())) {
                        result[c.getY()] = c;
                    }
                    break;
                default:
                    if ((result[c.getY()] == null) || (result[c.getY()].getX() < c.getX())) {
                        result[c.getY()] = c;
                    }
                    break;
            }
        }
        return result;
    }

    // Déplace les cases dans la direction donnée
    // Retourner VRAI si  au moins une case a bougé, retourne FAUX sinon
    public boolean lanceurDeplacerCases(int direction) {
        Case[] extremites = this.getCasesExtremites(direction);
        deplacement = false; // pour vérifier si on a bougé au moins une case après le déplacement, avant d'en rajouter une nouvelle
        for (int i = 0; i < TAILLE; i++) {
            switch (direction) {
                case HAUT:
                    this.deplacerCasesRecursif(extremites, i, direction, 0);
                    break;
                case BAS:
                    this.deplacerCasesRecursif(extremites, i, direction, 0);
                    break;
                case GAUCHE:
                    this.deplacerCasesRecursif(extremites, i, direction, 0);
                    break;
                default:
                    this.deplacerCasesRecursif(extremites, i, direction, 0);
                    break;
            }
        }
        return deplacement;
    }

    /* Déplace les cases les plus proches de la direction donnée
    * puis on déplace les cases voisines (en fusionnant si nécessaire)
    * et ainsi de suite jusqu'à ce qu'on n'ai plus de voisins à déplacer
     */
    private void deplacerCasesRecursif(Case[] extremites, int rangee, int direction, int compteur) {
        if (extremites[rangee] != null) {
            if ((direction == HAUT && extremites[rangee].getY() != compteur)
                    || (direction == BAS && extremites[rangee].getY() != TAILLE - 1 - compteur)
                    || (direction == GAUCHE && extremites[rangee].getX() != compteur)
                    || (direction == DROITE && extremites[rangee].getX() != TAILLE - 1 - compteur)) {
                this.grille.remove(extremites[rangee]);
                System.out.println("[DEBUG] Déplacement :\n\tAvant : "+extremites[rangee]);
                switch (direction) {
                    case HAUT:
                        extremites[rangee].setY(compteur);
                        break;
                    case BAS:
                        extremites[rangee].setY(TAILLE - 1 - compteur);
                        break;
                    case GAUCHE:
                        extremites[rangee].setX(compteur);
                        break;
                    default:
                        extremites[rangee].setX(TAILLE - 1 - compteur);
                        break;
                }
                this.grille.add(extremites[rangee]);
                System.out.println("\tAprès : "+extremites[rangee]);
                deplacement = true;
            }
            Case voisin = extremites[rangee].getVoisinDirect(-direction);
            if (voisin != null) {
                if (extremites[rangee].valeurEgale(voisin)) {
                    System.out.println("[DEBUG] Fusion :\n\tAvant : "+extremites[rangee]+" & " +voisin);
                    this.fusion(extremites[rangee]);
                    System.out.println("\tAprès : "+extremites[rangee]);
                    extremites[rangee] = voisin.getVoisinDirect(-direction);
                    this.grille.remove(voisin);
                    this.deplacerCasesRecursif(extremites, rangee, direction, compteur + 1);
                } else {
                    extremites[rangee] = voisin;
                    this.deplacerCasesRecursif(extremites, rangee, direction, compteur + 1);
                }
            }
        }
    }

// FIN
}
