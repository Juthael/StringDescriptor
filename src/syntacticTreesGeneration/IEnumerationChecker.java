package syntacticTreesGeneration;

import exceptions.DescriptorsBuilderException;

public interface IEnumerationChecker {

	boolean getSimpleEnumerationWasFound();
	
	boolean getSecondDegreeEnumerationWasFound();
	
	boolean getEnumerationWasFound();

	IEnumerationRelationalData getEnumerationRelationalData() throws DescriptorsBuilderException;

	

}