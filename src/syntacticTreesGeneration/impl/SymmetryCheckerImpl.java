package syntacticTreesGeneration.impl;

import java.util.ArrayList;
import java.util.List;

import exceptions.DescriptorsBuilderException;
import syntacticTreesGeneration.IEnumerationRelationalData;
import syntacticTreesGeneration.ISymmetryChecker;
import syntacticTreesGeneration.ISymmetryRelationalData;
import syntacticTreesGeneration.ISymmetryWithCentralElementChecker;
import syntacticTreesGeneration.ISymmetryWithNoCentralElementChecker;

public class SymmetryCheckerImpl implements ISymmetryChecker {

	private final boolean wholeStringIsDescribed;
	private final String dimension;
	private final List<String> values;
	private final IEnumerationRelationalData enumerationRelationalData;
	private final boolean symmetryWasFound;
	private ISymmetryWithCentralElementChecker symmetryWithCentralElementChecker;
	private ISymmetryWithNoCentralElementChecker symmetryWithNoCentralElementChecker;
	
	public SymmetryCheckerImpl(boolean wholeStringIsDescribed, String dimension, List<String> values, 
			IEnumerationRelationalData enumerationRelationalData) throws DescriptorsBuilderException {
		this.wholeStringIsDescribed = wholeStringIsDescribed;
		this.dimension = dimension;
		this.values = values;
		this.enumerationRelationalData = enumerationRelationalData;
		symmetryWithCentralElementChecker = 
				new SymmetryWithCentralElementCheckerImpl(
						this.wholeStringIsDescribed, this.dimension, this.values, this.enumerationRelationalData);
		symmetryWithNoCentralElementChecker = 
				new SymmetryWithNoCentralElementCheckerImpl(
						this.wholeStringIsDescribed, this.dimension, this.values, this.enumerationRelationalData);
		if (symmetryWithCentralElementChecker.getSymmetryWithCentralElementWasFound() == true) 
			symmetryWasFound = true;
		else if (symmetryWithNoCentralElementChecker.getSymmetryWithNoCenterWasFound() == true) 
			symmetryWasFound = true;
		else symmetryWasFound = false;
	}
	
	public boolean getSymmetryWasFound() {
		return symmetryWasFound;
	}
	
	public ISymmetryRelationalData getSymmetryRelationalData() throws DescriptorsBuilderException {
		ISymmetryRelationalData symmetryRelationalData;
		if (symmetryWasFound == true) {
			if (symmetryWithCentralElementChecker.getSymmetryWithCentralElementWasFound() == true)
				symmetryRelationalData = symmetryWithCentralElementChecker.getSymmetryRelationalData();
			else if (symmetryWithNoCentralElementChecker.getSymmetryWithNoCenterWasFound() == true)
				symmetryRelationalData = symmetryWithNoCentralElementChecker.getSymmetryRelationalData();
			else throw new DescriptorsBuilderException(
					"SymmetryChecker.getSymmetryRelationalData() : inconsistant information");
		}
		else throw new DescriptorsBuilderException(
				"SymmetryChecker.getSymmetryRelationalData() : can't get a relation that was not found.");
		return symmetryRelationalData;
	}

}
