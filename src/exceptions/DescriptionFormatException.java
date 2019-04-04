package exceptions;

public class DescriptionFormatException extends RuntimeException {

	private static final long serialVersionUID = -4337615912096803785L;

	public DescriptionFormatException() {
	}

	public DescriptionFormatException(String arg0) {
		super(arg0);
	}

	public DescriptionFormatException(Throwable arg0) {
		super(arg0);
	}

	public DescriptionFormatException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public DescriptionFormatException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

}
