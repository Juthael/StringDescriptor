package model.synTreeModel;

import exceptions.OrderedSetsGenerationException;
import exceptions.SynTreeGenerationException;
import exceptions.VerbalizationException;

public interface IStartGrammElementST extends IStartElementST, IGrammaticalST {
	
	IStartElementST getTreeWithAbstractFrames() throws VerbalizationException, CloneNotSupportedException, 
		SynTreeGenerationException, OrderedSetsGenerationException;	

}
