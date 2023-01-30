package Entite;
import java.awt.Graphics2D;
import Main.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Joueur implements Entite {

    /* Attribut Entite */
    private int x, y;
    private Hitbox hitbox;
    public double CoeurMax = 4;

    /* Vie */
    public Vie vie = new Vie(this);
    private long cooldown = 1000;
    private long tempsAttente;
    private boolean annimation_coeur;

    /* Affichage */
    public ControleClavier mouvement;
    public Jeu jeu;
    public int vitesse = 3;
    public final static int dynamisme = 10;
    private BufferedImage Avant_marche1, Avant_marche2, Avant_static, Derriere_marche1, Derriere_marche2, Derriere_static, Droite_marche, Droit_static, Gauche_static, Gauche_marche;
    private int changement_texture, compteur;
    public String deplacement;
    public Inventaire inv;
    private Fleche tabFleche[];
    public int nbrMaxFleche = 25, nbrCourantFleche;
    public int comptPiece = 0;
    


    public Joueur(Jeu jeu, ControleClavier mouvement) {
        this.hitbox = new Hitbox(this,-15, 66, 50, -13); 
        this.nbrCourantFleche = 0;
        this.x = 420;
        this.y = 320 ;
        this.jeu = jeu;
        this.mouvement = mouvement;
        getImage();
        this.deplacement = "bas";
        this.changement_texture = 2;
        this.inv = new Inventaire(10, 10, jeu.tailleCaseReelle * 5 + 15, jeu.tailleCaseReelle * 4, jeu.listeObj);
        this.tabFleche = new Fleche[nbrMaxFleche];
        for (int i=0; i< nbrMaxFleche; i++) {
            tabFleche[i] = null;
        }

    }

    public void SubitDegat(double degat) {
        if (tempsAttente - System.currentTimeMillis() < 0) {
            tempsAttente = System.currentTimeMillis() + cooldown;
            vie.setVieA(vie.getVie());
            vie.degat(degat);
            this.annimation_coeur = true;
        }
    }

    public boolean estPresent() {
        return vie.getVie() != 0 ;
    }

    public int getVitesse() {
        return this.vitesse;
    }

    public void attaquer() {
        if (mouvement.fleche) {
            if (this.nbrCourantFleche != this.nbrMaxFleche) {
                nbrCourantFleche ++;
                int i=0;
                while (tabFleche[i] != null) {
                    i++;
                }
                tabFleche[i] = new Fleche(this, this.jeu, false);
            }
            
        }
    }

    public int getX() { return this.x; }
    
    public int getY() { return this.y; }
    
    public void setX (int x) {
        this.x = x;

    }
    
    public void setY(int y) {
    	this.y = y;
    }
    

    public Hitbox getHitbox() { return this.hitbox; }

    public double getCoeurMax() { return this.CoeurMax; }

    public Vie getVieJoueur() { return this.vie; }

    /**Update les coordonnees
     * @param x Correspond au deplacement selon x
     * @param y Correspond au deplacement selon y
     */
    public void miseAJour() {
        //Mise a jour de toutes les fleches courantes
        for (int i=0; i< nbrMaxFleche; i++) {
            if (tabFleche[i] != null) {
                // Si apres mise a jour la fleche touche un obstacle
                tabFleche[i].miseAJour();
                if (!tabFleche[i].estPresent()) {
                    tabFleche[i] = null;
                    nbrCourantFleche--;
                }
            }
        }
        if (annimation_coeur) {
            vie.miseAJour();
            if (vie.compteurPourVie == 60) {
                this.annimation_coeur = false;
                vie.compteurPourVie = 0;
            }
        }
        this.deplacementJoueur();
        compteur ++;
        if (compteur%dynamisme == 0) {
            //dynamisme de notre joueur 
            if (changement_texture == 1) {
                changement_texture = 2;
            } else if (changement_texture == 2) {
                changement_texture = 1;
            }
            this.attaquer();
            compteur = 0;
            
            
        }
       

    }
    public void afficher(Graphics2D g) {
        vie.afficher(g); // afficher la vie du joueur 
        //hitbox.afficher(g); // afficher la hitbox du joueur 
        this.afficherJoueur(g); //// afficher le joueur 
        // Afficher toute les fleches courantes
        for (int i=0; i< nbrMaxFleche; i++) {
            if (tabFleche[i] != null) {
                tabFleche[i].afficher(g);
            }
        }
    }

    public Direction getDirection() {
        switch (deplacement) {
            case "gauche" : 
            case "gauche_static" :
                return Direction.GAUCHE;
            case "droite" : 
            case "droite_static" :
                return Direction.DROITE;
            case "haut" : 
            case "derriere_static" :
                return Direction.HAUT;
            case "bas" : 
            case "avant_static" :
                return Direction.BAS;
        }
        return Direction.BAS;
    }

    private void getImage() {
        try {
            Avant_marche1 = ImageIO.read(getClass().getResourceAsStream("/ressources/Avant_marche1.png"));
            Avant_marche2 = ImageIO.read(getClass().getResourceAsStream("/ressources/Avant_marche2.png"));
            Avant_static = ImageIO.read(getClass().getResourceAsStream("/ressources/Avant_static.png"));
            Derriere_marche1 = ImageIO.read(getClass().getResourceAsStream("/ressources/Derriere_marche1.png"));
            Derriere_marche2 = ImageIO.read(getClass().getResourceAsStream("/ressources/Derriere_marche2.png"));
            Derriere_static = ImageIO.read(getClass().getResourceAsStream("/ressources/Derriere_static.png"));
            Droite_marche = ImageIO.read(getClass().getResourceAsStream("/ressources/Droite_marche.png"));
            Droit_static = ImageIO.read(getClass().getResourceAsStream("/ressources/Droite_static.png"));
            Gauche_static = ImageIO.read(getClass().getResourceAsStream("/ressources/Gauche_static.png"));
            Gauche_marche = ImageIO.read(getClass().getResourceAsStream("/ressources/Gauche_marche.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void afficherJoueur(Graphics2D g) {
        BufferedImage image = null;
        // 0 -> statique
        switch (deplacement) {
        case "haut":
            if (changement_texture == 1) {
                image = Derriere_marche1;
            }
            if (changement_texture == 2) {
                image = Derriere_marche2;
            }
            break;
        case "derriere_static" : 
            image = Derriere_static;
            break;
        
        case "bas":
            if (changement_texture == 1) {
                image = Avant_marche1;
            }
            if (changement_texture == 2) {
                image = Avant_marche2;
            }
            break;
        case "avant_static" : 
            image = Avant_static;
            break;
        case "gauche" :
            if (changement_texture == 1) {
                image = Gauche_marche;
            }
            if (changement_texture == 2) {
                image = Gauche_static;
            }
            break;
        case "gauche_static" : 
            image = Gauche_static;
            break;
        case "droite" :
            if (changement_texture == 1) {
                image = Droite_marche;
            }
            if (changement_texture == 2) {
                image = Droit_static;
            }
            break;
        case "droite_static" : 
            image = Droit_static;
            break;
        }

        g.drawImage(image, this.x, this.y, jeu.tailleCaseReelle, jeu.tailleCaseReelle, null);

        /* Dessiner les Hitbox */

    }

    private void deplacementJoueur() {
        if (mouvement.bas) {
            deplacement = "bas";
        }
        else if (mouvement.haut) {
            deplacement = "haut";
        }
        else if (mouvement.gauche) {
            deplacement = "gauche";
        }
        else if (mouvement.droite) {
            deplacement = "droite";
        } else { 
            switch (deplacement) {
            case "bas" :
                deplacement = "avant_static";
                break;
            case "haut" :
                deplacement = "derriere_static";
                break;
            case "droite" :
                deplacement = "droite_static";
                break;
            case "gauche" :
                deplacement = "gauche_static";
                break;
            }
        }   
        if (!jeu.collisionVerificateur.verifierCollision(this)){
            switch(deplacement){
            case "haut" :
                this.y -= vitesse;
                break;
            case "bas" :
                this.y += vitesse;
                break;
            case "gauche" :
                this.x -= vitesse;
                break;
            case "droite" :
                this.x += vitesse;
                break;
            }
        }
    }
}