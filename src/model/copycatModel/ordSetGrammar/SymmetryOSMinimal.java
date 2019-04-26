package model.copycatModel.ordSetGrammar;

import model.orderedSetModel.impl.MinimalOS;

public class SymmetryOSMinimal extends MinimalOS implements ISymmetryOS {

	private static final String NAME = "symmetry";
	
	public SymmetryOSMinimal(String elementID) {
		super(elementID);
	}

	@Override
	public String getDescriptorName() {
		return NAME;
	}

}
