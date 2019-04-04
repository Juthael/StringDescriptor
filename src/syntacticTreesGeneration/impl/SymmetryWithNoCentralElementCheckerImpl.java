package syntacticTreesGeneration.implementations;

import java.util.ArrayList;

import exceptions.DescriptorsBuilderCriticalException;
import syntacticTreesGeneration.interfaces.EnumerationRelationalDataInterface;
import syntacticTreesGeneration.interfaces.SymmetryRelationalDataInterface;
import syntacticTreesGeneration.interfaces.SymmetryWithNoCentralElementCheckerInterface;

public class SymmetryWithNoCentralElementCheckerV1 implements SymmetryWithNoCentralElementCheckerInterface {
	
	
	private final boolean wholeStringIsDescribed;
	private final String dimension;
	private final ArrayList<String> values;
	private final EnumerationRelationalDataInterface enumerationRelationalData;
	private boolean symmetryWithNoCenterWasFound;
	private String typeOfSymmetry = "withoutCentralElement";

	public SymmetryWithNoCentralElementCheckerV1(boolean wholeStringIsDescribed, String dimension, ArrayList<String> values, 
			EnumerationRelationalDataInterface enumerationRelationalData) throws DescriptorsBuilderCriticalException {
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
				else throw new DescriptorsBuilderCriticalException(
						"SymmetryWithNoCentralElementChecker : unexpected dimension");
			}
		}
	}
	
	@Override
	public boolean getSymmetryWithNoCenterWasFound() {
		return symmetryWithNoCenterWasFound;
	}
	
	@Override
	public SymmetryRelationalDataInterface getSymmetryRelationalData() throws DescriptorsBuilderCriticalException {
		if (symmetryWithNoCenterWasFound == true) {
			String enumerationValue = enumerationRelationalData.getEnumerationValue();
			SymmetryRelationalDataInterface symmetryRelationalData = 
					new SymmetryRelationalDataV1(dimension, enumerationValue, typeOfSymmetry);
			return symmetryRelationalData;
		}
		else throw new DescriptorsBuilderCriticalException("SymmetryWithNoCentralElementChecker : "
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
