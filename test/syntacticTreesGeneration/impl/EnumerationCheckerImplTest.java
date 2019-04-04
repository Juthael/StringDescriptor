package descriptorsGeneration.implementations;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import exceptions.DescriptorsBuilderCriticalException;
import syntacticTreesGeneration.implementations.EnumerationCheckerV1;
import syntacticTreesGeneration.interfaces.EnumerationCheckerInterface;

public class EnumerationCheckerV1Test {

	private boolean wholeStringIsDescribed_true = true;
	private boolean wholeStringIsDescribed_false = false;
	private String dimension = "dontCare";
	private ArrayList<String> listOfSize1 = new ArrayList<String>(Arrays.asList("1"));
	private ArrayList<String> listOfSize1Values = new ArrayList<String>(Arrays.asList("1","2","3"));
	private ArrayList<String> listOfSizeMoreThan1Values = new ArrayList<String>(Arrays.asList("1","2,3","3,4,5"));
	private ArrayList<String> listOfSizeMoreThan1ValuesWithSymmetry = new ArrayList<String>(Arrays.asList("1,2,3","3,2,1"));
	private ArrayList<String> tooBigListOfSizeMoreThan1Values = 
			new ArrayList<String>(Arrays.asList("1,2","2,3","3,4","4,5","5,6"));
	
	@Test
	public void whenValuesArentSize1AndComponentsDoesntCoverWholeStringThenNoEnumerationFound() 
			throws DescriptorsBuilderCriticalException {
		EnumerationCheckerInterface enumerationChecker = 
				new EnumerationCheckerV1(wholeStringIsDescribed_false, dimension, listOfSizeMoreThan1Values);
		assertEquals(enumerationChecker.getSimpleEnumerationWasFound(), false);
	}
	
	@Test 
	public void whenValuesArentSize1AndTooManyComponentsThenNoEnumeration() throws DescriptorsBuilderCriticalException {
		EnumerationCheckerInterface enumerationChecker = 
				new EnumerationCheckerV1(wholeStringIsDescribed_true, dimension, tooBigListOfSizeMoreThan1Values);
		assertEquals(enumerationChecker.getSimpleEnumerationWasFound(), false);
	}
	
	@Test
	public void whenValuesAreSize1ThenEnumerationIsFound() throws DescriptorsBuilderCriticalException {
		EnumerationCheckerInterface enumerationChecker = 
				new EnumerationCheckerV1(wholeStringIsDescribed_false, dimension, listOfSize1Values);
		assertEquals(enumerationChecker.getSimpleEnumerationWasFound(), true);		
	}
	
	@Test 
	public void whenValuesArentSize1ButWholeStringCoveredAndNotTooManyComponentsAndSymmetryThenEnumIsFound() 
			throws DescriptorsBuilderCriticalException{
		EnumerationCheckerInterface enumerationChecker = 
				new EnumerationCheckerV1(wholeStringIsDescribed_true, dimension, 
						listOfSizeMoreThan1ValuesWithSymmetry);
		assertEquals(enumerationChecker.getSecondDegreeEnumerationWasFound(), true);			
	}
	
	@Test
	public void whenLessThan2ValuesToCheckThenThrowNoException() {
		try {
			EnumerationCheckerInterface enumerationChecker = 
					new EnumerationCheckerV1(wholeStringIsDescribed_false, dimension, listOfSize1);
		}
		catch (DescriptorsBuilderCriticalException expected) {
			fail();
		}
	}	

}
