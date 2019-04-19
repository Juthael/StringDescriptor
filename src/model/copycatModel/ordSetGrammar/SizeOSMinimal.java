package model.copycatModel.ordSetGrammar;

import model.orderedSetModel.impl.MinimalLowerSetElement;

public class SizeOSMinimal extends MinimalLowerSetElement implements ISizeOS {

	private static final String NAME = "size";
	
	public SizeOSMinimal(String elementID) {
		super(elementID);
	}

	@Override
	public String getDescriptorName() {
		return NAME;
	}

}
