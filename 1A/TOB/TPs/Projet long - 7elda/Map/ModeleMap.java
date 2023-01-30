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

public class ModeleMap implements Salle{
	private Jeu jeu;
	public Map[] map;
	public int[][] coordonneeMap;//Creer une matrice qui sera les coordonnees sur la map
	
	public ModeleMap(Jeu jeu) {
		this.jeu = jeu;
		map = new Map[50]; //On cree 50 types differents de briques
		getTexture();
		coordonneeMap = new int [jeu.maxCol][jeu.maxLigne];
		creerMap();
	}

	public void getTexture() {
		try {
			map[0] = new Map();
			map[0].image = ImageIO.read(ModeleMap.class.getResource("/ressources/sol(1).png")); 
			
			map[1] = new Map();
			map[1].image = ImageIO.read(ModeleMap.class.getResource("/ressources/mur_haut_1.png")); 
			map[1].collision = true;
			
			map[2] = new Map();
			map[2].image = ImageIO.read(ModeleMap.class.getResource("/ressources/mur_haut_2.png")); 
			map[2].collision = true;
			
			map[3] = new Map();
			map[3].image = ImageIO.read(ModeleMap.class.getResource("/ressources/mur_haut_3.png")); 
			map[3].collision = true;
			
			map[4] = new Map();
			map[4].image = ImageIO.read(ModeleMap.class.getResource("/ressources/mur_haut_4.png")); 
			map[4].collision = true;
			
			map[5] = new Map();
			map[5].image = ImageIO.read(ModeleMap.class.getResource("/ressources/eclairagebis_haut.png")); 
			
			map[6] = new Map();
			map[6].image = ImageIO.read(ModeleMap.class.getResource("/ressources/eclairage.png")); 
			
			map[7] = new Map();
			map[7].image = ImageIO.read(ModeleMap.class.getResource("/ressources/eclairagebis.png")); 
			
			map[8] = new Map();
			map[8].image = ImageIO.read(ModeleMap.class.getResource("/ressources/mur_bas_1.png")); 
			map[8].collision = true;
			
			map[9] = new Map();
			map[9].image = ImageIO.read(ModeleMap.class.getResource("/ressources/mur_bas_2.png")); 
			
			map[10] = new Map();
			map[10].image = ImageIO.read(ModeleMap.class.getResource("/ressources/porte_bas.png")); 
			map[10].collision_porte = true;
			
			map[11] = new Map();
			map[11].image = ImageIO.read(ModeleMap.class.getResource("/ressources/obstacle.png")); 
			map[11].collision = true;
			
			map[12] = new Map();
			map[12].image = ImageIO.read(ModeleMap.class.getResource("/ressources/portecube11.png")); 

			map[13] = new Map();
			map[13].image = ImageIO.read(ModeleMap.class.getResource("/ressources/portecube12.png")); 
			
			map[14] = new Map();
			map[14].image = ImageIO.read(ModeleMap.class.getResource("/ressources/mur_bas_3.png")); 
			map[14].collision = true;
			
			map[15] = new Map();
			map[15].image = ImageIO.read(ModeleMap.class.getResource("/ressources/mur_bas_4.png")); 
			map[15].collision = true;
			
			map[16] = new Map();
			map[16].image = ImageIO.read(ModeleMap.class.getResource("/ressources/mur_bas_5.png"));
			map[16].collision = true; 
			
			map[17] = new Map();
			map[17].image = ImageIO.read(ModeleMap.class.getResource("/ressources/mur_bas_6.png")); 
			map[17].collision = true;
			
			map[18] = new Map();
			map[18].image = ImageIO.read(ModeleMap.class.getResource("/ressources/mur_cotegauche_1.png")); 
			
			map[19] = new Map();
			map[19].image = ImageIO.read(ModeleMap.class.getResource("/ressources/mur_cotegauche_2.png")); 
			
			map[20] = new Map();
			map[20].image = ImageIO.read(ModeleMap.class.getResource("/ressources/mur_cotegauche_3.png")); 
			
			map[21] = new Map();
			map[21].image = ImageIO.read(ModeleMap.class.getResource("/ressources/mur_cotegauche_4.png")); 
			
			map[22] = new Map();
			map[22].image = ImageIO.read(ModeleMap.class.getResource("/ressources/mur_cotegauche_5.png")); 
			
			map[23] = new Map();
			map[23].image = ImageIO.read(ModeleMap.class.getResource("/ressources/mur_cotegauche_6.png")); 
			
			map[24] = new Map();
			map[24].image = ImageIO.read(ModeleMap.class.getResource("/ressources/mur_cotegauche_7.png")); 
			
			map[25] = new Map();
			map[25].image = ImageIO.read(ModeleMap.class.getResource("/ressources/mur_de_base.png")); 
			
			map[26] = new Map();
			map[26].image = ImageIO.read(ModeleMap.class.getResource("/ressources/mur_cotedroit_1.png")); 
			
			map[27] = new Map();
			map[27].image = ImageIO.read(ModeleMap.class.getResource("/ressources/mur_cotedroit_2.png")); 
			
			map[28] = new Map();
			map[28].image = ImageIO.read(ModeleMap.class.getResource("/ressources/mur_cotedroit_3.png")); 
			
			map[29] = new Map();
			map[29].image = ImageIO.read(ModeleMap.class.getResource("/ressources/mur_cotedroit_4.png")); 
			
			map[30] = new Map();
			map[30].image = ImageIO.read(ModeleMap.class.getResource("/ressources/mur_cotedroit_5.png")); 
			
			map[31] = new Map();
			map[31].image = ImageIO.read(ModeleMap.class.getResource("/ressources/mur_cotedroit_6.png")); 
			
			map[32] = new Map();
			map[32].image = ImageIO.read(ModeleMap.class.getResource("/ressources/mur_cotedroit_7.png")); 
			
			map[33] = new Map();
			map[33].image = ImageIO.read(ModeleMap.class.getResource("/ressources/mur_haut_5.png")); 
			map[33].collision = true;
			
			map[34] = new Map();
			map[34].image = ImageIO.read(ModeleMap.class.getResource("/ressources/mur_haut_6.png")); 
			map[34].collision = true;
			
			map[35] = new Map();
			map[35].image = ImageIO.read(ModeleMap.class.getResource("/ressources/mur_haut_7.png"));
			map[35].collision = true;
			
			map[36] = new Map();
			map[36].image = ImageIO.read(ModeleMap.class.getResource("/ressources/mur_haut_8.png"));
			map[36].collision = true;
					
			map[37] = new Map();
			map[37].image = ImageIO.read(ModeleMap.class.getResource("/ressources/mur_cotedroit_8.png"));
			
			map[38] = new Map();
			map[38].image = ImageIO.read(ModeleMap.class.getResource("/ressources/mur_cotegauche_8.png"));
			
			map[39] = new Map();
			map[39].image = ImageIO.read(ModeleMap.class.getResource("/ressources/coin_bas_droit_1.png"));
			
			map[40] = new Map();
			map[40].image = ImageIO.read(ModeleMap.class.getResource("/ressources/coin_bas_droit_2.png"));
			
			map[41] = new Map();
			map[41].image = ImageIO.read(ModeleMap.class.getResource("/ressources/coin_bas_gauche_1.png"));
			
			map[42] = new Map();
			map[42].image = ImageIO.read(ModeleMap.class.getResource("/ressources/coin_bas_gauche_2.png"));
			
			map[43] = new Map();
			map[43].image = ImageIO.read(ModeleMap.class.getResource("/ressources/coin_haut_droit_1.png"));
			
			map[44] = new Map();
			map[44].image = ImageIO.read(ModeleMap.class.getResource("/ressources/coin_haut_droit_2.png"));
			
			map[45] = new Map();
			map[45].image = ImageIO.read(ModeleMap.class.getResource("/ressources/coin_haut_gauche_1.png"));
			
			map[46] = new Map();
			map[46].image = ImageIO.read(ModeleMap.class.getResource("/ressources/coin_haut_gauche_2.png"));
			
			
			for (int k = 1; k<47; k++) {
				map[k].collision = true;
				map[k].collision_porteb = false;
				map[k].collision_portec = false;
				map[k].collision_ported = false;
			}
			
			map[12].collision = false; 
			map[13].collision = false; 
		}catch(IOException e) {
			System.out.print("Le fichier est inexistant");
		}
		
	}
	public void creerMap()  {
		//Permet de lire le fichier txt
		InputStream carte = getClass().getResourceAsStream("/ressources/carte.txt");
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
	
	public Map[] getMap() {
		return this.map;
	}
	
	public int[][] getCoordonneeMap () {
		return this.coordonneeMap;
	}
	

	
	}