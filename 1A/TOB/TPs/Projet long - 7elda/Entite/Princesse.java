package Entite;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.ControleClavier;
import Main.Jeu;

public class Princesse implements Entite{
	public int x;
	public int y;
	private int nbrCourantFleche;
    public ControleClavier mouvement;
    public Jeu jeu;
    private Hitbox hitbox;
    /* Vie */
    public int vitesse = 3;
    public final static int dynamisme = 10;
    private BufferedImage Avant_marche1;
    private int changement_texture, compteur;
    private String deplacement;


    public Princesse(Jeu jeu, ControleClavier mouvement) {
        this(jeu, mouvement, 500, 450);
    }
    
    public Princesse(Jeu jeu, ControleClavier mouvement, int x, int y) {
    	this.hitbox = new Hitbox(this,-15, 66, 50, -13); 
    	this.nbrCourantFleche = 0;
        this.x = 420;
        this.y = 320 ;
        this.jeu = jeu;
        this.mouvement = mouvement;
        getImage();
        this.deplacement = "bas";
        this.changement_texture = 2;
    	
    	this.x = 500;
    	this.y = 450;
    	this.jeu = jeu;
    }

    public int getVitesse() {
        return this.vitesse;
    }


    public int getX() { return this.x; }
    
    public int getY() { return this.y; }

    /**Update les coordonnees
     * @param x Correspond au deplacement selon x
     * @param y Correspond au deplacement selon y
     */
    public void miseAJour() {
        this.deplacementJoueur();
        compteur ++;
        if (compteur%dynamisme == 0) {
            //dynamisme de notre joueur 
            if (changement_texture == 1) {
                changement_texture = 2;
            } else if (changement_texture == 2) {
                changement_texture = 1;
            }
            compteur = 0;
            
            
        }

    }
    public void afficher(Graphics2D g) {
        
        this.afficherJoueur(g);
        // Afficher toute les fleches courantes
        
    }

    public Direction getDirection() {
        return Direction.BAS;
    }

    private void getImage() {
        try {
            Avant_marche1 = ImageIO.read(getClass().getResourceAsStream("/ressources/princesse.png"));
       
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void afficherJoueur(Graphics2D g) {
        BufferedImage image = null;

        image = Avant_marche1;

      

        g.drawImage(image, this.x, this.y, jeu.tailleCaseReelle, jeu.tailleCaseReelle, null);

    }

    private void deplacementJoueur() {
       
        deplacement = "bas";
        
       
    }

	@Override
	public boolean estPresent() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double getCoeurMax() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public Hitbox getHitbox() {
		// TODO Auto-generated method stub
		return this.hitbox;
	}
	
}