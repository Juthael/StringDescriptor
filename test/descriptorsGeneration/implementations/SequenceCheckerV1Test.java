package descriptorsGeneration.implementations;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import exceptions.DescriptorsBuilderCriticalException;
import settings.DescGenSettings;
import syntacticTreesGeneration.implementations.EnumerationCheckerV1;
import syntacticTreesGeneration.implementations.SequenceCheckerV1;
import syntacticTreesGeneration.interfaces.EnumerationCheckerInterface;
import syntacticTreesGeneration.interfaces.EnumerationRelationalDataInterface;
import syntacticTreesGeneration.interfaces.SequenceCheckerInterface;

class SequenceCheckerV1Test {
	
	private boolean wholeStringIsDescribed_true = true;
	private boolean wholeStringIsDescribed_false = false;
	private String dimension = "dontCare";
	private ArrayList<String> listOfSize1ValuesWithCommonDiff = new ArrayList<String>(Arrays.asList("1","2","3"));
	private ArrayList<String> listOfSize1ValuesWithNoCommonDiff = new ArrayList<String>(Arrays.asList("1","2","4"));
	private ArrayList<String> listOfValuesOfDifferentSizes = new ArrayList<String>(Arrays.asList("1","2,3","4,5,6"));
	private ArrayList<String> listOf2ndDegreeValuesWithCommonPatternAndDifference = 
			new ArrayList<String>(Arrays.asList("1,2","2,3","3,4"));
	private ArrayList<String> listOf2ndDegreeValuesWithNoCommonPatternButCommonDifference =	
			new ArrayList<String>(Arrays.asList("1,2","2,3","3,5"));
	private ArrayList<String> listOfIdenticalValues = new ArrayList<String>(Arrays.asList("1","1","1"));
	private ArrayList<String> listOfValuesWithIncrementTooBig = new ArrayList<String>(Arrays.asList(
			"0",Integer.toString(DescGenSettings.MAX_INCREMENT_ABS_VALUE + 1), 
			Integer.toString(2*(DescGenSettings.MAX_INCREMENT_ABS_VALUE + 1))));
	private EnumerationCheckerInterface enumerationChecker;
	private SequenceCheckerInterface sequenceChecker;

	@Test
	void ifValuesArentTheSameSizeThenNoSequenceFound() throws DescriptorsBuilderCriticalException {
		enumerationChecker = new EnumerationCheckerV1(wholeStringIsDescribed_true, dimension, listOfValuesOfDifferentSizes);
		EnumerationRelationalDataInterface enumerationRelationalData = enumerationChecker.getEnumerationRelationalData();
		sequenceChecker = 
				new SequenceCheckerV1(
						wholeStringIsDescribed_true, dimension, listOfValuesOfDifferentSizes, enumerationRelationalData);
		assertEquals(sequenceChecker.getSequenceWasFound(), false);
	}
	
	@Test
	void ifNoCommonDifferenceValueThenNoSequenceFound() throws DescriptorsBuilderCriticalException {
		enumerationChecker = new EnumerationCheckerV1(wholeStringIsDescribed_false, dimension, listOfSize1ValuesWithNoCommonDiff);
		EnumerationRelationalDataInterface enumerationRelationalData = enumerationChecker.getEnumerationRelationalData();
		sequenceChecker = 
				new SequenceCheckerV1(
						wholeStringIsDescribed_false, dimension, listOfSize1ValuesWithNoCommonDiff, enumerationRelationalData);
		assertEquals(sequenceChecker.getSequenceWasFound(), false);
	}
	
	@Test
	void ifNoCommonPatternBtw2ndDegreeValuesThenNoSequenceFound() throws DescriptorsBuilderCriticalException {
		enumerationChecker = 
				new EnumerationCheckerV1(wholeStringIsDescribed_true, dimension, listOf2ndDegreeValuesWithNoCommonPatternButCommonDifference);
		EnumerationRelationalDataInterface enumerationRelationalData = enumerationChecker.getEnumerationRelationalData();
		sequenceChecker = 
				new SequenceCheckerV1(
						wholeStringIsDescribed_true, dimension, 
						listOf2ndDegreeValuesWithNoCommonPatternButCommonDifference, enumerationRelationalData);
		assertEquals(sequenceChecker.getSequenceWasFound(), false);		
	}
	
	@Test
	void ifIncrementIsSuperiorToTheMaxValueInSettingsThenNoSequenceFound() throws DescriptorsBuilderCriticalException {
		enumerationChecker = 
				new EnumerationCheckerV1(wholeStringIsDescribed_false, dimension, listOfValuesWithIncrementTooBig);
		EnumerationRelationalDataInterface enumerationRelationalData = enumerationChecker.getEnumerationRelationalData();
		sequenceChecker = 
				new SequenceCheckerV1(
						wholeStringIsDescribed_false, dimension, listOfValuesWithIncrementTooBig, enumerationRelationalData);
		assertEquals(sequenceChecker.getSequenceWasFound(), false);	
	}
	
	@Test
	void ifValuesAreIdenticalThenSequenceIsFound() throws DescriptorsBuilderCriticalException {
		enumerationChecker = 
				new EnumerationCheckerV1(wholeStringIsDescribed_false, dimension, listOfIdenticalValues);
		EnumerationRelationalDataInterface enumerationRelationalData = enumerationChecker.getEnumerationRelationalData();
		sequenceChecker = 
				new SequenceCheckerV1(wholeStringIsDescribed_false, dimension, listOfIdenticalValues, enumerationRelationalData);
		assertEquals(sequenceChecker.getSequenceWasFound(), true);			
	}
	
	@Test
	void ifCommonDifferenceBtwSize1ValuesThenSequenceFound() throws DescriptorsBuilderCriticalException {
		enumerationChecker = 
				new EnumerationCheckerV1(wholeStringIsDescribed_false, dimension, listOfSize1ValuesWithCommonDiff);
		EnumerationRelationalDataInterface enumerationRelationalData = enumerationChecker.getEnumerationRelationalData();
		sequenceChecker = 
				new SequenceCheckerV1(wholeStringIsDescribed_false, dimension,
						listOfSize1ValuesWithCommonDiff, enumerationRelationalData);
		assertEquals(sequenceChecker.getSequenceWasFound(), true);			
	}
	
	@Test
	void ifCommonDifferenceBtw2ndDegreeValuesWithSamePatternThenSequenceFound() throws DescriptorsBuilderCriticalException {
		enumerationChecker = 
				new EnumerationCheckerV1(
						wholeStringIsDescribed_true, dimension, listOf2ndDegreeValuesWithCommonPatternAndDifference);
		EnumerationRelationalDataInterface enumerationRelationalData = enumerationChecker.getEnumerationRelationalData();
		sequenceChecker = 
				new SequenceCheckerV1(wholeStringIsDescribed_true, dimension,
						listOf2ndDegreeValuesWithCommonPatternAndDifference, enumerationRelationalData);
		assertEquals(sequenceChecker.getSequenceWasFound(), true);				
	}

}
