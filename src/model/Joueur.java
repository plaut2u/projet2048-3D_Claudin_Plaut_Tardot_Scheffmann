package model;

/**
 * Contient les variables, le constructeur et les méthodes relatifs à un Joueur.
 *
 * @author Tom
 */
public class Joueur {

    // Variables
    private String pseudo, motDePasse; //pseudo et mot de passe
    private int meilleurScore, nbVictoires; //le meilleur score et le nombres de victoires du joueur
    private boolean jeuEnCours; //si le joueur à actuellement une partie en cours

    // Constructeur
    public Joueur(String name, String mdp) {
        this.pseudo = name;
        this.motDePasse = mdp;
        this.meilleurScore = 0;
        this.nbVictoires = 0;
        this.jeuEnCours = false;
    }
    
    //surcharge du constructeur
    public Joueur(String name, int best, int nb) {
        this.pseudo = name;
        this.meilleurScore = best;
        this.nbVictoires = nb;
    }

    //GETTERS
    public String getPseudo() {
        return this.pseudo;
    }

    public String getPassword() {
        return this.motDePasse;
    }

    public int getMeilleurScore() {
        return this.meilleurScore;
    }

    public int getNombreVictoires() {
        return this.nbVictoires;
    }

    public boolean getJeuEnCours() {
        return this.jeuEnCours;
    }

    //SETTERS
    public void setPseudo(String name) {
        this.pseudo = name;
    }

    public void setPassword(String pw) {
        this.motDePasse = pw;
    }

    public void setMeilleurScore(int x) {
        this.meilleurScore = x;
    }

    public void setNbvictoires(int n) {
        this.nbVictoires = n;
    }

    public void setJeuEnCours(boolean b) {
        this.jeuEnCours = b;
    }
}
