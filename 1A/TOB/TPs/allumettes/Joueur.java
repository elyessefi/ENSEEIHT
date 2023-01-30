package allumettes;
public class Joueur {
    private String nom;
    private Strategie strategie;

    public Joueur(String nom, Strategie strategie) {
        this.nom = nom;
        this.strategie = strategie;
    }

    public String getNom() {
        return this.nom;
    }

    public Strategie getStrategie() {
        return this.strategie;
    }
    
    public void setStrategie(Strategie strat) {
        this.strategie = strat;
    }

    public int getPrise(Jeu jeu) {
        return this.strategie.getPrise(jeu);
    }
}
