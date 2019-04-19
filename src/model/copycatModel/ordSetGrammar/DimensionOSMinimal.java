package model.copycatModel.ordSetGrammar;

import model.orderedSetModel.impl.MinimalLowerSetElement;

public class DimensionOSMinimal extends MinimalLowerSetElement implements IDimensionOS {

	private static final String NAME = "dimension";
	
	public DimensionOSMinimal(String elementID) {
		super(elementID);
	}

	@Override
	public String getDescriptorName() {
		return NAME;
	}

}
