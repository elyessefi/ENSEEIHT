package Main;
import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors
import Entite.Ennemi1;
import Entite.Joueur;
import Entite.Objet;
import Map.Magasin;
import Map.Salle;
import Map.Salle2;
import Map.SalleClassique;
import Map.SalleClassique3;
import Map.SalleClassiquepassage;
import Map.SallePrincesse;
import Map.SalleSecrete;
import Map.Salleboss;

import java.io.FileWriter;

public class CreationSauvegarde {

    public Jeu jeu;
    public final static int nbSauvegardeMax = 3;

    public CreationSauvegarde (Jeu jeu) {
        this.jeu = jeu;
    }
   

    public void debutSauvegarde() {
        try {
            int compt = 1;
            File repertoire = new File (System.getProperty("user.dir") + "\\Sauvegarde");
            if (repertoire.list().length < 3) {
                String chemin = System.getProperty("user.dir") + "\\Sauvegarde\\Sauvegarde_";
                String sauvegardeCourante = String.valueOf(compt) + ".txt";
                String s = chemin.concat(sauvegardeCourante);
                boolean fin = false;
                while (!fin) {
                    File file = new File(s);
                    if (file.createNewFile()) {
                        fin = true; 
                        System.out.println("File created: " + file.getName());
                        FileWriter fic = new FileWriter(s);
                        this.ecrireSauvegarde(fic);
                        fic.close();

                    } else {
                        compt++;
                        sauvegardeCourante = String.valueOf(compt) + ".txt";
                        s = chemin.concat(sauvegardeCourante);
                        System.out.println("File already exists.");
                    }
                }
            } else {
                throw new MaxSauvegardeException();
            }
           
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (MaxSauvegardeException e) {
            System.out.println("Max de sauvegarde atteint !");
        } catch (NullPointerException e) {
            this.debutSauvegardeAlternatif();
        }
    }

    public void debutSauvegardeAlternatif() {
            try {
                int compt = 1;
                File repertoire = new File (System.getProperty("user.dir") + "/Sauvegarde");
                if (repertoire.list().length < 3) {
                    String chemin = System.getProperty("user.dir") + "/Sauvegarde/Sauvegarde_";
                    String sauvegardeCourante = String.valueOf(compt) + ".txt";
                    String s = chemin.concat(sauvegardeCourante);
                    boolean fin = false;
                    while (!fin) {
                        File file = new File(s);
                        if (file.createNewFile()) {
                            fin = true; 
                            System.out.println("File created: " + file.getName());
                            FileWriter fic = new FileWriter(s);
                            this.ecrireSauvegarde(fic);
                            fic.close();
    
                        } else {
                            compt++;
                            sauvegardeCourante = String.valueOf(compt) + ".txt";
                            s = chemin.concat(sauvegardeCourante);
                            System.out.println("File already exists.");
                        }
                    }
                } else {
                    throw new MaxSauvegardeException();
                }
               
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            } catch (MaxSauvegardeException e) {
                System.out.println("Max de sauvegarde atteint !");
            }
        }
    
    

    public void ecrireSauvegarde(FileWriter fic) throws IOException {

        this.sauvegardeJoueur(fic);

        if (!jeu.ennemis.isEmpty()) {
            int compt = 0;
            while (compt < jeu.ennemis.size()) {
                this.sauvegardeEnnemi(fic, jeu.ennemis.get(compt));
                compt++;
            }
        } 
        this.sauvegardeMap(fic);
        this.sauvegardeBool(fic);
    }

    public void sauvegardeJoueur(FileWriter f) throws IOException {
        Joueur j = jeu.joueur;
        f.write("Joueur ");
        // Sauvegarde Point de vie
        this.ecrire(j.vie.getVie(), f);
        // Sauvegarde Position
        this.ecrire(j.getX(), f);
        this.ecrire(j.getY(), f);
        this.ecrire(j.comptPiece, f);
        // Sauvegarde Inventaire
        if (jeu.joueur.inv.listeObjet[0].image != jeu.listeObj.listeObjet[0][0].image) {
            this.sauvegardeInventaire(f, jeu.joueur.inv);
        }
    }

    public void sauvegardeEnnemi(FileWriter f, Ennemi1 e) throws IOException {
        f.write("Ennemi ");
        // Sauvegarde Vie
        this.ecrire(e.pdv, f);
        // Sauvegarde Position
        this.ecrire(e.getX(), f);
        this.ecrire(e.getY(), f);
    }

    public void sauvegardeInventaire(FileWriter f, Inventaire inv) throws IOException {
        Objet[] liste = inv.listeObjet;
        int compt = 0;
        int nbObjet = inv.getNbObjetInv();
        f.write("Inventaire ");
        while (compt < nbObjet) {
            f.write(liste[compt].nom + " ");
            this.ecrire(liste[compt].nb, f);
            compt++;
        }

        
    }

    public void sauvegardeBoss(FileWriter f) throws IOException {

    }

    public void sauvegardeMap(FileWriter f) throws IOException {
        Salle sallecourante = jeu.salle.getSalle();
        f.write("Map ");
        if (sallecourante instanceof SalleClassique) {
            f.write("SalleClassique ");
        } else if (sallecourante instanceof SallePrincesse) {
            f.write("SallePrincesse ");
        } else if (sallecourante instanceof SalleSecrete) {
            f.write("SalleSecrete ");
        } else if (sallecourante instanceof Magasin) {
            f.write("Magasin ");
        } else if (sallecourante instanceof SalleClassique3) {
            f.write("SalleClassique3 ");
        } else if (sallecourante instanceof SalleClassiquepassage) {
            f.write("SalleClassiquepassage ");
        } else if (sallecourante instanceof Salle2) {
            f.write("Salle2 ");
        } else if (sallecourante instanceof Salleboss) {
            f.write("Salleboss ");
        }
    }

    public void ecrire(int i, FileWriter f) throws IOException{
        String s = String.valueOf(i);
        f.write(s + " ");
    }

    public void ecrire(double d, FileWriter f) throws IOException{
        String s = String.valueOf(d);
        f.write(s + " ");
    }

    public void sauvegardeBool(FileWriter f) throws IOException{
        f.write(String.valueOf(jeu.bossVaincu) + " ");
        f.write(String.valueOf(jeu.recompense) + " ");
        f.write(String.valueOf(jeu.premiereEntre) + " ");
        f.write(String.valueOf(jeu.collision.chgt_salle) + " ");
        f.write(String.valueOf(jeu.collision.chgt_salle_3sallecclassique2) + " ");
        f.write(String.valueOf(jeu.collision.chgt_salle_classique_3) + " ");
        f.write(String.valueOf(jeu.collision.chgt_salle_classique_bis) + " ");
        f.write(String.valueOf(jeu.collision.chgt_salle_msalleclassique) + " ");
        f.write(String.valueOf(jeu.collision.chgt_sallebis_salleclassique) + " ");
        f.write(String.valueOf(jeu.collision.chgt_salleboss) + " ");
        f.write(String.valueOf(jeu.collision.chgt_salleboss_classique) + " ");
        f.write(String.valueOf(jeu.collision.chgt_salleclas3_sallesecrete) + " ");
        f.write(String.valueOf(jeu.collision.chgt_salleprinc_salleclassique) + " ");
        f.write(String.valueOf(jeu.collision.chgt_salleprincesse) + " ");
        f.write(String.valueOf(jeu.collision.chgt_sallesecrete_salleclassique3) + " ");
        f.write(String.valueOf(jeu.collision.lancer_magasin) + " ");
        f.write(String.valueOf(jeu.collision.lancer_salle_3salleclassique2) + " ");
        f.write(String.valueOf(jeu.collision.lancer_salle_classique3) + " ");
        f.write(String.valueOf(jeu.collision.lancer_salle_classiquebis) + " ");
        f.write(String.valueOf(jeu.collision.lancer_sallebis_salleclassique) + " ");
        f.write(String.valueOf(jeu.collision.lancer_salleboss) + " ");
        f.write(String.valueOf(jeu.collision.lancer_salleboss_classique) + " ");
        f.write(String.valueOf(jeu.collision.lancer_salleclas3_sallesecrete) + " ");
        f.write(String.valueOf(jeu.collision.lancer_salleprincesse) + " ");
        f.write(String.valueOf(jeu.collision.lancer_sallesecrete) + " ");
        f.write(String.valueOf(jeu.collision.lancer_sallesecrete_salleclassique3) + " ");
        f.write(String.valueOf(jeu.collision.nepeutopasentre) + " ");
        f.write(String.valueOf(jeu.collision.retour_salleclassique) + " ");

    }
}