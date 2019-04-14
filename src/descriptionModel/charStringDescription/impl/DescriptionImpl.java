package descriptionModel.charStringDescription.impl;

import java.util.List;

import copycatModel.synTreeModel.grammar.CharString;
import descriptionModel.charStringDescription.IDescription;
import exceptions.VerbalizationException;
import verbalization.dataEncoding.encoders.IVerbalizer;
import verbalization.dataEncoding.encoders.impl.VerbalizerImpl;

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
		IVerbalizer verbalizer = new VerbalizerImpl(wholeStringDescriptor);
		descriptionInNaturalLanguage = verbalizer.getTranslationInNaturalLanguage();
		return descriptionInNaturalLanguage;
	}	

}
