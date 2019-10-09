package model;

import java.util.Scanner;

/*
 * @author William
 */
public class Main implements Parametres {
    public static void main(String[] args) {
        
        // Initialisation
        Plateau p = new Plateau();
        System.out.println("========================================\n");
        System.out.println("Nouvelle partie :");
        System.out.println(p);
        //
        boolean b = p.nouvelleCasePlateau();
        System.out.println("========================================\n");
        System.out.println(p);
        //
        Scanner sc = new Scanner(System.in);
        
        /*System.out.println("X:");
        int x= sc.nextInt();
        System.out.println("Y:");
        int y= sc.nextInt();
        System.out.println("Valeur:");
        int valeur= sc.nextInt();
        Case c = new Case(x,y,valeur);
        g.getGrid().remove(c);
        System.out.println(g);*/
        
        while (p.checkMovesPlateau() == true) {
            System.out.println("========================================\n");
            System.out.println("Déplacer vers la Droite (d), Gauche (g), Haut (h), ou Bas (b) ?");
            String s = sc.nextLine();
            s.toLowerCase();
            if (!(s.equals("d") || s.equals("droite")
                    || s.equals("g") || s.equals("gauche")
                    || s.equals("h") || s.equals("haut")
                    || s.equals("b") || s.equals("bas"))) {
                System.out.println("Vous devez écrire d pour Droite, g pour Gauche, h pour Haut ou b pour Bas");
            } else {
                int direction;
                if (s.equals("d") || s.equals("droite")) {
                    direction = DROITE;
                } else if (s.equals("g") || s.equals("gauche")) {
                    direction = GAUCHE;
                } else if (s.equals("h") || s.equals("haut")) {
                    direction = HAUT;
                } else {
                    direction = BAS;
                }
                //boolean b2 = g.lanceurDeplacerCases(direction);
                boolean b2 = p.lanceurDeplacerCasePlateau(direction);
                if (b2 == true) {
                    //b = g.nouvelleCase();
                    b = p.nouvelleCasePlateau();
                    if (b == false) p.gameOver();
                }
                System.out.println(p);
                if (p.calculScore()>=OBJECTIF) p.victory();
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
    
// FIN
}