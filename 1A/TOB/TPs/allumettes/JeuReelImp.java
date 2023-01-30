package allumettes;

public class JeuReelImp implements Jeu {

		private int nbAllumettes; //public or protected ?
		private String typeArbitre;

		public JeuReelImp(int nbAllumettes, String typeArbitre) {
            this.typeArbitre = typeArbitre;
			this.nbAllumettes = nbAllumettes;
		}
		public int getNombreAllumettes() {
			return this.nbAllumettes;
		}
		/**obtenir le type de l'arbitre
		 * @retrun le type d'arbitre String
		*/
		public String getTypeArbitre() {
		    return this.typeArbitre;
		}

		public void retirer(int nbPrise) throws CoupInvalideException {
			if (nbPrise > this.getNombreAllumettes()) {
				throw new CoupInvalideException(nbPrise, "> "
				+ this.getNombreAllumettes());
			} else if (nbPrise < 1) {
				throw new CoupInvalideException(nbPrise, "< 1");
	        } else if (nbPrise > Jeu.PRISE_MAX) {
				throw new CoupInvalideException(nbPrise, "> " + Jeu.PRISE_MAX);
			}
			this.nbAllumettes = this.getNombreAllumettes() - nbPrise;
		}
}
