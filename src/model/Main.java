package model;

import java.util.Scanner;
/**
 * Fonction principale du 2048-3D (et affichage sans interface graphique).
 * 
 * @author William
 */
public class Main implements Parametres {

    public static void main(String[] args) {
        // INITIALISATION
        boolean b;     //nouvelleCasePlateau()
        Plateau p = new Plateau();
        Scanner sc = new Scanner(System.in);//scanner pour la direction
        Scanner scOne = new Scanner(System.in); // scanner pour choisir avec ou sans interface graphique
        Scanner scSave = new Scanner(System.in);//scanner pour la sauvegarde (sérialisation)
        Scanner scLoad = new Scanner(System.in); //scanner pour le chargement (désérialisation)

        // Demander à l'utilisateur s'il veut une interface graphique ou non
        System.out.println("Bonjour ! Voulez-vous lancer avec Interface Graphique ? Oui(y) / Non(n)");
        String rep = scOne.nextLine();
        rep.toLowerCase();
        while (!(rep.equals("y") || rep.equals("n"))) {
            System.out.println("[ERREUR] Réponse non valide ! Réessayez avec une réponse valide.\n"
                    + "\tAppuyez sur (y) pour lancer avec interface grahique, ou sur (n) sinon.");
            rep = scOne.nextLine();
            rep.toLowerCase();
        }
        if (rep.equals("y")) {  // si oui on lance l'interface graphique du dossier application
            application.Main.main(args);
        } else { // sinon on lance le jeu dans la console
            // DESERIALISATION (CHARGEMENT)
            System.out.println("\nAvez-vous une partie sauvegardée ? Oui(y) / Non(n)");
            String load = scLoad.nextLine();
            load.toLowerCase();
            while (!(load.equals("y") || load.equals("n"))) {
                System.out.println("[ERREUR] Réponse non valide ! Réessayez avec une réponse valide.\n"
                        + "\tAppuyez sur (y) pour charger la dernière partie enregistrée, ou sur (n) pour lancer une nouvelle partie.");
                load = scLoad.nextLine();
                load.toLowerCase();
            }
            if (load.equals("y")) {
                if (Enregistreur.deserialiser() != null) {
                    p = Enregistreur.deserialiser();
                    System.out.println("Partie récupérée !");
                } else {
                    System.out.println("Aucune partie trouvée !"
                            + "\nCréation d'une nouvelle partie !");
                    b = p.nouvelleCasePlateau();
                }
            } else {
                System.out.println("\nCréation d'une nouvelle partie !");
                b = p.nouvelleCasePlateau();
            }

            // DEBUT
            System.out.println(p);
            while (p.checkMovesPlateau() == true) {
                System.out.println("\nDéplacer vers la Droite (d), Gauche (q), Haut (z), ou Bas (s) ?\nOu déplacer dessus (a) / dessous (e). Appuyez sur (x) pour quitter le Jeu.");
                String s = sc.nextLine();
                s.toLowerCase();
                if (!(s.equals("d") || s.equals("droite")
                        || s.equals("q") || s.equals("gauche")
                        || s.equals("z") || s.equals("haut")
                        || s.equals("s") || s.equals("bas")
                        || s.equals("a") || s.equals("dessus")
                        || s.equals("e") || s.equals("dessous")
                        || s.equals("x") || s.equals("quitter"))) {
                    System.out.println("[ERREUR] Réponse non valide ! Réessayez avec une réponse valide.\n"
                            + "\tVous devez appuyer sur (d) pour Droite, (q) pour Gauche, (z) pour Haut ou (s) pour Bas;\n"
                            + "\tsur (a) pour Dessus, (e) pour Dessous, ou encore (x) pour quitter le jeu.");
                } else {
                    // QUITTER
                    if (s.equals("x") || s.equals("quitter")) {
                        System.out.println("Voulez vous sauvegarder ? Oui (y) / Non (n)");
                        String save = scSave.nextLine();
                        save.toLowerCase();
                        while (!(save.equals("y") || save.equals("n"))) {
                            System.out.println("[ERREUR] Réponse non valide ! Réessayez avec une réponse valide.\n"
                                    + "\tApuyez sur (y) pour sauvegarder, ou sur (n) pour quitter sans sauvegarder.");
                            save = scSave.nextLine();
                            save.toLowerCase();
                        }
                        // SERIALIZATION (SAUVEGARDE)
                        if (save.equals("y")) {
                            Enregistreur.serialiser(p);
                            System.out.println("Partie sauvegardée !");
                        }
                        System.out.println("Merci d'avoir joué !");
                        System.exit(0);
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
        }

    }

// FIN
}
