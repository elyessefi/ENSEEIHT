package Entite;

import java.io.IOException;

import javax.imageio.ImageIO;

public class ListeObjet {
	
	public Objet listeObjet[][];
	
	
	public ListeObjet() {
		this.listeObjet = new Objet[15][4];
		this.init();
	}
	
	public void init() {
		try {
			this.listeObjet[1][0] = new Objet();
			this.listeObjet[1][0].image = ImageIO.read(getClass().getResourceAsStream("/ressources_entite/epee.png"));
			this.listeObjet[1][0].nom = "epee";
			this.listeObjet[1][0].cummulable = false;
				
			this.listeObjet[0][0] = new Objet();
			this.listeObjet[0][0].image = ImageIO.read(getClass().getResourceAsStream("/ressources_entite/vide.png"));
			this.listeObjet[0][0].nom = "vide";
			this.listeObjet[0][0].cummulable = false;
			
			this.listeObjet[2][0] = new Objet();
			this.listeObjet[2][0].image = ImageIO.read(getClass().getResourceAsStream("/ressources_entite/potion.png"));
			this.listeObjet[2][0].nom = "potion";
			this.listeObjet[2][0].cummulable = true;
				
			this.listeObjet[3][0] = new Objet();
			this.listeObjet[3][0].image = ImageIO.read(getClass().getResourceAsStream("/ressources_entite/bouclier.png"));	
			this.listeObjet[3][0].cummulable = false;
			this.listeObjet[3][0].nom = "bouclier";
			
			this.listeObjet[4][0] = new Objet();
			this.listeObjet[4][0].image = ImageIO.read(getClass().getResourceAsStream("/ressources_entite/piece1.png"));
			this.listeObjet[4][0].cummulable = true;
			this.listeObjet[4][0].nom = "piece";
			
			this.listeObjet[4][1] = new Objet();
			this.listeObjet[4][1].image = ImageIO.read(getClass().getResourceAsStream("/ressources_entite/piece2.png"));
			
			this.listeObjet[4][2] = new Objet();
			this.listeObjet[4][2].image = ImageIO.read(getClass().getResourceAsStream("/ressources_entite/piece3.png"));
			
			//this.listeObjet[4][3] = new Objet();
			//this.listeObjet[4][3].image = ImageIO.read(getClass().getResourceAsStream("/ressources_entite/piece4.png"));
			
			this.listeObjet[5][0] = new Objet();
			this.listeObjet[5][0].image = ImageIO.read(getClass().getResourceAsStream("/ressources_entite/cle_boss.png"));
			this.listeObjet[5][0].cummulable = false;
			this.listeObjet[5][0].nom = "cle_boss";

			this.listeObjet[5][1] = new Objet();
			this.listeObjet[5][1].image = ImageIO.read(getClass().getResourceAsStream("/ressources_entite/cle_boss1.png"));
			
			this.listeObjet[5][2] = new Objet();
			this.listeObjet[5][2].image = ImageIO.read(getClass().getResourceAsStream("/ressources_entite/cle_boss2.png"));

			this.listeObjet[6][0] = new Objet();
			this.listeObjet[6][0].image = ImageIO.read(getClass().getResourceAsStream("/ressources_entite/cle_princesse.png"));
			this.listeObjet[6][0].cummulable = false;
			this.listeObjet[6][0].nom = "cle_princesse";

			this.listeObjet[6][1] = new Objet();
			this.listeObjet[6][1].image = ImageIO.read(getClass().getResourceAsStream("/ressources_entite/cle_princesse1.png"));
			
			this.listeObjet[6][2] = new Objet();
			this.listeObjet[6][2].image = ImageIO.read(getClass().getResourceAsStream("/ressources_entite/cle_princesse2.png"));
				
		} catch (IOException e) {
				e.printStackTrace();
		}
		
	}
	
	public int getIndice(String nom) {
		int ind = 0;
		for (int k = 0; k < 7; k++) {
			if (this.listeObjet[k][0].nom.equals(nom)) {
				ind = k;
			}
		}
		return ind;
	}

}