package Map;

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Main.Jeu;

public class SallePrincesse implements Salle{
	private Jeu jeu;
	public Map[] map;
	public int[][] coordonneeMap;//Creer une matrice qui sera les coordonnees sur la map
	
	
	public SallePrincesse(Jeu jeu) {
		this.jeu = jeu;
		map = new Map[15]; //On cree 15 types differents de briques
		getTexture();
		coordonneeMap = new int [jeu.maxCol][jeu.maxLigne];
		creerMap();
	}
	@Override
	public void getTexture() {
		try {
			map[0] = new Map();
			map[0].image = ImageIO.read(SalleClassique.class.getResource("/ressources_salleprincesse/solsallePrincesse.png")); 
			
			map[1] = new Map();
			map[1].image = ImageIO.read(SalleClassique.class.getResource("/ressources_salleprincesse/bloc centre.png")); 
			map[1].collision = true;
			
			map[2] = new Map();
			map[2].image = ImageIO.read(SalleClassique.class.getResource("/ressources_salleprincesse/coinhautgauchesallePrincesse.png")); 
			map[2].collision = true;
			
			map[3] = new Map();
			map[3].image = ImageIO.read(SalleClassique.class.getResource("/ressources_salleprincesse/facebascentrale.png")); 
			map[3].collision = true;
			
			map[4] = new Map();
			map[4].image = ImageIO.read(SalleClassique.class.getResource("/ressources_salleprincesse/road.png")); 
			
			map[5] = new Map();
			map[5].image = ImageIO.read(SalleClassique.class.getResource("/ressources_salleprincesse/facegauchesallePrincesse.png")); 
			map[5].collision = true;
			
			map[6] = new Map();
			map[6].image = ImageIO.read(SalleClassique.class.getResource("/ressources_salleprincesse/facedroitecentrale.png")); 
			map[6].collision = true;
			
			map[7] = new Map();
			map[7].image = ImageIO.read(SalleClassique.class.getResource("/ressources_salleprincesse/fenetre.png")); 
			map[7].collision = true;
			map[8] = new Map();
			map[8].image = ImageIO.read(SalleClassique.class.getResource("/ressources_salleprincesse/coinbasgauchesallePrincesse.png")); 
			map[8].collision = true;
			
			map[9] = new Map();
			map[9].image = ImageIO.read(SalleClassique.class.getResource("/ressources_salleprincesse/coinbasdroitesallePrincesse.png")); 
			map[9].collision = true;
			
			map[10] = new Map();
			map[10].image = ImageIO.read(SalleClassique.class.getResource("/ressources_salleprincesse/coinhautdroitesallePrincesse.png")); 
			map[10].collision = true;
			
			map[11] = new Map();
			map[11].image = ImageIO.read(SalleClassique.class.getResource("/ressources_salleprincesse/porte.png")); 
			map[11].collision_porte_princesse=true;
			
		}catch(IOException e) {
			System.out.print("Le fichier est inexistant");
		}
		
	}
	
	@Override
	public void creerMap()  {
		//Permet de lire le fichier txt
		InputStream carte = getClass().getResourceAsStream("/ressources_salleprincesse/carte_princesse.txt");
		BufferedReader map = new BufferedReader(new InputStreamReader(carte));
		int colonne = 0;
		int ligne = 0 ;
		try {
		//On parcourt tout le fichier txt
		while (colonne < jeu.maxCol && ligne < jeu.maxLigne) {
			//On lit la ligne 
			
				String ligne_map = map.readLine();
			
			
			//Permet de parcourir toute la ligne
			while(colonne<jeu.maxCol) {
				String[] words = ligne_map.split(" ");
				int valeur_case = Integer.parseInt(words[colonne]);
				//On initialise notr matrice avec la valeur du fichier txt
				coordonneeMap[colonne][ligne] = valeur_case;
				colonne ++;
			}
			//Quand on a finit de traiter une ligne on passe a la suivante
			if(colonne == jeu.maxCol) {
				colonne =0;
				ligne++;
			}
			
		}
		map.close();
		}catch(IOException e){
			System.out.println("Erreur sur le fichier");
		}
	}
	
	@Override
	public void dessiner(Graphics graphique) {
		
		int cube_pos_x =0;
		int cube_pos_y = 0;
		int longueur = 0;
		int largeur = 0 ;
		
		while (longueur < jeu.maxCol && largeur < jeu.maxLigne) {
			int dessiner_map = coordonneeMap[longueur][largeur];
			graphique.drawImage(map[dessiner_map].image, cube_pos_x, cube_pos_y, jeu.tailleCaseReelle, jeu.tailleCaseReelle, null); // Dessiner l'image
			longueur += 1;
			cube_pos_x += jeu.tailleCaseReelle;
			
			if (longueur == jeu.maxCol) {
				longueur = 0;
				cube_pos_x = 0;
				cube_pos_y += jeu.tailleCaseReelle; 
				largeur += 1;	
			}	
		}
	}

	@Override
	public Map[] getMap() {
		return this.map;
	}

	@Override
	public int[][] getCoordonneeMap() {
		return this.coordonneeMap;
	}
}