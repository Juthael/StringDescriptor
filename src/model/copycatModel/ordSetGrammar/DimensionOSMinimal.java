package model.copycatModel.ordSetGrammar;

import model.orderedSetModel.impl.MinimalOS;

public class DimensionOSMinimal extends MinimalOS implements IDimensionOS {

	private static final String NAME = "dimension";
	
	public DimensionOSMinimal(String elementID) {
		super(elementID);
	}

	@Override
	public String getDescriptorName() {
		return NAME;
	}

}
