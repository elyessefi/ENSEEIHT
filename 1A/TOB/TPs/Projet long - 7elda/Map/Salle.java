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

import Main.Jeu;

import java.awt.Graphics;

public interface Salle {
	 
	
	//int[][] coordonneeMap = new int [Jeu.maxCol][Jeu.maxLigne];
	 //Map[] map =  new Map[50];
	 void getTexture();
	 
	 void creerMap() ;
	 
	 void dessiner(Graphics graphique);
	 
	 Map[] getMap();
	 
	 int[][] getCoordonneeMap () ;
}
