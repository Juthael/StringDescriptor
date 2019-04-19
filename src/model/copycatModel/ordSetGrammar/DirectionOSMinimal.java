package model.copycatModel.ordSetGrammar;

import model.orderedSetModel.impl.MinimalLowerSetElement;

public class DirectionOSMinimal extends MinimalLowerSetElement implements IDirectionOS {

	private static final String NAME = "direction";
	
	public DirectionOSMinimal(String elementID) {
		super(elementID);
	}

	@Override
	public String getDescriptorName() {
		return NAME;
	}

}
