package syntacticTreesGeneration;

import exceptions.DescriptorsBuilderException;

public interface ISymmetryWithCentralElementChecker {

	boolean getSymmetryWithCentralElementWasFound();

	ISymmetryRelationalData getSymmetryRelationalData() throws DescriptorsBuilderException;

}