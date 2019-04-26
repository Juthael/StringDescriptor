package model.copycatModel.ordSetGrammar;

import model.orderedSetModel.impl.MinimalOS;

public class SizeOSMinimal extends MinimalOS implements ISizeOS {

	private static final String NAME = "size";
	
	public SizeOSMinimal(String elementID) {
		super(elementID);
	}

	@Override
	public String getDescriptorName() {
		return NAME;
	}

}
