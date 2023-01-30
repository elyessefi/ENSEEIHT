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

public class Magasin implements Salle {
	private Jeu jeu;
	public Map[] map;
	public int[][] coordonneeMap;//Creer une matrice qui sera les coordonnees sur la map
	
	public Magasin(Jeu jeu) {
		this.jeu = jeu;
		map = new Map[80]; //On cree 80 types differents de briques
		getTexture();
		coordonneeMap = new int [jeu.maxCol][jeu.maxLigne];
		creerMap();
	}

	public void getTexture() {
		try {
			
			map[0] = new Map();
			map[0].image = ImageIO.read(SalleClassique.class.getResource("/ressourcesmagasin/sol1.png"));
			
			map[1] = new Map();
			map[1].image = ImageIO.read(SalleClassique.class.getResource("/ressourcesmagasin/sol2.png"));
			
			map[2] = new Map();
			map[2].image = ImageIO.read(SalleClassique.class.getResource("/ressourcesmagasin/sol3.png"));
			
			map[3] = new Map();
			map[3].image = ImageIO.read(SalleClassique.class.getResource("/ressourcesmagasin/sol4.png"));
			
			map[4] = new Map();
			map[4].image = ImageIO.read(SalleClassique.class.getResource("/ressourcesmagasin/sol5.png"));
			
			map[5] = new Map();
			map[5].image = ImageIO.read(SalleClassique.class.getResource("/ressourcesmagasin/sol6.png"));
			
			map[6] = new Map();
			map[6].image = ImageIO.read(SalleClassique.class.getResource("/ressourcesmagasin/sol7.png"));
			
			map[7] = new Map();
			map[7].image = ImageIO.read(SalleClassique.class.getResource("/ressourcesmagasin/sol8.png"));
			
			map[8] = new Map();
			map[8].image = ImageIO.read(SalleClassique.class.getResource("/ressourcesmagasin/sol9.png"));
			
			map[9] = new Map();
			map[9].image = ImageIO.read(SalleClassique.class.getResource("/ressourcesmagasin/sol10.png"));
			
			map[10] = new Map();
			map[10].image = ImageIO.read(SalleClassique.class.getResource("/ressourcesmagasin/sol11.png"));
			
			map[11] = new Map();
			map[11].image = ImageIO.read(SalleClassique.class.getResource("/ressourcesmagasin/sol12.png"));
			
			map[12] = new Map();
			map[12].image = ImageIO.read(SalleClassique.class.getResource("/ressourcesmagasin/sol13.png"));
			
			map[13] = new Map();
			map[13].image = ImageIO.read(SalleClassique.class.getResource("/ressourcesmagasin/sol14.png"));
			
			map[14] = new Map();
			map[14].image = ImageIO.read(SalleClassique.class.getResource("/ressourcesmagasin/sol15.png"));
			
			map[15] = new Map();
			map[15].image = ImageIO.read(SalleClassique.class.getResource("/ressourcesmagasin/sol16.png"));
			
			map[16] = new Map();
			map[16].image = ImageIO.read(SalleClassique.class.getResource("/ressourcesmagasin/sol17.png"));
			
			map[17] = new Map();
			map[17].image = ImageIO.read(SalleClassique.class.getResource("/ressourcesmagasin/sol18.png"));
			
			map[18] = new Map();
			map[18].image = ImageIO.read(SalleClassique.class.getResource("/ressourcesmagasin/sol19.png"));
			
			map[19] = new Map();
			map[19].image = ImageIO.read(SalleClassique.class.getResource("/ressourcesmagasin/sol20.png"));
			
			map[20] = new Map();
			map[20].image = ImageIO.read(SalleClassique.class.getResource("/ressourcesmagasin/sol21.png"));
			
			map[21] = new Map();
			map[21].image = ImageIO.read(SalleClassique.class.getResource("/ressourcesmagasin/sol22.png"));
			
			map[22] = new Map();
			map[22].image = ImageIO.read(SalleClassique.class.getResource("/ressourcesmagasin/sol23.png"));
			
			map[23] = new Map();
			map[23].image = ImageIO.read(SalleClassique.class.getResource("/ressourcesmagasin/sol24.png"));
			
			map[24] = new Map();
			map[24].image = ImageIO.read(SalleClassique.class.getResource("/ressourcesmagasin/sol25.png"));
			
			map[25] = new Map();
			map[25].image = ImageIO.read(SalleClassique.class.getResource("/ressourcesmagasin/sol26.png"));
			
			map[26] = new Map();
			map[26].image = ImageIO.read(SalleClassique.class.getResource("/ressourcesmagasin/sol27.png"));
			
			map[27] = new Map();
			map[27].image = ImageIO.read(SalleClassique.class.getResource("/ressourcesmagasin/sol28.png"));
			
			map[28] = new Map();
			map[28].image = ImageIO.read(SalleClassique.class.getResource("/ressourcesmagasin/sol29.png"));
			
			map[29] = new Map();
			map[29].image = ImageIO.read(SalleClassique.class.getResource("/ressourcesmagasin/sol30.png"));
			
			map[30] = new Map();
			map[30].image = ImageIO.read(SalleClassique.class.getResource("/ressourcesmagasin/sol31.png"));
			
			map[31] = new Map();
			map[31].image = ImageIO.read(SalleClassique.class.getResource("/ressourcesmagasin/sol32.png"));
			
			map[32] = new Map();
			map[32].image = ImageIO.read(SalleClassique.class.getResource("/ressourcesmagasin/sol33.png"));
			
			map[33] = new Map();
			map[33].image = ImageIO.read(SalleClassique.class.getResource("/ressourcesmagasin/sol34.png"));
			
			map[34] = new Map();
			map[34].image = ImageIO.read(SalleClassique.class.getResource("/ressourcesmagasin/sol35.png"));
			map[34].collision_portec = true;
			
			map[35] = new Map();
			map[35].image = ImageIO.read(SalleClassique.class.getResource("/ressourcesmagasin/mur1.png"));
			
			map[36] = new Map();
			map[36].image = ImageIO.read(SalleClassique.class.getResource("/ressourcesmagasin/mur2.png"));
			
			map[37] = new Map();
			map[37].image = ImageIO.read(SalleClassique.class.getResource("/ressourcesmagasin/mur3.png"));
			
			map[38] = new Map();
			map[38].image = ImageIO.read(SalleClassique.class.getResource("/ressourcesmagasin/mur4.png"));
			
			map[39] = new Map();
			map[39].image = ImageIO.read(SalleClassique.class.getResource("/ressourcesmagasin/mur_droit_5.png"));
			
			map[40] = new Map();
			map[40].image = ImageIO.read(SalleClassique.class.getResource("/ressourcesmagasin/mur_droit_6.png"));
			
			map[41] = new Map();
			map[41].image = ImageIO.read(SalleClassique.class.getResource("/ressourcesmagasin/mur_droit_7.png"));
			
			map[42] = new Map();
			map[42].image = ImageIO.read(SalleClassique.class.getResource("/ressourcesmagasin/mur_droit_8.png"));
			
			map[43] = new Map();
			map[43].image = ImageIO.read(SalleClassique.class.getResource("/ressourcesmagasin/mur_gauche_1.png"));
			
			map[44] = new Map();
			map[44].image = ImageIO.read(SalleClassique.class.getResource("/ressourcesmagasin/mur_gauche_2.png"));
			
			map[45] = new Map();
			map[45].image = ImageIO.read(SalleClassique.class.getResource("/ressourcesmagasin/mur_gauche_3.png"));
			
			map[46] = new Map();
			map[46].image = ImageIO.read(SalleClassique.class.getResource("/ressourcesmagasin/mur_gauche_4.png"));
			
			map[47] = new Map();
			map[47].image = ImageIO.read(SalleClassique.class.getResource("/ressourcesmagasin/mur_gauche_5.png"));
			
			map[48] = new Map();
			map[48].image = ImageIO.read(SalleClassique.class.getResource("/ressourcesmagasin/mur_gauche_6.png"));
			
			map[49] = new Map();
			map[49].image = ImageIO.read(SalleClassique.class.getResource("/ressourcesmagasin/mur_gauche_7.png"));
			
			map[50] = new Map();
			map[50].image = ImageIO.read(SalleClassique.class.getResource("/ressourcesmagasin/mur_gauche_8.png"));
			
			map[51] = new Map();
			map[51].image = ImageIO.read(SalleClassique.class.getResource("/ressourcesmagasin/mur_haut_1.png"));
			
			map[52] = new Map();
			map[52].image = ImageIO.read(SalleClassique.class.getResource("/ressourcesmagasin/mur_haut_2.png"));
			
			map[53] = new Map();
			map[53].image = ImageIO.read(SalleClassique.class.getResource("/ressourcesmagasin/mur_haut_3.png"));
			
			map[54] = new Map();
			map[54].image = ImageIO.read(SalleClassique.class.getResource("/ressourcesmagasin/mur_haut_4.png"));
			
			map[55] = new Map();
			map[55].image = ImageIO.read(SalleClassique.class.getResource("/ressourcesmagasin/mur_haut_5.png"));
			
			map[56] = new Map();
			map[56].image = ImageIO.read(SalleClassique.class.getResource("/ressourcesmagasin/mur_haut_6.png"));
			
			map[57] = new Map();
			map[57].image = ImageIO.read(SalleClassique.class.getResource("/ressourcesmagasin/mur_haut_7.png"));
			
			map[58] = new Map();
			map[58].image = ImageIO.read(SalleClassique.class.getResource("/ressourcesmagasin/mur_haut_8.png"));
			
			map[59] = new Map();
			map[59].image = ImageIO.read(SalleClassique.class.getResource("/ressourcesmagasin/mur_bas_1.png"));
			
			map[60] = new Map();
			map[60].image = ImageIO.read(SalleClassique.class.getResource("/ressourcesmagasin/mur_bas_2.png"));
			
			map[61] = new Map();
			map[61].image = ImageIO.read(SalleClassique.class.getResource("/ressourcesmagasin/mur_bas_3.png"));
			
			map[62] = new Map();
			map[62].image = ImageIO.read(SalleClassique.class.getResource("/ressourcesmagasin/mur_bas_4.png"));
			
			map[63] = new Map();
			map[63].image = ImageIO.read(SalleClassique.class.getResource("/ressourcesmagasin/mur_bas_5.png"));
			
			map[64] = new Map();
			map[64].image = ImageIO.read(SalleClassique.class.getResource("/ressourcesmagasin/mur_bas_6.png"));
			
			map[65] = new Map();
			map[65].image = ImageIO.read(SalleClassique.class.getResource("/ressourcesmagasin/mur_bas_7.png"));
			
			map[66] = new Map();
			map[66].image = ImageIO.read(SalleClassique.class.getResource("/ressourcesmagasin/coin_1.png"));
			
			map[67] = new Map();
			map[67].image = ImageIO.read(SalleClassique.class.getResource("/ressourcesmagasin/coin_2.png"));
			
			map[68] = new Map();
			map[68].image = ImageIO.read(SalleClassique.class.getResource("/ressourcesmagasin/coin_3.png"));
			
			map[69] = new Map();
			map[69].image = ImageIO.read(SalleClassique.class.getResource("/ressourcesmagasin/coin_4.png"));
			
			map[70] = new Map();
			map[70].image = ImageIO.read(SalleClassique.class.getResource("/ressourcesmagasin/coin_5.png"));
			
			map[71] = new Map();
			map[71].image = ImageIO.read(SalleClassique.class.getResource("/ressourcesmagasin/coin_6.png"));
			
			map[72] = new Map();
			map[72].image = ImageIO.read(SalleClassique.class.getResource("/ressourcesmagasin/coin_7.png"));
			
			map[73] = new Map();
			map[73].image = ImageIO.read(SalleClassique.class.getResource("/ressourcesmagasin/coin_8.png"));
			
			map[74] = new Map();
			map[74].image = ImageIO.read(SalleClassique.class.getResource("/ressourcesmagasin/sol35.png"));
			
			map[75] = new Map();
			map[75].image = ImageIO.read(SalleClassique.class.getResource("/ressourcesmagasin/porte2.png"));
			
			map[76] = new Map();
			map[76].image = ImageIO.read(SalleClassique.class.getResource("/ressourcesmagasin/porte6.png"));
			
			map[77] = new Map();
			map[77].image = ImageIO.read(SalleClassique.class.getResource("/ressourcesmagasin/porte7.png"));
			
			map[78] = new Map();
			map[78].image = ImageIO.read(SalleClassique.class.getResource("/ressourcesmagasin/porte8.png"));
			
			for (int k = 35; k<73; k++) {
				map[k].collision = true;
			}
			

		}catch(IOException e) {
			System.out.print("Le fichier est inexistant");
		}
		
	}
	public void creerMap()  {
		//Permet de lire le fichier txt
		InputStream carte = getClass().getResourceAsStream("/ressourcesmagasin/carte3");
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