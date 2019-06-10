package verbalization.dataEncoding.encoders.impl;

import exceptions.VerbalizationException;
import model.copycatModel.synTreeGrammar.CharString;
import verbalization.dataEncoding.encoders.IDescriptionCoder;
import verbalization.dataEncoding.encoders.IVerbalizer;
import verbalization.dataEncoding.encodingModel.IDescriptionCodeGetter;
import verbalization.verbalStructureModel.IDescription;
import verbalization.verbalStructureModel.impl.DescriptionImpl;

public class Verbalizer implements IVerbalizer {

	private String descriptionInNaturalLanguage;
	
	public Verbalizer(CharString descriptorToBeTranslated) throws VerbalizationException {
		IDescriptionCoder descriptionCoder = new DescriptionCoder(descriptorToBeTranslated);
		IDescriptionCodeGetter descriptionCodeGetter = descriptionCoder.getDescriptionCodeGetter();
		IDescription description = new DescriptionImpl(descriptionCodeGetter);
		descriptionInNaturalLanguage = description.getVerbalDescription();
	}

	@Override
	public String getTranslationInNaturalLanguage() {
		return descriptionInNaturalLanguage;
	}

}
