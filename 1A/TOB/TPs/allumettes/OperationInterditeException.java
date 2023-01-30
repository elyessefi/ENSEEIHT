package allumettes;

public class OperationInterditeException extends RuntimeException {
	private String tricheur;

    public OperationInterditeException() {
        super();
    }

	public OperationInterditeException(String tricheur) {
		super();
        this.tricheur = tricheur;
	}

    public String getTricheur() {
        return this.tricheur;
    }
}
