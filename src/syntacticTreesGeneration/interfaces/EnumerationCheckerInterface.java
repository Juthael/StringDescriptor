package syntacticTreesGeneration.interfaces;

import exceptions.DescriptorsBuilderCriticalException;

public interface EnumerationCheckerInterface {

	boolean getSimpleEnumerationWasFound();
	
	boolean getSecondDegreeEnumerationWasFound();
	
	boolean getEnumerationWasFound();

	EnumerationRelationalDataInterface getEnumerationRelationalData() throws DescriptorsBuilderCriticalException;

	

}