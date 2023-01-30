package allumettes;
import java.util.Scanner;
/**Une realisation de l'interface Strategie qui definie
 * la strategie humaine
*/
public class Humain implements Strategie {
	static Scanner scanner = new Scanner(System.in);
	private String nomJoueur;
	/** Construire une strategie humaine
	*/
	public Humain(String nom) {
		this.nomJoueur = nom;
	}
	/** obtenir le nombre des allumettes prise par cette strategie
	* @param jeu Jeu
	* @return  retirer int
	*/
    @Override public int getPrise(Jeu jeu) throws NumberFormatException  {
        int nbPrise;
           try {
    	        System.out.print(this.getNom() + ", combien d'allumettes ? ");
    	        String retirer = this.scanner.next();
    	        try {
        	        if (retirer.equals("triche")) {
        	        jeu.retirer(1);
        	        System.out.println("[Une allumette en moins, plus que "
        	        + jeu.getNombreAllumettes() + ". Chut ! ]");
        	        return getPrise(jeu);
        	        }
        	     } catch (CoupInvalideException e) { }
                 nbPrise = Integer.parseInt(retirer);
                 } catch (NumberFormatException f) {
                    throw new NumberFormatException();
                 }
         return nbPrise;
    }

    private String getNom() {
        return this.nomJoueur;
    }
}
