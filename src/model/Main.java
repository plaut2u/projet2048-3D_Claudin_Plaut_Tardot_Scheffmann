package model;

import java.util.Scanner;

public class Main implements Parametres {

    public static void main(String[] args) {

        Scanner scOne = new Scanner(System.in);
        System.out.println("Voulez-vous lancer avec Interface Graphique ? (oui/non)");
        String rep = scOne.nextLine();

        if (rep.equals("oui")) {
            application.Main.main(args);
        } else {

            System.out.println("NBGRILLES = " + NBGRILLES);
            System.out.println("TAILLE = " + TAILLE);
            // SCANNER
            Scanner sc = new Scanner(System.in);//scanner pour la direction
            Scanner scSave = new Scanner(System.in);//scanner pour la sauvegarde (sérialisation)
            Scanner scLoad = new Scanner(System.in); //scanner pour le chargement (désérialisation)

            // INITIALISATION
            Plateau p = new Plateau();
            // DESERIALISATION
            System.out.println("========================================\n");
            System.out.println("Bonjour ! Avez-vous une partie sauvegardée ? Oui(y) / Non (n)");
            String load = scLoad.nextLine();
            load.toLowerCase();
            if (!(load.equals("y") || load.equals("n"))) {
                System.out.println("Réponse non valide !\nEntrez une réponse valide : appuyez sur (y) pour reprendre une partie sauvegardée, ou sur (n) pour annuler.");
            } else {
                if (load.equals("y")) {
                    System.out.println("Partie récupérée !");
                    p = Enregistreur.deserialiser();
                }
            }
            // 
            System.out.println("========================================\n");
            System.out.println("Nouvelle partie :");
            //
            boolean b = p.nouvelleCasePlateau();
            System.out.println(p);

            // test
            //Case caseTest = new Case(4,2,69);
            while (p.checkMovesPlateau() == true) {
                System.out.println("========================================\n");
                System.out.println("Déplacer vers la Droite (d), Gauche (q), Haut (z), ou Bas (s) ?\nOu déplacer dessus (a) / dessous (e). Appuyez sur (x) pour quitter le Jeu.");
                String s = sc.nextLine();
                s.toLowerCase();
                if (!(s.equals("d") || s.equals("droite")
                        || s.equals("q") || s.equals("gauche")
                        || s.equals("z") || s.equals("haut")
                        || s.equals("s") || s.equals("bas")
                        || s.equals("a") || s.equals("dessus")
                        || s.equals("e") || s.equals("dessous")
                        || s.equals("x") || s.equals("quitter"))) {
                    System.out.println("Vous devez écrire (d) pour Droite, (q) pour Gauche, (z) pour Haut ou (s) pour Bas\n"
                            + "Ou (a) pour Dessus, (e) pour Dessous. Ou encore (x) pour quitter le Jeu.");
                } else {
                    // QUITTER / SAUVEGARDE
                    if (s.equals("x") || s.equals("quitter")) {
                        System.out.println("Voulez vous sauvegarder ? Oui(y) / Non (n)");
                        String save = scSave.nextLine();
                        save.toLowerCase();
                        if (!(save.equals("y") || save.equals("n"))) {
                            System.out.println("Réponse non valide !\nEntrez une réponse valide : appuyez sur (y) pour sauvegarder, ou sur (n) pour quitter sans sauvegarder.");
                        } else {
                            // SAUVEGARDE
                            if (save.equals("y")) {
                                System.out.println("Partie sauvegardée !");
                                Enregistreur.serialiser(p);
                            }
                            System.out.println("Merci d'avoir joué !");
                            System.exit(0);
                        }
                    }
                    // DEPLACEMENT
                    int direction;
                    if (s.equals("d") || s.equals("droite")) {
                        direction = DROITE;
                    } else if (s.equals("q") || s.equals("gauche")) {
                        direction = GAUCHE;
                    } else if (s.equals("z") || s.equals("haut")) {
                        direction = HAUT;
                    } else if (s.equals("s") || s.equals("bas")) {
                        direction = BAS;
                    } else if (s.equals("a") || s.equals("dessus")) {
                        direction = UP;
                    } else {
                        direction = DOWN;
                    }
                    boolean b2 = p.lanceurDeplacerPlateau(direction);
                    if (b2 == true) {
                        b = p.nouvelleCasePlateau();
                        if (b == false) {
                            p.gameOver();
                        }
                    }
                    System.out.println(p);
                    if (p.calculScore() >= OBJECTIF) {
                        p.victory();
                    }
                }
            }
            p.gameOver();
            /*
        // Bout de code pour tester manuellement si une grille contient une case ou pas
        Scanner sc = new Scanner(System.in);
        System.out.println("x :");
        int x = sc.nextInt();
        System.out.println("y :");
        int y = sc.nextInt();
        Case c = new Case(x, y, 2);
        Case c2 = new Case(x, y, 4);
        System.out.println("test1=" + g.getGrid().contains(c));
        System.out.println("test2=" + g.getGrid().contains(c2));
             */
        }
    }

// FIN
}
