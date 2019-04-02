package descriptorsVerbalization.model;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import exceptions.VerbalizationException;
import verbalization.implementations.InstructionCoderV2;
import verbalization.implementations.verbalStructureModel.InstructionV2;
import verbalization.interfaces.InstructionCoderInterface;
import verbalization.interfaces.dataEncodingModel.InstructionCodeGetterInterface;
import verbalization.interfaces.verbalStructureModel.InstructionInterface;

class InstructionTest {

	@Test
	void whenParameterIsThisRX2GroupCodeThenExpectedVerbalDescriptionIsGiven1() throws VerbalizationException {
		String nbOfComponents = "5"; 
		List<String> relationXListOfProperties = new ArrayList<String>();
		boolean descriptionIsUnexpected = false;
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
		InstructionInterface instruction = new InstructionV2(instructionCodeGetter);
		// System.out.println(instruction.getVerbalInstruction());
		if ((!instruction.getVerbalInstruction().contains("4")) ||
				!instruction.getVerbalInstruction().contains("increasing"))
			descriptionIsUnexpected = true;
		assertFalse(descriptionIsUnexpected);
	}
	
	@Test
	void whenParameterIsThisRX2GroupCodeThenExpectedVerbalDescriptionIsGiven2() throws VerbalizationException {
		boolean descriptionIsUnexpected = false;
		String nbOfComponents = "5"; 
		List<String> relationXListOfProperties = new ArrayList<String>();
		relationXListOfProperties.add("group/relations/relationX2/relation/dimension/size");
		relationXListOfProperties.add("group/relations/relationX2/relation/enumeration/1");
		relationXListOfProperties.add("group/relations/relationX2/relation/sequence/commonDiff/2");
		relationXListOfProperties.add("group/relations/relationX2/relation/sequence/absCommonDiff/2");
		relationXListOfProperties.add("group/relations/relationX2/relation/dimension/:letter.platonicLetter");
		relationXListOfProperties.add("group/relations/relationX2/relation/enumeration/1");
		relationXListOfProperties.add("group/relations/relationX2/relation/sequence/commonDiff/-1");
		relationXListOfProperties.add("group/relations/relationX2/relation/sequence/absCommonDiff/1");
		InstructionCoderInterface instructionCoder = new InstructionCoderV2(nbOfComponents, relationXListOfProperties);
		InstructionCodeGetterInterface instructionCodeGetter = instructionCoder.getInstructionCodeGetter();
		InstructionInterface instruction = new InstructionV2(instructionCodeGetter);
		//System.out.println(instruction.getVerbalInstruction());
		if ((!instruction.getVerbalInstruction().contains("4")) ||
				!instruction.getVerbalInstruction().contains("increasing") ||
				!instruction.getVerbalInstruction().contains("reducing"))
			descriptionIsUnexpected = true;
		assertFalse(descriptionIsUnexpected);		
	}
	
	@Test
	void whenParameterIsThisRX3GroupCodeThenExpectedVerbalDescriptionIsGiven() throws VerbalizationException {
		boolean descriptionIsUnexpected = false;
		String nbOfComponents = "5"; 
		List<String> relationXListOfProperties = new ArrayList<String>();
		relationXListOfProperties.add("group/relations/relationX3/relation/dimension/size");
		relationXListOfProperties.add("group/relations/relationX3/relation/enumeration/1");
		relationXListOfProperties.add("group/relations/relationX3/relation/sequence/commonDiff/2");
		relationXListOfProperties.add("group/relations/relationX3/relation/sequence/absCommonDiff/2");
		relationXListOfProperties.add("group/relations/relationX3/relation/dimension/:letter.platonicLetter");
		relationXListOfProperties.add("group/relations/relationX3/relation/enumeration/1");
		relationXListOfProperties.add("group/relations/relationX3/relation/sequence/commonDiff/-1");
		relationXListOfProperties.add("group/relations/relationX3/relation/sequence/absCommonDiff/1");
		relationXListOfProperties.add("group/relations/relationX3/relation/dimension/commonDiff");
		relationXListOfProperties.add("group/relations/relationX3/relation/enumeration/1");
		relationXListOfProperties.add("group/relations/relationX3/relation/sequence/commonDiff/-2");
		relationXListOfProperties.add("group/relations/relationX3/relation/sequence/absCommonDiff/2");
		InstructionCoderInterface instructionCoder = new InstructionCoderV2(nbOfComponents, relationXListOfProperties);
		InstructionCodeGetterInterface instructionCodeGetter = instructionCoder.getInstructionCodeGetter();
		InstructionInterface instruction = new InstructionV2(instructionCodeGetter);
		//System.out.println(instruction.getVerbalInstruction());
		if (instruction.getVerbalInstruction().contains("4")) {
			int firstReductionIndex = instruction.getVerbalInstruction().indexOf("reducing");
			int secondReductionIndex = instruction.getVerbalInstruction().lastIndexOf("reducing");
			if (firstReductionIndex == secondReductionIndex)
				descriptionIsUnexpected = true;
		}
		else descriptionIsUnexpected = true;
		assertFalse(descriptionIsUnexpected);	
	}	
	
	@Test
	void whenParameterIsA2ndDegreeEnumGroupCodeThenExpectedVerbalDescriptionIsGiven() throws VerbalizationException {
		boolean descriptionIsUnexpected = false;
		String nbOfComponents = "5"; 
		List<String> relationXListOfProperties = new ArrayList<String>();
		relationXListOfProperties.add("group/relations/relation/dimension/letter.platonicLetter");
		relationXListOfProperties.add("group/relations/relation/enumeration/1,5,2");
		InstructionCoderInterface instructionCoder = new InstructionCoderV2(nbOfComponents, relationXListOfProperties);
		InstructionCodeGetterInterface instructionCodeGetter = instructionCoder.getInstructionCodeGetter();
		InstructionInterface instruction = new InstructionV2(instructionCodeGetter);
		//System.out.println(instruction.getVerbalInstruction());
		if (!instruction.getVerbalInstruction().contains("e and b")) 
			descriptionIsUnexpected = true;
		assertFalse(descriptionIsUnexpected);	
	}		
	
	@Test
	void whenParameterIsASize5ConstantSequenceGroupCodeThenExpectedVerbalDescriptionIsGiven() throws VerbalizationException {
		boolean descriptionIsUnexpected = false;
		String nbOfComponents = "5"; 
		List<String> relationXListOfProperties = new ArrayList<String>();
		relationXListOfProperties.add("group/relations/relation/dimension/letter.platonicLetter");
		relationXListOfProperties.add("group/relations/relation/enumeration/0");
		relationXListOfProperties.add("group/relations/relation/sequence/commonDiff/0");
		relationXListOfProperties.add("group/relations/relation/sequence/absCommonDiff/0");
		InstructionCoderInterface instructionCoder = new InstructionCoderV2(nbOfComponents, relationXListOfProperties);
		InstructionCodeGetterInterface instructionCodeGetter = instructionCoder.getInstructionCodeGetter();
		InstructionInterface instruction = new InstructionV2(instructionCodeGetter);
		//System.out.println(instruction.getVerbalInstruction());
		if (!instruction.getVerbalInstruction().equals("repeat 4 times ;"))
			descriptionIsUnexpected = true;
		assertFalse(descriptionIsUnexpected);	
	}	
	
	@Test
	void whenParameterIsASize2ConstantSequenceGroupCodeThenExpectedVerbalDescriptionIsGiven() throws VerbalizationException {
		boolean descriptionIsUnexpected = false;
		String nbOfComponents = "2"; 
		List<String> relationXListOfProperties = new ArrayList<String>();
		relationXListOfProperties.add("group/relations/relation/dimension/letter.platonicLetter");
		relationXListOfProperties.add("group/relations/relation/enumeration/0");
		relationXListOfProperties.add("group/relations/relation/sequence/commonDiff/0");
		relationXListOfProperties.add("group/relations/relation/sequence/absCommonDiff/0");
		InstructionCoderInterface instructionCoder = new InstructionCoderV2(nbOfComponents, relationXListOfProperties);
		InstructionCodeGetterInterface instructionCodeGetter = instructionCoder.getInstructionCodeGetter();
		InstructionInterface instruction = new InstructionV2(instructionCodeGetter);
		//System.out.println(instruction.getVerbalInstruction());
		if (!instruction.getVerbalInstruction().equals("repeat ;"))
			descriptionIsUnexpected = true;
		assertFalse(descriptionIsUnexpected);	
	}
	
	@Test
	void whenParameterIsAGen2Size1RelationGroupCodeThenExpectedVerbalDescriptionIsGiven() throws VerbalizationException {
		boolean descriptionIsUnexpected = false;
		String nbOfComponents = "1"; 
		List<String> relationXListOfProperties = new ArrayList<String>();
		relationXListOfProperties.add("group/relations/relation/dimension/letter.platonicLetter");
		relationXListOfProperties.add("group/relations/relation/enumeration/1");
		relationXListOfProperties.add("group/relations/relation/sequence/commonDiff/1");
		relationXListOfProperties.add("group/relations/relation/sequence/absCommonDiff/1");
		InstructionCoderInterface instructionCoder = new InstructionCoderV2(nbOfComponents, relationXListOfProperties);
		InstructionCodeGetterInterface instructionCodeGetter = instructionCoder.getInstructionCodeGetter();
		InstructionInterface instruction = new InstructionV2(instructionCodeGetter);
		//System.out.println(instruction.getVerbalInstruction());
		if (!instruction.getVerbalInstruction().contains("consider it as a group"))
			descriptionIsUnexpected = true;
		assertFalse(descriptionIsUnexpected);
	}	
	
	@Test
	void whenParameterIsASignalElementGroupCodeThenExpectedVerbalDescriptionIsGiven() throws VerbalizationException {
		boolean descriptionIsUnexpected = false;
		String nbOfComponents = "0"; 
		List<String> relationXListOfProperties = new ArrayList<String>();
		relationXListOfProperties.add("group/size/1");
		relationXListOfProperties.add("group/position/1");
		relationXListOfProperties.add("group/letter/position/2");
		relationXListOfProperties.add("group/letter/platonicLetter/2");
		InstructionCoderInterface instructionCoder = new InstructionCoderV2(nbOfComponents, relationXListOfProperties);
		InstructionCodeGetterInterface instructionCodeGetter = instructionCoder.getInstructionCodeGetter();
		InstructionInterface instruction = new InstructionV2(instructionCodeGetter);
		//System.out.println(instruction.getVerbalInstruction());
		if (!instruction.getVerbalInstruction().contains("write letter"))
			descriptionIsUnexpected = true;
		assertFalse(descriptionIsUnexpected);
	}		

}
