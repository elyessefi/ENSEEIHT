package allumettes;

public class Tricheur implements Strategie {
		public Tricheur() { }
		@Override public int getPrise(Jeu jeu) {
			try {
			    while (jeu.getNombreAllumettes() > 2) {
			        jeu.retirer(1);
			    }
			    jeu.retirer(jeu.PRISE_MAX + 1);
			} catch (CoupInvalideException e) {
			    System.out.println("[Je triche...]");
				System.out.println("[Allumettes restantes : "
				                    + jeu.getNombreAllumettes() + "]");
			}
			return 1;
        }
}
