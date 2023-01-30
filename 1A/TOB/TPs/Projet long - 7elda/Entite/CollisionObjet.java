package Entite;
import Main.*;

public class CollisionObjet {
	Jeu jeu;
	
	public CollisionObjet(Jeu jeu) {
		this.jeu = jeu;
	}
	
	public void verifierCollision(ObjetCollectable o) {
		
		//Determination des coordonnes de chaque face du jeu.joueur
		int faceHaut = jeu.joueur.getY() + 40;
		int faceBas = jeu.joueur.getY() + jeu.tailleCaseReelle;
		int faceGauche = jeu.joueur.getX() + 17;
		int faceDroit = jeu.joueur.getX() + 28 + 17;

		

		if (jeu.joueur.mouvement.haut) {
			faceHaut = (faceHaut - jeu.joueur.vitesse);
			if (faceHaut < o.getY() + jeu.tailleCaseReelle && faceHaut > o.getY()) {
				if ((faceDroit < o.getX() + jeu.tailleCaseReelle && faceDroit > o.getX()) ||
						(faceGauche > o.getX() && faceGauche < o.getX() + jeu.tailleCaseReelle)) {
					o.collisionRencontree = true;
				}
			}
		}
		else if(jeu.joueur.mouvement.bas) {
			faceBas = (faceBas + jeu.joueur.vitesse);
			if (faceBas > o.getY() && faceBas < o.getY() + jeu.tailleCaseReelle) {
				if ((faceDroit < o.getX() + jeu.tailleCaseReelle && faceDroit > o.getX()) ||
						(faceGauche > o.getX() && faceGauche < o.getX() + jeu.tailleCaseReelle)) {
					o.collisionRencontree = true;
				}

			}
		}
		else if (jeu.joueur.mouvement.droite) {
			faceDroit = (faceDroit + jeu.joueur.vitesse);
			if (faceDroit > o.getX() && faceDroit < o.getX() + jeu.tailleCaseReelle) {
				if ((faceHaut < o.getY() + jeu.tailleCaseReelle && faceHaut > o.getY()) ||
						(faceBas > o.getY() && faceBas < o.getY() + jeu.tailleCaseReelle)) {
					o.collisionRencontree = true;
				}
			}
		}
		else if (jeu.joueur.mouvement.gauche) {
			faceGauche = (faceGauche - jeu.joueur.vitesse);
			if (faceGauche < o.getX() + jeu.tailleCaseReelle && faceGauche > o.getX()) {
				if ((faceHaut < o.getY() + jeu.tailleCaseReelle && faceHaut > o.getY()) ||
						(faceBas > o.getY() && faceBas < o.getY() + jeu.tailleCaseReelle)) {
					o.collisionRencontree = true;
				}
			}
		}
	}
}
