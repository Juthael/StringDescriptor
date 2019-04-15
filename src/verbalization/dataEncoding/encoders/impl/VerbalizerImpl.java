package verbalization.dataEncoding.encoders.impl;

import exceptions.VerbalizationException;
import model.copycatModel.synTreeGrammar.CharString;
import verbalization.dataEncoding.encoders.IDescriptionCoder;
import verbalization.dataEncoding.encoders.IVerbalizer;
import verbalization.dataEncoding.encodingModel.IDescriptionCodeGetter;
import verbalization.verbalStructureModel.IDescription;
import verbalization.verbalStructureModel.impl.DescriptionImpl;

public class VerbalizerImpl implements IVerbalizer {

	private String descriptionInNaturalLanguage;
	
	public VerbalizerImpl(CharString descriptorToBeTranslated) throws VerbalizationException {
		IDescriptionCoder descriptionCoder = new DescriptionCoderImpl(descriptorToBeTranslated);
		IDescriptionCodeGetter descriptionCodeGetter = descriptionCoder.getDescriptionCodeGetter();
		IDescription description = new DescriptionImpl(descriptionCodeGetter);
		descriptionInNaturalLanguage = description.getVerbalDescription();
	}

	@Override
	public String getTranslationInNaturalLanguage() {
		return descriptionInNaturalLanguage;
	}

}
