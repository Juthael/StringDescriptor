package model.synTreeModel;

import exceptions.OrderedSetsGenerationException;
import exceptions.SynTreeGenerationException;
import exceptions.VerbalizationException;

public interface IStartGrammElementST extends IStartElementST, IGrammaticalST {
	
	ISyntacticTree getTreeWithAbstractFrames() throws VerbalizationException, CloneNotSupportedException, 
		SynTreeGenerationException, OrderedSetsGenerationException;	

}
