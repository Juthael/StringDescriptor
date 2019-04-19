package model.copycatModel.ordSetGrammar;

import model.orderedSetModel.impl.MinimalLowerSetElement;

public class EndPositionOSMinimal extends MinimalLowerSetElement implements IEndPositionOS {

	private static final String NAME = "endPosition";
	
	public EndPositionOSMinimal(String elementID) {
		super(elementID);
	}

	@Override
	public String getDescriptorName() {
		return NAME;
	}

}
