package syntacticTreesGeneration.interfaces;

import copycatModel.grammar.CharString;
import exceptions.DescriptorsBuilderCriticalException;

public interface CharStringBuilderInterface {

	/* (non-Javadoc)
	 * @see descriptorsGeneration.CharStringBuilder#getCharString()
	 */
	CharString getCharString() throws DescriptorsBuilderCriticalException;

}