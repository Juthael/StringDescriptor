package syntacticTreesGeneration.implementations;

import java.util.ArrayList;

import exceptions.DescriptorsBuilderCriticalException;
import syntacticTreesGeneration.interfaces.EnumerationRelationalDataInterface;
import syntacticTreesGeneration.interfaces.SymmetryRelationalDataInterface;
import syntacticTreesGeneration.interfaces.SymmetryWithCentralElementCheckerInterface;
import syntacticTreesGeneration.interfaces.SymmetryWithNoCentralElementCheckerInterface;

public class SymmetryWithCentralElementCheckerV1 implements SymmetryWithCentralElementCheckerInterface {
	
	private final boolean wholeStringIsDescribed;
	private final String dimension;
	private final ArrayList<String> values;
	private final EnumerationRelationalDataInterface enumerationRelationalData;
	private boolean symmetryWithCentralElementWasFound;
	private String typeOfSymmetry = "withCentralElement";	

	public SymmetryWithCentralElementCheckerV1(boolean wholeStringIsDescribed, String dimension, ArrayList<String> values, 
			EnumerationRelationalDataInterface enumerationRelationalData) throws DescriptorsBuilderCriticalException {
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
					ArrayList<String> extremityValues = new ArrayList<String>();
					extremityValues.add(this.values.get(0));
					extremityValues.add(this.values.get(2));
					SymmetryWithNoCentralElementCheckerInterface symmetryWithNoCentralElementChecker = 
							new SymmetryWithNoCentralElementCheckerV1(this.wholeStringIsDescribed, this.dimension, 
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
					ArrayList<String> extremityValues = new ArrayList<String>();
					extremityValues.add(this.values.get(0));
					extremityValues.add(this.values.get(2));
					SymmetryWithNoCentralElementCheckerInterface symmetryWithNoCentralElementChecker = 
							new SymmetryWithNoCentralElementCheckerV1(this.wholeStringIsDescribed, this.dimension, 
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
	public SymmetryRelationalDataInterface getSymmetryRelationalData() throws DescriptorsBuilderCriticalException {
		if (symmetryWithCentralElementWasFound == true) {
			String enumerationValue = enumerationRelationalData.getEnumerationValue();
			SymmetryRelationalDataInterface symmetryRelationalData = 
					new SymmetryRelationalDataV1(dimension, enumerationValue, typeOfSymmetry);
			return symmetryRelationalData;
		}
		else throw new DescriptorsBuilderCriticalException("SymmetryWithCentralElementChecker : "
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
