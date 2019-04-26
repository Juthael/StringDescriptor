package model.copycatModel.ordSetGrammar;

import model.orderedSetModel.impl.MinimalOS;

public class AbsCommonDiffOSMinimal extends MinimalOS implements IAbsCommonDiffOS {
	
	private static final String NAME = "absCommonDiff";

	public AbsCommonDiffOSMinimal(String elementID) {
		super(elementID);
	}

	@Override
	public String getDescriptorName() {
		return NAME;
	}

}
