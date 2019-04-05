package syntacticTreesGeneration;

import exceptions.SynTreeGenerationException;

public interface ISymmetryWithNoCentralElementChecker {

	boolean getSymmetryWithNoCenterWasFound();

	ISymmetryRelationalData getSymmetryRelationalData() throws SynTreeGenerationException;

}