package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

/**
 * Contient les variables, le constructeur et les méthodes relatifs à une Grille.
 *
 * @author William
 */
public class Grille implements Parametres, java.io.Serializable {

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
    /**
     * Représentation d'une grille dans la console.
     * <br>Exemple :
     * <br>[0, 2, 0]
     * <br>[4, 8, 16]
     * <br>[0, 0, 2048]
     * 
     * @return String affichage de la grille
     */
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

    /**
     * Supprime les cases donc la valeur vaut 0.
     */
    public void clean() {
        for (Case c : this.grille) {
            if (c.getValeur() == 0) {
                this.grille.remove(c);
            }
        }
    }

    /**
     * Création d'une nouvelle case, à condition qu'il reste au moins un emplacement vide dans la grille.
     * 
     * @return boolean Renvoie VRAI si on a réussi à créer une nouvelle case, FAUX sinon
     */
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
            return true;
        } else {
            return false;
        }
    }

    /**
     * Vérifie si des mouvements sont encore possible sur la grille.
     * 
     * @return boolean Retourne VRAI si des mouvements sont encore possibles, FAUX sinon
     */
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
    /**
     * Multiplie la valeur de la case par 2.
     * 
     * @param c Case qui va voir sa valeur doublée
     */
    private void fusion(Case c) {
        c.setValeur(c.getValeur() * 2);
        if (this.valeurMax < c.getValeur()) {
            this.valeurMax = c.getValeur();
        }
        deplacement = true;
    }

    /**
    * Retourne les 3 cases les plus proches de la direction choisie :
    * <br>- si direction = HAUT : retourne les 3 cases qui sont le plus en haut (une pour chaque colonne)
    * <br>- si direction = DROITE : retourne les 3 cases qui sont le plus à droite (une pour chaque ligne)
    * <br>- si direction = BAS : retourne les 3 cases qui sont le plus en bas (une pour chaque colonne)
    * <br>- si direction = GAUCHE : retourne les 3 cases qui sont le plus à gauche (une pour chaque ligne)
    * <br>Attention : le tableau retourné peut contenir des null si les lignes/colonnes sont vides.
    * 
    * @param direction Direction dans laquelle on veut obtenir l'extrémité de la grille
    * @return Case[] Tableau de cases qui correspondent à un bord de la grille à l'extrémité de la direction donnée
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
    
    /**
     * Déplace les cases dans la direction donnée.
     * 
     * @param direction Direction dans laquelle on veut déplacer les cases de la grille
     * @return boolean Retourner VRAI si au moins une case a bougé, FAUX sinon
     */
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

    /**
     * Déplace les cases les plus proches de la direction donnée,
     * puis on déplace les cases voisines (en fusionnant si nécessaire),
     * et ainsi de suite jusqu'à ce qu'on n'ai plus de voisins à déplacer.
     * 
     * @param extremites Tableau de cases à l'extrémité d'une grille dans une direction donnée
     * @param rangee Numérique qui permet de sélectionner une case à déplacer
     * @param direction Direction dans laquelle on déplace les cases de la grille
     * @param compteur Compteur qu'on incrémente pour la récursivité
     */
    private void deplacerCasesRecursif(Case[] extremites, int rangee, int direction, int compteur) {
        if (extremites[rangee] != null) {
            if ((direction == HAUT && extremites[rangee].getY() != compteur)
                    || (direction == BAS && extremites[rangee].getY() != TAILLE - 1 - compteur)
                    || (direction == GAUCHE && extremites[rangee].getX() != compteur)
                    || (direction == DROITE && extremites[rangee].getX() != TAILLE - 1 - compteur)) {
                this.grille.remove(extremites[rangee]);
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
                deplacement = true;
            }
            Case voisin = extremites[rangee].getVoisinDirect(-direction);
            if (voisin != null) {
                if (extremites[rangee].valeurEgale(voisin)) {
                    this.fusion(extremites[rangee]);
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
