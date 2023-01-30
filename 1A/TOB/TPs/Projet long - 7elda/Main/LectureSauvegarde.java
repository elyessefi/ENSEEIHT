package Main;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import Entite.Ennemi1;
import Entite.Joueur;
import Map.Magasin;
import Map.Salle2;
import Map.SalleClassique;
import Map.SalleClassique3;
import Map.SalleClassiquepassage;
import Map.SallePrincesse;
import Map.SalleSecrete;
import Map.Salleboss;


public class LectureSauvegarde {
    
    public Jeu jeu; // Le jeu en cours


    public LectureSauvegarde(Jeu jeu) {
        this.jeu = jeu;
    }

    /*
    * Charge une sauvegarde donnee
    */
    public void chargerSauvegarde(int numeroSauvegarde) {
        try {
            InputStream sauvegarde = getClass().getResourceAsStream("/Sauvegarde/Sauvegarde_" + numeroSauvegarde + ".txt");
            BufferedReader buffer = new BufferedReader(new InputStreamReader(sauvegarde));

            String donnees = buffer.readLine();
            String[] donneesSplit = donnees.split(" ");

            int compt = 0;
            int comptEnnemi = 0;
            int nbDonnee = donneesSplit.length;
            while (compt < nbDonnee - 1) {
                if (donneesSplit[compt].equals("Joueur")) {
                    this.chargerJoueur(donneesSplit);
                    compt += 5;
                } else if (donneesSplit[compt].equals("Inventaire")) {
                    compt += 2 * this.chargerInventaire(donneesSplit, compt) + 1;
                   
                } else if (donneesSplit[compt].equals("Ennemi")) {
                    this.chargerEnnemi(donneesSplit, compt, comptEnnemi);
                    compt += 4;
                    comptEnnemi ++;
                } else if (donneesSplit[compt].equals("Map")) {
                    this.chargerMap(donneesSplit, compt);
                   compt += 2;
                } else if (donneesSplit[compt].equals("false") || donneesSplit[compt].equals("true")) {
                   this.chargerBool(donneesSplit, compt - 1);
                   System.out.println("la");
                   compt += 28;
                }
                
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /*
    * Donne le nombre actuel de sauvegarde
    */
    public int getNbSauvegarde() {
        try {
            File repertoire = new File (System.getProperty("user.dir") + "\\Sauvegarde");
            return repertoire.list().length;
        } catch (NullPointerException e) {
            File repertoire = new File(System.getProperty("user.dir") + "/Sauvegarde");
            System.out.println("la");
            return repertoire.list().length;
        }
    }

    /*
    * Charge les donnees du joueur
    */
    public void chargerJoueur(String[] donneeSplit) {
        Joueur j = this.jeu.joueur;
        double pointDeVie = Double.parseDouble(donneeSplit[1]);
        int posX = Integer.parseInt(donneeSplit[2]);
        int posY = Integer.parseInt(donneeSplit[3]);
        int Argent = Integer.parseInt(donneeSplit[4]);

        j.vie.setVie(pointDeVie);
        j.setX(posX);
        j.setY(posY);
        j.comptPiece = Argent;
    }
    /*
    * Charge les donnees de l'inventaire du joueur
    */
    public int chargerInventaire(String[] donneeSplit, int debut) {
        int compt = debut + 1;
        int nbObjet = 0;
        while (!donneeSplit[compt].equals("Ennemi") && !donneeSplit[compt].equals("Map") ) {
            int ind = jeu.listeObj.getIndice(donneeSplit[compt]);
            compt ++;
            this.jeu.joueur.inv.ajouterObjet(jeu.listeObj.listeObjet[ind][0]);

            int quantite = Integer.parseInt(donneeSplit[compt]);
            this.jeu.joueur.inv.listeObjet[nbObjet].nb = quantite;
            nbObjet++;
            compt ++;
        }
        return nbObjet;
    }

    /*
    * Charge les donnees d'un ennemi
    */
    public void chargerEnnemi(String[] donneeSplit, int debut, int comptEnnemi) {
        jeu.ennemis.clear();

        int pdv = Integer.parseInt(donneeSplit[debut + 1]);
        int x = Integer.parseInt(donneeSplit[debut + 2]);
        int y = Integer.parseInt(donneeSplit[debut + 3]);

        jeu.ennemis.add(new Ennemi1(jeu, x, y, 1, pdv));

 
        
    }

    /*
    * Charge les donnees du jeu
    */
    public void chargerBool(String[] donneeSplit, int debut) {
        jeu.bossVaincu = Boolean.parseBoolean(donneeSplit[debut + 1]);
        jeu.recompense = Boolean.parseBoolean(donneeSplit[debut + 2]);
        jeu.premiereEntre = Boolean.parseBoolean(donneeSplit[debut + 3]);
        jeu.collision.chgt_salle = Boolean.parseBoolean(donneeSplit[debut + 4]); 
        jeu.collision.chgt_salle_3sallecclassique2 = Boolean.parseBoolean(donneeSplit[debut + 5]);
        jeu.collision.chgt_salle_classique_3 = Boolean.parseBoolean(donneeSplit[debut + 6]);
        jeu.collision.chgt_salle_classique_bis = Boolean.parseBoolean(donneeSplit[debut + 7]);
        jeu.collision.chgt_salle_msalleclassique = Boolean.parseBoolean(donneeSplit[debut + 8]);
        jeu.collision.chgt_sallebis_salleclassique = Boolean.parseBoolean(donneeSplit[debut + 9]);
        jeu.collision.chgt_salleboss = Boolean.parseBoolean(donneeSplit[debut + 10]);
        jeu.collision.chgt_salleboss_classique = Boolean.parseBoolean(donneeSplit[debut + 11]);
        jeu.collision.chgt_salleclas3_sallesecrete = Boolean.parseBoolean(donneeSplit[debut + 12]);
        jeu.collision.chgt_salleprinc_salleclassique = Boolean.parseBoolean(donneeSplit[debut + 13]);
        jeu.collision.chgt_salleprincesse = Boolean.parseBoolean(donneeSplit[debut + 14]);
        jeu.collision.chgt_sallesecrete_salleclassique3 = Boolean.parseBoolean(donneeSplit[debut + 15]);
        jeu.collision.lancer_magasin = Boolean.parseBoolean(donneeSplit[debut + 16]);
        jeu.collision.lancer_salle_3salleclassique2 = Boolean.parseBoolean(donneeSplit[debut + 17]);
        jeu.collision.lancer_salle_classique3 = Boolean.parseBoolean(donneeSplit[debut + 18]);
        jeu.collision.lancer_salle_classiquebis = Boolean.parseBoolean(donneeSplit[debut + 19]);
        jeu.collision.lancer_sallebis_salleclassique = Boolean.parseBoolean(donneeSplit[debut + 20]);
        jeu.collision.lancer_salleboss = Boolean.parseBoolean(donneeSplit[debut + 21]);
        jeu.collision.lancer_salleboss_classique = Boolean.parseBoolean(donneeSplit[debut + 22]);
        jeu.collision.lancer_salleclas3_sallesecrete = Boolean.parseBoolean(donneeSplit[debut + 23]);
        jeu.collision.lancer_salleprincesse = Boolean.parseBoolean(donneeSplit[debut + 24]);
        jeu.collision.lancer_sallesecrete = Boolean.parseBoolean(donneeSplit[debut + 25]);
        jeu.collision.lancer_sallesecrete_salleclassique3 = Boolean.parseBoolean(donneeSplit[debut + 26]);
        jeu.collision.nepeutopasentre = Boolean.parseBoolean(donneeSplit[debut + 27]);
        jeu.collision.retour_salleclassique = Boolean.parseBoolean(donneeSplit[debut + 28]);
    }

    /*
    * Charge les donnees de la map
    */
    public void chargerMap(String[] donneeSplit, int debut) {
        switch (donneeSplit[debut +1]) {
            case "SalleClassique" :
                jeu.salleSauvegarde = new SalleClassique(jeu);
            break;
            case "SalleClassique3" :
                jeu.salleSauvegarde = new SalleClassique3(jeu);
            break;
            case "Salleboss" :
                jeu.salleSauvegarde = new Salleboss(jeu);
            break;
            case "SallePrincesse" :
                jeu.salleSauvegarde = new SallePrincesse(jeu);
            break;
            case "SalleSecrete" :
                jeu.salleSauvegarde = new SalleSecrete(jeu);
            break;
            case "Salle2" :
            jeu.salleSauvegarde = new Salle2(jeu);
            break;
            case "SalleClassiquepassage" : 
            jeu.salleSauvegarde = new SalleClassiquepassage(jeu);
            break;
            case "Magasin" :
            jeu.salleSauvegarde = new Magasin(jeu);
            break;
        }
    }
}