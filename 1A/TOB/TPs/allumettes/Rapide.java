package allumettes;
/**Une realisation de l'interface Strategie qui definit une strategie rapide
ou l'ordinateur retire le maximum d'allumettes possible
*/

public class Rapide implements Strategie {
	/** Construire une strategie rapide par defaut
	*/
	public Rapide() { }
	/**obtenir le nombre d'allumettes prise par le startegie rapide
	 * @param jeu Jeu
	 * @return retirer int
	*/
    @Override public int getPrise(Jeu jeu) {
        int retirer = Math.min(jeu.getNombreAllumettes(), jeu.PRISE_MAX);
        return retirer;
    }
}
