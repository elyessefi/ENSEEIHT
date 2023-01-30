package Entite;
import java.awt.Graphics2D;

public interface Entite {

    /**Savoir si l'entite est present sur la map
     * @return un booleen (true = present, false = absent)
     */
    public boolean estPresent();

    public void afficher(Graphics2D g);
    
    public void miseAJour();

    public Direction getDirection();

    public int getVitesse();

    public double getCoeurMax();

    public int getX();

    public int getY();
    
    public Hitbox getHitbox();

}