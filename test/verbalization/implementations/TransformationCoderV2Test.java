package verbalization.implementations;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import verbalization.interfaces.TransformationCoderInterface;

class TransformationCoderV2Test {

	@Test
	void whenParameterIsASequenceRelationThenExpectedPredicateCodeIsReturned() {
		String nbOfComponents = "5"; 
		List<String> relationListOfProperties = new ArrayList<String>();
		relationListOfProperties.add("group/relations/relationX2/relation/dimension/letter.platonicLetter");
		relationListOfProperties.add("group/relations/relationX2/relation/enumeration/1,2,3,4,5");
		relationListOfProperties.add("group/relations/relationX2/relation/sequence/commonDiff/1");
		relationListOfProperties.add("group/relations/relationX2/relation/sequence/absCommonDiff/1");
		try {
			TransformationCoderInterface transformationCoder = new TransformationCoderV2(nbOfComponents, relationListOfProperties);
			List<String> listOfPredicateCodes = transformationCoder.getTransformationCodeGetter().getListOfPredicateCodes();
			/* for (String code : listOfPredicateCodes) {
				System.out.println(code);
			}
			System.out.println(""); */
			if (listOfPredicateCodes.size() == 1) {
				assertTrue(listOfPredicateCodes.get(0).equals("manyLetterIncrease"));
			}
			else throw new Exception();
		}
		catch (Exception unexpected) {
			fail();
		}
	}
	
	@Test
	void whenParameterIsASequenceSubRelationThenExpectedPredicateCodeIsReturned() {
		String nbOfComponents = "5"; 
		List<String> relationListOfProperties = new ArrayList<String>();
		relationListOfProperties.add("group/relations/relationX2/relation/dimension/:letter.platonicLetter");
		relationListOfProperties.add("group/relations/relationX2/relation/enumeration/1,2,3,4,5");
		relationListOfProperties.add("group/relations/relationX2/relation/sequence/commonDiff/1");
		relationListOfProperties.add("group/relations/relationX2/relation/sequence/absCommonDiff/1");
		try {
			TransformationCoderInterface transformationCoder = 
					new TransformationCoderV2(nbOfComponents, relationListOfProperties);
			List<String> listOfPredicateCodes = transformationCoder.getTransformationCodeGetter().getListOfPredicateCodes();
			/* for (String code : listOfPredicateCodes) {
				System.out.println(code);
			}
			System.out.println(""); */
			if (listOfPredicateCodes.size() == 1) {
				assertTrue(listOfPredicateCodes.get(0).equals("manySubLetterIncrease"));
			}
			else throw new Exception();
		}
		catch (Exception unexpected) {
			fail();
		}		
	}
	
	@Test
	void whenParameterIsAConstantSequenceThenExpectedPredicateCodeIsReturned() {
		String nbOfComponents = "5"; 
		List<String> relationListOfProperties = new ArrayList<String>();
		relationListOfProperties.add("group/relations/relationX2/relation/dimension/size");
		relationListOfProperties.add("group/relations/relationX2/relation/enumeration/1,1,1,1,1");
		relationListOfProperties.add("group/relations/relationX2/relation/sequence/commonDiff/0");
		relationListOfProperties.add("group/relations/relationX2/relation/sequence/absCommonDiff/0");
		try {
			TransformationCoderInterface transformationCoder = 
					new TransformationCoderV2(nbOfComponents, relationListOfProperties);
			List<String> listOfPredicateCodes = transformationCoder.getTransformationCodeGetter().getListOfPredicateCodes();
			/* for (String code : listOfPredicateCodes) {
				System.out.println(code);
			}
			System.out.println(""); */
			if (listOfPredicateCodes.size() == 1) {
				assertTrue(listOfPredicateCodes.get(0).equals("manySizeEquals"));
			}
			else throw new Exception();
		}
		catch (Exception unexpected) {
			fail();
		}		
	}
	
	@Test
	void whenParameterIsAGen2Size1RelationThenExpectedPredicateCodeIsReturned() {
		String nbOfComponents = "1"; 
		List<String> relationListOfProperties = new ArrayList<String>();
		relationListOfProperties.add("group/relations/relationX2/relation/dimension/letter.platonicLetter");
		relationListOfProperties.add("group/relations/relationX2/relation/enumeration/1");
		relationListOfProperties.add("group/relations/relationX2/relation/sequence/commonDiff/0");
		relationListOfProperties.add("group/relations/relationX2/relation/sequence/absCommonDiff/0");
		try {
			TransformationCoderInterface transformationCoder = 
					new TransformationCoderV2(nbOfComponents, relationListOfProperties);
			List<String> listOfPredicateCodes = transformationCoder.getTransformationCodeGetter().getListOfPredicateCodes();
			/* for (String code : listOfPredicateCodes) {
				System.out.println(code);
			}
			System.out.println(""); */
			if (listOfPredicateCodes.size() == 1) {
				assertTrue(listOfPredicateCodes.get(0).equals("oneLetterEquals"));
			}
			else throw new Exception();
		}
		catch (Exception unexpected) {
			fail();
		}	
	}	
	
	@Test
	void whenParameterIsAGen1RelationThenExpectPredicateCodeIsReturned() {
		String nbOfComponents = "0"; 
		List<String> relationListOfProperties = new ArrayList<String>();
		relationListOfProperties.add("group/size/1");
		relationListOfProperties.add("group/position/1");
		relationListOfProperties.add("group/letter/position/1");
		relationListOfProperties.add("group/letter/platonicLetter/1");
		try {
			TransformationCoderInterface transformationCoder = 
					new TransformationCoderV2(nbOfComponents, relationListOfProperties);
			List<String> listOfPredicateCodes = transformationCoder.getTransformationCodeGetter().getListOfPredicateCodes();
			/* for (String code : listOfPredicateCodes) {
				System.out.println(code);
			}
			System.out.println(""); */
			if (listOfPredicateCodes.size() == 1) {
				assertTrue(listOfPredicateCodes.get(0).equals("WriteLetterEffector"));
			}
			else throw new Exception();
		}
		catch (Exception unexpected) {
			fail();
		}		
	}
	
	@Test
	void whenParameterContainsManyDimensionsThenExpectedPredicateCodesAreReturned() {
		String nbOfComponents = "3"; 
		List<String> relationListOfProperties = new ArrayList<String>();
		relationListOfProperties.add("group/relations/relationX2/relation/dimension/size");
		relationListOfProperties.add("group/relations/relationX2/relation/dimension/:letter.platonicLetter");
		relationListOfProperties.add("group/relations/relationX2/relation/enumeration/1,4,3");
		try {
			TransformationCoderInterface transformationCoder = 
					new TransformationCoderV2(nbOfComponents, relationListOfProperties);
			List<String> listOfPredicateCodes = transformationCoder.getTransformationCodeGetter().getListOfPredicateCodes();
			/* for (String code : listOfPredicateCodes) {
				System.out.println(code);
			}
			System.out.println(""); */
			if (listOfPredicateCodes.size() == 2) {
				assertTrue(listOfPredicateCodes.get(0).equals("manySizeEnumerate") &&
						listOfPredicateCodes.get(1).equals("manySubLetterEnumerate"));
			}
			else throw new Exception();
		}
		catch (Exception unexpected) {
			fail();
		}
	}	
	
	
	
	

}
