package syntacticTreesGeneration;

import exceptions.SynTreeGenerationException;
import model.copycatModel.synTreeGrammar.CharString;

public interface ICharStringBuilder {

	/* (non-Javadoc)
	 * @see descriptorsGeneration.CharStringBuilder#getCharString()
	 */
	CharString getCharString() throws SynTreeGenerationException;

}