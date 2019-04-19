package model.copycatModel.ordSetGrammar;

import model.orderedSetModel.impl.MinimalLowerSetElement;

public class SymmetryOSMinimal extends MinimalLowerSetElement implements ISymmetryOS {

	private static final String NAME = "symmetry";
	
	public SymmetryOSMinimal(String elementID) {
		super(elementID);
	}

	@Override
	public String getDescriptorName() {
		return NAME;
	}

}
