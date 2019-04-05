package exceptions;

public class DescriptorsBuilderException extends Exception {

	private static final long serialVersionUID = -4469903173051482351L;

	public DescriptorsBuilderException() {
	}

	public DescriptorsBuilderException(String arg0) {
		super(arg0);
	}

	public DescriptorsBuilderException(Throwable arg0) {
		super(arg0);
	}

	public DescriptorsBuilderException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public DescriptorsBuilderException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

}
