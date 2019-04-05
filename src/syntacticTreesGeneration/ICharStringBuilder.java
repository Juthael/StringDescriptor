package syntacticTreesGeneration;

import copycatModel.grammar.CharString;
import exceptions.DescriptorsBuilderException;

public interface ICharStringBuilder {

	/* (non-Javadoc)
	 * @see descriptorsGeneration.CharStringBuilder#getCharString()
	 */
	CharString getCharString() throws DescriptorsBuilderException;

}