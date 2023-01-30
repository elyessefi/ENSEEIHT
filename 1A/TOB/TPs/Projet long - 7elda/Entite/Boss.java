package Entite;

import Main.*;

import java.util.Random;

import javax.imageio.ImageIO;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class Boss extends Ennemi {
	
	private Boolean vivant;
	private int x;
	private int y;
	private int pdv;
	private int update;
	private Random r;
	private int attaque;
	private Direction direction;
	private Jeu jeu;
	private BufferedImage image;
	private Fleche tabFleche[];
    public int nbrMaxFleche = 100;
    private int nbrCourantFleche;
    private Boolean att1, att2;
	
	public Boss(Jeu jeu) {
		vivant = true;
		x = 600;
		y = 250;
		pdv = 50;
		r = new Random();
		update = 0;
		att1 = false;
		att2 = false;
		this.jeu = jeu;
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/ressources_entite/boss_Avant_static.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.tabFleche = new Fleche[nbrMaxFleche];
		for (int i=0; i< nbrMaxFleche; i++) {
            tabFleche[i] = null;
        }
	}

	@Override
	public boolean estPresent() {
		return vivant;
	}

	@Override
	public void afficher(Graphics2D g) {
		if (this.vivant) {
			g.drawImage(image, x-100, y-150, jeu.tailleCaseReelle*4, jeu.tailleCaseReelle*4, null);
	        for (int i=0; i< nbrMaxFleche; i++) {
	            if (tabFleche[i] != null) {
	                tabFleche[i].afficher(g);
	            }
	        }
		}
	}

	@Override
	public void miseAJour() {
	if (vivant) {
		if (this.estPresent()) {
			if (update >= 600) {
				att1 = false;
				att2 = false;
				update = 0;
				attaque = getRandomNumberInRange(1,4);
				System.out.println(attaque);
				switch(attaque) {
					case 1 :
						att1 = true;
					case 2 :
						att2 = true;
					case 3 :
						attaque2();
				}
			}
			else if ((update > 300)&&att1) {
				att1 = false;
			}
			else if ((update > 100) && att2) {
				att2 = false;
			}
			else if ((update % 50 == 0) && att1) {
				attaque1();
			}
			else if ((update % 5 == 0) && att2) {
				attaque1();
			}
			update += 1;
			for (int i=0; i< nbrMaxFleche; i++) {
	            if (tabFleche[i] != null) {
	                // Si après mise a jour la fleche touche un obstacle
	                tabFleche[i].miseAJour();
	                if (!tabFleche[i].estPresent()) {
	                    tabFleche[i] = null;
	                    nbrCourantFleche--;
	                }
	            }
	        }
		}
	}
	}
	
	private int getRandomNumberInRange(int min, int max) {
		return r.nextInt(max - min) + min;
	}
	
	private void attaque1() {
		final Direction[] t = {Direction.GAUCHE, Direction.HAUT, Direction.DROITE, Direction.BAS};
		for (int i = 0; i < 4; i++) {
			direction = t[i];
			int k = 0;
			while (tabFleche[k] != null) {
		        k++;
		    }
			tabFleche[k] = new Fleche(this, jeu, true);
		}
	}
	
	private void attaque2() {
		jeu.ajouterEnnemi1(x + 200, y);
		jeu.ajouterEnnemi1(x - 200, y);
	}
	
	
	public void tuer() {
		this.vivant = false;
		jeu.bossVaincu = true;
		jeu.listeEntite[0] = new ObjetCollectable(jeu, this.getX(), this.getY() + 20, "cle_princesse", 1, true);
		System.out.println(jeu.listeEntite[0].ind);
	}

	@Override
	public Direction getDirection() {
		return direction;
	}

	@Override
	public int getVitesse() {
		return 0;
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}
	
	@Override
	public void retirerDegats(int deg) {
		this.pdv -= deg;
		if (pdv <= 0) {
			tuer();
		}
	}
	
	public int getType() {
		return 2;
	}
	

}