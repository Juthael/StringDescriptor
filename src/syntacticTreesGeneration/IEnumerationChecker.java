package syntacticTreesGeneration;

import exceptions.SynTreeGenerationException;

public interface IEnumerationChecker {

	boolean getSimpleEnumerationWasFound();
	
	boolean getSecondDegreeEnumerationWasFound();
	
	boolean getEnumerationWasFound();

	IEnumerationRelationalData getEnumerationRelationalData() throws SynTreeGenerationException;

	

}