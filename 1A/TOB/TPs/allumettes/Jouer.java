package allumettes;

/** Lance une partie des 13 allumettes en fonction des arguments fournis
 * sur la ligne de commande.
 * @author	Xavier Crégut
 * @version	$Revision: 1.5 $
 */
public class Jouer {

    static final int NOMBRE_ALLUMETTES = 13;

	/** Lancer une partie. En argument sont donnés les deux joueurs sous
	 * la forme nom@stratégie.
	 * @param args la description des deux joueurs
	 */
	 private static Strategie etablirStrategie(String nomDeStrategie,
	                String joueurHumain) {
		if (nomDeStrategie.equals("humain")) {
            return new Humain(joueurHumain);
		} else if (nomDeStrategie.equals("rapide")) {
            return new Rapide();
		} else if (nomDeStrategie.equals("naif")) {
            return new Naif();
		} else if (nomDeStrategie.equals("tricheur")) {
            return new Tricheur();
		} else if (nomDeStrategie.equals("expert")) {
            return new Expert();
		}
		return null;
	}

	 private static Joueur construireJoueur(String args) throws ConfigurationException  {
	    String[] nometStrategie = args.split("@");
	    Strategie strategie = etablirStrategie(nometStrategie[1], nometStrategie[0]);
	    return new Joueur(nometStrategie[0], strategie);
	 }

	public static void main(String[] args) {
		try {
			verifierNombreArguments(args);
            String typeArbitre;
            Joueur joueur1;
            Joueur joueur2;
            if (args[0].equals("-confiant")) {
                typeArbitre = "confiant";
                joueur1 = construireJoueur(args[1]);
                joueur2 = construireJoueur(args[2]);
            } else {
                typeArbitre = "!confiant";
                joueur1 = construireJoueur(args[0]);
                joueur2 = construireJoueur(args[1]);
            }
            Arbitre arbitrePartie = new Arbitre(joueur1, joueur2);
			JeuReelImp partie = new JeuReelImp(NOMBRE_ALLUMETTES, typeArbitre);
            arbitrePartie.arbitrer(partie);
	    	} catch (ConfigurationException e) {
			System.out.println();
			System.out.println("Erreur : " + e.getMessage());
			afficherUsage();
			System.exit(1);
		} catch (OperationInterditeException ee) {
			System.out.println("Abandon de la partie car " + ee.getTricheur()
			                    + " triche !");
	    } catch (ArrayIndexOutOfBoundsException c) {
	        afficherUsage();
	    } catch (NullPointerException cc) {
	        afficherUsage();
	    }
	}

	private static void verifierNombreArguments(String[] args) {
		final int nbJoueurs = 2;
		if (args.length < nbJoueurs) {
			throw new ConfigurationException("Trop peu d'arguments : "
					+ args.length);
		}
		if (args.length > nbJoueurs + 1) {
			throw new ConfigurationException("Trop d'arguments : "
					+ args.length);
		}
	}


	/** Afficher des indications sur la manière d'exécuter cette classe. */
	public static void afficherUsage() {
		System.out.println("\n" + "Usage :"
				+ "\n\t" + "java allumettes.Jouer joueur1 joueur2"
				+ "\n\t\t" + "joueur est de la forme nom@stratégie"
				+ "\n\t\t" + "strategie = naif | rapide | expert | humain | tricheur"
				+ "\n"
				+ "\n\t" + "Exemple :"
				+ "\n\t" + "	java allumettes.Jouer Xavier@humain "
					   + "Ordinateur@naif"
				+ "\n"
				);
	}
}
