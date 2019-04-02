package verbalization.implementations;

import copycatModel.grammar.CharString;
import exceptions.VerbalizationException;
import verbalization.implementations.verbalStructureModel.DescriptionV2;
import verbalization.interfaces.DescriptionCoderInterface;
import verbalization.interfaces.VerbalizerInterface;
import verbalization.interfaces.dataEncodingModel.DescriptionCodeGetterInterface;
import verbalization.interfaces.verbalStructureModel.DescriptionInterface;

public class VerbalizerV2 implements VerbalizerInterface {

	private String descriptionInNaturalLanguage;
	
	public VerbalizerV2(CharString descriptorToBeTranslated) throws VerbalizationException {
		DescriptionCoderInterface descriptionCoder = new DescriptionCoderV1(descriptorToBeTranslated);
		DescriptionCodeGetterInterface descriptionCodeGetter = descriptionCoder.getDescriptionCodeGetter();
		DescriptionInterface description = new DescriptionV2(descriptionCodeGetter);
		descriptionInNaturalLanguage = description.getVerbalDescription();
	}

	@Override
	public String getTranslationInNaturalLanguage() {
		return descriptionInNaturalLanguage;
	}

}
