package model.copycatModel.ordSetGrammar;

import model.orderedSetModel.impl.MinimalLowerSetElement;

public class CommonDiffOSMinimal extends MinimalLowerSetElement implements ICommonDiffOS {

	private static final String NAME = "commonDiff";
	
	public CommonDiffOSMinimal(String elementID) {
		super(elementID);
	}

	@Override
	public String getDescriptorName() {
		return NAME;
	}

}
