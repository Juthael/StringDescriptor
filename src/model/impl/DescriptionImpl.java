package model.impl;

import java.util.List;

import copycatModel.grammar.CharString;
import exceptions.VerbalizationException;
import model.IDescription;
import verbalization.implementations.VerbalizerV2;
import verbalization.interfaces.VerbalizerInterface;

public class DescriptionImpl implements IDescription {

	private CharString wholeStringDescriptor;
	
	public DescriptionImpl(CharString wholeStringDescriptor) {
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
		VerbalizerInterface verbalizer = new VerbalizerV2(wholeStringDescriptor);
		descriptionInNaturalLanguage = verbalizer.getTranslationInNaturalLanguage();
		return descriptionInNaturalLanguage;
	}	

}
