package Main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInputMenu implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {

    }
	/*
	 * Definir les actions quand l'utilisateur clique avec la souris 
	 */
    @Override
    public void mousePressed(MouseEvent e) {

        int mx = e.getX();
        int my = e.getY();

        // PlayButton
        if (mx >= NewMenuPrincipal.WIDTH / 2 - 80 && mx <= NewMenuPrincipal.WIDTH / 2 + 80) {
            if (my >= 350 && my <= 400) {
                if (NewMenuPrincipal.State == NewMenuPrincipal.STATE.MENU) {
                    NewMenuPrincipal.ActionJouer();
                }
            }
        }

        // HelpButton
        if (mx >= NewMenuPrincipal.WIDTH / 2 - 80 && mx <= NewMenuPrincipal.WIDTH / 2 + 80) {
            if (my >= 450 && my <= 500) {
                if (NewMenuPrincipal.State == NewMenuPrincipal.STATE.MENU) {
                    NewMenuPrincipal.ActionHelp();
                }
            }
        }

        // QuitButton
        if (mx >= NewMenuPrincipal.WIDTH / 2 - 80 && mx <= NewMenuPrincipal.WIDTH / 2 + 80) {
            if (my >= 550 && my <= 600) {
                if (NewMenuPrincipal.State == NewMenuPrincipal.STATE.MENU) {
                    NewMenuPrincipal.ActionQuitter();
                }
            }
        }
        // SaveButton
        if (mx >= NewMenuPrincipal.WIDTH / 2 - 80 && mx <= NewMenuPrincipal.WIDTH / 2 + 80) {
            if (my >= 650 && my <= 700) {
                if (NewMenuPrincipal.State == NewMenuPrincipal.STATE.MENU) {
                    NewMenuPrincipal.ActionSauvegarder();
                }
            }
        }
     // Passer
        if (mx >= NewMenuPrincipal.WIDTH / 2 - 80 && mx <= NewMenuPrincipal.WIDTH / 2 + 80) {
            if (my >= 665 && my <= 700) {
                if (NewMenuPrincipal.State == NewMenuPrincipal.STATE.INSTRUCTION) {
                    NewMenuPrincipal.ActionPasser();
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

}
        
        
        