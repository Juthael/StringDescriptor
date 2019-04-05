package syntacticTreesGeneration;

import exceptions.SynTreeGenerationException;

public interface ISequenceChecker {

	boolean getSequenceWasFound();

	ISequenceRelationalData getSequenceRelationalData() throws SynTreeGenerationException;

}