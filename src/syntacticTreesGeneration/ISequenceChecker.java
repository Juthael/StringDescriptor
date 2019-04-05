package syntacticTreesGeneration;

import exceptions.DescriptorsBuilderException;

public interface ISequenceChecker {

	boolean getSequenceWasFound();

	ISequenceRelationalData getSequenceRelationalData() throws DescriptorsBuilderException;

}