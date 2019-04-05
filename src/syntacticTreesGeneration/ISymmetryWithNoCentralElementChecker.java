package syntacticTreesGeneration;

import exceptions.DescriptorsBuilderException;

public interface ISymmetryWithNoCentralElementChecker {

	boolean getSymmetryWithNoCenterWasFound();

	ISymmetryRelationalData getSymmetryRelationalData() throws DescriptorsBuilderException;

}