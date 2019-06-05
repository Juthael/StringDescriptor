package model.synTreeModel;

import exceptions.OrderedSetsGenerationException;
import exceptions.SynTreeGenerationException;
import exceptions.VerbalizationException;
import model.orderedSetModel.impl.OmegaOS;

public interface IStartElementST extends IGrammaticalST {
	
	OmegaOS upgradeAsTheSupremumOfAnOrderedSet() throws OrderedSetsGenerationException, VerbalizationException; 
	
	String getVerbalDescription() throws VerbalizationException;
	
	ISyntacticTree getTreeWithAbstractFrames() throws VerbalizationException, CloneNotSupportedException, 
		SynTreeGenerationException, OrderedSetsGenerationException;	

}
