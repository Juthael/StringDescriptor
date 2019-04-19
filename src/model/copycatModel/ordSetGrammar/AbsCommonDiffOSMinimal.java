package model.copycatModel.ordSetGrammar;

import model.orderedSetModel.impl.MinimalLowerSetElement;

public class AbsCommonDiffOSMinimal extends MinimalLowerSetElement implements IAbsCommonDiffOS {
	
	private static final String NAME = "absCommonDiff";

	public AbsCommonDiffOSMinimal(String elementID) {
		super(elementID);
	}

	@Override
	public String getDescriptorName() {
		return NAME;
	}

}
