package verbalization.implementations;

import java.util.ArrayList;
import java.util.List;

import exceptions.VerbalizationException;
import verbalization.implementations.dataEncodingModel.InstructionCodeGetterV1;
import verbalization.interfaces.InstructionCoderInterface;
import verbalization.interfaces.TransformationCoderInterface;
import verbalization.interfaces.dataEncodingModel.InstructionCodeGetterInterface;
import verbalization.interfaces.dataEncodingModel.TransformationCodeGetterInterface;

public class InstructionCoderV2 implements InstructionCoderInterface {

	private InstructionCodeGetterInterface instructionCodeGetter;
	
	public InstructionCoderV2(String nbOfComponents, List<String> listOfRelationXProperties) 
			throws VerbalizationException {
		instructionCodeGetter = setInstructionCodeGetter(nbOfComponents, listOfRelationXProperties);
	}

	@Override
	public InstructionCodeGetterInterface getInstructionCodeGetter() {
		return instructionCodeGetter;
	}
	
	private InstructionCodeGetterInterface setInstructionCodeGetter(String nbOfComponents, 
			List<String> listOfRelationXProperties) throws VerbalizationException {
		InstructionCodeGetterInterface instructionCodeGetter;
		List<TransformationCodeGetterInterface> listOfTransformationCodeGetters = 
				new ArrayList<TransformationCodeGetterInterface>();
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
			TransformationCoderInterface transformationCoder = 
					new TransformationCoderV2(nbOfComponents, listOfRelationProperties);
			listOfTransformationCodeGetters.add(transformationCoder.getTransformationCodeGetter());
		}
		instructionCodeGetter = new InstructionCodeGetterV1(nbOfComponents, listOfTransformationCodeGetters);
		return instructionCodeGetter;		
	}

}
