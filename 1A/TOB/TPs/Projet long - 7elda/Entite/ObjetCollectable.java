package Entite;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Main.Jeu;

public class ObjetCollectable implements Entite{
	
	public int x, y;
	public BufferedImage image1, image2, image3, image4;
	public int compteur = 0;
	public int numeroImage = 1;
	public Jeu jeu;
	public int label;
	public boolean collisionRencontree;
	public int ind;
	public boolean estPresent = false;
	
	public ObjetCollectable(Jeu jeu, int x, int y, String nom, int label, boolean estPresent) {
		this.jeu = jeu;
		this.x = x;
		this.y = y;
		this.label = label;
		this.estPresent = estPresent;
		this.ind = jeu.listeObj.getIndice(nom);
		this.image1 = jeu.listeObj.listeObjet[ind][0].image;
		this.image2 = jeu.listeObj.listeObjet[ind][1].image;
		this.image3 = jeu.listeObj.listeObjet[ind][2].image;
		
		
	}
	
	public int getPos() {
		int k = 0;
		while (jeu.listeEntite[k] != null && jeu.listeEntite[k].label != label) {
			k++;
		}
		return k;
	}
	
	
	public void retirer() {
		this.estPresent = false;
		int k = getPos();
		int j = 0;
		ObjetCollectable[] listeTampon = new ObjetCollectable[15];
		while (jeu.listeEntite[k + 1] != null) {
			listeTampon[j] = jeu.listeEntite[k + 1];
			j++;
			k++;
		}
		k = 0;
		for (int i = getPos(); i < j + 1; i++) {
 			jeu.listeEntite[i] = listeTampon[k];
			k++;
		}
		
	}
	
	public void miseAJour() {
		if (estPresent) {
			collisionRencontree = false;
			jeu.verifObj.verifierCollision(this);
			if (collisionRencontree) {
				numeroImage = 4;

				this.retirer();
				if (this.ind == 4) {
					jeu.joueur.comptPiece++;
				} else {
					jeu.joueur.inv.ajouterObjet(jeu.listeObj.listeObjet[ind][0]);
				}
			} else {
				this.compteur++;
				if (this.compteur > 5) {
					switch (numeroImage) {
					case 1 :
						this.numeroImage = 2;
						break;
					case 2 :
						this.numeroImage = 3;
						break;
					case 3 :
						this.numeroImage = 1;
						break;
					}
					this.compteur = 0;
				}
			
			}
		}
		
	}
	
	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}
	
	public int getVitesse() {
		return 0;
	}
	

	public boolean estPresent() {
		return false;
	}


	public void afficher(Graphics2D g) {
		BufferedImage image = null;
		
		switch (this.numeroImage) {
		case 1 :
			image = this.image1;
			break;
		case 2 :
			image = this.image2;
			break;
		case 3 :
			image = this.image3;
			break;
		case 4 : 
			image = this.image4;
			
			break;
			
		}
		
		g.drawImage(image, this.x, this.y, jeu.tailleCaseReelle/2, jeu.tailleCaseReelle/2, null);
	}

	@Override
	public Direction getDirection() {
		// TODO Auto-generated method stub
		return null;
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