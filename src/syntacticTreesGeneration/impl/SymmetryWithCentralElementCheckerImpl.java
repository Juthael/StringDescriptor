package syntacticTreesGeneration.impl;

import java.util.ArrayList;
import java.util.List;

import exceptions.DescriptorsBuilderException;
import syntacticTreesGeneration.IEnumerationRelationalData;
import syntacticTreesGeneration.ISymmetryRelationalData;
import syntacticTreesGeneration.ISymmetryWithCentralElementChecker;
import syntacticTreesGeneration.ISymmetryWithNoCentralElementChecker;

public class SymmetryWithCentralElementCheckerImpl implements ISymmetryWithCentralElementChecker {
	
	private final boolean wholeStringIsDescribed;
	private final String dimension;
	private final List<String> values;
	private final IEnumerationRelationalData enumerationRelationalData;
	private boolean symmetryWithCentralElementWasFound;
	private String typeOfSymmetry = "withCentralElement";	

	public SymmetryWithCentralElementCheckerImpl(boolean wholeStringIsDescribed, String dimension, List<String> values, 
			IEnumerationRelationalData enumerationRelationalData) throws DescriptorsBuilderException {
		this.wholeStringIsDescribed = wholeStringIsDescribed;
		this.dimension = dimension;
		this.values = values;
		this.enumerationRelationalData = enumerationRelationalData;
		symmetryWithCentralElementWasFound = true;
		if (this.wholeStringIsDescribed == true && this.values.size() == 3) {
			String dimensionLastValue = getDimensionLastValue();
			if (dimensionLastValue.equals("commonDiff")) {
				String centerValue = this.values.get(1);
				String[] centerSubValues = centerValue.split(",");
				for (String subValue : centerSubValues) {
					if (!subValue.equals("0"))
						symmetryWithCentralElementWasFound = false;
				}
				if (symmetryWithCentralElementWasFound == true) {
					List<String> extremityValues = new ArrayList<String>();
					extremityValues.add(this.values.get(0));
					extremityValues.add(this.values.get(2));
					ISymmetryWithNoCentralElementChecker symmetryWithNoCentralElementChecker = 
							new SymmetryWithNoCentralElementCheckerImpl(this.wholeStringIsDescribed, this.dimension, 
									extremityValues, this.enumerationRelationalData);
					if (symmetryWithNoCentralElementChecker.getSymmetryWithNoCenterWasFound() != true)
						symmetryWithCentralElementWasFound = false;					
				}
			}
			else if (dimensionLastValue.equals("enumeration") || dimensionLastValue.equals("size") || 
					dimensionLastValue.equals("platonicLetter")) {
				boolean centralElementIsMadeOfIdenticalValues = 
						testIfCentralElementIsMadeOfIdenticalValues(this.values.get(1));
				if (centralElementIsMadeOfIdenticalValues == true) {
					List<String> extremityValues = new ArrayList<String>();
					extremityValues.add(this.values.get(0));
					extremityValues.add(this.values.get(2));
					ISymmetryWithNoCentralElementChecker symmetryWithNoCentralElementChecker = 
							new SymmetryWithNoCentralElementCheckerImpl(this.wholeStringIsDescribed, this.dimension, 
									extremityValues, this.enumerationRelationalData);
					if (symmetryWithNoCentralElementChecker.getSymmetryWithNoCenterWasFound() != true)
						symmetryWithCentralElementWasFound = false;						
				}
				else symmetryWithCentralElementWasFound = false;
			}
			else symmetryWithCentralElementWasFound = false;
		}
		else symmetryWithCentralElementWasFound = false;
	}
	
	@Override
	public boolean getSymmetryWithCentralElementWasFound() {
		return symmetryWithCentralElementWasFound;
	}
	
	@Override
	public ISymmetryRelationalData getSymmetryRelationalData() throws DescriptorsBuilderException {
		if (symmetryWithCentralElementWasFound == true) {
			String enumerationValue = enumerationRelationalData.getEnumerationValue();
			ISymmetryRelationalData symmetryRelationalData = 
					new SymmetryRelationalDataImpl(dimension, enumerationValue, typeOfSymmetry);
			return symmetryRelationalData;
		}
		else throw new DescriptorsBuilderException("SymmetryWithCentralElementChecker : "
				+ "can't get a symmetry that wasn't found.");
	}
	
	private String getDimensionLastValue() {
		String dimensionLastValue;
		String[] dimensionArray = dimension.split("/");
		dimensionLastValue = dimensionArray[dimensionArray.length-1];
		return dimensionLastValue;
	}	
	
	private boolean testIfCentralElementIsMadeOfIdenticalValues(String centralValue) {
		boolean centralElementIsMadeOfIdenticalValues = true;
		if (centralValue.length() > 1) {
			String[] valueArray = centralValue.split(",");
			String refString = valueArray[0];
			int valueIndex = 1;
			while (valueIndex < valueArray.length && centralElementIsMadeOfIdenticalValues == true) {
				if (!valueArray[valueIndex].equals(refString))
					centralElementIsMadeOfIdenticalValues = false;
				valueIndex++;
			}
		}
		return centralElementIsMadeOfIdenticalValues;
	}

}
