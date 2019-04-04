package descriptorsGeneration.implementations;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import exceptions.DescriptorsBuilderCriticalException;
import syntacticTreesGeneration.implementations.EnumerationCheckerV1;
import syntacticTreesGeneration.implementations.SymmetryCheckerV1;
import syntacticTreesGeneration.interfaces.EnumerationCheckerInterface;
import syntacticTreesGeneration.interfaces.EnumerationRelationalDataInterface;
import syntacticTreesGeneration.interfaces.SymmetryCheckerInterface;

class SymmetryCheckerV1Test {
	
	private boolean wholeStringIsCovered_true = true;
	private boolean wholeStringIsCovered_false = false;
	private String dimensionEnumeration = "dontCare/enumeration";
	private String dimensionPlatonicLetter = "dontCare/platonicLetter";
	private String dimensionCommonDiff = "dontCare/commonDiff";
	private String illegalDimension = "dontCare";
	private ArrayList<String> listOfReversedValues = new ArrayList<String>(
			Arrays.asList("1,2,3","3,2,1"));
	private ArrayList<String> listWithNoSymmetry = new ArrayList<String>(
			Arrays.asList("1,2,3","3,2,2"));	
	private ArrayList<String> listOfReversedValuesWithLegalCentralElement = new ArrayList<String>(
			Arrays.asList("1,2,3","6,6","3,2,1"));	
	private ArrayList<String> listOfReversedValuesWithIllegalCentralElement = new ArrayList<String>(
			Arrays.asList("1,2,3","6,7","3,2,1"));	
	private ArrayList<String> listOfOppositeValues = new ArrayList<String>(
			Arrays.asList("-2","2"));
	private ArrayList<String> listOfOppositeValuesWith0AsCentralElement = new ArrayList<String>(
			Arrays.asList("-2", "0", "2"));
	private ArrayList<String> listOfOppositeValuesWithIllegalCentralElement = new ArrayList<String>(
			Arrays.asList("-2", "1", "2"));
	EnumerationCheckerInterface enumerationChecker;
	EnumerationRelationalDataInterface enumerationRelationalData;
	SymmetryCheckerInterface symmetryChecker;
	

	@Test
	void whenTheWholeStringIsntCoveredThenException() throws DescriptorsBuilderCriticalException {
		try {
			enumerationChecker = 
					new EnumerationCheckerV1(wholeStringIsCovered_false, dimensionEnumeration, listOfReversedValues);	
			enumerationRelationalData = enumerationChecker.getEnumerationRelationalData();
			symmetryChecker = 
					new SymmetryCheckerV1(
							wholeStringIsCovered_false, dimensionEnumeration, listOfReversedValues, enumerationRelationalData);
			fail();
		}
		catch (DescriptorsBuilderCriticalException expected) {
		}
	}
	
	@Test
	void whenDimensionIsIllegalThenThrowsException() {
		try {
			enumerationChecker = 
					new EnumerationCheckerV1(wholeStringIsCovered_true, illegalDimension, listOfReversedValues);
			enumerationRelationalData = enumerationChecker.getEnumerationRelationalData();
			symmetryChecker = 
					new SymmetryCheckerV1(
							wholeStringIsCovered_true, illegalDimension, listOfReversedValues, enumerationRelationalData);
			fail();
		}
		catch (DescriptorsBuilderCriticalException expected) {
		}
	}
	
	@Test
	void whenNoSymmetryThenNoSymmetryFound() throws DescriptorsBuilderCriticalException {
		enumerationChecker = 
				new EnumerationCheckerV1(wholeStringIsCovered_true, dimensionEnumeration, listWithNoSymmetry);
		enumerationRelationalData = enumerationChecker.getEnumerationRelationalData();
		symmetryChecker = 
				new SymmetryCheckerV1(
						wholeStringIsCovered_true, dimensionEnumeration, listWithNoSymmetry, enumerationRelationalData);
		assertEquals(symmetryChecker.getSymmetryWasFound(), false);		
	}
	
	@Test
	void whenOrderIsReversedAndDimensionIsCommonDiffThenNoSymmetryFound() throws DescriptorsBuilderCriticalException {
		enumerationChecker = 
				new EnumerationCheckerV1(wholeStringIsCovered_true, dimensionCommonDiff, listOfReversedValues);
		enumerationRelationalData = enumerationChecker.getEnumerationRelationalData();
		symmetryChecker = 
				new SymmetryCheckerV1(
						wholeStringIsCovered_true, dimensionCommonDiff, listOfReversedValues, enumerationRelationalData);
		assertEquals(symmetryChecker.getSymmetryWasFound(), false);			
	}
	
	@Test
	void whenOrderIsReversedAndDimensionNotCommonDiffButLegalThenSymmetryFound() 
			throws DescriptorsBuilderCriticalException {
		enumerationChecker = 
				new EnumerationCheckerV1(wholeStringIsCovered_true, dimensionPlatonicLetter, listOfReversedValues);
		enumerationRelationalData = enumerationChecker.getEnumerationRelationalData();
		symmetryChecker = 
				new SymmetryCheckerV1(
						wholeStringIsCovered_true, dimensionPlatonicLetter, listOfReversedValues, enumerationRelationalData);
		assertEquals(symmetryChecker.getSymmetryWasFound(), true);	
	}
	
	@Test
	void whenOrderIsReversedWithLegalCentralElementAndDimensionNotCommonDiffButLegalThenSymmetryFound() 
			throws DescriptorsBuilderCriticalException {
		enumerationChecker = 
				new EnumerationCheckerV1(wholeStringIsCovered_true, dimensionEnumeration, 
						listOfReversedValuesWithLegalCentralElement);
		enumerationRelationalData = enumerationChecker.getEnumerationRelationalData();
		symmetryChecker = 
				new SymmetryCheckerV1(
						wholeStringIsCovered_true, dimensionEnumeration, 
						listOfReversedValuesWithLegalCentralElement, enumerationRelationalData);
		assertEquals(symmetryChecker.getSymmetryWasFound(), true);	
	}
	
	@Test
	void whenOrderIsReversedWithLegalCentralElementAndDimensionIsCommonDiffThenNoSymmetryFound() 
			throws DescriptorsBuilderCriticalException {
		enumerationChecker = 
				new EnumerationCheckerV1(wholeStringIsCovered_true, dimensionCommonDiff, 
						listOfReversedValuesWithLegalCentralElement);
		enumerationRelationalData = enumerationChecker.getEnumerationRelationalData();
		symmetryChecker = 
				new SymmetryCheckerV1(
						wholeStringIsCovered_true, dimensionCommonDiff, 
						listOfReversedValuesWithLegalCentralElement, enumerationRelationalData);
		assertEquals(symmetryChecker.getSymmetryWasFound(), false);	
	}	
	
	
	@Test
	void whenValuesAreOppositeAndDimensionIsCommonDiffThenSymmetryFound() 
			throws DescriptorsBuilderCriticalException {
		enumerationChecker = 
				new EnumerationCheckerV1(wholeStringIsCovered_true, dimensionCommonDiff, listOfOppositeValues);
		enumerationRelationalData = enumerationChecker.getEnumerationRelationalData();
		symmetryChecker = 
				new SymmetryCheckerV1(
						wholeStringIsCovered_true, dimensionCommonDiff, listOfOppositeValues, enumerationRelationalData);
		assertEquals(symmetryChecker.getSymmetryWasFound(), true);	
	}	
	
	@Test
	void whenValuesAreOppositeWith0AsCentralElementAndDimensionIsCommonDiffThenSymmetryFound() 
			throws DescriptorsBuilderCriticalException {
		enumerationChecker = 
				new EnumerationCheckerV1(wholeStringIsCovered_true, dimensionCommonDiff, 
						listOfOppositeValuesWith0AsCentralElement);
		enumerationRelationalData = enumerationChecker.getEnumerationRelationalData();
		symmetryChecker = 
				new SymmetryCheckerV1(
						wholeStringIsCovered_true, dimensionCommonDiff, listOfOppositeValuesWith0AsCentralElement, 
						enumerationRelationalData);
		assertEquals(symmetryChecker.getSymmetryWasFound(), true);	
	}	
	
	@Test
	void whenValuesAreOppositeWithCentralElementNot0AndDimensionIsCommonDiffThenNoSymmetryFound() 
			throws DescriptorsBuilderCriticalException {
		enumerationChecker = 
				new EnumerationCheckerV1(wholeStringIsCovered_true, dimensionCommonDiff, 
						listOfOppositeValuesWithIllegalCentralElement);
		enumerationRelationalData = enumerationChecker.getEnumerationRelationalData();
		symmetryChecker = 
				new SymmetryCheckerV1(
						wholeStringIsCovered_true, dimensionCommonDiff, listOfOppositeValuesWithIllegalCentralElement, 
						enumerationRelationalData);
		assertEquals(symmetryChecker.getSymmetryWasFound(), false);	
	}	

}
