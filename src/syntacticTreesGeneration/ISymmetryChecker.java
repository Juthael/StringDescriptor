package syntacticTreesGeneration;

import exceptions.SynTreeGenerationException;

public interface ISymmetryChecker {

	boolean getSymmetryWasFound();

	ISymmetryRelationalData getSymmetryRelationalData() throws SynTreeGenerationException;

}