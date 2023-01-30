package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import Main.Jeu.EtatJeu;


public class ControleClavier implements KeyListener{

	public boolean haut, bas, gauche, droite, save, fleche, afficherMap;
	public boolean inv = false;
	public boolean give = false;
	public boolean menu =false;
	Jeu jeu;
	
	/*
	 * Definir les controles au clavier
	 */
	public ControleClavier(Jeu jeu) {
		this.jeu = jeu;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	}
	
	/*
	 * Definir les actions associees à l'enfoncement d'une touche du clavier
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		
		int codeTouche = e.getKeyCode();
		
		
		
		if(codeTouche == KeyEvent.VK_Z) {
			haut = true;
		}
		
		if(codeTouche == KeyEvent.VK_Q) {
			gauche = true;
		}

		if(codeTouche == KeyEvent.VK_S) {
			bas = true;
		}

		if(codeTouche == KeyEvent.VK_D) {
			droite = true;
		}
		
		if(codeTouche == KeyEvent.VK_I) {
			if (inv == false) {
				inv = true;
				//jeu.etatJeu = EtatJeu.INVENTAIRE;
			} else {
				inv = false;
			}
		}
		
		if(codeTouche == KeyEvent.VK_C) {
			give = true;
		}
		
		if(codeTouche == KeyEvent.VK_UP) {
			
			jeu.i.clavier --;
			if(jeu.i.clavier >= 2) {
				jeu.i.clavier = 0;
			
		}
		}
		if(codeTouche == KeyEvent.VK_DOWN) {
			jeu.i.clavier ++;
			
			if(jeu.i.clavier <= 0) {
				jeu.i.clavier = 2;
			}
			
		}
		
		if(codeTouche == KeyEvent.VK_ENTER) {
			if(jeu.i.entrer == 0) {
				NewMenuPrincipal.fenetre.setVisible(false);
				NewMenuPrincipal menuprincipal = new NewMenuPrincipal();
				try {
					menuprincipal.render();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					
			}
			if(jeu.i.quitter == 0) {
				System.exit(0);
			}
			if(jeu.i.clavier == 0) {
				jeu.i.epee = true;
			}else if(jeu.i.clavier ==1) {
				jeu.i.coeur = true;
			}else if(jeu.i.clavier ==2) {
				jeu.i.bouclier = true;
			}
			
		}
		
		if(codeTouche == KeyEvent.VK_N) {
			save = true;
		}
		
		if(codeTouche == KeyEvent.VK_E) {
			fleche = true;
		}
		if(codeTouche == KeyEvent.VK_M) {
			afficherMap = true;
		}



		
	}
	/*
	 * Definir les actions associees au relachement d'une touche du clavier
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		int codeTouche = e.getKeyCode();
		
		if(codeTouche == KeyEvent.VK_Z) {
			haut = false;
		}
		
		if(codeTouche == KeyEvent.VK_Q) {
			gauche = false;
		}

		if(codeTouche == KeyEvent.VK_S) {
			bas = false;
		}

		if(codeTouche == KeyEvent.VK_D) {
			droite = false;
		}
		
		if(codeTouche == KeyEvent.VK_ENTER) {
			if(jeu.i.clavier == 0) {
				jeu.i.epee = false;
			}else if(jeu.i.clavier ==1) {
				jeu.i.coeur = false;
			}else if(jeu.i.clavier ==2) {
				jeu.i.bouclier = false;
			}
			
		}
		
		if(codeTouche == KeyEvent.VK_N) {
			save = false;
		}
		if(codeTouche == KeyEvent.VK_E) {
			fleche = false;
		}
		if(codeTouche == KeyEvent.VK_M) {
			afficherMap = false;
		}
	}
	
}	