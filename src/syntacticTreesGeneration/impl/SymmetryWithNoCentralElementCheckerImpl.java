package syntacticTreesGeneration.impl;

import java.util.List;

import exceptions.SynTreeGenerationException;
import syntacticTreesGeneration.IEnumerationRelationalData;
import syntacticTreesGeneration.ISymmetryRelationalData;
import syntacticTreesGeneration.ISymmetryWithNoCentralElementChecker;

public class SymmetryWithNoCentralElementCheckerImpl implements ISymmetryWithNoCentralElementChecker {
	
	
	private final boolean wholeStringIsDescribed;
	private final String dimension;
	private final List<String> values;
	private final IEnumerationRelationalData enumerationRelationalData;
	private boolean symmetryWithNoCenterWasFound;
	private String typeOfSymmetry = "withoutCentralElement";

	public SymmetryWithNoCentralElementCheckerImpl(boolean wholeStringIsDescribed, String dimension, List<String> values, 
			IEnumerationRelationalData enumerationRelationalData) throws SynTreeGenerationException {
		this.wholeStringIsDescribed = wholeStringIsDescribed;
		this.dimension = dimension;
		this.values = values;
		this.enumerationRelationalData = enumerationRelationalData;
		symmetryWithNoCenterWasFound = false;
		if (this.wholeStringIsDescribed == true) {
			boolean thereAreTwoValuesOfIdenticalSize = testIfThereAreTwoValuesOfIdenticalSize();
			if (thereAreTwoValuesOfIdenticalSize == true) {
				String dimensionLastValue = getDimensionLastValue();
				if (dimensionLastValue.equals("enumeration") || dimensionLastValue.equals("size") || 
						dimensionLastValue.equals("platonicLetter")) {
					symmetryWithNoCenterWasFound = testIfEnumerationValuesOrderIsReversed();
				}
				else if (dimensionLastValue.equals("commonDiff")) {
					symmetryWithNoCenterWasFound = testIfCommonDiffValuesAreOpposite();
				}
				else throw new SynTreeGenerationException(
						"SymmetryWithNoCentralElementChecker : unexpected dimension");
			}
		}
	}
	
	@Override
	public boolean getSymmetryWithNoCenterWasFound() {
		return symmetryWithNoCenterWasFound;
	}
	
	@Override
	public ISymmetryRelationalData getSymmetryRelationalData() throws SynTreeGenerationException {
		if (symmetryWithNoCenterWasFound == true) {
			String enumerationValue = enumerationRelationalData.getEnumerationValue();
			ISymmetryRelationalData symmetryRelationalData = 
					new SymmetryRelationalDataImpl(dimension, enumerationValue, typeOfSymmetry);
			return symmetryRelationalData;
		}
		else throw new SynTreeGenerationException("SymmetryWithNoCentralElementChecker : "
				+ "can't get a symmetry that wasn't found.");
	}
	
	private boolean testIfThereAreTwoValuesOfIdenticalSize() {
		boolean thereAreTwoValuesOfIdenticalSize = false;
		if (values.size() == 2) {
			String[] value1Array = values.get(0).split(",");
			String[] value2Array = values.get(1).split(",");
			if (value1Array.length == value2Array.length)
				thereAreTwoValuesOfIdenticalSize = true;
		}
		return thereAreTwoValuesOfIdenticalSize;
	}
	
	private String getDimensionLastValue() {
		String dimensionLastValue;
		String[] dimensionArray = dimension.split("/");
		dimensionLastValue = dimensionArray[dimensionArray.length-1];
		return dimensionLastValue;
	}
	
	private boolean testIfEnumerationValuesOrderIsReversed() {
		boolean valuesOrderIsReversed = true;
		String[] value1Array = values.get(0).split(",");
		String[] value2Array = values.get(1).split(",");
		int lastSubValueIndex = (value1Array.length - 1);
		int subValueIndex = 0;
		while (valuesOrderIsReversed == true && subValueIndex <= lastSubValueIndex) {
			if (!value1Array[subValueIndex].equals(value2Array[lastSubValueIndex-subValueIndex]))
				valuesOrderIsReversed = false;
			subValueIndex++;
		}
		return valuesOrderIsReversed;
	}
	
	private boolean testIfCommonDiffValuesAreOpposite() {
		boolean commonDiffValuesAreOpposite = true;
		String[] value1Array = values.get(0).split(",");
		String[] value2Array = values.get(1).split(",");	
		int lastSubValueIndex = (value1Array.length - 1);
		for (int i=0 ; i<=lastSubValueIndex ; i++) {
			Integer subValue1 = Integer.parseInt(value1Array[i]);
			Integer subValue2 = Integer.parseInt(value2Array[lastSubValueIndex-i]);
			if (subValue1 != -subValue2)
				commonDiffValuesAreOpposite = false;
		}
		return commonDiffValuesAreOpposite;
	}

}
