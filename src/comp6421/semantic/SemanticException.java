package comp6421.semantic;

public class SemanticException extends Exception {

	private static final long serialVersionUID = -3778242997636567108L;

	public SemanticException(String message) {
		super("Exception: " + message);
	}

}
