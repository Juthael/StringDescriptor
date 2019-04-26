package model.copycatModel.ordSetGrammar;

import model.orderedSetModel.impl.MinimalOS;

public class CommonDiffOSMinimal extends MinimalOS implements ICommonDiffOS {

	private static final String NAME = "commonDiff";
	
	public CommonDiffOSMinimal(String elementID) {
		super(elementID);
	}

	@Override
	public String getDescriptorName() {
		return NAME;
	}

}
