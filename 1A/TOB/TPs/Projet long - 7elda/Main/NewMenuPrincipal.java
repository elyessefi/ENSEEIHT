package Main;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.Thread;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class NewMenuPrincipal {

    static int WIDTH = 500;
    static int HEIGHT = 800;

    public static JFrame fenetre = new JFrame();
    /** Etat du menu */
    public static enum STATE {MENU, JEU, AIDE, PASSER, INSTRUCTION, SAUVEGARDE};
    static STATE State = STATE.MENU;

    /** Sur quelle bouton se trouve le curseur */
    public enum BUTTON_ENTERED {NONE, JOUER, AIDE, QUITTER, PASSER, SAUVEGARDE};
    public static BUTTON_ENTERED buttonEntered = BUTTON_ENTERED.NONE;

    /** Les rectangles des Bouttons */
    public Rectangle playButton = new Rectangle(NewMenuPrincipal.WIDTH / 2 - 70, 350, 140, 50);
    public Rectangle helpButton = new Rectangle(NewMenuPrincipal.WIDTH / 2 - 70, 450, 140, 50);
    public Rectangle quitButton = new Rectangle(NewMenuPrincipal.WIDTH / 2 - 70, 550, 140, 50);
    public Rectangle SaveButton = new Rectangle(NewMenuPrincipal.WIDTH / 2 - 70, 650, 140, 50);
    public Rectangle boutonPasser = new Rectangle(NewMenuPrincipal.WIDTH / 2 - 70, 665, 130, 50);
    
    static ImageIcon icone = new ImageIcon("ressources/icone.png");
    static MouseInputMenu mouseListener = new MouseInputMenu();

    public static Jeu jeu = new Jeu();
    Graphics graph;
    private static JFrame frame = new JFrame("Menu 7elda");


    public void render() throws IOException, InterruptedException {

        frame.setLocation(20, 30);

        while (State == STATE.MENU) {
            Thread.sleep(10); // Limiter les FPS

            /** === Affichage === */
            frame.setIconImage(icone.getImage());
            frame.setContentPane(new JPanel() {
                BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/ressources/menu_principal.jpg"));

                public void paintComponent(Graphics g) {

                    g.drawImage(image, 0, 0, 500, 800, this);
                    Graphics2D g2d = (Graphics2D) g;

                    Font fnt1 = new Font("arial", Font.BOLD, 27); // Creation d'une nouvelle police d'ecriture pour le
                                                                  // menu
                    g.setFont(fnt1); // Appliquer la nouvelle police d'ecriture

                    g.setColor((buttonEntered == BUTTON_ENTERED.JOUER) ? Color.RED : Color.WHITE);
                    g.drawString("Jouer", NewMenuPrincipal.WIDTH / 2 - 35, 380);
                    g2d.draw(playButton);

                    g.setColor((buttonEntered == BUTTON_ENTERED.AIDE) ? Color.RED : Color.WHITE);
                    g.drawString("Controle", NewMenuPrincipal.WIDTH / 2 - 55, 480);
                    g2d.draw(helpButton);

                    g.setColor((buttonEntered == BUTTON_ENTERED.QUITTER) ? Color.RED : Color.WHITE);
                    g.drawString("Quitter", NewMenuPrincipal.WIDTH / 2 - 45, 580);
                    g2d.draw(quitButton);

                    g.setColor((buttonEntered == BUTTON_ENTERED.SAUVEGARDE) ? Color.RED : Color.WHITE);
                    g.drawString("Sauvegarde", NewMenuPrincipal.WIDTH / 2 - 75, 680);
                    g2d.draw(SaveButton);

                    g2d.dispose();
                    super.paintComponent(g);
                    this.addMouseListener(mouseListener);
                }
                
            });

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(NewMenuPrincipal.WIDTH, NewMenuPrincipal.HEIGHT);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            /** === Check curseur sur bouttons === */
            double mx = 0;
            double my = 0;
            if (frame.getMousePosition() != null) {
                mx = frame.getMousePosition().getX();
                my = frame.getMousePosition().getY();
            }

            NewMenuPrincipal.buttonEntered = NewMenuPrincipal.BUTTON_ENTERED.NONE;

            /** PlayButton */
            if (mx >= NewMenuPrincipal.WIDTH / 2 - 80 && mx <= NewMenuPrincipal.WIDTH / 2 + 80) {
                if (my >= 380 && my <= 440) {
                    NewMenuPrincipal.buttonEntered = NewMenuPrincipal.BUTTON_ENTERED.JOUER;
                }
            }

            /** HelpButton */
            if (mx >= NewMenuPrincipal.WIDTH / 2 - 80 && mx <= NewMenuPrincipal.WIDTH / 2 + 80) {
                if (my >= 480 && my <= 540) {
                    NewMenuPrincipal.buttonEntered = NewMenuPrincipal.BUTTON_ENTERED.AIDE;
                }
            }

            /** QuitButton */
            if (mx >= NewMenuPrincipal.WIDTH / 2 - 80 && mx <= NewMenuPrincipal.WIDTH / 2 + 80) {
                if (my >= 580 && my <= 640) {
                    NewMenuPrincipal.buttonEntered = NewMenuPrincipal.BUTTON_ENTERED.QUITTER;
                }
            }

            /** SaveButton */
            if (mx >= NewMenuPrincipal.WIDTH / 2 - 80 && mx <= NewMenuPrincipal.WIDTH / 2 + 80) {
                if (my >= 680 && my <= 740) {
                    NewMenuPrincipal.buttonEntered = NewMenuPrincipal.BUTTON_ENTERED.SAUVEGARDE;
                }
            }
        }

        /** On attend la fin de menu Sauvegarde */
        while (State == STATE.SAUVEGARDE) {
            Thread.sleep(100);
        }
        /** === Lancer les explications === */
       
            frame.setContentPane(new JPanel() {
                BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/ressources/menu_explication.png"));

                public void paintComponent(Graphics g) {
                    
                    g.drawImage(image, 0, 0, 500, 800, this);
                    Graphics2D g2d = (Graphics2D) g;

                    Font fnt1 = new Font("arial", Font.BOLD, 27); // Creation d'une nouvelle police d'ecriture pour le
                                                                  // menu
                    g.setFont(fnt1); // Appliquer la nouvelle police d'écriture

                    g.setColor((buttonEntered == BUTTON_ENTERED.PASSER) ? Color.RED : Color.WHITE);
                    g.drawString("Passer", NewMenuPrincipal.WIDTH / 2 - 45, 700);
                    g2d.draw(boutonPasser);
                   
                    g2d.dispose();
                    super.paintComponent(g);
                    this.addMouseListener(mouseListener);
                }
            });

            NewMenuPrincipal.buttonEntered = NewMenuPrincipal.BUTTON_ENTERED.NONE;
            
            frame.setSize(NewMenuPrincipal.WIDTH, NewMenuPrincipal.HEIGHT);
            frame.setVisible(true);
    }

    /** Action de Jouer */
    static public void ActionJouer() {
    	State = STATE.INSTRUCTION;
    }
    
    public static void ActionPasser() {
        /** === LANCEMENT DU JEU === */

        /* Fermeture du menu */
        frame.setVisible(false);
        frame.removeMouseListener(mouseListener);
        frame.setFocusable(false);
        frame.dispose();

        /* Lancement du jeu*/
        State = STATE.PASSER;
    	
    	// Ajout du jeu a la fenetre
        fenetre.pack();
		fenetre.setSize(1024, 780);
        fenetre.add(jeu);
        fenetre.setTitle("7elda");
        //Ajouter de la musique au jeu.
		 try {
			 java.net.URL soundFile = NewMenuPrincipal.class.getResource("/ressources/musique.wav");
	          AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile); 
	          AudioFormat format = audioIn.getFormat();   //Obtenir une ressource du type clip
	         DataLine.Info info = new DataLine.Info(Clip.class, format);
	         Clip clip = (Clip)AudioSystem.getLine(info);
	         clip.open(audioIn); //Ouvrir l'audio et charger le son
	         clip.start();
	         //Le fichier ne contient pas le bon audio
	      } catch (UnsupportedAudioFileException e1) {
              e1.printStackTrace(); //Afficher l'erreur grace a une methode de la classe Throwable
	      } catch (IOException e1) {
              e1.printStackTrace(); //Une ligne ne peut pas etre ouverte
	      } catch (LineUnavailableException e1) {
              e1.printStackTrace();
	      }
        // Lancement de la boucle du jeu
        jeu.debutThread();
        
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setLocationRelativeTo(null);
        fenetre.setIconImage(icone.getImage());
        fenetre.setVisible(true);
    
    }
    /** Action de Aide */
    static public void ActionHelp() {
    	JFrame fenetre = new JFrame();
    	JLabel texte =new JLabel("Si vous cherchez de l'aide pour trouver la salle secrete alors regardez bien les murs... peut etre qu'une salle se cache derriere");
    	fenetre.add(texte);
    	fenetre.setBackground(Color.WHITE);
    	fenetre.setVisible(true);
    	fenetre.pack();
    }

    /** Action de Quitter */
    static public void ActionQuitter() {
        System.exit(0);
    }

    public static void ActionSauvegarder() {
        State = STATE.SAUVEGARDE;
        new MenuSauvegarde();
    }
        
}