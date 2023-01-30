package Entite;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import Main.*;

public class Fleche implements Entite{
    public int x,y;
    private Entite entite;
    private BufferedImage fleche_droite, fleche_gauche, fleche_haut, fleche_bas, image;
    private boolean estPresent;
    private int vitesse = 6;
    private Direction direction;
    private Jeu jeu;
    
    //Lilian : fleche alliee ou ennemie ,
    private Boolean Ennemi;

    //Lilian : modification du constructeur pour indiquer la nature alliee ou ennemie de la fleche
    public Fleche(Entite e, Jeu jeu, Boolean ennemi) {
        this.jeu = jeu;
        this.entite = e;
        direction = e.getDirection();
        this.estPresent = true;
        this.Ennemi = ennemi;
        getImage();
        switch (entite.getDirection()) {
            case DROITE :
                this.x = entite.getX() + 32;
                this.y = entite.getY();
                image = fleche_droite;
                break;
            case GAUCHE : 
                this.x = entite.getX() - 32;
                this.y = entite.getY();
                image = fleche_gauche;
                break;
            case HAUT : 
                this.x = entite.getX();
                this.y = entite.getY() - 32;
                image = fleche_haut;
                break;
            case BAS : 
                this.x = entite.getX();
                this.y = entite.getY() + 32;
                image = fleche_bas;
                break;
        }
        //afficher fleche
    }
    
    public void setVitesse(int x) {
        this.vitesse = x;
    }


    @Override
    public boolean estPresent() {
        return this.estPresent;
    }

    @Override
    public Direction getDirection() {
        return this.direction;
    }

    @Override
    public int getVitesse() {
        return this.vitesse;
    }

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }

    public void miseAJour() {
        if (this.estPresent) {
            if (!jeu.collisionVerificateur.verifierCollision(this)){
                switch (direction) {
                    case DROITE :
                        this.x += this.vitesse;
                        break;
                    case GAUCHE : 
                        this.x -= this.vitesse;
                        break;
                    case HAUT : 
                        this.y -= this.vitesse;
                        break;
                    case BAS : 
                        this.y += this.vitesse;
                        break;
                }
            } else {
                this.estPresent = false;
            }
            for (Ennemi e : jeu.ennemis) {
            	if (jeu.collisionVerificateur.verifCollisionEntites(this, e, Ennemi)) {
                    e.retirerDegats(1);
                    this.estPresent = false;
                }
            	
            }
            if (jeu.collisionVerificateur.verifCollisionEntites(this, jeu.boss, Ennemi)) {
            	jeu.boss.retirerDegats(1);
            	this.estPresent = false;
            }
            if (jeu.collisionVerificateur.verifCollisionEntites(jeu.joueur, this, Ennemi)) {
            	jeu.joueur.SubitDegat(0.5);
            	this.estPresent = false;
            }
            
        }   
    }
    public void afficher(Graphics2D g) {
        g.drawImage(this.image, this.x, this.y, jeu.tailleCaseReelle,jeu.tailleCaseReelle, null);

    }
    
    private void getImage() {
        try {
            fleche_gauche = ImageIO.read(getClass().getResourceAsStream("/ressources_entite/fleche_gauche.png"));
            fleche_bas = ImageIO.read(getClass().getResourceAsStream("/ressources_entite/fleche_bas.png"));
            fleche_droite = ImageIO.read(getClass().getResourceAsStream("/ressources_entite/fleche_droite.png"));
            fleche_haut = ImageIO.read(getClass().getResourceAsStream("/ressources_entite/fleche_haut.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	@Override
	public double getCoeurMax() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Hitbox getHitbox() {
		// TODO Auto-generated method stub
		return null;
	}
}