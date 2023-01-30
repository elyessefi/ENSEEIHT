package Main;

import java.awt.image.BufferedImage;

import Entite.ListeObjet;
import Entite.Objet;

public class Inventaire {
		
		public Jeu jeu;
		public int nbColonne, nbLigne;
		public int tailleInv = nbColonne * nbLigne;
		public int hauteur, largeur;
		public Objet[] listeObjet;
		public ListeObjet tousLesObjets;
		public boolean vide, plein;
		
		/*
		 * Definir la taille de l'inventaire et les objets que l'on peut ajouter
		 */
		public Inventaire(int nbCol, int nbLigne, int largeur, int hauteur, ListeObjet liste) {
			this.hauteur = hauteur;
			this.largeur = largeur;
			this.nbColonne = nbCol;
			this.nbLigne = nbLigne;
			this.listeObjet = new Objet[12];
			this.tousLesObjets = liste;
			this.vide = true;
			this.plein = false;
			this.initInventaire();
		}
		
		/*
		 * Initialiser l'inventaire
		 */
		public void initInventaire() {
			
			BufferedImage image = this.tousLesObjets.listeObjet[0][0].image;
			for (int k = 0; k < 12; k++) {
				
				this.listeObjet[k] = new Objet();
				this.listeObjet[k].image = image;
			}
		}
		
		/*
		 * Ajouter un objet a l'inventaire
		 */
		public void ajouterObjet(Objet objet) {
			try {
				int nbObjet = this.getNbObjetInv();
				
				if (nbObjet < 12) {
					
					if(!estDansInv(objet)) {
						this.listeObjet[nbObjet].image = objet.image;
						this.listeObjet[nbObjet].nb = 1;
						this.listeObjet[nbObjet].cummulable = objet.cummulable;
						this.listeObjet[nbObjet].nom = objet.nom;
						
					} else {
						if (objet.cummulable) {
							int pos = this.postion(objet);
							this.listeObjet[pos].nb++;
							System.out.println("La pos" + pos);
						}
					}
					
				} else {
					throw new InventairePleinException();
				}
				System.out.println(nbObjet);
				
				
			} catch (InventairePleinException e) {
				System.out.println("Inventaire plein");
			}
			
		}
		
		/*
		 * Retirer un objet de l'inventaire
		 */
		public void retirerObjet(Objet objet) {
			try {
				int nbObjet = this.getNbObjetInv();
				if (nbObjet  > 0) {
					
					boolean fin = false;
					int compt = 0;
					
					while (!fin && compt < 12) {
						if (this.listeObjet[compt].image == objet.image) {
							if (objet.cummulable && this.listeObjet[compt].nb > 1) {
								this.listeObjet[compt].nb--;
							} else {
								Objet[] listeTampon = new Objet[12 - compt + 1];
								int j = 0;
								
								for (int k = compt + 1; k < 12; k++) {
									listeTampon[j] = this.listeObjet[k];
									j++;
								}
								j = 0;
								for (int i = compt; i < 12; i++) {
									if ( i == 11) {
										this.listeObjet[i] = this.tousLesObjets.listeObjet[0][0];
									} else {
										this.listeObjet[i] = listeTampon[j];
										j++;
									}
								}
								
								fin = true;
							}
							
						} compt++;
					}
					

				} else {
					throw new InventaireVideException();
				}
			} catch (InventaireVideException f) {
				System.out.println("Inventaire vide");
			}
		}
		
		/*
		 * Obtenir le nb d'objets dans l'inventaire
		 */
		public int getNbObjetInv() {
			int k = 0;
			int compt = 0;
			while (k < 12 && this.listeObjet[k].image != this.tousLesObjets.listeObjet[0][0].image) {
				compt++;
				k++;
			}
			return compt;
		}
		
		/*
		 * Savoir si l'objet est dans l'inventaire
		 * @param : un objet
		 */
		public boolean estDansInv(Objet o) {
			int nb = getNbObjetInv();
			for (int k = 0; k < nb; k++) {
				if (this.listeObjet[k].image == o.image) {
					return true;
				}
			}
			return false;
		}

		/*
		 * Obtenir la position de l'objet dans l'inventaire
		 * @param :l'objet
		 */
		public int postion(Objet o) {
			int compt = 0;
			int nb = getNbObjetInv();
			boolean trouve = false;
			try {
				if (estDansInv(o)) {
					while (compt < nb && !trouve) {
						if (o.image == this.listeObjet[compt].image) {
							trouve = true;
						} else {
							compt++;
						}
					}
					return compt;
				} else {
					throw new ObjetAbsentException();
				}
			} catch (ObjetAbsentException e) {
				return 0;
			}
			
		}
		
		/*
		 * Verifier si le joueur possede la cle permettant d'acceder a la salle de la princesse
		 */
		public boolean checkClePrincesse() {
			int k = this.getNbObjetInv();
			int compt = 0;
			boolean fin = false;

			while(!fin && compt < k) {
				if (this.listeObjet[compt].image == this.tousLesObjets.listeObjet[6][0].image) {
					fin = true;
				} else {
					compt++;
				}
			}
			return fin;
		}

		/*
		 * Verifier si le joueur possede la cle pour rentrer dans la salle du boss
		 */
		public boolean checkCleBoss() {
			int k = this.getNbObjetInv();
			int compt = 0;
			boolean fin = false;

			while(!fin && compt < k) {
				if (this.listeObjet[compt].image == this.tousLesObjets.listeObjet[5][0].image) {
					fin = true;
				} else {
					compt++;
				}
			}

			return fin;
		}
}