package syntacticTreesGeneration;

import exceptions.DescriptorsBuilderException;

public interface ISymmetryChecker {

	boolean getSymmetryWasFound();

	ISymmetryRelationalData getSymmetryRelationalData() throws DescriptorsBuilderException;

}