package model.copycatModel.ordSetGrammar;

import model.orderedSetModel.impl.MinimalOS;

public class PositionOSMinimal extends MinimalOS implements IPositionOS {

	private static final String NAME = "position";
	
	public PositionOSMinimal(String elementID) {
		super(elementID);
	}

	@Override
	public String getDescriptorName() {
		return NAME;
	}

}


