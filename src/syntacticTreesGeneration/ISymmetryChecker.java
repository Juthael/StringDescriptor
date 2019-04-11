package syntacticTreesGeneration;

import exceptions.SynTreeGenerationException;

public interface ISymmetryChecker {

	boolean getSymmetryWasFound();
	
	boolean getValuesAreIdentical() throws SynTreeGenerationException;

	ISymmetryRelationalData getSymmetryRelationalData() throws SynTreeGenerationException;

}