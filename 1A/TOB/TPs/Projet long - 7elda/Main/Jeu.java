package Main;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import Entite.Boss;
import Entite.CollisionObjet;
import Entite.Druide;
import Entite.Ennemi;
import Entite.Ennemi1;
import Entite.Joueur;
import Entite.ListeObjet;
import Entite.ObjetCollectable;
import Entite.Princesse;
import Map.SalleSecrete;
import Map.Salleboss;
import Map.SallePrincesse;
import Map.Importationsalle;
import Map.Magasin;
import Map.Salle;
import Map.SalleClassique;
import Map.SalleClassique3;
import Map.SalleClassiquepassage;

public class Jeu extends JPanel implements Runnable {
	//Parametres Ecran
	public final static int tailleCaseDeBase = 32; // Textures en 32 X 32
	public final int echelle = 2;
	public final int tailleCaseReelle = tailleCaseDeBase * echelle; // Taille de l'ecran
	public final int maxCol = 16; // Nombre de cases en colonne
	public final int maxLigne = 12; // Nombre de cases en ligne
	public final int largeurEcran = 1024; // Taille de la fenetre en largeur
	public final int hauteurEcran = 750; //Taille de la fenetre en hauteur
	public final int IPS = 60; // Images par seconde
	public Graphics2D g;
	public Collision collisionVerificateur = new Collision(this);
	public ListeObjet listeObj = new ListeObjet();
	public ObjetCollectable listeEntite[];
	public CollisionObjet verifObj = new CollisionObjet(this);
	//public ObjetCollectable listeEntite1[];
	public List<Ennemi1> ennemis = new ArrayList<>();

	public boolean recompense = false;
	public Boss boss = new Boss(this);
	public boolean bossVaincu = false;
	public boolean premiereEntre = true;
	CreationSauvegarde gestionnaireSauvegarde = new CreationSauvegarde(this);
	public Salle salleSauvegarde = new SalleClassique(this);
	
	
	/** Etat de jeu (JEU - PAUSE - INVENTAIRE) */
	enum EtatJeu {
		EN_JEU, PAUSE, INVENTAIRE
	};
	public static EtatJeu etatJeu = EtatJeu.EN_JEU;
	//Mettre le jeu en pause
	public Boolean pause = false;
	JFrame fenetrePause = new JFrame("Pause");

	
	JButton ajouter, retirer;

	
	//Definir la carte du jeu
	SalleClassique map =new SalleClassique(this);
	public SalleSecrete salle2 = new SalleSecrete(this);
	public Importationsalle salle  =  new Importationsalle(new SalleClassique(this));
	public Salle sallec;
	// Parametre joueur
	ControleClavier gestionnaireTouche = new ControleClavier(this);
	
	//Definir la collision 
	Collision collision = new Collision(this);
	public Joueur joueur = new Joueur(this, gestionnaireTouche);
	public GUI i = new GUI(this, joueur);
	public Princesse princesse = new Princesse(this, gestionnaireTouche);
	public Druide druide = new Druide(this, gestionnaireTouche);
	//Map entiere
	MapEntier mapEntier = new MapEntier(gestionnaireTouche, joueur, this);

	
	// Creation du "jeu"
	public Jeu() {
		ennemis.add(new Ennemi1(this, 400, 400, 1, 2));
		this.listeEntite = new ObjetCollectable[20];

		
		this.setPreferredSize(new Dimension(largeurEcran,hauteurEcran)); // Taille de la fenetre
		this.setDoubleBuffered(true);
		this.addKeyListener(gestionnaireTouche); // Ajout du gestionnaire de touches
		this.setFocusable(true);
		mapEntier.setPreferredSize(new Dimension(new Dimension(largeurEcran, hauteurEcran)));
		add(mapEntier);
	}
	
	public void ajouterEnnemi1(int x, int y) {
		ennemis.add(new Ennemi1(this,x,y, 2, 2));
		
	}
	
	/* Getteur du joueur (pour les ennemis) */
	public Joueur getJoueur() {
		return joueur;
	}

	
	// Creation de la boucle
	Thread threadJeu;
	
	public void debutThread() {
		threadJeu = new Thread(this);
		threadJeu.start();
	}

	// Methode qui actualise la position du joueur toutes les x secondes
	@Override
	public void run() {
		
		double intervalleAffichage = 1000000000 / IPS; // Une actualisation toutes les x secondes
		double delta = 0;
		long itePrecedente = System.nanoTime();
		long iteCourante;
		
		while(threadJeu != null) {
			
			if (joueur.mouvement.save) {
				threadJeu = null;
			}
			iteCourante = System.nanoTime();
			
			delta += (iteCourante - itePrecedente) / intervalleAffichage;
			itePrecedente = iteCourante;
			
			if (delta >= 1) {
				miseAJour();
				repaint();
				delta -= 1;
			}
		}
		
		Container contenu = fenetrePause.getContentPane();
		
		JLabel Texte = new JLabel("Que souhaitez-vous faire ?");
		JPanel boutons = new JPanel(new FlowLayout());
		contenu.add(boutons,BorderLayout.SOUTH);
		contenu.add(Texte, BorderLayout.NORTH);

		
		JButton boutonQuitter = new JButton("Quitter");
		JButton boutonSauvegarder = new JButton("Sauvegarder");
		JButton boutonReprendre = new JButton("Reprendre");
		boutons.add(boutonQuitter);
		boutons.add(boutonSauvegarder);
		boutons.add(boutonReprendre);
		boutonQuitter.addActionListener(new ActionQuitter());
		boutonReprendre.addActionListener(new ActionReprendre(this));
		boutonSauvegarder.addActionListener(new ActionSauvegarder());

		fenetrePause.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetrePause.setResizable(false); // Pas la possibilite de deformer la fenetre
        fenetrePause.setLocation(200, 300);
        fenetrePause.setSize(600,300);
		fenetrePause.isFocusable();
		fenetrePause.setVisible(true);
		

	}
	
	public void miseAJour() {
		
		if (gestionnaireTouche.afficherMap == true){
			mapEntier.dessinerMapEntier();
	}
	
		if (etatJeu == EtatJeu.EN_JEU) {

			joueur.miseAJour(); // Actualise la direction du joueur
			
			
			int k=0;
			while(this.listeEntite[k] != null) {
				this.listeEntite[k].miseAJour();
				k++;
			}
			
			for (Ennemi1 e : ennemis) {
				if (e.estPresent() && e.vivant) {
					e.miseAJour();
				} 
				
			}
			if (sallec instanceof Salleboss && !bossVaincu) {
				boss.miseAJour();
			}
		}
		
		
	}

	// Affichage (tous les elements qui seront affiches devront contenir une methode
		// afficher)
		public void paintComponent(Graphics graphique) {
			super.paintComponent(graphique);
			Graphics2D graphique2D = (Graphics2D) graphique;
			/** Affichage des elements du jeu */
			if (salleSauvegarde instanceof SalleClassique) {
			} else {
				salle = new Importationsalle(salleSauvegarde);

			}
			salle.getSalle().dessiner(graphique2D);
			sallec = salle.getSalle();

			joueur.afficher(graphique2D);
			i.afficher(graphique2D);
			i.afficherPiece(graphique2D);
			i.afficherEntite(graphique2D);
			
			if (sallec instanceof Magasin) {
				druide.afficher(graphique2D);
				boolean collision_druide = Collision.collisionJoueur(joueur, druide);
				if(collision_druide ==true) {
					i.dessinerMagasin(graphique2D);
				}
			}

			if (sallec instanceof Salleboss) {
				if (!bossVaincu) {
					boss.afficher(graphique2D);
				}
				
				i.afficher(graphique2D);
				i.afficherEntite(graphique2D);
			}

			if (sallec instanceof SallePrincesse) {
				princesse.afficher(graphique2D);
				boolean collision_princesse = Collision.collisionJoueur(joueur, princesse);
				if(collision_princesse ==true) {
					i.DessinerFenetreDialogue(graphique2D, "M.Nicobaharaye vous voila enfin ! Je ne vous remercierez jamais assez.");
				}
			}
			
			for (Ennemi e : ennemis) {
				e.afficher(graphique2D);
			}
			
			if (joueur.mouvement.inv) {
				i.afficherInventaire(graphique2D, joueur.inv);
			}
			
			//Passage de la salle classique a la salle classiquepassage
			if(collisionVerificateur.lancer_sallesecrete==true) {
				salle = new Importationsalle(new SalleClassiquepassage(this));
				sallec =salle.getSalle();

				if (collisionVerificateur.chgt_salle == true) {
					joueur.setY(20*33);
					joueur.miseAJour();
					collisionVerificateur.chgt_salle =false;
				}
				salle.getSalle().dessiner(graphique2D);
				joueur.afficher(graphique2D);
				i.afficher(graphique2D);
				if (joueur.mouvement.inv) {
					i.afficherInventaire(graphique2D, joueur.inv);
				}
				//for (Ennemi e : ennemis) {
					//e.afficher(graphique2D);
				//}
			}
			
			
			// Pour passer de la salle classiquepassage a la salle classique3
			if(collisionVerificateur.lancer_salle_classiquebis==true) {
				salle = new Importationsalle(new SalleClassique3(this));
				sallec =salle.getSalle();
				
				if (collisionVerificateur.chgt_salle_classique_bis == true) {
					joueur.setY(20*33);
					joueur.miseAJour();
					collisionVerificateur.chgt_salle_classique_bis =false;
				}
				salle.getSalle().dessiner(graphique2D);

				joueur.afficher(graphique2D);
				i.afficher(graphique2D);
				if (joueur.mouvement.inv) {
					i.afficherInventaire(graphique2D, joueur.inv);
				}
			}
				
			
			// Pour passer de la salle classique3 au magasin
			if(collisionVerificateur.lancer_salle_classique3 == true) {
				salle = new Importationsalle(new Magasin(this));

				
				if (collisionVerificateur.chgt_salle_classique_3 == true) {
					joueur.setX(14*33+15);
					joueur.setY(19*33);
					collisionVerificateur.chgt_salle_classique_3=false;
				}
				salle.getSalle().dessiner(graphique2D);

				joueur.afficher(graphique2D);
				//ennemi1.afficher(graphique2D);
				i.afficher(graphique2D);
				if (joueur.mouvement.inv) {
					i.afficherInventaire(graphique2D, joueur.inv);
				}
				
				//Affichage du druide du magasin
				druide.afficher(graphique2D);
				boolean collision_druide = Collision.collisionJoueur(joueur, druide);
				if(collision_druide ==true) {
					i.dessinerMagasin(graphique2D);
				}

			}
			

			//Pour passer du magasin a la salle classique3
			if(collisionVerificateur.lancer_magasin == true) {
				salle = new Importationsalle(new SalleClassique3(this));
				sallec =salle.getSalle();
				
				if (collisionVerificateur.chgt_salle_msalleclassique == true) {
					joueur.setX(14*33+15);
					joueur.setY(3*33);
					collisionVerificateur.chgt_salle_msalleclassique=false;
				}
				salle.getSalle().dessiner(graphique2D);

				joueur.afficher(graphique2D);
				//ennemi1.afficher(graphique2D);
				i.afficher(graphique2D);
				if (joueur.mouvement.inv) {
					i.afficherInventaire(graphique2D, joueur.inv);
				}
				
			}
			
			
			//Pour passer de la salle classique3 a la salle classiquepassage
			if(collisionVerificateur.lancer_salle_3salleclassique2 == true) {
				salle = new Importationsalle(new SalleClassiquepassage(this));
				sallec =salle.getSalle();
				
				if (collisionVerificateur.chgt_salle_3sallecclassique2 == true) {
					joueur.setX(14*33+15);
					joueur.setY(3*33);
					collisionVerificateur.chgt_salle_3sallecclassique2=false;
				}
				salle.getSalle().dessiner(graphique2D);

				joueur.afficher(graphique2D);
				//ennemi1.afficher(graphique2D);
				i.afficher(graphique2D);
				if (joueur.mouvement.inv) {
					i.afficherInventaire(graphique2D, joueur.inv);
				}
				
			}
			
			//Pour passer de la salle classiquepassage a la salle classique
			if(collisionVerificateur.lancer_sallebis_salleclassique == true) {
				salle = new Importationsalle(new SalleClassique(this));
				sallec =salle.getSalle();
				
				if (collisionVerificateur.chgt_sallebis_salleclassique == true) {
					joueur.setX(14*33+15);
					joueur.setY(3*33);
					collisionVerificateur.chgt_sallebis_salleclassique=false;
				}
				salle.getSalle().dessiner(graphique2D);

				joueur.afficher(graphique2D);
				//ennemi1.afficher(graphique2D);
				i.afficher(graphique2D);
				if (joueur.mouvement.inv) {
					i.afficherInventaire(graphique2D, joueur.inv);
				}
				
			}
			
			// Pour passer de la salle classique a la salle princesse
			if(collisionVerificateur.lancer_salleprincesse == true) {
				salle = new Importationsalle(new SallePrincesse(this));
				sallec =salle.getSalle();
				
				if (collisionVerificateur.chgt_salleprincesse == true) {
					joueur.setX(3*33+15);
					joueur.setY(9*33);
					collisionVerificateur.chgt_salleprincesse=false;
				}
				salle.getSalle().dessiner(graphique2D);

				joueur.afficher(graphique2D);
				i.afficher(graphique2D);
				princesse.afficher(graphique2D);
				boolean collision_princesse = Collision.collisionJoueur(joueur, princesse);
				if(collision_princesse ==true) {
					i.DessinerFenetreDialogue(graphique2D, "M.Nicobaharaye vous voila enfin ! Je ne vous remercierez jamais assez.");
				}
				if (joueur.mouvement.inv) {
					i.afficherInventaire(graphique2D, joueur.inv);
				}
			}
			
			//Pour passer de la salle princesse a la salle classique
			if(collisionVerificateur.retour_salleclassique==true) {
				
				salle = new Importationsalle(new SalleClassique(this));
				sallec =salle.getSalle();
				
				if (collisionVerificateur.chgt_salleprinc_salleclassique == true) {
					joueur.setX(27*33+15);
					joueur.setY(10*33);
					collisionVerificateur.chgt_salleprinc_salleclassique=false;
				}
				salle.getSalle().dessiner(graphique2D);

				joueur.afficher(graphique2D);

				i.afficher(graphique2D);
				
				if (joueur.mouvement.inv) {
					i.afficherInventaire(graphique2D, joueur.inv);
				}
			}
			
			//Pour passer de la salle classique a la salle du boss
				if(collisionVerificateur.lancer_salleboss==true) {
				 if (collisionVerificateur.nepeutopasentre == true) {
					 salle = new Importationsalle(new Salleboss(this));
					sallec =salle.getSalle();
				
				if (collisionVerificateur.chgt_salleboss == true) {
					joueur.setY(2*33);
					collisionVerificateur.chgt_salleboss=false;
				}
				salle.getSalle().dessiner(graphique2D);

				joueur.afficher(graphique2D);
				if (!bossVaincu) {
					boss.afficher(graphique2D);
				}
				
				i.afficher(graphique2D);
				i.afficherEntite(graphique2D);
				if (joueur.mouvement.inv) {
					i.afficherInventaire(graphique2D, joueur.inv);
				}
				 }
				
			}
			
			//Pour passer de la salle du boss a la salle classique
				if(collisionVerificateur.lancer_salleboss_classique==true) {
					
					salle = new Importationsalle(new SalleClassique(this));
					sallec =salle.getSalle();
					
					if (collisionVerificateur.chgt_salleboss_classique == true) {
						joueur.setY(19*33);
						collisionVerificateur.chgt_salleboss_classique=false;
					}
					salle.getSalle().dessiner(graphique2D);

					joueur.afficher(graphique2D);

					i.afficher(graphique2D);
					
					if (joueur.mouvement.inv) {
						i.afficherInventaire(graphique2D, joueur.inv);
					}
				}
				
				//Pour passer de la salle classique3 a la salle secrete
				if(collisionVerificateur.lancer_salleclas3_sallesecrete == true) {
					salle = new Importationsalle(new SalleSecrete(this));
					sallec =salle.getSalle();
					if (collisionVerificateur.chgt_salleclas3_sallesecrete == true) {
						joueur.setX(3*33);
						joueur.setY(10*33+15);
						collisionVerificateur.chgt_salleclas3_sallesecrete=false;
						i.viderEntite();
						ennemis.clear();
					}
					salle.getSalle().dessiner(graphique2D);
					joueur.afficher(graphique2D);
					i.afficher(graphique2D);
					i.afficherPiece(graphique2D);
					if (!recompense) {
						listeEntite[0] = new ObjetCollectable(this, 33*15 +11, 33*4, "cle_boss", 0, true);
						if (premiereEntre) {
							for (int k = 1; k < 15; k++) {
							listeEntite[k] = new ObjetCollectable(this, 33*15 - 100 + 30*k, 33*4 + 300, "piece", k, true);
							} 
							premiereEntre = false;
						}
						
						if (joueur.inv.estDansInv(listeObj.listeObjet[5][0])) {
							recompense = true;
						}
						
					}
					i.afficherEntite(graphique2D);
					if (joueur.mouvement.inv) {
						i.afficherInventaire(graphique2D, joueur.inv);
					}
				}
				
				//Pour passer de la salle secrete a la salle classique3
				if(collisionVerificateur.lancer_sallesecrete_salleclassique3 == true) {
					salle = new Importationsalle(new SalleClassique3(this));
					sallec =salle.getSalle();
					
					if (collisionVerificateur.chgt_sallesecrete_salleclassique3 == true) {
						joueur.setX(27*33+15);
						joueur.setY(10*33+15);
						collisionVerificateur.chgt_sallesecrete_salleclassique3=false;
					}
					salle.getSalle().dessiner(graphique2D);

					joueur.afficher(graphique2D);
					i.afficher(graphique2D);
					
					if (joueur.mouvement.inv) {
						i.afficherInventaire(graphique2D, joueur.inv);
					}
				}
			i.afficherPiece(graphique2D);
			graphique2D.dispose();
			
			}
		
		private class ActionQuitter implements ActionListener {
			public void actionPerformed(ActionEvent b) {
				System.exit(0);
			}
		}
		
		private class ActionReprendre implements ActionListener {
			private Jeu jeu; 

			public ActionReprendre(Jeu jeu) {
				this.jeu = jeu;
			}

			public void actionPerformed(ActionEvent b) {
				this.jeu.fenetrePause.setVisible(false);
				joueur.mouvement.save = false;
				this.jeu.debutThread();

				
			}
		}
		
		private class ActionSauvegarder implements ActionListener {
			public void actionPerformed(ActionEvent b) {
				gestionnaireSauvegarde.debutSauvegarde();
			}
		}

}
