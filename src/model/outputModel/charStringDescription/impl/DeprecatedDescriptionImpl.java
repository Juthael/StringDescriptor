package model.outputModel.charStringDescription.impl;

import java.util.List;

import exceptions.VerbalizationException;
import model.copycatModel.synTreeGrammar.CharString;
import model.outputModel.charStringDescription.IDeprecatedDescription;
import verbalization.dataEncoding.encoders.IVerbalizer;
import verbalization.dataEncoding.encoders.impl.Verbalizer;

public class DeprecatedDescriptionImpl implements IDeprecatedDescription {

	private CharString wholeStringDescriptor;
	
	public DeprecatedDescriptionImpl(CharString wholeStringDescriptor) {
		this.wholeStringDescriptor = wholeStringDescriptor;
	}
	
	@Override
	public List<String> getCompleteDescription() {
		return wholeStringDescriptor.getListOfPropertiesWithPath();
	}
	
	@Override
	public List<String> getListOfRelevantPropertiesWithPath() {
		return wholeStringDescriptor.getListOfRelevantPropertiesWithPath();
	}
	
	@Override
	public String getDescriptionInNaturalLanguage() throws VerbalizationException{
		String descriptionInNaturalLanguage;
		IVerbalizer verbalizer = new Verbalizer(wholeStringDescriptor);
		descriptionInNaturalLanguage = verbalizer.getTranslationInNaturalLanguage();
		return descriptionInNaturalLanguage;
	}	

}
