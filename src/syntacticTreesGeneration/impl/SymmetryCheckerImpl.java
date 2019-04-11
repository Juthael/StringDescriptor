package syntacticTreesGeneration.impl;

import java.util.List;

import exceptions.SynTreeGenerationException;
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
			IEnumerationRelationalData enumerationRelationalData) throws SynTreeGenerationException {
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
		symmetryWasFound = (symmetryWithCentralElementChecker.getSymmetryWithCentralElementWasFound() == true ||
				symmetryWithNoCentralElementChecker.getSymmetryWithNoCenterWasFound() == true);
	}
	
	@Override
	public boolean getSymmetryWasFound() {
		return symmetryWasFound;
	}
	
	@Override public boolean getValuesAreIdentical() throws SynTreeGenerationException {
		boolean valuesAreIdentical = true;
		if (values.size() > 1) {
			int valueIndex = 1;
			while (valuesAreIdentical == true && valueIndex < values.size()) {
				if (!values.get(valueIndex).equals(values.get(0)))
					valuesAreIdentical = false;
				valueIndex++;
			}
			return valuesAreIdentical;
		}
		else throw new SynTreeGenerationException("SymmetryCheckerImpl.getValuesAreIdentical() : this method shouldn't "
				+ "be invoked with a list of values of size " + values.size() + ".");
	}
	
	@Override
	public ISymmetryRelationalData getSymmetryRelationalData() throws SynTreeGenerationException {
		ISymmetryRelationalData symmetryRelationalData;
		if (symmetryWasFound == true) {
			if (symmetryWithCentralElementChecker.getSymmetryWithCentralElementWasFound() == true)
				symmetryRelationalData = symmetryWithCentralElementChecker.getSymmetryRelationalData();
			else if (symmetryWithNoCentralElementChecker.getSymmetryWithNoCenterWasFound() == true)
				symmetryRelationalData = symmetryWithNoCentralElementChecker.getSymmetryRelationalData();
			else throw new SynTreeGenerationException(
					"SymmetryChecker.getSymmetryRelationalData() : inconsistant information");
		}
		else throw new SynTreeGenerationException(
				"SymmetryChecker.getSymmetryRelationalData() : can't get a relation that was not found.");
		return symmetryRelationalData;
	}

}
