package syntacticTreesGeneration;

import copycatModel.synTreeModel.grammar.CharString;
import exceptions.SynTreeGenerationException;

public interface ICharStringBuilder {

	/* (non-Javadoc)
	 * @see descriptorsGeneration.CharStringBuilder#getCharString()
	 */
	CharString getCharString() throws SynTreeGenerationException;

}