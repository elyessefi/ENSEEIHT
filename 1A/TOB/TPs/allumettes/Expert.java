package allumettes;
/**Une realisation de l'interface Strategie qui definit une strategie expert
ou l'ordinateur joue du mieux qu'il se peut
*/
public class Expert implements Strategie {
    int magicNumber = 4;
	/** Construire une strategie expert par defaut
	*/
	public Expert() { }
	/**obtenir le nombre d'allumettes prise par le startegie expert
	 * @param jeu Jeu
	 * @return retirer int
	*/
	@Override public int getPrise(Jeu jeu) {
        int retirer = (jeu.getNombreAllumettes()) % magicNumber;
        if (retirer != 1) {
        	retirer = (retirer + jeu.PRISE_MAX) % magicNumber;
        } else {
        	retirer = 1;
        }
        return retirer;
    }
}
