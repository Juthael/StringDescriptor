package model.copycatModel.ordSetGrammar;

import model.orderedSetModel.impl.MinimalOS;

public class PlatonicLetterOSMinimal extends MinimalOS implements IPlatonicLetterOS {

	private static final String NAME = "platonicLetter";
	
	public PlatonicLetterOSMinimal(String elementID) {
		super(elementID);
	}

	@Override
	public String getDescriptorName() {
		return NAME;
	}

}
