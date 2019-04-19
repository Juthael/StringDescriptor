package model.copycatModel.ordSetGrammar;

import model.orderedSetModel.impl.MinimalLowerSetElement;

public class EnumerationOSMinimal extends MinimalLowerSetElement implements IEnumerationOS {

	private static final String NAME = "enumeration";
	
	public EnumerationOSMinimal(String elementID) {
		super(elementID);
	}

	@Override
	public String getDescriptorName() {
		return NAME;
	}

}
