package model.copycatModel.ordSetGrammar;

import model.orderedSetModel.impl.MinimalLowerSetElement;

public class PositionOSMinimal extends MinimalLowerSetElement implements IPositionOS {

	private static final String NAME = "position";
	
	public PositionOSMinimal(String elementID) {
		super(elementID);
	}

	@Override
	public String getDescriptorName() {
		return NAME;
	}

}


