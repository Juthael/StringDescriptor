package model.copycatModel.ordSetGrammar;

import model.orderedSetModel.impl.MinimalOS;

public class DirectionOSMinimal extends MinimalOS implements IDirectionOS {

	private static final String NAME = "direction";
	
	public DirectionOSMinimal(String elementID) {
		super(elementID);
	}

	@Override
	public String getDescriptorName() {
		return NAME;
	}

}
