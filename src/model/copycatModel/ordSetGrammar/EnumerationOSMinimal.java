package model.copycatModel.ordSetGrammar;

import model.orderedSetModel.impl.MinimalOS;

public class EnumerationOSMinimal extends MinimalOS implements IEnumerationOS {

	private static final String NAME = "enumeration";
	
	public EnumerationOSMinimal(String elementID) {
		super(elementID);
	}

	@Override
	public String getDescriptorName() {
		return NAME;
	}

}
