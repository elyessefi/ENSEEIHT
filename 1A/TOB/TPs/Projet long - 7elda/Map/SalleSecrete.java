
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

public class SalleSecrete implements Salle{
	
	private Jeu jeu;
	public Map[] map;
	public int[][] coordonneeMap;//Creer une matrice qui sera les coordonnees sur la map
	
	public SalleSecrete(Jeu jeu) {
		this.jeu = jeu;
		map = new Map[51]; //On cree 10 types differents de briques
		getTexture();
		coordonneeMap = new int [jeu.maxCol][jeu.maxLigne];
		creerMap();
	}
	
	@Override
	public void getTexture() {
		try {
			
			
			map[0] = new Map();
			map[0].image = ImageIO.read(SalleClassique.class.getResource("/ressources_sallesecrete/bloc_vert.png")); 
			map[0].collision = false; 

			map[3] = new Map();
			map[3].image = ImageIO.read(SalleClassique.class.getResource("/ressources_sallesecrete/sol_gauche.png")); 
			map[3].collision = false; 

			map[4] = new Map();
			map[4].image = ImageIO.read(SalleClassique.class.getResource("/ressources_sallesecrete/sol_droite.png")); 
			map[4].collision = false; 

			map[5] = new Map();
			map[5].image = ImageIO.read(SalleClassique.class.getResource("/ressources_sallesecrete/sol_dessin_bas_droite.png")); 
			map[5].collision = false; 

			map[6] = new Map();
			map[6].image = ImageIO.read(SalleClassique.class.getResource("/ressources_sallesecrete/sol_dessin_bas_gauche.png")); 
			map[6].collision = false; 

			map[7] = new Map();
			map[7].image = ImageIO.read(SalleClassique.class.getResource("/ressources_sallesecrete/sol_dessin_haut_gauche.png")); 
			map[7].collision = false; 

			map[8] = new Map();
			map[8].image = ImageIO.read(SalleClassique.class.getResource("/ressources_sallesecrete/sol_dessin_haut_droite.png")); 
			map[8].collision = false; 

			map[9] = new Map();
			map[9].image = ImageIO.read(SalleClassique.class.getResource("/ressources_sallesecrete/sol_dessin_haut_droite2.png")); 
			map[9].collision = false; 

			map[10] = new Map();
			map[10].image = ImageIO.read(SalleClassique.class.getResource("/ressources_sallesecrete/sol_dessin_haut_gauche_2.png")); 
			map[10].collision = false; 

			map[11] = new Map();
			map[11].image = ImageIO.read(SalleClassique.class.getResource("/ressources_sallesecrete/sol_dessin_haut_gauche_3.png")); 
			map[11].collision = false; 

			map[12] = new Map();
			map[12].image = ImageIO.read(SalleClassique.class.getResource("/ressources_sallesecrete/sol_dessin_haut_droite_3.png")); 
			map[12].collision = false; 

			map[13] = new Map();
			map[13].image = ImageIO.read(SalleClassique.class.getResource("/ressources_sallesecrete/sol2.png")); 
			map[13].collision = false; 

			map[14] = new Map();
			map[14].image = ImageIO.read(SalleClassique.class.getResource("/ressources_sallesecrete/sol3.png")); 
			map[14].collision = false; 

			map[15] = new Map();
			map[15].image = ImageIO.read(SalleClassique.class.getResource("/ressources_sallesecrete/sol4.png")); 
			map[15].collision = false; 

			map[16] = new Map();
			map[16].image = ImageIO.read(SalleClassique.class.getResource("/ressources_sallesecrete/sol_dessin_haut_gauche4.png")); 
			map[16].collision = false; 

			map[17] = new Map();
			map[17].image = ImageIO.read(SalleClassique.class.getResource("/ressources_sallesecrete/sol5.png")); 
			map[17].collision = false; 

			map[18] = new Map();
			map[18].image = ImageIO.read(SalleClassique.class.getResource("/ressources_sallesecrete/sol6.png")); 
			map[18].collision = false; 

			map[19] = new Map();
			map[19].image = ImageIO.read(SalleClassique.class.getResource("/ressources_sallesecrete/sol7.png")); 
			map[19].collision = false; 

			map[20] = new Map();
			map[20].image = ImageIO.read(SalleClassique.class.getResource("/ressources_sallesecrete/mur1_haut.png")); 
			map[20].collision = true; 

			map[21] = new Map();
			map[21].image = ImageIO.read(SalleClassique.class.getResource("/ressources_sallesecrete/mur2_haut.png")); 
			map[21].collision = true; 

			map[22] = new Map();
			map[22].image = ImageIO.read(SalleClassique.class.getResource("/ressources_sallesecrete/mur3_cotegauche.png")); 
			map[22].collision = true; 

			map[23] = new Map();
			map[23].image = ImageIO.read(SalleClassique.class.getResource("/ressources_sallesecrete/mur4_cotegauche.png")); 
			map[23].collision = true; 

			map[24] = new Map();
			map[24].image = ImageIO.read(SalleClassique.class.getResource("/ressources_sallesecrete/mur5_cotegauche.png")); 
			map[24].collision = true; 

			map[25] = new Map();
			map[25].image = ImageIO.read(SalleClassique.class.getResource("/ressources_sallesecrete/coindroit.png")); 
			map[25].collision = true; 

			map[26] = new Map();
			map[26].image = ImageIO.read(SalleClassique.class.getResource("/ressources_sallesecrete/mur1_droit.png")); 
			map[26].collision = true; 

			map[27] = new Map();
			map[27].image = ImageIO.read(SalleClassique.class.getResource("/ressources_sallesecrete/mur2_droit.png")); 
			map[27].collision = true; 

			map[28] = new Map();
			map[28].image = ImageIO.read(SalleClassique.class.getResource("/ressources_sallesecrete/mur1_bas.png")); 
			map[28].collision = true; 

			map[29] = new Map();
			map[29].image = ImageIO.read(SalleClassique.class.getResource("/ressources_sallesecrete/mur2_bas.png")); 
			map[29].collision = true; 

			map[30] = new Map();
			map[30].image = ImageIO.read(SalleClassique.class.getResource("/ressources_sallesecrete/sol_dessin_haut_droite4.png")); 
			map[30].collision = false; 

			map[31] = new Map();
			map[31].image = ImageIO.read(SalleClassique.class.getResource("/ressources_sallesecrete/sol_dessin_haut_gauche_4.png")); 
			map[31].collision = false; 

			map[32] = new Map();
			map[32].image = ImageIO.read(SalleClassique.class.getResource("/ressources_sallesecrete/sol_dessin_haut_droite_5.png")); 
			map[32].collision = false; 

			map[33] = new Map();
			map[33].image = ImageIO.read(SalleClassique.class.getResource("/ressources_sallesecrete/sol8.png")); 
			map[33].collision = false; 

			map[34] = new Map();
			map[34].image = ImageIO.read(SalleClassique.class.getResource("/ressources_sallesecrete/sol9.png")); 
			map[34].collision = false; 

			map[35] = new Map();
			map[35].image = ImageIO.read(SalleClassique.class.getResource("/ressources_sallesecrete/sol10.png")); 
			map[35].collision = false; 

			map[36] = new Map();
			map[36].image = ImageIO.read(SalleClassique.class.getResource("/ressources_sallesecrete/sol11.png")); 
			map[36].collision = false; 

			map[37] = new Map();
			map[37].image = ImageIO.read(SalleClassique.class.getResource("/ressources_sallesecrete/sol12.png")); 
			map[37].collision = false; 

			map[38] = new Map();
			map[38].image = ImageIO.read(SalleClassique.class.getResource("/ressources_sallesecrete/mur3_haut.png")); 
			map[38].collision = true; 

			map[39] = new Map();
			map[39].image = ImageIO.read(SalleClassique.class.getResource("/ressources_sallesecrete/mur3_bas.png")); 
			map[39].collision = true; 

			map[40] = new Map();
			map[40].image = ImageIO.read(SalleClassique.class.getResource("/ressources_sallesecrete/coinbasdroit.png")); 
			map[40].collision = true; 

			map[41] = new Map();
			map[41].image = ImageIO.read(SalleClassique.class.getResource("/ressources_sallesecrete/coinbasgauche.png")); 
			map[41].collision = true; 

			map[42] = new Map();
			map[42].image = ImageIO.read(SalleClassique.class.getResource("/ressources_sallesecrete/porte1.png")); 
			map[42].collision = false; 
			map[42].collision_porteb = true;

			
			map[43] = new Map();
			map[43].image = ImageIO.read(SalleClassique.class.getResource("/ressources_sallesecrete/porte2.png")); 
			map[43].collision = false; 
			map[43].collision_porteb = true;

			map[44] = new Map();
			map[44].image = ImageIO.read(SalleClassique.class.getResource("/ressources_sallesecrete/porte3.png")); 
			map[44].collision = false; 


			
			map[45] = new Map();
			map[45].image = ImageIO.read(SalleClassique.class.getResource("/ressources_sallesecrete/porte4.png")); 
			
			map[46] = new Map();
			map[46].image = ImageIO.read(SalleClassique.class.getResource("/ressources_sallesecrete/porte5.png")); 


			map[47] = new Map();
			map[47].image = ImageIO.read(SalleClassique.class.getResource("/ressources_sallesecrete/porte6.png"));
			
			map[48] = new Map();
			map[48].image = ImageIO.read(SalleClassique.class.getResource("/ressources_sallesecrete/sol_gauche2.png")); 
			map[48].collision = false; 

			map[49] = new Map();
			map[49].image = ImageIO.read(SalleClassique.class.getResource("/ressources_sallesecrete/sol_droite2.png")); 
			map[49].collision = false; 

			map[2] = new Map();
			map[2].image = ImageIO.read(SalleClassique.class.getResource("/ressources_sallesecrete/sol_droite3.png")); 
			map[2].collision = false; 

			map[1] = new Map();
			map[1].image = ImageIO.read(SalleClassique.class.getResource("/ressources_sallesecrete/sol_gauche3.png")); 
			map[1].collision = false; 

		
		}catch(IOException e) {
			System.out.print("Le fichier est inexistant");
		}
		
	
	}
	
	@Override
	
	public void creerMap()  {
		//Permet de lire le fichier txt
		InputStream carte = getClass().getResourceAsStream("/ressources_sallesecrete/salle2.txt");
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
	
	public Map[] getMap() {
		return this.map;
	}
	
	public int[][] getCoordonneeMap () {
		return this.coordonneeMap;
	}
}