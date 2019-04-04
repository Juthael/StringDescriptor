package verbalization.implementations;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import exceptions.VerbalizationException;
import verbalization.interfaces.InstructionCoderInterface;
import verbalization.interfaces.dataEncodingModel.InstructionCodeGetterInterface;
import verbalization.interfaces.dataEncodingModel.TransformationCodeGetterInterface;

public class InstructionCoderV2Test {

	@Test
	public void whenRelationXContainsSingleRelationThenExpectedCodeIsReturned() throws VerbalizationException {
		List<String> listOfReturnedCodes = new ArrayList<String>();
		String nbOfComponents = "5"; 
		List<String> relationXListOfProperties = new ArrayList<String>();
		relationXListOfProperties.add("group/relations/relation/dimension/size");
		relationXListOfProperties.add("group/relations/relation/enumeration/1,1,1,1,1");
		relationXListOfProperties.add("group/relations/relation/sequence/commonDiff/0");
		relationXListOfProperties.add("group/relations/relation/sequence/absCommonDiff/0");
		InstructionCoderInterface instructionCoder = new InstructionCoderV2(nbOfComponents, relationXListOfProperties);
		InstructionCodeGetterInterface instructionCodeGetter = instructionCoder.getInstructionCodeGetter();
		for (TransformationCodeGetterInterface transformationCodeGetter : 
			instructionCodeGetter.getListOfTransformationCodeGetters()) {
			listOfReturnedCodes.addAll(transformationCodeGetter.getListOfPredicateCodes());
		}
		assertTrue(listOfReturnedCodes.size() == 1 &&
				listOfReturnedCodes.get(0).equals("manySizeEquals"));
	}
	
	@Test
	public void whenRelationXContainsManyRelationsThenExpectedCodesAreReturned() throws VerbalizationException {
		List<String> listOfReturnedCodes = new ArrayList<String>();
		String nbOfComponents = "5"; 
		List<String> relationXListOfProperties = new ArrayList<String>();
		relationXListOfProperties.add("group/relations/relationX2/relation/dimension/size");
		relationXListOfProperties.add("group/relations/relationX2/relation/enumeration/1,1,1,1,1");
		relationXListOfProperties.add("group/relations/relationX2/relation/sequence/commonDiff/0");
		relationXListOfProperties.add("group/relations/relationX2/relation/sequence/absCommonDiff/0");
		relationXListOfProperties.add("group/relations/relationX2/relation/dimension/letter.platonicLetter");
		relationXListOfProperties.add("group/relations/relationX2/relation/enumeration/1,2,3,4,5");
		relationXListOfProperties.add("group/relations/relationX2/relation/sequence/commonDiff/1");
		relationXListOfProperties.add("group/relations/relationX2/relation/sequence/absCommonDiff/1");
		InstructionCoderInterface instructionCoder = new InstructionCoderV2(nbOfComponents, relationXListOfProperties);
		InstructionCodeGetterInterface instructionCodeGetter = instructionCoder.getInstructionCodeGetter();
		for (TransformationCodeGetterInterface transformationCodeGetter : 
			instructionCodeGetter.getListOfTransformationCodeGetters()) {
			listOfReturnedCodes.addAll(transformationCodeGetter.getListOfPredicateCodes());
		}
		/* for (String code : listOfReturnedCodes) {
			System.out.println(code);
		} */
		assertTrue(listOfReturnedCodes.size() == 2 &&
				listOfReturnedCodes.get(0).equals("manySizeEquals") &&
				listOfReturnedCodes.get(1).equals("manyLetterIncrease"));
	}


}
