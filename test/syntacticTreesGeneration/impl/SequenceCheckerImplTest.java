package syntacticTreesGeneration.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import exceptions.DescriptorsBuilderException;
import settings.Settings;
import syntacticTreesGeneration.IEnumerationChecker;
import syntacticTreesGeneration.IEnumerationRelationalData;
import syntacticTreesGeneration.ISequenceChecker;
import syntacticTreesGeneration.impl.EnumerationCheckerImpl;
import syntacticTreesGeneration.impl.SequenceCheckerImpl;

public class SequenceCheckerImplTest {
	
	private boolean wholeStringIsDescribed_true = true;
	private boolean wholeStringIsDescribed_false = false;
	private String dimension = "dontCare";
	private List<String> listOfSize1ValuesWithCommonDiff = new ArrayList<String>(Arrays.asList("1","2","3"));
	private List<String> listOfSize1ValuesWithNoCommonDiff = new ArrayList<String>(Arrays.asList("1","2","4"));
	private List<String> listOfValuesOfDifferentSizes = new ArrayList<String>(Arrays.asList("1","2,3","4,5,6"));
	private List<String> listOf2ndDegreeValuesWithCommonPatternAndDifference = 
			new ArrayList<String>(Arrays.asList("1,2","2,3","3,4"));
	private List<String> listOf2ndDegreeValuesWithNoCommonPatternButCommonDifference =	
			new ArrayList<String>(Arrays.asList("1,2","2,3","3,5"));
	private List<String> listOfIdenticalValues = new ArrayList<String>(Arrays.asList("1","1","1"));
	private List<String> listOfValuesWithIncrementTooBig = new ArrayList<String>(Arrays.asList(
			"0",Integer.toString(Settings.MAX_INCREMENT_ABS_VALUE + 1), 
			Integer.toString(2*(Settings.MAX_INCREMENT_ABS_VALUE + 1))));
	private IEnumerationChecker enumerationChecker;
	private ISequenceChecker sequenceChecker;

	@Test
	public void ifValuesArentTheSameSizeThenNoSequenceFound() throws DescriptorsBuilderException {
		enumerationChecker = new EnumerationCheckerImpl(wholeStringIsDescribed_true, dimension, listOfValuesOfDifferentSizes);
		IEnumerationRelationalData enumerationRelationalData = enumerationChecker.getEnumerationRelationalData();
		sequenceChecker = 
				new SequenceCheckerImpl(
						wholeStringIsDescribed_true, dimension, listOfValuesOfDifferentSizes, enumerationRelationalData);
		assertEquals(sequenceChecker.getSequenceWasFound(), false);
	}
	
	@Test
	public void ifNoCommonDifferenceValueThenNoSequenceFound() throws DescriptorsBuilderException {
		enumerationChecker = new EnumerationCheckerImpl(wholeStringIsDescribed_false, dimension, listOfSize1ValuesWithNoCommonDiff);
		IEnumerationRelationalData enumerationRelationalData = enumerationChecker.getEnumerationRelationalData();
		sequenceChecker = 
				new SequenceCheckerImpl(
						wholeStringIsDescribed_false, dimension, listOfSize1ValuesWithNoCommonDiff, enumerationRelationalData);
		assertEquals(sequenceChecker.getSequenceWasFound(), false);
	}
	
	@Test
	public void ifNoCommonPatternBtw2ndDegreeValuesThenNoSequenceFound() throws DescriptorsBuilderException {
		enumerationChecker = 
				new EnumerationCheckerImpl(wholeStringIsDescribed_true, dimension, listOf2ndDegreeValuesWithNoCommonPatternButCommonDifference);
		IEnumerationRelationalData enumerationRelationalData = enumerationChecker.getEnumerationRelationalData();
		sequenceChecker = 
				new SequenceCheckerImpl(
						wholeStringIsDescribed_true, dimension, 
						listOf2ndDegreeValuesWithNoCommonPatternButCommonDifference, enumerationRelationalData);
		assertEquals(sequenceChecker.getSequenceWasFound(), false);		
	}
	
	@Test
	public void ifIncrementIsSuperiorToTheMaxValueInSettingsThenNoSequenceFound() throws DescriptorsBuilderException {
		enumerationChecker = 
				new EnumerationCheckerImpl(wholeStringIsDescribed_false, dimension, listOfValuesWithIncrementTooBig);
		IEnumerationRelationalData enumerationRelationalData = enumerationChecker.getEnumerationRelationalData();
		sequenceChecker = 
				new SequenceCheckerImpl(
						wholeStringIsDescribed_false, dimension, listOfValuesWithIncrementTooBig, enumerationRelationalData);
		assertEquals(sequenceChecker.getSequenceWasFound(), false);	
	}
	
	@Test
	public void ifValuesAreIdenticalThenSequenceIsFound() throws DescriptorsBuilderException {
		enumerationChecker = 
				new EnumerationCheckerImpl(wholeStringIsDescribed_false, dimension, listOfIdenticalValues);
		IEnumerationRelationalData enumerationRelationalData = enumerationChecker.getEnumerationRelationalData();
		sequenceChecker = 
				new SequenceCheckerImpl(wholeStringIsDescribed_false, dimension, listOfIdenticalValues, enumerationRelationalData);
		assertEquals(sequenceChecker.getSequenceWasFound(), true);			
	}
	
	@Test
	public void ifCommonDifferenceBtwSize1ValuesThenSequenceFound() throws DescriptorsBuilderException {
		enumerationChecker = 
				new EnumerationCheckerImpl(wholeStringIsDescribed_false, dimension, listOfSize1ValuesWithCommonDiff);
		IEnumerationRelationalData enumerationRelationalData = enumerationChecker.getEnumerationRelationalData();
		sequenceChecker = 
				new SequenceCheckerImpl(wholeStringIsDescribed_false, dimension,
						listOfSize1ValuesWithCommonDiff, enumerationRelationalData);
		assertEquals(sequenceChecker.getSequenceWasFound(), true);			
	}
	
	@Test
	public void ifCommonDifferenceBtw2ndDegreeValuesWithSamePatternThenSequenceFound() throws DescriptorsBuilderException {
		enumerationChecker = 
				new EnumerationCheckerImpl(
						wholeStringIsDescribed_true, dimension, listOf2ndDegreeValuesWithCommonPatternAndDifference);
		IEnumerationRelationalData enumerationRelationalData = enumerationChecker.getEnumerationRelationalData();
		sequenceChecker = 
				new SequenceCheckerImpl(wholeStringIsDescribed_true, dimension,
						listOf2ndDegreeValuesWithCommonPatternAndDifference, enumerationRelationalData);
		assertEquals(sequenceChecker.getSequenceWasFound(), true);				
	}

}
