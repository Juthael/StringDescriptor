package syntacticTreesGeneration.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import exceptions.SynTreeGenerationException;
import syntacticTreesGeneration.IEnumerationChecker;
import syntacticTreesGeneration.IEnumerationRelationalData;
import syntacticTreesGeneration.ISymmetryChecker;
import syntacticTreesGeneration.impl.EnumerationChecker;
import syntacticTreesGeneration.impl.SymmetryChecker;

public class SymmetryCheckerTest {
	
	private boolean wholeStringIsCovered_true = true;
	private boolean wholeStringIsCovered_false = false;
	private String dimensionEnumeration = "dontCare.enumeration";
	private String dimensionPlatonicLetter = "dontCare.platonicLetter";
	private String dimensionCommonDiff = "dontCare.commonDiff";
	private String illegalDimension = "dontCare";
	private List<String> listOfReversedValues = new ArrayList<String>(
			Arrays.asList("1,2,3","3,2,1"));
	private List<String> listWithNoSymmetry = new ArrayList<String>(
			Arrays.asList("1,2,3","3,2,2"));	
	private List<String> listOfReversedValuesWithLegalCentralElement = new ArrayList<String>(
			Arrays.asList("1,2,3","6,6","3,2,1"));	
	private List<String> listOfReversedValuesWithIllegalCentralElement = new ArrayList<String>(
			Arrays.asList("1,2,3","6,7","3,2,1"));	
	private List<String> listOfOppositeValues = new ArrayList<String>(
			Arrays.asList("-2","2"));
	private List<String> listOfOppositeValuesWith0AsCentralElement = new ArrayList<String>(
			Arrays.asList("-2", "0", "2"));
	private List<String> listOfOppositeValuesWithIllegalCentralElement = new ArrayList<String>(
			Arrays.asList("-2", "1", "2"));
	IEnumerationChecker enumerationChecker;
	IEnumerationRelationalData enumerationRelationalData;
	ISymmetryChecker symmetryChecker;
	

	@Test
	public void whenTheWholeStringIsntCoveredThenException() throws SynTreeGenerationException {
		try {
			enumerationChecker = 
					new EnumerationChecker(wholeStringIsCovered_false, dimensionEnumeration, listOfReversedValues);	
			enumerationRelationalData = enumerationChecker.getEnumerationRelationalData();
			symmetryChecker = 
					new SymmetryChecker(
							wholeStringIsCovered_false, dimensionEnumeration, listOfReversedValues, enumerationRelationalData);
			fail();
		}
		catch (SynTreeGenerationException expected) {
		}
	}
	
	@Test
	public void whenDimensionIsIllegalThenThrowsException() {
		try {
			enumerationChecker = 
					new EnumerationChecker(wholeStringIsCovered_true, illegalDimension, listOfReversedValues);
			enumerationRelationalData = enumerationChecker.getEnumerationRelationalData();
			symmetryChecker = 
					new SymmetryChecker(
							wholeStringIsCovered_true, illegalDimension, listOfReversedValues, enumerationRelationalData);
			fail();
		}
		catch (SynTreeGenerationException expected) {
		}
	}
	
	@Test
	public void whenNoSymmetryThenNoSymmetryFound() throws SynTreeGenerationException {
		enumerationChecker = 
				new EnumerationChecker(wholeStringIsCovered_true, dimensionEnumeration, listWithNoSymmetry);
		enumerationRelationalData = enumerationChecker.getEnumerationRelationalData();
		symmetryChecker = 
				new SymmetryChecker(
						wholeStringIsCovered_true, dimensionEnumeration, listWithNoSymmetry, enumerationRelationalData);
		assertEquals(symmetryChecker.getSymmetryWasFound(), false);		
	}
	
	@Test
	public void whenOrderIsReversedAndDimensionIsCommonDiffThenNoSymmetryFound() throws SynTreeGenerationException {
		enumerationChecker = 
				new EnumerationChecker(wholeStringIsCovered_true, dimensionCommonDiff, listOfReversedValues);
		enumerationRelationalData = enumerationChecker.getEnumerationRelationalData();
		symmetryChecker = 
				new SymmetryChecker(
						wholeStringIsCovered_true, dimensionCommonDiff, listOfReversedValues, enumerationRelationalData);
		assertEquals(symmetryChecker.getSymmetryWasFound(), false);			
	}
	
	@Test
	public void whenOrderIsReversedAndDimensionNotCommonDiffButLegalThenSymmetryFound() 
			throws SynTreeGenerationException {
		enumerationChecker = 
				new EnumerationChecker(wholeStringIsCovered_true, dimensionPlatonicLetter, listOfReversedValues);
		enumerationRelationalData = enumerationChecker.getEnumerationRelationalData();
		symmetryChecker = 
				new SymmetryChecker(
						wholeStringIsCovered_true, dimensionPlatonicLetter, listOfReversedValues, enumerationRelationalData);
		assertEquals(symmetryChecker.getSymmetryWasFound(), true);	
	}
	
	@Test
	public void whenOrderIsReversedWithLegalCentralElementAndDimensionNotCommonDiffButLegalThenSymmetryFound() 
			throws SynTreeGenerationException {
		enumerationChecker = 
				new EnumerationChecker(wholeStringIsCovered_true, dimensionEnumeration, 
						listOfReversedValuesWithLegalCentralElement);
		enumerationRelationalData = enumerationChecker.getEnumerationRelationalData();
		symmetryChecker = 
				new SymmetryChecker(
						wholeStringIsCovered_true, dimensionEnumeration, 
						listOfReversedValuesWithLegalCentralElement, enumerationRelationalData);
		assertEquals(symmetryChecker.getSymmetryWasFound(), true);	
	}
	
	@Test
	public void whenOrderIsReversedWithLegalCentralElementAndDimensionIsCommonDiffThenNoSymmetryFound() 
			throws SynTreeGenerationException {
		enumerationChecker = 
				new EnumerationChecker(wholeStringIsCovered_true, dimensionCommonDiff, 
						listOfReversedValuesWithLegalCentralElement);
		enumerationRelationalData = enumerationChecker.getEnumerationRelationalData();
		symmetryChecker = 
				new SymmetryChecker(
						wholeStringIsCovered_true, dimensionCommonDiff, 
						listOfReversedValuesWithLegalCentralElement, enumerationRelationalData);
		assertEquals(symmetryChecker.getSymmetryWasFound(), false);	
	}	
	
	
	@Test
	public void whenValuesAreOppositeAndDimensionIsCommonDiffThenSymmetryFound() 
			throws SynTreeGenerationException {
		enumerationChecker = 
				new EnumerationChecker(wholeStringIsCovered_true, dimensionCommonDiff, listOfOppositeValues);
		enumerationRelationalData = enumerationChecker.getEnumerationRelationalData();
		symmetryChecker = 
				new SymmetryChecker(
						wholeStringIsCovered_true, dimensionCommonDiff, listOfOppositeValues, enumerationRelationalData);
		assertEquals(symmetryChecker.getSymmetryWasFound(), true);	
	}	
	
	@Test
	public void whenValuesAreOppositeWith0AsCentralElementAndDimensionIsCommonDiffThenSymmetryFound() 
			throws SynTreeGenerationException {
		enumerationChecker = 
				new EnumerationChecker(wholeStringIsCovered_true, dimensionCommonDiff, 
						listOfOppositeValuesWith0AsCentralElement);
		enumerationRelationalData = enumerationChecker.getEnumerationRelationalData();
		symmetryChecker = 
				new SymmetryChecker(
						wholeStringIsCovered_true, dimensionCommonDiff, listOfOppositeValuesWith0AsCentralElement, 
						enumerationRelationalData);
		assertEquals(symmetryChecker.getSymmetryWasFound(), true);	
	}	
	
	@Test
	public void whenValuesAreOppositeWithCentralElementNot0AndDimensionIsCommonDiffThenNoSymmetryFound() 
			throws SynTreeGenerationException {
		enumerationChecker = 
				new EnumerationChecker(wholeStringIsCovered_true, dimensionCommonDiff, 
						listOfOppositeValuesWithIllegalCentralElement);
		enumerationRelationalData = enumerationChecker.getEnumerationRelationalData();
		symmetryChecker = 
				new SymmetryChecker(
						wholeStringIsCovered_true, dimensionCommonDiff, listOfOppositeValuesWithIllegalCentralElement, 
						enumerationRelationalData);
		assertEquals(symmetryChecker.getSymmetryWasFound(), false);	
	}	

}
