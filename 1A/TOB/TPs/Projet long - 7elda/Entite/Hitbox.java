package Entite;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Point;


public class Hitbox {

    /* Hitbox des 4 cotes */
    private int HitBox_FaceHaut, HitBox_FaceBas, HitBox_FaceGauche, HitBox_FaceDroit;
    /* L'entite de la hitbox */
    private Entite e;

    /* Constructeur */
    Hitbox (Entite entite, int HitBox_FaceHaut, int HitBox_FaceBas, int HitBox_FaceDroit, int HitBox_FaceGauche) {
        this.HitBox_FaceHaut = HitBox_FaceHaut;
        this.HitBox_FaceBas = HitBox_FaceBas;
        this.HitBox_FaceDroit = HitBox_FaceDroit;
        this.HitBox_FaceGauche = HitBox_FaceGauche;
        this.e = entite;
    }

    /* Retourne la hitbox d'en haut */
    public int getHitBox_FaceHaut() {
        return this.HitBox_FaceHaut;
    }

    /* Retourne la hitbox d'en bas */
    public int getHitBox_FaceBas() {
        return this.HitBox_FaceBas;
    }

    /* Retourne la hitbox de droite */
    public int getHitBox_FaceDroite() {
        return this.HitBox_FaceDroit;
    }

    /* Retourne la hitbox de gauche */
    public int getHitBox_FaceGauche() {
        return this.HitBox_FaceGauche;
    }

    public Point getPointGauche() {
        return new Point(e.getX() - HitBox_FaceGauche, e.getY() + HitBox_FaceBas/2 - HitBox_FaceHaut/2);
    }

    public Point getPointDroit() {
        return new Point(e.getX() + HitBox_FaceDroit, e.getY() + HitBox_FaceBas/2 - HitBox_FaceHaut/2);
    }

    public Point getPointHaut() {
        return new Point(e.getX() + HitBox_FaceDroit/2 - HitBox_FaceGauche/2, e.getY() - HitBox_FaceHaut);
    }

    public Point getPointBas() {
        return new Point(e.getX() + HitBox_FaceDroit/2 - HitBox_FaceGauche/2, e.getY() + HitBox_FaceBas);
    }

    public void afficher(Graphics2D g) {
        g.setColor(Color.RED);
        
        Point p1 = this.getPointGauche();
        g.drawLine((int) p1.getX(),(int) p1.getY() - 20,(int) p1.getX(),(int) p1.getY() + 20);

        Point p2 = this.getPointDroit();
        g.drawLine((int) p2.getX(),(int) p2.getY() - 20,(int) p2.getX(),(int) p2.getY() + 20);

        Point p3 = this.getPointHaut();
        g.drawLine((int) p3.getX() - 20,(int) p3.getY(),(int) p3.getX() + 20,(int) p3.getY());

        Point p4 = this.getPointBas();
        g.drawLine((int) p4.getX() - 20,(int) p4.getY(),(int) p4.getX() + 20,(int) p4.getY());

    }
}