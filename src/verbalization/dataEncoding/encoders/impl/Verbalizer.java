package verbalization.dataEncoding.encoders.impl;

import exceptions.VerbalizationException;
import model.copycatModel.synTreeGrammar.CharString;
import model.synTreeModel.IStartGrammElementST;
import verbalization.dataEncoding.encoders.IDescriptionCoder;
import verbalization.dataEncoding.encoders.IVerbalizer;
import verbalization.dataEncoding.encodingModel.IDescriptionCodeGetter;
import verbalization.verbalStructureModel.IDescription;
import verbalization.verbalStructureModel.impl.DescriptionImpl;

public class Verbalizer implements IVerbalizer {

	private String descriptionInNaturalLanguage;
	
	public Verbalizer(IStartGrammElementST descriptorToBeTranslated) throws VerbalizationException {
		CharString charString = (CharString) descriptorToBeTranslated;
		IDescriptionCoder descriptionCoder = new DescriptionCoder(charString);
		IDescriptionCodeGetter descriptionCodeGetter = descriptionCoder.getDescriptionCodeGetter();
		IDescription description = new DescriptionImpl(descriptionCodeGetter);
		descriptionInNaturalLanguage = description.getVerbalDescription();
	}

	@Override
	public String getTranslationInNaturalLanguage() {
		return descriptionInNaturalLanguage;
	}

}
