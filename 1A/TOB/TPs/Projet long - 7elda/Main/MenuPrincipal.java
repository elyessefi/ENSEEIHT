package Main;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import java.awt.Color;

import java.awt.Graphics;


public class MenuPrincipal{
    
	JPanel panel=new JPanel(new FlowLayout ( ) ); 
	ImageIcon icone = new ImageIcon("ressources/icone.png");
	Jeu jeu = new Jeu();
	Graphics graph;
	JFrame fenetre = new JFrame();
	public static JFrame fenetreJeu = new JFrame("7elda");;
	JButton boutonPasser = new JButton ( "Passer" ) ;
	BufferedImage img;
	JFrame menuSauvegarde = new JFrame("Menu Sauvegarde");
	LectureSauvegarde lecteur = new LectureSauvegarde(jeu);
	
	public static JFrame frame = new JFrame("7elda");
   
    private class ActionNouvellePartie implements ActionListener {
		 public void actionPerformed(ActionEvent bouton) {
			 	
			 
			 	frame.setVisible(false);
				fenetre.setResizable(false); // Pas la possibilité de déformer la fenêtre
				fenetre.setTitle("7elda"); // Titre de la fenêtre
				
				
				
			    boutonPasser.addActionListener(new ActionPasser());
			    //boutonPasser.setSize(50,50);
			    
				fenetre.setSize(300, 300);
				fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				fenetre.setLocationRelativeTo(null);  // Affichage de la fenêtre au centre de l'ecran
			    fenetre.setVisible(true);
			    try {
			    	
					fenetre.setContentPane(new JPanel() {
						BufferedImage img = ImageIO.read(getClass().getResourceAsStream("/ressources/menu_explication.png"));
					    public void paintComponent (Graphics g) {
					        super.paintComponent(g);
					        g.drawImage(img, 0, 0, 300, 300, this);
					        
					        
					    }
					});
					fenetre.add(boutonPasser);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			    
			    
			 } 
		 
	}
	private class ActionQuitter implements ActionListener {
		 public void actionPerformed(ActionEvent bouton) {
			 System.exit(0);
		 } 
		 
	}
	
	 private class ActionPasser implements ActionListener {
		 public void actionPerformed(ActionEvent bouton) {
			
			 fenetre.setVisible(false);
			 //Ajouter de la musique au jeu.
			 try {
				 java.net.URL soundFile = NewMenuPrincipal.class.getResource("/ressources/musique.wav");
		          AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile); 
		          AudioFormat format = audioIn.getFormat();  
		          //Obtenir une ressource du type clip
		         DataLine.Info info = new DataLine.Info(Clip.class, format);
		         Clip clip = (Clip)AudioSystem.getLine(info);
		         //Ouvrir l'audio et charger le son
		         clip.open(audioIn);
		         clip.start();
		         //Le fichier ne contient pas le bon audio
		      } catch (UnsupportedAudioFileException e1) {
		    	  //Afficher l'erreur grâce à une méthode de la classe Throwable
		    	  e1.printStackTrace();
		      } catch (IOException e1) {
		         e1.printStackTrace();
		         //Une ligne ne peut pas être ouverte
		      } catch (LineUnavailableException e1) {
		         e1.printStackTrace();
		      }
			
			
			 	// Ajout du jeu à la fenêtre
				Jeu jeu = new Jeu();
				fenetreJeu.add(jeu);
				fenetreJeu.pack();
				fenetreJeu.setSize(1024, 780);
				fenetreJeu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				fenetreJeu.setResizable(false);
				fenetreJeu.setLocationRelativeTo(null);
			    fenetreJeu.setVisible(true);
				// Lancement de la boucle du jeu
				jeu.debutThread();
		 }
		 }
		 private class menuSauvegarde implements ActionListener {
			public void actionPerformed(ActionEvent bouton) {
				
				frame.setVisible(false);
				menuSauvegarde.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				menuSauvegarde.setResizable(false);
				menuSauvegarde.setSize(600, 600);
				menuSauvegarde.setLocationRelativeTo(null);
				

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
		 }
		 private class Retour implements ActionListener {
			 public void actionPerformed(ActionEvent bouton) {
				 menuSauvegarde.setVisible(false);
				 frame.setVisible(true);

			 }
		 }

		 private class ChargerSave1 implements ActionListener {
			public void actionPerformed(ActionEvent bouton) {
				menuSauvegarde.setVisible(false);
				lecteur.chargerSauvegarde(1);
				fenetreJeu.add(jeu);
				fenetreJeu.pack();
				fenetreJeu.setSize(1024, 780);
				fenetreJeu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				fenetreJeu.setResizable(false);
				fenetreJeu.setLocationRelativeTo(null);
				fenetreJeu.setVisible(true);
				// Lancement de la boucle du jeu
				jeu.debutThread();
			}
		}
	
		private class ChargerSave2 implements ActionListener {
			public void actionPerformed(ActionEvent bouton) {
				menuSauvegarde.setVisible(false);
				lecteur.chargerSauvegarde(2);
				fenetreJeu.add(jeu);
				fenetreJeu.pack();
				fenetreJeu.setSize(1024, 780);
				fenetreJeu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				fenetreJeu.setResizable(false);
				fenetreJeu.setLocationRelativeTo(null);
				fenetreJeu.setVisible(true);
				// Lancement de la boucle du jeu
				jeu.debutThread();
			}
		}
	
		private class ChargerSave3 implements ActionListener {
			public void actionPerformed(ActionEvent bouton) {
				menuSauvegarde.setVisible(false);
				lecteur.chargerSauvegarde(3);
				fenetreJeu.add(jeu);
				fenetreJeu.pack();
				fenetreJeu.setSize(1024, 780);
				fenetreJeu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				fenetreJeu.setResizable(false);
				fenetreJeu.setLocationRelativeTo(null);
				fenetreJeu.setVisible(true);
				// Lancement de la boucle du jeu
				jeu.debutThread();
			}
		}
	public void Boutons() throws IOException {
		

	    frame.setContentPane(new JPanel() {
			BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/ressources/menu_principal.jpg"));
	        public void paintComponent (Graphics g) {
	            super.paintComponent(g);
	            g.drawImage(image, 0, 0, 300, 300, this);
	        }
	    });
		
		JButton bStart = new JButton ( "Start" ) ;
	    frame.add(bStart);
		JButton charger = new JButton("Charger");
		frame.add(charger);
	    JButton bQuitter = new JButton ( "Quitter" ) ;
	    frame.add(bQuitter);
		charger.addActionListener(new menuSauvegarde());
	    bStart.addActionListener(new ActionNouvellePartie());
	    bQuitter.addActionListener(new ActionQuitter());
		fenetre.setIconImage(icone.getImage());
		fenetreJeu.setIconImage(icone.getImage());
		frame.setIconImage(icone.getImage());
		menuSauvegarde.setIconImage(icone.getImage());
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setSize(300, 300);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
	    frame.setVisible(true);
	}
	
}
