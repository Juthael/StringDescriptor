package dao.transferObjects;

import java.util.List;

import copycatModel.grammar.CharString;
import dao.interfaces.DAODescription;
import exceptions.VerbalizationException;
import verbalization.implementations.VerbalizerV2;
import verbalization.interfaces.VerbalizerInterface;

public class DAODescriptionV1 implements DAODescription {

	private CharString wholeStringDescriptor;
	
	public DAODescriptionV1(CharString wholeStringDescriptor) {
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
