package Main;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JPanel;

import Entite.Joueur;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MapEntier extends JPanel {

	//added
	public Jeu jeu; 
	public Collision collisionVerificateur = new Collision(jeu);
	public Joueur joueur;
	
    public ControleClavier afficherMap;
    private boolean draw = false;
    private List<Tile> tiles;
    private BufferedImage background;

    public MapEntier(ControleClavier afficherMap, Joueur joueur, Jeu jeu) {
    	
        this.afficherMap = afficherMap;
        this.joueur = joueur;
        this.jeu = jeu;
   
        setBackground(Color.BLACK);
        tiles = loadImages();

    }

    public void dessinerMapEntier() {
        draw = true;
    }

    public void clearMap() {
        draw = false;
        this.revalidate();
        this.removeAll();
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        if (draw) {
            AffineTransform back = new AffineTransform();
            back.setToScale(0.86,0.86);
            g2d.drawImage(background,back,null);
            for (Tile tile : tiles) {
                AffineTransform at = new AffineTransform();
                at.setToScale(2.5, 2.5);
                at.translate(tile.getPoint().getX(), tile.getPoint().getY());
                g2d.drawImage(tile.getImage(), at, null);
            }
            AffineTransform at = new AffineTransform();
            at.setToScale(2.5, 2.5);
            Point point = tiles.get(3).getPoint();
            double divX = 0.1;
            double divY = 0.15;
            if (collisionVerificateur.lancer_magasin==true){
            	at.translate(point.getX()+(joueur.getX()*divX), (point.getY()-300)+(joueur.getY()*divY));
            }
            if (collisionVerificateur.lancer_salle_classiquebis==true){
            	at.translate(point.getX()+(joueur.getX()*divX), (point.getY()-200)+(joueur.getY()*divY));
            }
            if (collisionVerificateur.lancer_magasin==true){
            	at.translate(point.getX()+(joueur.getX()*divX), (point.getY()-100)+(joueur.getY()*divY));
            }
            if (collisionVerificateur.lancer_sallebis_salleclassique==true || collisionVerificateur.retour_salleclassique==true || collisionVerificateur.lancer_salleboss_classique==true){
            	at.translate(point.getX()+(joueur.getX()*divX), point.getY()+(joueur.getY()*divY));
            }
            if (collisionVerificateur.lancer_salleprincesse == true){
            	at.translate((point.getX()+100)+(joueur.getX()*divX), point.getY()+(joueur.getY()*divY));
            }
            if(collisionVerificateur.lancer_salleboss==true) {
            	at.translate((point.getX())+(joueur.getX()*divX), (point.getY()+100)+(joueur.getY()*divY));
            }
            g2d.setColor(Color.RED);
            g2d.fillOval((int) at.getTranslateX(), (int) at.getTranslateY(), 10, 10);
        }
    }

    private List<Tile> loadImages() {
        List<Tile> tiles = new ArrayList<Tile>();
        try {
            for (int i = 0; i < 6; i++) {

                BufferedImage image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResource("ressources/" + (i + 1) + ".png")));
                Tile tile = new Tile(image);
                tile.setPoint(new Point(2 * image.getWidth(), 0));
                if (i > 0) {
                    tile.setPoint(new Point(2 * image.getWidth(), i * image.getHeight()));
                }
                if (i == 4) {
                    tile.setPoint(new Point(3 * image.getWidth(), image.getHeight() * (i - 1)));
                }
                if (i == 5) {
                    tile.setPoint(new Point(2 * image.getWidth(), (i - 1) * image.getHeight()));
                }
                tiles.add(tile);

            }
            background = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResource("ressources/menu_principal.jpg")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tiles;
    }

    private class DrawMap extends JComponent {
        private List<Tile> tiles;
        private Point playerPoint;

        private DrawMap(List<Tile> tiles, Point playerCoordinates) {
            this.tiles = tiles;
            this.playerPoint = playerCoordinates;

        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            System.out.println("I was called");
            Graphics2D g2d = (Graphics2D) g;

            for (Tile tile : tiles) {
                AffineTransform at = new AffineTransform();
                at.translate(tile.getPoint().getX(), tile.getPoint().getY());
                g2d.drawImage(tile.getImage(), at, null);
            }
            System.out.println("Drawing");
        }
    }

    private class Tile {
        private Point point;
        private BufferedImage image;

        public Tile(BufferedImage image) {
            this.point = new Point();
            this.image = image;
        }

        public Point getPoint() {
            return point;
        }

        public void setPoint(Point point) {
            this.point = point;
        }

        public BufferedImage getImage() {
            return image;
        }

        public void setImage(BufferedImage image) {
            this.image = image;
        }
    }

    private class Point {
        private int x ;
        private int y ;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point() {
        }

        public int getX() {
            return this.x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return this.y;
        }

        public void setY(int y) {
            this.y = y;
        }
    }
}