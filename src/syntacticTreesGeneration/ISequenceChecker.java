package syntacticTreesGeneration.interfaces;

import exceptions.DescriptorsBuilderCriticalException;

public interface SequenceCheckerInterface {

	boolean getSequenceWasFound();

	SequenceRelationalDataInterface getSequenceRelationalData() throws DescriptorsBuilderCriticalException;

}