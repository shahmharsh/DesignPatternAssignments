package videoStore;

public class NotEnoughCopiesException extends Exception {

	private static final long serialVersionUID = 1L;

	public NotEnoughCopiesException(String message) {
        super(message);
    }
}