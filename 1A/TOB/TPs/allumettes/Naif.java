package allumettes;
import java.util.Random;
/** une realisation de l'interface Strategie qui definit une strategie naif
 * ou l'ordinateur choisit aleatoirement un nombre entre 1 et PRISE_MAX
*/
public class Naif implements Strategie {
    /**Construire une strategie naif par defaut
    */
	public Naif() { }

	/**obtenir le nombre des allumettes prise par l'ordinateur en strategie  naif
	 * @param jeu Jeu
	 * @return retirer int
	*/
    @Override public int getPrise(Jeu jeu) {
        Random random = new Random();
        int retirer = random.nextInt(jeu.PRISE_MAX) + 1;
        return retirer;
    }
}
