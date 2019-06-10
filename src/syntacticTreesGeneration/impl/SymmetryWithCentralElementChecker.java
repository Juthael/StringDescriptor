package syntacticTreesGeneration.impl;

import java.util.ArrayList;
import java.util.List;

import exceptions.SynTreeGenerationException;
import syntacticTreesGeneration.IEnumerationRelationalData;
import syntacticTreesGeneration.ISymmetryRelationalData;
import syntacticTreesGeneration.ISymmetryWithCentralElementChecker;
import syntacticTreesGeneration.ISymmetryWithNoCentralElementChecker;

public class SymmetryWithCentralElementChecker implements ISymmetryWithCentralElementChecker {
	
	private final boolean wholeStringIsDescribed;
	private final String dimension;
	private final List<String> values;
	private final IEnumerationRelationalData enumerationRelationalData;
	private boolean symmetryWithCentralElementWasFound;
	private String typeOfSymmetry = "withCentralElement";	

	public SymmetryWithCentralElementChecker(boolean wholeStringIsDescribed, String dimension, List<String> values, 
			IEnumerationRelationalData enumerationRelationalData) throws SynTreeGenerationException {
		this.wholeStringIsDescribed = wholeStringIsDescribed;
		this.dimension = dimension;
		this.values = values;
		this.enumerationRelationalData = enumerationRelationalData;
		symmetryWithCentralElementWasFound = true;
		if (this.wholeStringIsDescribed == true && this.values.size() == 3) {
			String dimensionType = DimensionEncodingManager.getDimensionTypeFromCode(dimension);
			if (dimensionType.equals("commonDiff")) {
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
							new SymmetryWithNoCentralElementChecker(this.wholeStringIsDescribed, this.dimension, 
									extremityValues, this.enumerationRelationalData);
					if (symmetryWithNoCentralElementChecker.getSymmetryWithNoCenterWasFound() != true)
						symmetryWithCentralElementWasFound = false;					
				}
			}
			else if (dimensionType.equals("enumeration") || dimensionType.equals("size") || 
					dimensionType.equals("platonicLetter")) {
				boolean centralElementIsMadeOfIdenticalValues = 
						testIfCentralElementIsMadeOfIdenticalValues(this.values.get(1));
				if (centralElementIsMadeOfIdenticalValues == true) {
					List<String> extremityValues = new ArrayList<String>();
					extremityValues.add(this.values.get(0));
					extremityValues.add(this.values.get(2));
					ISymmetryWithNoCentralElementChecker symmetryWithNoCentralElementChecker = 
							new SymmetryWithNoCentralElementChecker(this.wholeStringIsDescribed, this.dimension, 
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
	public ISymmetryRelationalData getSymmetryRelationalData() throws SynTreeGenerationException {
		if (symmetryWithCentralElementWasFound == true) {
			String enumerationValue = enumerationRelationalData.getEnumerationValue();
			ISymmetryRelationalData symmetryRelationalData = 
					new SymmetryRelationalData(dimension, enumerationValue, typeOfSymmetry);
			return symmetryRelationalData;
		}
		else throw new SynTreeGenerationException("SymmetryWithCentralElementChecker : "
				+ "can't get a symmetry that wasn't found.");
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
