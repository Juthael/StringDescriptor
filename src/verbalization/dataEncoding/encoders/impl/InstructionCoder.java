package verbalization.dataEncoding.encoders.impl;

import java.util.ArrayList;
import java.util.List;

import exceptions.VerbalizationException;
import verbalization.dataEncoding.encoders.IInstructionCoder;
import verbalization.dataEncoding.encoders.ITransformationCoder;
import verbalization.dataEncoding.encodingModel.IInstructionCodeGetter;
import verbalization.dataEncoding.encodingModel.ITransformationCodeGetter;
import verbalization.dataEncoding.encodingModel.impl.InstructionCodeGetterImpl;

public class InstructionCoder implements IInstructionCoder {

	private IInstructionCodeGetter instructionCodeGetter;
	
	public InstructionCoder(String nbOfComponents, List<String> listOfRelationXProperties) 
			throws VerbalizationException {
		instructionCodeGetter = setInstructionCodeGetter(nbOfComponents, listOfRelationXProperties);
	}

	@Override
	public IInstructionCodeGetter getInstructionCodeGetter() {
		return instructionCodeGetter;
	}
	
	private IInstructionCodeGetter setInstructionCodeGetter(String nbOfComponents, 
			List<String> listOfRelationXProperties) throws VerbalizationException {
		IInstructionCodeGetter instructionCodeGetter;
		List<ITransformationCodeGetter> listOfTransformationCodeGetters = 
				new ArrayList<ITransformationCodeGetter>();
		List<List<String>> listsOfRelationProperties = new ArrayList<List<String>>();
		List<String> currentListOfRelationProperties = new ArrayList<String>();
		for (String property : listOfRelationXProperties) {
			if (property.contains("relation/dimension")) {
				if (currentListOfRelationProperties.isEmpty()) {
					currentListOfRelationProperties.add(property);
				}
				else {
					List<String> currentListOfRelationPropertiesClone = new ArrayList<String>();
					currentListOfRelationPropertiesClone.addAll(currentListOfRelationProperties);
					listsOfRelationProperties.add(currentListOfRelationPropertiesClone);
					currentListOfRelationProperties = new ArrayList<String>();
					currentListOfRelationProperties.add(property);
				}
			}
			else currentListOfRelationProperties.add(property);
		}
		listsOfRelationProperties.add(currentListOfRelationProperties);
		for (List<String> listOfRelationProperties : listsOfRelationProperties) {
			ITransformationCoder transformationCoder = 
					new TransformationCoder(nbOfComponents, listOfRelationProperties);
			listOfTransformationCodeGetters.add(transformationCoder.getTransformationCodeGetter());
		}
		instructionCodeGetter = new InstructionCodeGetterImpl(nbOfComponents, listOfTransformationCodeGetters);
		return instructionCodeGetter;		
	}

}
