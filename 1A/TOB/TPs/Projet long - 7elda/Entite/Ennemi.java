package Entite;

import java.awt.Graphics2D;

public class Ennemi implements Entite{
	
	private boolean vivant;
	private Direction direction;
	private int vitesse;
	private int x;
	private int y;
	private int pdv;

	@Override
	public boolean estPresent() {
		return vivant;
	}

	@Override
	public void afficher(Graphics2D g) {
		;
		
	}

	@Override
	public void miseAJour() {
		;
	}

	@Override
	public Direction getDirection() {
		return direction;
	}

	@Override
	public int getVitesse() {
		return vitesse;
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}
	
	public void retirerDegats(int deg) {
		;
	}
	
	public int getType() {
		return 0;
	}

	@Override
	public double getCoeurMax() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Hitbox getHitbox() {
		// TODO Auto-generated method stub
		return null;
	}

}
