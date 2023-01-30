package Entite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import Main.Jeu;



public class Vie {
    public Boolean estAttaquer, estRestaurer;
    private BufferedImage Coeur_vide, Coeur_plein, Coeur_moitie;
    private double vie, vie_Ancienne;
    private Joueur e;
    private double coutAttaque;
    public int compteurPourVie;
    
    public Vie(Joueur e) {
        this.e = e;
        this.vie = e.CoeurMax;
        getImage();
        this.coutAttaque = 0.5;
        this.compteurPourVie = 0;
        this.estAttaquer = false;
        this.estRestaurer = false;
        this.vie_Ancienne = e.CoeurMax;
        
    }
    public double getVie() {
        return this.vie;
    }
    
    public void setVie(double d) {
    	this.vie = d;
    }
    public void setVieA(double d) {
    	this.vie_Ancienne = d;
    }
    
    public void setAttaque(double x) {
        this.coutAttaque = x;
    }
    
    public void miseAJour() {
        if (compteurPourVie != 60) {
            compteurPourVie++;
        }
            
    }

    public void degat(double degat) {
        if (this.vie - degat > 0) {
            this.vie -= degat;
        } else {
            this.vie = 0;
        }
    }
    public void afficher (Graphics2D g) {
        if (this.compteurPourVie != 0 && (this.compteurPourVie <= 10 || this.compteurPourVie >= 20 && this.compteurPourVie <= 30 || this.compteurPourVie >= 40 && this.compteurPourVie <= 50) ) {
            afficherVie(g, this.vie_Ancienne);
            
        } else {
            afficherVie(g, this.vie);
        }   
    }

    private void afficherVie(Graphics2D g, double x) {
        int i = 1;
        while (i <= e.CoeurMax) {
            if (i <= x/1) {
                g.drawImage(Coeur_plein, (i - 1)*Jeu.tailleCaseDeBase,0, null);
            } else if ((int) Math.ceil(x) != x/1 && i == (int) Math.ceil(x)) {
                g.drawImage(Coeur_moitie, (i - 1)*Jeu.tailleCaseDeBase,0, null);
            } else {
                g.drawImage(Coeur_vide, (i - 1)*Jeu.tailleCaseDeBase,0, null);
            }
            i ++; 
        }
    }

    private void getImage() {
        try {
        Coeur_plein = ImageIO.read(getClass().getResourceAsStream("/ressources_entite/Coeur_plein.png"));
        Coeur_moitie = ImageIO.read(getClass().getResourceAsStream("/ressources_entite/Coeur_moitie.png"));
        Coeur_vide = ImageIO.read(getClass().getResourceAsStream("/ressources_entite/Coeur_vide.png"));
        } catch (IOException e) {
            System.out.println("Problème dans les fichiers reliée a la vie");
        }
    }

}