package syntacticTreesGeneration.implementations;

import java.util.ArrayList;

import exceptions.DescriptorsBuilderCriticalException;
import syntacticTreesGeneration.interfaces.EnumerationRelationalDataInterface;
import syntacticTreesGeneration.interfaces.SymmetryCheckerInterface;
import syntacticTreesGeneration.interfaces.SymmetryRelationalDataInterface;
import syntacticTreesGeneration.interfaces.SymmetryWithCentralElementCheckerInterface;
import syntacticTreesGeneration.interfaces.SymmetryWithNoCentralElementCheckerInterface;

public class SymmetryCheckerV1 implements SymmetryCheckerInterface {

	private final boolean wholeStringIsDescribed;
	private final String dimension;
	private final ArrayList<String> values;
	private final EnumerationRelationalDataInterface enumerationRelationalData;
	private final boolean symmetryWasFound;
	private SymmetryWithCentralElementCheckerInterface symmetryWithCentralElementChecker;
	private SymmetryWithNoCentralElementCheckerInterface symmetryWithNoCentralElementChecker;
	
	public SymmetryCheckerV1(boolean wholeStringIsDescribed, String dimension, ArrayList<String> values, 
			EnumerationRelationalDataInterface enumerationRelationalData) throws DescriptorsBuilderCriticalException {
		this.wholeStringIsDescribed = wholeStringIsDescribed;
		this.dimension = dimension;
		this.values = values;
		this.enumerationRelationalData = enumerationRelationalData;
		symmetryWithCentralElementChecker = 
				new SymmetryWithCentralElementCheckerV1(
						this.wholeStringIsDescribed, this.dimension, this.values, this.enumerationRelationalData);
		symmetryWithNoCentralElementChecker = 
				new SymmetryWithNoCentralElementCheckerV1(
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
	
	public SymmetryRelationalDataInterface getSymmetryRelationalData() throws DescriptorsBuilderCriticalException {
		SymmetryRelationalDataInterface symmetryRelationalData;
		if (symmetryWasFound == true) {
			if (symmetryWithCentralElementChecker.getSymmetryWithCentralElementWasFound() == true)
				symmetryRelationalData = symmetryWithCentralElementChecker.getSymmetryRelationalData();
			else if (symmetryWithNoCentralElementChecker.getSymmetryWithNoCenterWasFound() == true)
				symmetryRelationalData = symmetryWithNoCentralElementChecker.getSymmetryRelationalData();
			else throw new DescriptorsBuilderCriticalException(
					"SymmetryChecker.getSymmetryRelationalData() : inconsistant information");
		}
		else throw new DescriptorsBuilderCriticalException(
				"SymmetryChecker.getSymmetryRelationalData() : can't get a relation that was not found.");
		return symmetryRelationalData;
	}

}
