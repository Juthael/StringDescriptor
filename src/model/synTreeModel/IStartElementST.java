package model.synTreeModel;

import exceptions.OrderedSetsGenerationException;
import exceptions.VerbalizationException;
import model.orderedSetModel.impl.OmegaOS;

public interface IStartElementST extends ISyntacticTree {
	
	OmegaOS upgradeAsTheSupremumOfAnOrderedSet() throws OrderedSetsGenerationException, VerbalizationException; 
	
	String getVerbalDescription() throws VerbalizationException;

}
