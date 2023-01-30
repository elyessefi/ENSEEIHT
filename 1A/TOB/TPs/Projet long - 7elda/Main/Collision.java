
package Main;

import java.awt.Point;

import Entite.*;

public class Collision {
	
	Jeu jeu;
	// Pour passer de la salle classique a la salle du boss
	public boolean lancer_salleboss = false;
	public boolean chgt_salleboss = false;
	
	//Pour passer de la salle du boss a la salle classique
	public boolean lancer_salleboss_classique = false;
	public boolean chgt_salleboss_classique = false;

	//Pour passer de la salle classique a la salle princesse
	public boolean lancer_salleprincesse = false;
	public boolean chgt_salleprincesse = false;
	
	//Pour passer de la salle princesse a la salle classique
	public boolean retour_salleclassique = false;
	public boolean chgt_salleprinc_salleclassique = false;

	//Pour passer de la salle classique a la salle classiquepassage
	public boolean chgt_salle = false;
	public boolean lancer_sallesecrete = false;

	//Pour passer de la salle classiquepassage a la salle classique3
	public boolean chgt_salle_classique_bis  = false;
	public boolean lancer_salle_classiquebis = false;
	
	//Pour passer de la salle classique3 au magasin
	public boolean lancer_salle_classique3 = false; 
	public boolean chgt_salle_classique_3  = false;
	public boolean nepeutopasentre = false;
	
	//Pour passer du magasin a la salle classique3
	public boolean chgt_salle_msalleclassique = false;
	public boolean lancer_magasin = false;

	//Pour passer de la salleclassique3 a la salle classiquepassage
	public boolean chgt_salle_3sallecclassique2= false;
	public boolean lancer_salle_3salleclassique2 = false; 
	
	// Pour passer de la salle classiquepassage a la salle classique
	public boolean lancer_sallebis_salleclassique =false;
	public boolean chgt_sallebis_salleclassique = false;
	
	//Pour passer de la salle classique3 a la salle secrete
	public boolean lancer_salleclas3_sallesecrete = false;
	public boolean chgt_salleclas3_sallesecrete = false;
	
	//Pour passer de la salle secrete a la salle classique3
	public boolean lancer_sallesecrete_salleclassique3 = false;
	public boolean chgt_sallesecrete_salleclassique3 = false;
	
	/*
	 * Definir la collision du jeu
	 * @Param : Jeu 
	 */
	public Collision(Jeu jeu) {
		this.jeu = jeu;
	}
	
	/*
	 * Verifier si il y a une collision entre le joueur et les elements de la salle
	 * @param : Jouueur
	 */
	public Boolean verifierCollision(Entite joueur) {
		
		//Determination des coordonnees de chaque face du joueur
		int faceHaut = joueur.getY() + 40;
		int faceBas = joueur.getY() + jeu.tailleCaseReelle;
		int faceGauche = joueur.getX() + 17;
		int faceDroit = joueur.getX() + 28 + 17;
		
		//Determination des colonnes et les lignes
		int faceHautLigne = faceHaut/jeu.tailleCaseReelle;
		int faceBasLigne = faceBas/jeu.tailleCaseReelle;
		int faceGaucheColonne = faceGauche/jeu.tailleCaseReelle;
		int faceDroitColonne = faceDroit/jeu.tailleCaseReelle;
		
		int coin1, coin2;
		if (joueur.getDirection() == Direction.HAUT) {
			faceHautLigne = (faceHaut - joueur.getVitesse())/jeu.tailleCaseReelle;
			coin1 = jeu.salle.getSalle().getCoordonneeMap()[faceGaucheColonne][faceHautLigne];
			coin2 = jeu.salle.getSalle().getCoordonneeMap()[faceDroitColonne][faceHautLigne];
			
			
			if((joueur instanceof Joueur) && (jeu.salle.getSalle().getMap()[coin1].collision_porte == true || jeu.salle.getSalle().getMap()[coin2].collision_porte == true)) {
				this.lancer_salleboss = false;
				this.lancer_sallesecrete_salleclassique3 = false;
				this.lancer_magasin = false;
				this.lancer_salleprincesse =false;
				this.retour_salleclassique = false;
				this.chgt_salleboss = false;
				this.chgt_sallesecrete_salleclassique3 =false;
				this.chgt_salle_classique_bis = false;
				this.lancer_salle_classiquebis = false;
				this.chgt_salle_classique_3 = false;
				this.lancer_salle_classique3 = false;
				this.chgt_salle_msalleclassique = false;
				this.chgt_salle_3sallecclassique2= false;
				this.lancer_salle_3salleclassique2 = false; 
				this.lancer_sallebis_salleclassique =false;
				this.chgt_sallebis_salleclassique = false;
				this.chgt_salleprincesse = false;
				this.chgt_salleprinc_salleclassique = false;
				this.lancer_salleboss_classique = false;
				this.chgt_salleboss_classique = false;
				this.lancer_salleclas3_sallesecrete = false;
				this.chgt_salleclas3_sallesecrete = false;
				this.nepeutopasentre = false;

				this.chgt_salle = true;
				this.lancer_sallesecrete = true;
				
				return true;
				
				
			}
			
			else if((joueur instanceof Joueur) && (jeu.salle.getSalle().getMap()[coin1].collision_porteboss == true || jeu.salle.getSalle().getMap()[coin2].collision_porteboss == true)) {
				this.lancer_salleboss = false;
				this.lancer_sallesecrete_salleclassique3 = false;
				this.lancer_magasin = false;
				this.lancer_salleprincesse =false;
				this.retour_salleclassique = false;
				this.chgt_salleboss = false;
				this.chgt_sallesecrete_salleclassique3 =false;
				this.chgt_salle_classique_bis = false;
				this.lancer_salle_classiquebis = false;
				this.chgt_salle_classique_3 = false;
				this.lancer_salle_classique3 = false;
				this.chgt_salle_msalleclassique = false;
				this.chgt_salle_3sallecclassique2= false;
				this.lancer_salle_3salleclassique2 = false; 
				this.lancer_sallebis_salleclassique =false;
				this.chgt_sallebis_salleclassique = false;
				this.chgt_salleprincesse = false;
				this.chgt_salleprinc_salleclassique = false;
				this.lancer_sallesecrete = false;
				this.chgt_salle = false;
				this.lancer_salleclas3_sallesecrete = false;
				this.chgt_salleclas3_sallesecrete = false;
				this.nepeutopasentre = false;
				this.lancer_salleboss_classique = true;
				this.chgt_salleboss_classique = true;
				return true;
				
				
			}
			
			else if((joueur instanceof Joueur) && (jeu.salle.getSalle().getMap()[coin1].collision_porte_chgtsallebis == true || jeu.salle.getSalle().getMap()[coin2].collision_porte_chgtsallebis == true)) {
				this.lancer_salleboss = false;
				this.lancer_sallesecrete_salleclassique3 = false;
				this.lancer_magasin = false;
				this.lancer_salleprincesse =false;
				this.retour_salleclassique = false;
				this.chgt_salleboss = false;
				this.chgt_sallesecrete_salleclassique3 =false;				
				this.lancer_sallesecrete = false;
				this.chgt_salle = false;
				this.chgt_salle_msalleclassique = false;
				this.chgt_salle_classique_3 = false;
				this.lancer_salle_classique3 = false;
				this.chgt_salle_3sallecclassique2= false;
				this.lancer_salle_3salleclassique2 = false; 
				this.lancer_sallebis_salleclassique =false;
				this.chgt_sallebis_salleclassique = false;
				this.chgt_salleprincesse = false;
				this.chgt_salleprinc_salleclassique = false;
				this.lancer_salleboss_classique = false;
				this.chgt_salleboss_classique = false;
				this.lancer_salleclas3_sallesecrete = false;
				this.chgt_salleclas3_sallesecrete = false;
				this.nepeutopasentre = false;
				this.chgt_salle_classique_bis = true;
				this.lancer_salle_classiquebis = true;
				return true;
				
			}
			
			else if((joueur instanceof Joueur) && (jeu.salle.getSalle().getMap()[coin1].collision_porte_chgtsalle3 == true || jeu.salle.getSalle().getMap()[coin2].collision_porte_chgtsalle3 == true)) {
				this.lancer_salleboss = false;
				this.lancer_sallesecrete_salleclassique3 = false;
				this.lancer_magasin = false;
				this.lancer_salleprincesse =false;
				this.retour_salleclassique = false;
				this.chgt_salleboss = false;
				this.chgt_sallesecrete_salleclassique3 =false;				
				this.lancer_sallesecrete = false;
				this.chgt_salle = false;
				this.chgt_salle_classique_bis = false;
				this.lancer_salle_classiquebis = false;
				this.chgt_salle_msalleclassique = false;
				this.chgt_salle_3sallecclassique2= false;
				this.lancer_salle_3salleclassique2 = false; 
				this.lancer_sallebis_salleclassique =false;
				this.chgt_sallebis_salleclassique = false;
				this.chgt_salleprincesse = false;
				this.chgt_salleprinc_salleclassique = false;
				this.lancer_salleboss_classique = false;
				this.chgt_salleboss_classique = false;
				this.lancer_salleclas3_sallesecrete = false;
				this.chgt_salleclas3_sallesecrete = false;
				this.chgt_salle_classique_3 = true;
				this.lancer_salle_classique3 = true;
				return true;
				
			}
			
			else if (jeu.salle.getSalle().getMap()[coin1].collision == true || jeu.salle.getSalle().getMap()[coin2].collision == true) {
				return true;
			}
		}
		else if(joueur.getDirection() == Direction.BAS) {
			faceBasLigne = (faceBas + joueur.getVitesse())/jeu.tailleCaseReelle;
			coin1 = jeu.salle.getSalle().getCoordonneeMap()[faceGaucheColonne][faceBasLigne];
			coin2 = jeu.salle.getSalle().getCoordonneeMap()[faceDroitColonne][faceBasLigne];
		
			
			
			if(((joueur instanceof Joueur) && (jeu.salle.getSalle().getMap()[coin1].collision_ported == true || jeu.salle.getSalle().getMap()[coin2].collision_ported == true))) {
				this.lancer_sallesecrete = false;
				this.lancer_sallesecrete_salleclassique3 = false;
				this.lancer_magasin = false;
				this.lancer_salleprincesse =false;
				this.retour_salleclassique = false;
				this.chgt_salle = false;
				this.chgt_salle_classique_bis = false;
				this.lancer_salle_classiquebis = false;
				this.chgt_salle_classique_3 = false;
				this.lancer_salle_classique3 = false;
				this.chgt_sallesecrete_salleclassique3 =false;
				this.chgt_salle_msalleclassique = false;
				this.chgt_salle_3sallecclassique2= false;
				this.lancer_salle_3salleclassique2 = false; 
				this.lancer_sallebis_salleclassique =false;
				this.chgt_sallebis_salleclassique = false;
				this.chgt_salleprincesse = false;
				this.chgt_salleprinc_salleclassique = false;
				this.lancer_salleboss_classique = false;
				this.chgt_salleboss_classique = false;
				this.lancer_salleclas3_sallesecrete = false;
				this.chgt_salleclas3_sallesecrete = false;
				if (jeu.joueur.inv.checkCleBoss() == true) {
					this.nepeutopasentre = true;
				}
				this.lancer_salleboss = true;
				this.chgt_salleboss = true;

			
			return true;


			
	}else if((joueur instanceof Joueur) && (jeu.salle.getSalle().getMap()[coin1].collision_portec == true || jeu.salle.getSalle().getMap()[coin2].collision_ported == true)) {
			this.lancer_sallesecrete = false;
			this.lancer_salleboss = false;
			this.lancer_sallesecrete_salleclassique3 = false;
			this.lancer_salleprincesse =false;
			this.retour_salleclassique = false;
			this.chgt_salle = false;
			this.chgt_salleboss = false;
			this.chgt_sallesecrete_salleclassique3 =false;
			this.chgt_salle_classique_bis = false;
			this.lancer_salle_classiquebis = false;
			this.chgt_salle_classique_3 = false;
			this.lancer_salle_classique3 = false;
			this.chgt_salle_3sallecclassique2= false;
			this.lancer_salle_3salleclassique2 = false;
			this.lancer_sallebis_salleclassique =false;
			this.chgt_sallebis_salleclassique = false;
			this.chgt_salleprincesse = false;
			this.chgt_salleprinc_salleclassique = false;
			this.lancer_salleboss_classique = false;
			this.chgt_salleboss_classique = false;
			this.lancer_salleclas3_sallesecrete = false;
			this.chgt_salleclas3_sallesecrete = false;
			this.nepeutopasentre = false;

				this.lancer_magasin = true;
				this.chgt_salle_msalleclassique = true;

			
			return true;

			
	}else if(((joueur instanceof Joueur) && (jeu.salle.getSalle().getMap()[coin1].collision_porte_chgt3salleclassique == true || jeu.salle.getSalle().getMap()[coin2].collision_porte_chgt3salleclassique == true))) {
				this.lancer_sallesecrete = false;
				this.lancer_salleboss = false;
				this.lancer_sallesecrete_salleclassique3 = false;
				this.lancer_salleprincesse =false;
				this.retour_salleclassique = false;
				this.chgt_salle = false;
				this.chgt_salleboss = false;
				this.chgt_sallesecrete_salleclassique3 =false;
				this.chgt_salle_classique_bis = false;
				this.lancer_salle_classiquebis = false;
				this.chgt_salle_classique_3 = false;
				this.lancer_salle_classique3 = false;
				this.lancer_magasin = false;
				this.chgt_salle_msalleclassique = false;
				this.lancer_sallebis_salleclassique =false;
				this.chgt_sallebis_salleclassique = false;
				this.chgt_salleprincesse = false;
				this.chgt_salleprinc_salleclassique = false;
				this.lancer_salleboss_classique = false;
				this.chgt_salleboss_classique = false;
				this.lancer_salleclas3_sallesecrete = false;
				this.nepeutopasentre = false;
				this.chgt_salleclas3_sallesecrete = false;
				this.chgt_salle_3sallecclassique2= true;
				this.lancer_salle_3salleclassique2 = true; 
				return true;

				
		}
			
			else if(((joueur instanceof Joueur) && (jeu.salle.getSalle().getMap()[coin1].collision_porte_chgtbissalleclassique == true || jeu.salle.getSalle().getMap()[coin2].collision_porte_chgtbissalleclassique == true))) {
				this.lancer_sallesecrete = false;
				this.chgt_salleboss_classique = false;
				this.lancer_salleboss = false;
				this.lancer_sallesecrete_salleclassique3 = false;
				this.lancer_salleprincesse =false;
				this.retour_salleclassique = false;
				this.chgt_salle = false;
				this.chgt_salleboss = false;
				this.chgt_sallesecrete_salleclassique3 =false;
				this.chgt_salle_classique_bis = false;
				this.lancer_salle_classiquebis = false;
				this.chgt_salle_classique_3 = false;
				this.lancer_salle_classique3 = false;
				this.lancer_magasin = false;
				this.chgt_salle_msalleclassique = false;
				this.chgt_salle_3sallecclassique2= false;
				this.lancer_salle_3salleclassique2 = false; 
				this.chgt_salleprincesse = false;
				this.chgt_salleprinc_salleclassique = false;
				this.lancer_salleboss_classique = false;
				this.lancer_salleclas3_sallesecrete = false;
				this.chgt_salleclas3_sallesecrete = false;
				this.nepeutopasentre = false;
				this.lancer_sallebis_salleclassique =true;
				this.chgt_sallebis_salleclassique = true;
				
				return true;

				
		}

			else if (jeu.salle.getSalle().getMap()[coin1].collision == true || jeu.salle.getSalle().getMap()[coin2].collision == true) {
				return true;
			}
			
		}
		else if (joueur.getDirection() == Direction.DROITE) {
			faceDroitColonne = (faceDroit + joueur.getVitesse())/jeu.tailleCaseReelle;
			coin1 = jeu.salle.getSalle().getCoordonneeMap()[faceDroitColonne][faceHautLigne];
			coin2 = jeu.salle.getSalle().getCoordonneeMap()[faceDroitColonne][faceBasLigne];
			if((jeu.joueur.inv.checkClePrincesse()) && ((joueur instanceof Joueur) && (jeu.salle.getSalle().getMap()[coin1].collision_portee == true || jeu.salle.getSalle().getMap()[coin2].collision_portee == true))) {
				this.lancer_sallesecrete = false;
				this.lancer_salleboss = false;
				this.lancer_magasin = false;
				this.lancer_sallesecrete_salleclassique3 = false;
				this.retour_salleclassique = false;
				this.chgt_salle = false;
				this.chgt_salleboss = false;
				this.chgt_sallesecrete_salleclassique3 =false;
				this.chgt_salle_classique_bis = false;
				this.lancer_salle_classiquebis = false;
				this.chgt_salle_classique_3 = false;
				this.lancer_salle_classique3 = false;
				this.chgt_salle_msalleclassique = false;
				this.chgt_salle_3sallecclassique2= false;
				this.lancer_salle_3salleclassique2 = false; 
				this.lancer_sallebis_salleclassique =false;
				this.chgt_sallebis_salleclassique = false;
				this.chgt_salleprinc_salleclassique = false;
				this.lancer_salleboss_classique = false;
				this.chgt_salleboss_classique = false;
				this.lancer_salleclas3_sallesecrete = false;
				this.chgt_salleclas3_sallesecrete = false;
				this.nepeutopasentre = false;
				this.lancer_salleprincesse =true;
				this.chgt_salleprincesse = true;
				return true;

				
		
			}
			
			
			else if((joueur instanceof Joueur) && (jeu.salle.getSalle().getMap()[coin1].collision_portesecrete == true || jeu.salle.getSalle().getMap()[coin2].collision_portesecrete == true)) {
					this.lancer_sallesecrete = false;
					this.lancer_salleboss = false;
					this.lancer_magasin = false;
					this.lancer_sallesecrete_salleclassique3 = false;
					this.retour_salleclassique = false;
					this.chgt_salle = false;
					this.chgt_salleboss = false;
					this.chgt_sallesecrete_salleclassique3 =false;
					this.chgt_salle_classique_bis = false;
					this.lancer_salle_classiquebis = false;
					this.chgt_salle_classique_3 = false;
					this.lancer_salle_classique3 = false;
					this.chgt_salle_msalleclassique = false;
					this.chgt_salle_3sallecclassique2= false;
					this.lancer_salle_3salleclassique2 = false; 
					this.lancer_sallebis_salleclassique =false;
					this.chgt_sallebis_salleclassique = false;
					this.chgt_salleprinc_salleclassique = false;
					this.lancer_salleboss_classique = false;
					this.chgt_salleboss_classique = false;
					this.lancer_salleprincesse =false;
					this.chgt_salleprincesse = false;
					this.nepeutopasentre = false;
					this.lancer_salleclas3_sallesecrete = true;
					this.chgt_salleclas3_sallesecrete = true;
					return true;

					
			
				}
			
			
			else if (jeu.salle.getSalle().getMap()[coin1].collision == true || jeu.salle.getSalle().getMap()[coin2].collision == true) {
				return true;
			}
		}
		else if (joueur.getDirection() == Direction.GAUCHE) {
			faceGaucheColonne = (faceGauche - joueur.getVitesse())/jeu.tailleCaseReelle;
			coin1 = jeu.salle.getSalle().getCoordonneeMap()[faceGaucheColonne][faceHautLigne];
			coin2 = jeu.salle.getSalle().getCoordonneeMap()[faceGaucheColonne][faceBasLigne];
			
			if((joueur instanceof Joueur) && (jeu.salle.getSalle().getMap()[coin1].collision_porte_princesse == true || jeu.salle.getSalle().getMap()[coin2].collision_porte_princesse == true)) {
				this.lancer_sallesecrete = false;
				this.lancer_salleboss = false;
				this.lancer_magasin = false;
				this.lancer_sallesecrete_salleclassique3 = false;
				this.lancer_salleprincesse =false;
				this.chgt_salle = false;
				this.chgt_salleboss = false;
				this.chgt_sallesecrete_salleclassique3 =false;
				this.chgt_salle_classique_bis = false;
				this.lancer_salle_classiquebis = false;
				this.chgt_salle_classique_3 = false;
				this.lancer_salle_classique3 = false;
				this.chgt_salle_msalleclassique = false;
				this.chgt_salle_3sallecclassique2= false;
				this.lancer_salle_3salleclassique2 = false; 
				this.lancer_sallebis_salleclassique =false;
				this.chgt_sallebis_salleclassique = false;
				this.chgt_salleprincesse = false;
				this.lancer_salleboss_classique = false;
				this.chgt_salleboss_classique = false;
				this.lancer_salleclas3_sallesecrete = false;
				this.chgt_salleclas3_sallesecrete = false;
				this.nepeutopasentre = false;
				this.chgt_salleprinc_salleclassique = true;
				this.retour_salleclassique = true;

				return true;

				
		
			}
			
			else if((joueur instanceof Joueur)&&(jeu.salle.getSalle().getMap()[coin1].collision_porteb == true || jeu.salle.getSalle().getMap()[coin2].collision_porteb == true)) {
				this.lancer_sallesecrete = false;
				this.lancer_salleboss = false;
				this.lancer_magasin = false;
				this.lancer_salleprincesse =false;
				this.retour_salleclassique = false;
				this.chgt_salle = false;
				this.chgt_salleboss = false;
				this.chgt_salle_classique_bis = false;
				this.lancer_salle_classiquebis = false;
				this.chgt_salle_classique_3 = false;
				this.lancer_salle_classique3 = false;
				this.chgt_salle_msalleclassique = false;
				this.chgt_salle_3sallecclassique2= false;
				this.lancer_salle_3salleclassique2 = false; 
				this.lancer_sallebis_salleclassique =false;
				this.chgt_sallebis_salleclassique = false;
				this.chgt_salleprincesse = false;
				this.chgt_salleprinc_salleclassique = false;
				this.lancer_salleboss_classique = false;
				this.chgt_salleboss_classique = false;
				this.lancer_salleclas3_sallesecrete = false;
				this.chgt_salleclas3_sallesecrete = false;
				this.nepeutopasentre = false;
				this.lancer_sallesecrete_salleclassique3 = true;
				this.chgt_sallesecrete_salleclassique3 =true;


			
				
			}
			else if (jeu.salle.getSalle().getMap()[coin1].collision == true || jeu.salle.getSalle().getMap()[coin2].collision == true) {
				return true;
			}
			
		} 
		return false;
	}
	
	



	private static boolean PointDedans(Entite e, Point p) {
		
		if ((p.getX() > e.getHitbox().getPointGauche().getX()) 
		&& (p.getX() < e.getHitbox().getPointDroit().getX()) 
		&& (p.getY() < e.getHitbox().getPointBas().getY()) 
		&& (p.getY() > e.getHitbox().getPointHaut().getY())) 
	
		{
			return true;
		}
		return false;
	}
	/*
	 * Verifier la collision entre deux entites
	 * @param : premiere et deuxieme entite
	 */
	public Boolean verifCollisionEntites(Entite e1, Entite e2) {
		int val1 = abs(e1.getX() - e2.getX());
		int val2 = abs(e1.getY() - e2.getY());
		return ((val1 < 32) && (val2 < 32));
	}
	/*
	 * Verifier la collision entre la fleche et un ennemi
	 * @param : une fleche, un ennemi
	 */
	public Boolean verifCollisionEntites(Fleche e1, Ennemi e2, Boolean ennemi) {
		int val1 = abs(e1.getX() - e2.getX());
		int val2 = abs(e1.getY() - e2.getY());
		return ((val1 < e2.getType()*32) && (val2 < e2.getType()*32) && !ennemi);
	}
	
	/*
	 * Verifier la collision entre le joueur et une fleche
	 * @param : le joueur et la fleche
	 */
	public Boolean verifCollisionEntites(Joueur e1, Fleche e2, Boolean ennemi) {
		int val1 = abs(e1.getX() - e2.getX());
		int val2 = abs(e1.getY() - e2.getY());
		return ((val1 < 32) && (val2 < 32) && ennemi);
	}
	
	/*
	 * Calculer la valeur absolue
	 * @param : Un entier
	 */
	private int abs(int i) {
		if (i >= 0) {
			return i;
		}
		else {
			return -i;
		}
	}

	/*
	 * Verifier la collision entre un joueur et une entite
	 * @param : le joueur et l'entite
	 */
	public static boolean collisionJoueur(Joueur joueur, Entite e) {
	
		if (PointDedans(joueur, e.getHitbox().getPointDroit()) || PointDedans(joueur, e.getHitbox().getPointGauche()) || PointDedans(joueur, e.getHitbox().getPointBas()) || PointDedans(joueur, e.getHitbox().getPointHaut()) ) {
			return true;
		}
		return false;
		
	}
}