package model.copycatModel.ordSetGrammar;

import model.orderedSetModel.impl.MinimalOS;

public class EndPositionOSMinimal extends MinimalOS implements IEndPositionOS {

	private static final String NAME = "endPosition";
	
	public EndPositionOSMinimal(String elementID) {
		super(elementID);
	}

	@Override
	public String getDescriptorName() {
		return NAME;
	}

}
