package model.copycatModel.ordSetGrammar;

import model.orderedSetModel.impl.MinimalLowerSetElement;

public class PlatonicLetterOSMinimal extends MinimalLowerSetElement implements IPlatonicLetterOS {

	private static final String NAME = "platonicLetter";
	
	public PlatonicLetterOSMinimal(String elementID) {
		super(elementID);
	}

	@Override
	public String getDescriptorName() {
		return NAME;
	}

}
