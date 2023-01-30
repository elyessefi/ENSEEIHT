package Main;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.Color;

class MenuSauvegarde extends JFrame {

    JFrame menuSauvegarde = new JFrame("Menu Sauvegarde");
    LectureSauvegarde lecteur = new LectureSauvegarde(NewMenuPrincipal.jeu);

    public MenuSauvegarde() {
        ImageIcon icone = new ImageIcon("ressources/icone.png");
        menuSauvegarde.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuSauvegarde.setResizable(false);
        menuSauvegarde.setSize(600, 600);
        menuSauvegarde.setLocationRelativeTo(null);
        menuSauvegarde.setIconImage(icone.getImage());
        

        BorderLayout bl = new BorderLayout();
        bl.setHgap(80);
        bl.setVgap(80);

        JPanel panel = new JPanel();
        panel.setLayout(bl);
        panel.setBackground(Color.GRAY.darker());

        GridLayout gl = new GridLayout(3, 1);
        gl.setVgap(20); 

        JPanel souspanel = new JPanel();
        souspanel.setLayout(gl);
        souspanel.setBackground(Color.GRAY.darker());

        int nbSauvegarde = lecteur.getNbSauvegarde();

        JButton[] listeBouton = new JButton[10];

        JButton retour = new JButton("Retour");
        retour.addActionListener(new Retour());
        
        for (int j = 0; j < 3; j++) {
            int i = j + 1;
            listeBouton[j] = new JButton("Sauvegarde " + i);
            listeBouton[j].setBackground(new Color(242, 170, 76));
            listeBouton[j].setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        }

        listeBouton[0].addActionListener(new ChargerSave1());
        listeBouton[1].addActionListener(new ChargerSave2());
        listeBouton[2].addActionListener(new ChargerSave3());

        listeBouton[3] = new JButton("-");
        listeBouton[3].setBackground(new Color(242, 170, 76).darker());
        listeBouton[3].setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

        listeBouton[4] = new JButton("-");
        listeBouton[4].setBackground(new Color(242, 170, 76).darker());
        listeBouton[4].setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

        listeBouton[5] = new JButton("-");
        listeBouton[5].setBackground(new Color(242, 170, 76).darker());
        listeBouton[5].setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

        for (int k = 0; k < nbSauvegarde; k++) {
            souspanel.add(listeBouton[k]);
        }

        for (int k = 3; k < 6 - nbSauvegarde; k++) {
            souspanel.add(listeBouton[k]);
        }


        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();

        panel1.setBackground(Color.GRAY.darker());
        panel2.setBackground(Color.GRAY.darker());
        panel3.setBackground(Color.GRAY.darker());
        panel4.setBackground(Color.GRAY.darker());

        panel.add(souspanel, BorderLayout.CENTER);
        panel.add(panel1, BorderLayout.NORTH);
        panel.add(panel2, BorderLayout.EAST);
        panel.add(panel3, BorderLayout.WEST);
        panel.add(panel4, BorderLayout.SOUTH);
        panel.add(retour, BorderLayout.PAGE_END);

        menuSauvegarde.add(panel);

        menuSauvegarde.setVisible(true);
    }

     private class Retour implements ActionListener {
        public void actionPerformed(ActionEvent bouton) {
            menuSauvegarde.setVisible(false);
            NewMenuPrincipal menuPrincipal = new NewMenuPrincipal();
            try {
                menuPrincipal.render();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private class ChargerSave1 implements ActionListener {
       public void actionPerformed(ActionEvent bouton) {
           menuSauvegarde.setVisible(false);
           lecteur.chargerSauvegarde(1);
           NewMenuPrincipal.State = NewMenuPrincipal.STATE.INSTRUCTION;
       }
   }

   private class ChargerSave2 implements ActionListener {
       public void actionPerformed(ActionEvent bouton) {
        menuSauvegarde.setVisible(false);
        lecteur.chargerSauvegarde(2);
        NewMenuPrincipal.State = NewMenuPrincipal.STATE.INSTRUCTION;
       }
   }

   private class ChargerSave3 implements ActionListener {
       public void actionPerformed(ActionEvent bouton) {
        menuSauvegarde.setVisible(false);
        lecteur.chargerSauvegarde(3);
        NewMenuPrincipal.State = NewMenuPrincipal.STATE.INSTRUCTION;
       }
   }
}