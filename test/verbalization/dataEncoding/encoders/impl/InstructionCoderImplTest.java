package verbalization.dataEncoding.encoders.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import exceptions.VerbalizationException;
import verbalization.dataEncoding.encoders.IInstructionCoder;
import verbalization.dataEncoding.encoders.impl.InstructionCoderImpl;
import verbalization.dataEncoding.encodingModel.IInstructionCodeGetter;
import verbalization.dataEncoding.encodingModel.ITransformationCodeGetter;

public class InstructionCoderImplTest {

	@Test
	public void whenRelationXContainsSingleRelationThenExpectedCodeIsReturned() throws VerbalizationException {
		List<String> listOfReturnedCodes = new ArrayList<String>();
		String nbOfComponents = "5"; 
		List<String> relationXListOfProperties = new ArrayList<String>();
		relationXListOfProperties.add("frame/relations/relation/dimension/size");
		relationXListOfProperties.add("frame/relations/relation/enumeration/1,1,1,1,1");
		relationXListOfProperties.add("frame/relations/relation/sequence/commonDiff/0");
		relationXListOfProperties.add("frame/relations/relation/sequence/absCommonDiff/0");
		IInstructionCoder instructionCoder = new InstructionCoderImpl(nbOfComponents, relationXListOfProperties);
		IInstructionCodeGetter instructionCodeGetter = instructionCoder.getInstructionCodeGetter();
		for (ITransformationCodeGetter transformationCodeGetter : 
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
		relationXListOfProperties.add("frame/relations/relationX2/relation/dimension/size");
		relationXListOfProperties.add("frame/relations/relationX2/relation/enumeration/1,1,1,1,1");
		relationXListOfProperties.add("frame/relations/relationX2/relation/sequence/commonDiff/0");
		relationXListOfProperties.add("frame/relations/relationX2/relation/sequence/absCommonDiff/0");
		relationXListOfProperties.add("frame/relations/relationX2/relation/dimension/letter.platonicLetter");
		relationXListOfProperties.add("frame/relations/relationX2/relation/enumeration/1,2,3,4,5");
		relationXListOfProperties.add("frame/relations/relationX2/relation/sequence/commonDiff/1");
		relationXListOfProperties.add("frame/relations/relationX2/relation/sequence/absCommonDiff/1");
		IInstructionCoder instructionCoder = new InstructionCoderImpl(nbOfComponents, relationXListOfProperties);
		IInstructionCodeGetter instructionCodeGetter = instructionCoder.getInstructionCodeGetter();
		for (ITransformationCodeGetter transformationCodeGetter : 
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
