package allumettes;

/** Arbitre controle le jeu en calculant le nombre d"allumettes
  * restants et prises et en determinant le gagnant et le perdant
*/
public class Arbitre {

	private Joueur joueur1;
	private Joueur joueur2;

 /** Construire un arbitre a partir de deux jouers
  * @param Joueur_1 Joueur
  * @param Joueur_2 Joueur
 */
    public Arbitre(Joueur joueur1, Joueur joueur2) {
        this.joueur1 = joueur1;
        this.joueur2 = joueur2;
    }
  /** obtenir le premier joueur
   * @return joueur1
  */
    //public Joueur getJoueur1() {
    //    return this.joueur1;
    //}
   /** obtenir le deuxieme joueur
    * @return joueur2
   */
    //public Joueur getJoueur2() {
     //   return this.joueur2;
    //}
    /**gerer une partie du jeu
     * @param jeu JeuReelImp
    */
    public void arbitrer(Jeu jeu) {
        Jeu proxy = new JeuProcurationImp((JeuReelImp) jeu);
    	boolean tour = true;
        boolean ecrireAllumettesRestants = true;
        while (jeu.getNombreAllumettes() > 0) {
            Joueur joueurActuelle = this.joueur1;
            if (!tour) {
                joueurActuelle = this.joueur2;
            }
            if (ecrireAllumettesRestants) {
        	    System.out.println("Allumettes restantes : " + jeu.getNombreAllumettes());
        	}
        	ecrireAllumettesRestants = true;
                try {
                	int nbAllumettesretire;
                    if (((JeuReelImp) jeu).getTypeArbitre().equals("confiant")) {
                        nbAllumettesretire = joueurActuelle.getPrise(jeu);
                    } else {
                        nbAllumettesretire = joueurActuelle.getPrise(proxy);
                    }
        	    	if (nbAllumettesretire == 1 || nbAllumettesretire == 0
        	    	|| nbAllumettesretire == -1) {
                        System.out.println(joueurActuelle.getNom() + " prend "
                        + nbAllumettesretire + " allumette.");
        		    } else {
       			        System.out.println(joueurActuelle.getNom() + " prend "
       			        + nbAllumettesretire + " allumettes.");
       			    }
       			    jeu.retirer(nbAllumettesretire);
       			    tour = !tour;
       			    System.out.println("");
                    } catch (NumberFormatException e) {
                        System.out.println("Vous devez donner un entier.");
                        ecrireAllumettesRestants = false;
                    } catch (OperationInterditeException ee) {
       		             System.out.println("[Je triche...]");
       		             throw new OperationInterditeException(joueurActuelle.
       		                        getNom());
       		        } catch (CoupInvalideException eee) {
       		             System.out.println("Impossible ! Nombre invalide : "
       		             + eee.getCoup() + " (" + eee.getProbleme() + ")");
       		             System.out.println("");
                 }
        }
        this.perdantGagnant(tour);
     }
    /** determiner le perdant et le gagnant en se basant sur le dernie coup
    * @param tour boolean
    */
    private void perdantGagnant(boolean tour) {
    	Joueur perdant;
    	Joueur gagnant;
    	if (!tour) {
        	perdant = this.joueur1;
        	gagnant = this.joueur2;
        } else {
        	perdant = this.joueur2;
        	gagnant = this.joueur1;
        }
        System.out.println(perdant.getNom() + " perd !");
        	System.out.println(gagnant.getNom() + " gagne !");
    }
}
