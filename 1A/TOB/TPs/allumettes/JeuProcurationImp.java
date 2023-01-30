package allumettes;

public class JeuProcurationImp implements Jeu {
	private JeuReelImp jeu;
	public JeuProcurationImp(JeuReelImp jeu) {
		this.jeu = jeu;
	}
	public int getNombreAllumettes() {
		return this.getjeu().getNombreAllumettes();
	}
	public void retirer(int nbPrise) throws OperationInterditeException {
		throw new OperationInterditeException();
	}

	public JeuReelImp getjeu() {
	    return this.jeu;
	}
}
