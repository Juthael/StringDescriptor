package exceptions;

public class StringFormatException extends Exception {

	private static final long serialVersionUID = -4337615912096803785L;

	public StringFormatException() {
	}

	public StringFormatException(String arg0) {
		super(arg0);
	}

	public StringFormatException(Throwable arg0) {
		super(arg0);
	}

	public StringFormatException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public StringFormatException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

}
