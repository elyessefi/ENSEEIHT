package allumettes;
/**Definition d'une strategie  */
public interface Strategie {
    /**obteinr le nombre des allumettes prises pendant un tour
     * @param jeu Jeu
     * @return nombre des allumttes prises
    */
    int getPrise(Jeu jeu) throws NumberFormatException;
}
