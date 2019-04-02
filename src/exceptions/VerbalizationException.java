package exceptions;

public class VerbalizationException extends Exception {

	private static final long serialVersionUID = -5391236543427157467L;

	public VerbalizationException() {
	}

	public VerbalizationException(String arg0) {
		super(arg0);
	}

	public VerbalizationException(Throwable cause) {
		super(cause);
	}

	public VerbalizationException(String message, Throwable cause) {
		super(message, cause);
	}

	public VerbalizationException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
