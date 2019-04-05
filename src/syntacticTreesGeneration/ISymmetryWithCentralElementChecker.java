package syntacticTreesGeneration;

import exceptions.SynTreeGenerationException;

public interface ISymmetryWithCentralElementChecker {

	boolean getSymmetryWithCentralElementWasFound();

	ISymmetryRelationalData getSymmetryRelationalData() throws SynTreeGenerationException;

}